package org.example

import griffon.core.GriffonApplication
import griffon.core.event.EventHandler

import javax.annotation.Nonnull

class ApplicationEventHandler implements EventHandler {
    void onBootstrapStart(@Nonnull GriffonApplication application) {
        if (application.startupArgs.length > 0) {
            application.localeAsString = application.startupArgs[0]
        }
    }
}