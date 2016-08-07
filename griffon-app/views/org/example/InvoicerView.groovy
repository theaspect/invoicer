package org.example

import griffon.core.artifact.GriffonView
import griffon.core.i18n.MessageSource
import griffon.metadata.ArtifactProviderFor

@ArtifactProviderFor(GriffonView)
class InvoicerView {
    FactoryBuilderSupport builder
    InvoicerModel model

    void initUI() {
        MessageSource ms = application.messageSource
        builder.application(title: ms.getMessage('application.title'),
                sizeToScene: true, centerOnScreen: true, name: 'mainWindow') {
            scene(fill: WHITE, width: 400, height: 120) {
                anchorPane(prefHeight: 80.0, prefWidth: 384.0) {
                    label(layoutX: 14.0, layoutY: 14.0, text: ms.getMessage('greeting.label'))
                    textField(layoutX: 172.0, layoutY: 11.0, prefWidth: 200.0,
                            text: bind(model.inputProperty()))
                    button(layoutX: 172.0, layoutY: 45.0, prefWidth: 200.0,
                            sayHelloAction)
                    label(layoutX: 14.0, layoutY: 80.0, prefWidth: 360.0,
                            text: bind(model.outputProperty()))
                }
            }
        }
    }
}