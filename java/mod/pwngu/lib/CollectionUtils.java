package mod.pwngu.lib;

import java.util.*;

public class CollectionUtils {

    private CollectionUtils() {}

    public static <K, V> void optimizeHashMap(HashMap<K, V> map) {

        Iterator<K> it = map.keySet().iterator();
        int index = 1;
        while(it.hasNext()) {

            V value = map.get(it.next());

            Iterator<K> iter = map.keySet().iterator();
            for(int i = 0; i < index && iter.hasNext(); i++) iter.next();
            while (iter.hasNext()) {

                K key = iter.next();
                if(map.get(key).equals(value))
                    map.put(key, value);
            }
            index++;
        }
    }
}
