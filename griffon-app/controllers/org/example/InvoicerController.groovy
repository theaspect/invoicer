package org.example

import griffon.core.artifact.GriffonController
import griffon.metadata.ArtifactProviderFor
import org.apache.commons.exec.OS
import org.apache.commons.validator.routines.EmailValidator
import org.apache.velocity.Template
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.Velocity
import org.webbitserver.handler.StaticFileHandler

import javax.inject.Inject

@ArtifactProviderFor(GriffonController)
class InvoicerController {
    InvoicerModel model

    @Inject
    private InvoicerService sampleService

    EmailService sendEmail = new EmailService()
    PhantomJsService phantomJsService = new PhantomJsService()

    void sayHello() {

        String result = sampleService.sayHello(model.input)

        runInsideUIAsync {
            model.output = result
        }

        Velocity.init()
        VelocityContext vc = new VelocityContext()
        vc.put("foo", result)
        Template template = Velocity.getTemplate("./griffon-app/resources/template.vm", "utf-8")
        File file = new File("test.html")
        BufferedWriter bw = new BufferedWriter(new FileWriter(file))
        template.merge(vc, bw)

        /*phantomJsService.render(binary(),"./griffon-app/resources/rasterize.js",
                "./griffon-app/resources/template.html", "D:/test.pdf")

        EmailValidator validator = EmailValidator.getInstance()
        if (validator.isValid("valera-bapbap56@mail.ru")) {
            sendEmail.send(result, "valera-bapbap56@mail.ru", "D:/test.pdf", "./config.properties")
        }*/

        bw.flush()
        bw.close()
        file.deleteOnExit()
    }

    String binary(){
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
        return  binaryFile
    }
}