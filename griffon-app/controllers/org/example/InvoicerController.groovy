package org.example

import griffon.core.artifact.GriffonController
import griffon.metadata.ArtifactProviderFor
import org.apache.commons.validator.routines.EmailValidator
import org.apache.velocity.Template
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.Velocity

import javax.inject.Inject

@ArtifactProviderFor(GriffonController)
class InvoicerController {
    InvoicerModel model

    @Inject
    private InvoicerService sampleService

    EmailService sendEmail = new EmailService()

    void sayHello() {
        String result = sampleService.sayHello(model.input)
        runInsideUIAsync { model.output = result }

        Velocity.init(); // инициализация Velocity
        VelocityContext vc = new VelocityContext(); // создание контекста Velocity
        vc.put("foo", result);
        // атрибут "result" связывается с именем переменной $foo в шаблоне и помещается в контекст
        Template template = Velocity.getTemplate("./griffon-app/resources/template.vm", "utf-8");
        // загрузка шаблона с именем template.vm
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // создается выходной поток
        template.merge(vc, bw); // метод merge() принимает набор данных в виде объекта "vc" и объект потока "bw"
        EmailValidator validator = EmailValidator.getInstance();
        if(validator.isValid("murzinaalina18@icloud.com")){
            sendEmail.send(result, "murzinaalina18@icloud.com")
        }

        bw.flush();
        bw.close();
    }
}