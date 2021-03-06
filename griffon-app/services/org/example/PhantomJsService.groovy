package org.example

class PhantomJsService {

    static void render(String binaryPath, String jsPath, String htmlFilePath, String saveToPdfPath) {
        File binary = new File(binaryPath)
        File js = new File(jsPath)
        File htmlAdress = new File(htmlFilePath)
        File savePdf = new File(saveToPdfPath)

        String cmd = "\"${binary.canonicalPath}\" \"${js.canonicalPath}\" " +
                "\"file:///${htmlAdress.canonicalPath}\" \"${savePdf.canonicalPath}\""

        def proc = cmd.execute()
        proc.waitForProcessOutput()
    }

}
