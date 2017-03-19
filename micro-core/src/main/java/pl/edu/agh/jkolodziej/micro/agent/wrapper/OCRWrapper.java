package pl.edu.agh.jkolodziej.micro.agent.wrapper;

import com.google.common.io.Files;
import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.Tesseract;
import pl.edu.agh.jkolodziej.micro.agent.helpers.AWSFileKeeper;
import pl.edu.agh.jkolodziej.micro.agent.helpers.CipherDataHelper;
import pl.edu.agh.jkolodziej.micro.agent.helpers.TesseractHelper;
import pl.edu.agh.jkolodziej.micro.agent.intents.OCRIntent;
import pl.edu.agh.jkolodziej.micro.agent.util.LibraryLoader;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author - Jakub Kołodziej
 */
public class OCRWrapper {
    private static final String TMP_FILENAME = "tmp_%s.jpg";

    private final OCRIntent ocrIntent;

    public OCRWrapper(OCRIntent ocrIntent) {
        this.ocrIntent = ocrIntent;
    }

    static {
        if (!System.getProperty("os.name").toLowerCase().startsWith("win")) {
            try {
                if (TesseractHelper.loadLibrary == null || TesseractHelper.loadLibrary.equals(Boolean.TRUE)) {
                    LibraryLoader.loadTesseract();
                }
            } catch (IOException e) {
            }
        }
    }

    public void makeService() {
        File tmpFile = null;
        try {
            byte[] bytes = CipherDataHelper.decryptByteArray(ocrIntent.getData());
            String filePath = null;
            if (!AWSFileKeeper.DIRECTORY.equals("")) {
                filePath = AWSFileKeeper.DIRECTORY + "/";
            } else {
                filePath = "";
            }
            tmpFile = new File(filePath + String.format(TMP_FILENAME, new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())));
            Files.write(bytes, tmpFile);
            ocrIntent.setResult(ocr(tmpFile));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (tmpFile != null) {
                tmpFile.delete();
            }
        }
    }

    public String ocr(File imageFile) throws Exception {
        Tesseract tess = new Tesseract();
        if (TesseractHelper.isAWS) {
            tess.setDatapath("/tmp/");
        } else if (!"".equals(TesseractHelper.libPath)) {
            tess.setDatapath(TesseractHelper.libPath + "/");
        }
        tess.setLanguage("pol");
        tess.setOcrEngineMode(ITessAPI.TessOcrEngineMode.OEM_TESSERACT_ONLY);
        String res = tess.doOCR(imageFile);
        return res;
    }

    public OCRIntent getOcrIntent() {
        return ocrIntent;
    }
}
