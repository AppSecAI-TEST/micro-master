package pl.edu.agh.jkolodziej.micro.helper;

import org.nzdis.micro.MicroMessage;
import pl.edu.agh.jkolodziej.micro.providers.PCProviderRole;

import static java.lang.Thread.sleep;

/**
 * @author - Jakub Kołodziej
 */
public class ResponsesMaker implements Runnable {
    private final PCProviderRole providerRole;

    public ResponsesMaker(PCProviderRole providerRole) {
        this.providerRole = providerRole;
    }

    @Override
    public void run() {
        while (true) {
            if (providerRole.getMessageToDo() != null) {
                MicroMessage reply = providerRole.getReply(providerRole.messageToDo);
                reply.setRecipient(providerRole.getMessageToDo().getSender());
                providerRole.send(reply);
                providerRole.setMessageToDo(null);
            } else {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

