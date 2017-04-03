package pl.edu.agh.jkolodziej.micro.providers;

import org.nzdis.micro.MicroMessage;
import pl.edu.agh.jkolodziej.micro.agent.intents.OCRIntent;
import pl.edu.agh.jkolodziej.micro.agent.roles.ProviderRole;
import pl.edu.agh.jkolodziej.micro.agent.wrapper.OCRWrapper;
import pl.edu.agh.jkolodziej.micro.helper.ResponsesMaker;

/**
 * @author - Jakub Kołodziej
 *         Provider which is resposible for  make specific services on PC side
 *         eg. OCR
 */
public class PCProviderRole extends ProviderRole {

    public MicroMessage messageToDo;

    public PCProviderRole(String workerName) {
        super(workerName);
    }

    @Override
    protected void initialize() {
        new Thread(new ResponsesMaker(this)).start();
    }

    @Override
    public void handleMessage(MicroMessage message) {
        messageToDo = message;
    }


    public MicroMessage getMessageToDo() {
        return messageToDo;
    }

    public void setMessageToDo(MicroMessage messageToDo) {
        this.messageToDo = messageToDo;
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
