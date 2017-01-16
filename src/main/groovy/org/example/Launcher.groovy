package org.example

import griffon.javafx.JavaFXGriffonApplication
import org.apache.commons.io.FileUtils
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class Launcher {

    static Logger logger = LogManager.getLogger(Launcher.class)


    static void main(String[] args) throws Exception {
        File file = new File("./config.properties")
        if (!file.isFile()) {
            FileUtils.copyFile("./griffon-app/resources/template.properties" as File, new File("./config.properties"))
            logger.info("Create new Config.properties file")
        }
        JavaFXGriffonApplication.main(args)
    }
}