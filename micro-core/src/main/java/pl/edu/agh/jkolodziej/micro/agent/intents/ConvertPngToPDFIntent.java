package pl.edu.agh.jkolodziej.micro.agent.intents;

import com.google.common.io.Files;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.nzdis.micro.constants.OperatingSystems;
import org.nzdis.micro.constants.PlatformConstants;
import pl.edu.agh.jkolodziej.micro.agent.helpers.AWSFileKeeper;
import pl.edu.agh.jkolodziej.micro.agent.helpers.AndroidFilesSaverHelper;
import pl.edu.agh.jkolodziej.micro.agent.helpers.CipherDataHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.nzdis.micro.bootloader.MicroBootProperties.bootProperties;

/**
 * @author - Jakub Kołodziej
 *         Intent which convert PNG file into PDF format
 */
public class ConvertPngToPDFIntent extends ServiceIntent {
    private static final String TMP_PDF_FILENAME = "tmpPdf_%s.pdf";

    public ConvertPngToPDFIntent() {
    }

    @Override
    public void makeService() {
        try {
            byte[] bytes = CipherDataHelper.decryptByteArray(data);
            String filePath = null;
            if (!AWSFileKeeper.DIRECTORY.equals("")) {
                filePath = AWSFileKeeper.DIRECTORY + "/";
            } else {
                filePath = bootProperties.getProperty(PlatformConstants.OPERATING_SYSTEM).equals(
                        OperatingSystems.ANDROID) ? (AndroidFilesSaverHelper.INTERNAL_DIRECTORY + "/") : "";
            }
            String tmpPdfFilePath = filePath + String.format(TMP_PDF_FILENAME, new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            result = createPDF(bytes, tmpPdfFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create encrypted PDF file from PNG file
     *
     * @param pngFile PNG file in byte array form
     * @param tmpFile temporary PDF file
     * @return encrypted PDF File from PNG file in String format
     * @throws Exception
     */
    public static String createPDF(byte[] pngFile, String tmpFile) throws Exception {
        Document document = new Document(PageSize.A4, 20, 20, 20, 20);
        PdfWriter.getInstance(document, new FileOutputStream(tmpFile));
        document.open();
        Image image = Image.getInstance(pngFile);
        image.scalePercent(50.0f);
        document.add(image);
        document.close();
        return CipherDataHelper.encryptByteArray(Files.toByteArray(new File(tmpFile)));
    }
}
