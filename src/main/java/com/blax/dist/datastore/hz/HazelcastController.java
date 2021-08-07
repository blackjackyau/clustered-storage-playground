package com.blax.dist.datastore.hz;

import com.hazelcast.collection.IList;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@AllArgsConstructor
@ConditionalOnProperty(prefix = "com.blax.hz", name = "enable", havingValue = "true")
@RequestMapping(value = "hz")
public class HazelcastController {

    private HazelcastInstance hazelcastInstance;

    @RequestMapping(value = "map/{key}", method = RequestMethod.GET)
    Map getMap(@PathVariable String key) {
        return hazelcastInstance.getMap(key);
    }

    @RequestMapping(value = "list/{key}", method = RequestMethod.GET)
    Collection getList(@PathVariable String key) {
        return hazelcastInstance.getList(key + "List");
    }

    @RequestMapping(value = "map/{key}", method = RequestMethod.POST)
    Map setMap(@PathVariable String key, @RequestBody Map values) {
        IMap<Object, Object> map = hazelcastInstance.getMap(key);
        IList<Object> keyList = hazelcastInstance.getList(key + "List");
        keyList.addAll(values.keySet());
        map.setAll(values);
        return map;
    }
}
