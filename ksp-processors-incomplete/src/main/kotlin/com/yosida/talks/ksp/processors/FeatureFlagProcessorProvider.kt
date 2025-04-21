package com.yosida.talks.ksp.processors

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated

class FeatureFlagProcessorProvider : SymbolProcessorProvider {

    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        // TODO: Return the actual FeatureFlagProcessor
        return DummySymbolProcessor()
    }

    inner class DummySymbolProcessor : SymbolProcessor {
        override fun process(resolver: Resolver): List<KSAnnotated> {
            return emptyList()
        }
    }
}
