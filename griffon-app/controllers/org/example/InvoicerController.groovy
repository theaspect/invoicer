package org.example

import griffon.core.artifact.GriffonController
import griffon.metadata.ArtifactProviderFor
import org.apache.commons.exec.OS
import org.apache.commons.validator.routines.EmailValidator
import org.apache.velocity.Template
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.Velocity
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import javax.inject.Inject

@ArtifactProviderFor(GriffonController)
class InvoicerController {

    Logger logger = LogManager.getLogger(this.class)

    InvoicerModel model

    @Inject
    private InvoicerService sampleService

    EmailService sendEmail = new EmailService()
    PhantomJsService phantomJsService = new PhantomJsService()

    void sayHello() {

        Properties properties = new Properties()
        InputStream mailSend = new FileInputStream("./config.properties")
        properties.load(mailSend)

        String result = sampleService.sayHello(model.input)

        runInsideUIAsync {
            model.output = result
        }

        String tempFile = "tempFile.html"
        String receiver = properties.getProperty("receiver")
        String loadTemplate = properties.getProperty("tamplate")
        File toPdf = new File(properties.getProperty("saveToPdfPath"))
        File file = new File(tempFile)

        Velocity.init()
        VelocityContext vc = new VelocityContext()
        vc.put("foo", result)
        Template template = Velocity.getTemplate(loadTemplate, "utf-8")

        BufferedWriter bw = new BufferedWriter(new FileWriter(file))
        template.merge(vc, bw)

        bw.flush()
        bw.close()

        logger.info("Render file start")
        phantomJsService.render(binary(), "./griffon-app/resources/rasterize.js", file.canonicalPath, toPdf.canonicalPath)
        logger.info("Render file end")

        EmailValidator validator = EmailValidator.getInstance()
        logger.info("Send mail start")
        if (validator.isValid(receiver)) {
            sendEmail.send(result, receiver, toPdf.canonicalPath, properties)
        }
        logger.info("Send mail end")

        file.deleteOnExit()
    }

    String binary() {

        logger.info("OS detection")
        String binaryFile = "./griffon-app/resources/"

        if (OS.familyWindows) {
            binaryFile += "phantomjs_win.exe"
        }
        if (OS.familyMac) {
            binaryFile += "phantomjs_mac"
        }
        if (OS.familyUnix) {
            if (System.getProperty("sun.arch.data.model").toInteger() == 64) {
                binaryFile += "phantomjs_lin64"
            } else {
                binaryFile += "phantomjs_lin32"
            }
        }
        return binaryFile
    }
}