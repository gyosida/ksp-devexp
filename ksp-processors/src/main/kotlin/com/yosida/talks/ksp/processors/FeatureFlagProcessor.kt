package com.yosida.talks.ksp.processors

import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate
import com.yosida.talks.ksp.analytics_api.annotations.GenFeatureFlag

class FeatureFlagProcessor(private val environment: SymbolProcessorEnvironment) : SymbolProcessor {

    private val logger = environment.logger

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val isEnabledFeatureFlagVisitor = IsEnabledFeatureFlagVisitor(environment.codeGenerator, logger)
        val symbols = resolver.getSymbolsWithAnnotation(GenFeatureFlag::class.qualifiedName!!)
            .filterIsInstance<KSClassDeclaration>()
            .filter { it.classKind.type == ClassKind.ENUM_CLASS.type }

        logger.info("Symbols with GenFeatureFlag: ${symbols.joinToString { it.simpleName.asString() }}")

        val resolvedSymbols = symbols.filter { it.validate() }

        logger.info("Resolved symbols with GenFeatureFlag: ${resolvedSymbols.joinToString { it.simpleName.asString() }}")

        resolvedSymbols.forEach { it.accept(isEnabledFeatureFlagVisitor, Unit) }

        val documentationContent = resolvedSymbols.map { it.accept(FeatureFlagDocsVisitor(logger), Unit) }
            .joinToString("\n\n")

        if (documentationContent.isNotBlank()) {
            generateDocumentation(documentationContent)
        }
        return (symbols - resolvedSymbols.toSet()).toList()
    }

    private fun generateDocumentation(content: String) {
        val filePath = "docs/FeatureFlagDocs"
        environment.codeGenerator.createNewFileByPath(
            Dependencies(true),
            path = filePath,
            extensionName = "md"
        ).use { it.write(content.toByteArray()) }
    }
}
