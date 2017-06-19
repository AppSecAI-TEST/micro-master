package pl.edu.agh.jkolodziej.micro.agent.roles;

import org.nzdis.micro.DefaultSocialRole;
import org.nzdis.micro.MicroMessage;
import pl.edu.agh.jkolodziej.micro.agent.intents.AddIntent;
import pl.edu.agh.jkolodziej.micro.agent.intents.OCRIntent;
import pl.edu.agh.jkolodziej.micro.agent.intents.ServiceIntent;

/**
 * @author - Jakub Kołodziej
 *         Provider Role - Agent which share agents services
 *         It could be run on each devices e.g: PC, Android Devices
 */
public class ProviderAgent extends DefaultSocialRole {

    protected final String workerName;

    public ProviderAgent(String workerName) {
        this.workerName = workerName;
    }

    @Override
    protected void initialize() {
        addApplicableIntent(AddIntent.class);
    }

    @Override
    public void handleMessage(MicroMessage message) {
        MicroMessage reply = getReply(message);
        reply.setRecipient(message.getSender());
        send(reply);
    }

    public MicroMessage getReply(MicroMessage message) {
        MicroMessage reply = message.createReply();
        reply.setIntent(null);
        if (!message.getIntent().getClass().equals(OCRIntent.class)) {
            ServiceIntent intent = message.getIntent();
            intent.setWorker(workerName);
            intent.makeService();
            reply.setIntent(intent);
        }
        return reply;
    }

    @Override
    protected void release() {
    }
}
