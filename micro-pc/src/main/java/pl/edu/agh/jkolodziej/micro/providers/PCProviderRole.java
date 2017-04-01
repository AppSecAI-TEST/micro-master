package pl.edu.agh.jkolodziej.micro.providers;

import org.nzdis.micro.MicroMessage;
import pl.edu.agh.jkolodziej.micro.agent.intents.OCRIntent;
import pl.edu.agh.jkolodziej.micro.agent.roles.ProviderRole;
import pl.edu.agh.jkolodziej.micro.agent.wrapper.OCRWrapper;

/**
 * @author - Jakub Kołodziej
 *         Provider which is resposible for  make specific services on PC side
 *         eg. OCR
 */
public class PCProviderRole extends ProviderRole {

    public PCProviderRole(String workerName) {
        super(workerName);
    }

    @Override
    public MicroMessage getReply(MicroMessage message) {
        MicroMessage reply = super.getReply(message);
        if (reply.getIntent() == null) {
            if (message.getIntent().getClass().equals(OCRIntent.class)) {
                OCRIntent intent = message.getIntent();
                intent.setWorker(workerName);
                OCRWrapper ocrWrapper = new OCRWrapper(intent);
                ocrWrapper.makeService();
                intent = ocrWrapper.getOcrIntent();
                reply.setIntent(intent);
            }
        }
        return reply;
    }
}
