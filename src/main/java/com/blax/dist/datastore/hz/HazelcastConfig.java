package com.blax.dist.datastore.hz;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "com.blax.hz", name = "enable", havingValue = "true")
public class HazelcastConfig {

    @Value("${com.blax.hz.bootstrap-address}")
    String bootstrapAddress;
    @Value("${com.blax.hz.cluster-name}")
    String clusterName;

    @Bean
    HazelcastInstance hzInstance() {
        ClientConfig config = new ClientConfig();
        config.getNetworkConfig().addAddress(bootstrapAddress);
        config.setClusterName(clusterName);
        return HazelcastClient.newHazelcastClient(config);
    }
}
