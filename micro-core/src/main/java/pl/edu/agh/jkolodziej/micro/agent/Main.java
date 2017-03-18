package pl.edu.agh.jkolodziej.micro.agent;

import org.nzdis.micro.bootloader.MicroConfigLoader;
import pl.edu.agh.jkolodziej.micro.agent.helpers.CipherDataHelper;
import pl.edu.agh.jkolodziej.micro.agent.intents.OCRIntent;

import java.io.InputStream;

/**
 * @author - Jakub Kołodziej
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = MicroConfigLoader.class.getClassLoader();
        InputStream stream = classLoader.getResourceAsStream("ocr/sample_ocr.jpg");
        byte[] bytes;
        bytes = new byte[stream.available()];
        stream.read(bytes);
        OCRIntent ocrIntent = new OCRIntent();
        ocrIntent.setData(CipherDataHelper.encryptByteArray(bytes));
        ocrIntent.makeService();
        System.out.println(ocrIntent.getResult());
    }
}
