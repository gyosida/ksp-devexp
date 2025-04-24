package com.yosida.talks.ksp.processors

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid

class IsEnabledFeatureFlagVisitor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger
) : KSVisitorVoid() {
    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        logger.info("Visiting class declaration: ${classDeclaration.simpleName.asString()}")
        classDeclaration.getEnumEntries().forEach {
            logger.info("Creating IsEnabledClass for enum entry: ${it.simpleName.asString()}")
            val isEnabledClassContent = createIsEnabledClass(it)
            createClassFile(it, isEnabledClassContent)
        }
    }

    private fun KSClassDeclaration.getEnumEntries(): List<KSClassDeclaration> {
        // TODO: only return KSClassDeclaration of enum entries
        return this.declarations.filterIsInstance<KSClassDeclaration>()
            .toList()
    }

    // TODO: add missing lines to the generated class
    private fun createIsEnabledClass(classDeclaration: KSClassDeclaration): String {
        return """
            // TODO Complete missing statements 
            
            class Is${classDeclaration.simpleName.asString()}Enabled(
                private val controller: FeatureFlagController
            ) {
                operator fun invoke(): Boolean {
                    // TODO Fix call of isFeatureEnabled 
                    return controller.isFeatureEnabled()
                }
            }
        """.trimIndent()
    }

    private fun createClassFile(classDeclaration: KSClassDeclaration, content: String) {
        val packageName = classDeclaration.packageName.asString()
        // FIXME: Add file name
        val fileName = ""
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
