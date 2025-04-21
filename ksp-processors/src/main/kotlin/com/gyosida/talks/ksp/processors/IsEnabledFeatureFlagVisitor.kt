package com.gyosida.talks.ksp.processors

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.yosida.talks.ksp.analytics_api.FeatureFlagController

class IsEnabledFeatureFlagVisitor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger
) : KSVisitorVoid() {
    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        super.visitClassDeclaration(classDeclaration, data)
        logger.info("Visiting class declaration: ${classDeclaration.simpleName.asString()}")
        classDeclaration.getEnumEntries().forEach {
            logger.info("Creating IsEnabledClass for enum entry: ${it.simpleName.asString()}")
            val isEnabledClassContent = createIsEnabledClass(it)
            createClassFile(it, isEnabledClassContent)
        }
    }

    private fun KSClassDeclaration.getEnumEntries(): List<KSClassDeclaration> {
        return this.declarations.filterIsInstance<KSClassDeclaration>()
            .filter { it.classKind == ClassKind.ENUM_ENTRY }
            .toList()
    }

    private fun createIsEnabledClass(classDeclaration: KSClassDeclaration): String {
        return """
            package ${classDeclaration.packageName.asString()}
            
            import ${FeatureFlagController::class.qualifiedName}
            import ${classDeclaration.qualifiedName!!.asString()}
            
            class Is${classDeclaration.simpleName.asString()}Enabled(
                private val controller: FeatureFlagController
            ) {
                operator fun invoke(): Boolean {
                    return controller.isFeatureEnabled(${classDeclaration.simpleName.asString()})
                }
            }
        """.trimIndent()
    }

    private fun createClassFile(classDeclaration: KSClassDeclaration, content: String) {
        val packageName = classDeclaration.packageName.asString()
        val fileName = "Is${classDeclaration.simpleName.asString()}Enabled"
        val file = codeGenerator.createNewFile(
            Dependencies(true),
            packageName,
            fileName,
        )
        file.use {
            it.write(content.toByteArray())
        }
    }
}
