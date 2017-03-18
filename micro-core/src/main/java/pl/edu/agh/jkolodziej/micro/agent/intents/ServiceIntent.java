package pl.edu.agh.jkolodziej.micro.agent.intents;

import org.nzdis.micro.Intent;

/**
 * @author - Jakub Kołodziej
 *         Abstract Intent which will be exchanged between micro agents
 */
public abstract class ServiceIntent implements Intent {
    /**
     * each <code>ServiceIntent</code>should make some action - realize some kind of service
     */
    public abstract void makeService();
}
