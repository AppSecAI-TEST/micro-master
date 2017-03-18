package pl.edu.agh.jkolodziej.micro.agent;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import pl.edu.agh.jkolodziej.micro.agent.helpers.AWSFileKeeper;
import pl.edu.agh.jkolodziej.micro.agent.helpers.TesseractHelper;
import pl.edu.agh.jkolodziej.micro.agent.intents.OCRIntent;
import pl.edu.agh.jkolodziej.micro.agent.wrapper.OCRWrapper;

/**
 * @author - Jakub Kołodziej
 *         OCR Handler for converting PNG to PDF service
 */
public class OCRFunctionHandler implements RequestHandler<OCRIntent, OCRIntent> {

    @Override
    public OCRIntent handleRequest(OCRIntent ocrIntent, Context context) {
        AWSFileKeeper.DIRECTORY = "/tmp/";
        TesseractHelper.isAWS = true;
        OCRWrapper ocrWrapper = new OCRWrapper(ocrIntent);
        ocrWrapper.makeService();
        return ocrWrapper.getOcrIntent();
    }
}
