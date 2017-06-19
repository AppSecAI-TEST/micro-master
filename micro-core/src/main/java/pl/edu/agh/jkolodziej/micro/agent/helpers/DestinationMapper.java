package pl.edu.agh.jkolodziej.micro.agent.helpers;

import com.google.common.collect.Maps;
import pl.edu.agh.jkolodziej.micro.agent.enums.TaskDestination;

import java.util.Map;

/**
 * @author - Jakub Kołodziej
 */
public class DestinationMapper {
    private static final Map<TaskDestination, String> AGENT_DESTINATION_MAP = Maps.newHashMap();

    static {
        AGENT_DESTINATION_MAP.put(TaskDestination.PC, "docker");
        AGENT_DESTINATION_MAP.put(TaskDestination.CLOUD, "AWS");
        AGENT_DESTINATION_MAP.put(TaskDestination.MOBILE, "android");
    }

    public static String getAgentNameByDestination(TaskDestination taskDestination) {
        return AGENT_DESTINATION_MAP.get(taskDestination);
    }
}
