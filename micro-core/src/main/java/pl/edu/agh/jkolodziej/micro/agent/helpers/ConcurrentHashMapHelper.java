package pl.edu.agh.jkolodziej.micro.agent.helpers;

import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author - Jakub Kołodziej
 *         class helper for micro-agent lib which allow run micro-agent platform
 *         on Android and standard JVM
 */
public class ConcurrentHashMapHelper<K, V> {

    public boolean contains(ConcurrentHashMap<K, V> map, K key) {
        return new HashSet<K>(Collections.list(map.keys())).contains(key);
    }

}
