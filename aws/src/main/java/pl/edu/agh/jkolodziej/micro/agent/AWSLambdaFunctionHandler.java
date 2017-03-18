package pl.edu.agh.jkolodziej.micro.agent;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import pl.edu.agh.jkolodziej.micro.agent.helpers.AWSFileKeeper;
import pl.edu.agh.jkolodziej.micro.agent.intents.ServiceIntent;

/**
 * @author - Jakub Kołodziej
 *         Generic Function Handler for each type of Custom Intent
 */
public class AWSLambdaFunctionHandler<I extends ServiceIntent> implements RequestHandler<I, I> {

    @Override
    public I handleRequest(I intent, Context context) {
        AWSFileKeeper.DIRECTORY = "/tmp/";
        intent.makeService();
        return intent;
    }
}
