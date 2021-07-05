package com.blax.dist.datastore;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "hz")
public class HazelcastController {

    private final HazelcastInstance hazelcastInstance;

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
