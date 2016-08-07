package org.example

import griffon.core.artifact.GriffonModel
import griffon.transform.FXObservable
import griffon.metadata.ArtifactProviderFor

@ArtifactProviderFor(GriffonModel)
class InvoicerModel {
    @FXObservable String input  = ''
    @FXObservable String output = ''
}