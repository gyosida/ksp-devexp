package com.yosida.talks.ksp.processors

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.visitor.KSDefaultVisitor

class FeatureFlagDocsVisitor(
    val logger: KSPLogger
) : KSDefaultVisitor<Unit, String>() {

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit): String {
        return createDocumentationContent(classDeclaration)
    }

    private fun KSClassDeclaration.getEnumEntries(): List<KSClassDeclaration> {
        return this.declarations.filterIsInstance<KSClassDeclaration>()
            .filter { it.classKind == ClassKind.ENUM_ENTRY }
            .toList()
    }

    private fun createDocumentationContent(enumClassDeclaration: KSClassDeclaration): String {
        logger.info("Creating documentation content for ${enumClassDeclaration.simpleName.asString()}")
        val enumEntries = enumClassDeclaration.getEnumEntries()
        // FIXME: Add table rows of all enum entries
        return """
    |# Feature flags
    |## ${enumClassDeclaration.simpleName.asString()} group
    || Id | Name | Description |
    ||----|------|-------------|
    | // TODO add rows for each enum entry
""".trimMargin()
    }

    private fun KSClassDeclaration.createFeatureFlagRowContent(): String? {
        logger.info("Creating descriptor row for ${this.simpleName.asString()}")
        val descriptorAnnotation = annotations.firstOrNull { it.shortName.asString() == "FeatureFlagDescriptor" }

        if (descriptorAnnotation == null) {
            logger.error("Missing FeatureFlagDescriptor annotation on ${this.simpleName.asString()}")
            return null
        }

        val name = this.simpleName.asString()
        val id = (descriptorAnnotation.arguments.firstOrNull { it.name?.asString() == "id" }?.value as? String)
            ?.takeIf { it.isNotEmpty() } ?: splitPascalCase(this.simpleName.asString()).joinToString("_").lowercase()
        val description =
            (descriptorAnnotation.arguments.firstOrNull { it.name?.asString() == "description" }?.value as? String)
                ?.takeIf { it.isNotEmpty() } ?: "No description provided."

        return "| $id | $name | $description |"
    }

    private fun splitPascalCase(input: String): List<String> {
        return input.split(
            "(?<=[a-z])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])".toRegex()
        ).filter { it.isNotEmpty() }
    }

    override fun defaultHandler(node: KSNode, data: Unit): String {
        logger.warn("defaultHandler called for node: $node")
        return ""
    }
}
