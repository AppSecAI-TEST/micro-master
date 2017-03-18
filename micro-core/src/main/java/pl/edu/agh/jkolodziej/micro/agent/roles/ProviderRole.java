package pl.edu.agh.jkolodziej.micro.agent.roles;

import org.nzdis.micro.DefaultSocialRole;
import org.nzdis.micro.MicroMessage;
import pl.edu.agh.jkolodziej.micro.agent.intents.AddIntent;
import pl.edu.agh.jkolodziej.micro.agent.intents.AddingFromFileIntent;
import pl.edu.agh.jkolodziej.micro.agent.intents.ConvertPngToPDFIntent;

/**
 * @author - Jakub Kołodziej
 *         Provider Role - Agent which share agents services
 *         It could be run on each devices e.g: PC, Android Devices
 */
public class ProviderRole extends DefaultSocialRole {

    protected final String workerName;

    public ProviderRole(String workerName) {
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
        if (message.getIntent().getClass().equals(AddIntent.class)) {
            AddIntent intent = message.getIntent();
            intent.makeService();
            intent.setWorker(workerName);
            reply.setIntent(intent);
        } else if (message.getIntent().getClass().equals(AddingFromFileIntent.class)) {
            AddingFromFileIntent intent = message.getIntent();
            intent.makeService();
            intent.setWorker(workerName);
            reply.setIntent(intent);
        } else if (message.getIntent().getClass().equals(ConvertPngToPDFIntent.class)) {
            ConvertPngToPDFIntent intent = message.getIntent();
            intent.makeService();
            intent.setWorker(workerName);
            reply.setIntent(intent);
        }
        return reply;
    }

    @Override
    protected void release() {
    }
}
