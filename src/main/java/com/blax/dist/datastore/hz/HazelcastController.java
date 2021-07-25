package com.blax.dist.datastore.hz;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@ConditionalOnProperty(prefix = "com.blax.hz", name = "enable", havingValue = "true")
@RequestMapping(value = "hz")
public class HazelcastController {

    private HazelcastInstance hazelcastInstance;

    @RequestMapping(value = "values", method = RequestMethod.GET)
    Map getValues() {
        return hazelcastInstance.getMap("values");
    }

    @RequestMapping(value = "values", method = RequestMethod.POST)
    Map setValues(@RequestBody Map values) {
        IMap<Object, Object> map = hazelcastInstance.getMap("values");
        map.setAll(values);
        return map;
    }
}
