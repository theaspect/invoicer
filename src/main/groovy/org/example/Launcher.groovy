package org.example

import griffon.javafx.JavaFXGriffonApplication
import org.apache.commons.io.FileUtils

class Launcher {
    static void main(String[] args) throws Exception {
        File file = new File("./config.properties")
        if(!file.isFile()){
            FileUtils.copyFile("./griffon-app/resources/template.properties" as File, new File("./config.properties"))
        }
        JavaFXGriffonApplication.main(args)
    }
}