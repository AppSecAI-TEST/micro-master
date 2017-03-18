package pl.edu.agh.jkolodziej.micro;

import org.nzdis.micro.SystemAgentLoader;
import pl.edu.agh.jkolodziej.micro.agent.helpers.TesseractHelper;
import pl.edu.agh.jkolodziej.micro.providers.PCProviderRole;

/**
 * @author - Jakub Kołodziej
 *         Sample PC client class, which provides sample services hosted by <code>ProviderRole</code>
 */
public class Main {

    public static void main(String[] args) {
        if (args.length > 1) {
            TesseractHelper.libPath = args[1];
        }
        if (args.length > 2) {
            TesseractHelper.loadLibrary = false;
        }
        SystemAgentLoader.newAgent(new PCProviderRole(args[0]), args[0]);
    }
}
