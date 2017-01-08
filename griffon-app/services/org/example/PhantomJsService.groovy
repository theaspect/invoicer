package org.example

import org.apache.commons.io.FileUtils
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.phantomjs.PhantomJSDriverService
import org.openqa.selenium.remote.DesiredCapabilities

class PhantomJsService {
    static void main(String[] args) {
        DesiredCapabilities caps = new DesiredCapabilities()
        caps.setJavascriptEnabled(true)
        caps.setCapability("takesScreenshot", true)
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "D:/phantomjs-2.1.1-windows/bin/phantomjs.exe")

        WebDriver driver = new PhantomJSDriver(caps)
        driver.get("./griffon-app/resources/test.html")
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        System.out.println("File:" + srcFile)
        FileUtils.copyFile(srcFile, new File("D:/screenshot_.pdf"))
        System.out.println("Done")

        driver.quit()
    }
}
