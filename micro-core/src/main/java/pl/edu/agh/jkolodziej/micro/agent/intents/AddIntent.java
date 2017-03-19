package pl.edu.agh.jkolodziej.micro.agent.intents;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author - Jakub Kołodziej
 *         Simple Intent which add two numbers
 */
public class AddIntent extends ServiceIntent {

    public AddIntent() {
    }

    @Override
    public void makeService() {
        List<Double> subs = Lists.newArrayList(Iterables.transform(Splitter.on(";").split(this.getData()), new Function<String, Double>() {
            @Override
            public Double apply(String sub) {
                return Double.valueOf(sub);
            }
        }));
        this.result = subs.get(0) + subs.get(1) + "";
    }
}
