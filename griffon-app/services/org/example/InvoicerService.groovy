package org.example

import griffon.core.artifact.GriffonService
import griffon.core.i18n.MessageSource
import griffon.metadata.ArtifactProviderFor

import javax.annotation.Nonnull
import javax.annotation.Nullable

import static griffon.util.GriffonNameUtils.isBlank

@ArtifactProviderFor(GriffonService)
class InvoicerService {
    @Nonnull
    String sayHello(@Nullable String input) {
        MessageSource ms = application.messageSource
        isBlank(input) ? ms.getMessage('greeting.default') : ms. getMessage('greeting.parameterized', [input])
    }
}
