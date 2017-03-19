package pl.edu.agh.jkolodziej.micro.agent.intents;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.nzdis.micro.constants.OperatingSystems;
import org.nzdis.micro.constants.PlatformConstants;
import pl.edu.agh.jkolodziej.micro.agent.helpers.AWSFileKeeper;
import pl.edu.agh.jkolodziej.micro.agent.helpers.AndroidFilesSaverHelper;
import pl.edu.agh.jkolodziej.micro.agent.helpers.CipherDataHelper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.nzdis.micro.bootloader.MicroBootProperties.bootProperties;

/**
 * @author - Jakub Kołodziej
 *         Intent wchich add numbers from file where data is in e.g format:
 *         <p>
 *         2;3
 *         1.3;2.4
 *         <p>
 *         each two numbers in new line, separated by semicolon
 */
public class AddingFromFileIntent extends ServiceIntent {
    private static final String TMP_FILENAME = "tmp_%s";

    public AddingFromFileIntent() {
    }

    @Override
    public void makeService() {
        File tmpFile = null;
        try {
            byte[] bytes = CipherDataHelper.decryptByteArray(data);
            String filePath;
            if (!AWSFileKeeper.DIRECTORY.equals("")) {
                filePath = AWSFileKeeper.DIRECTORY + "/";
            } else {
                filePath = bootProperties.getProperty(PlatformConstants.OPERATING_SYSTEM).equals(
                        OperatingSystems.ANDROID) ? (AndroidFilesSaverHelper.INTERNAL_DIRECTORY + "/") : "";
            }
            tmpFile = new File(filePath + String.format(TMP_FILENAME, new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())));
            Files.write(bytes, tmpFile);
            result = "";
            for (String line : Files.readLines(tmpFile, Charsets.UTF_8)) {
                result += (Double.valueOf(line.split(";")[0])
                        + Double.valueOf(line.split(";")[1]) + "\n");
            }
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
}
