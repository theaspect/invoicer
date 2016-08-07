package org.example

import griffon.core.artifact.GriffonController
import griffon.metadata.ArtifactProviderFor

import javax.inject.Inject

@ArtifactProviderFor(GriffonController)
class InvoicerController {
    InvoicerModel model

    @Inject
    private InvoicerService sampleService

    void sayHello() {
        String result = sampleService.sayHello(model.input)
        runInsideUIAsync { model.output = result }
    }
}