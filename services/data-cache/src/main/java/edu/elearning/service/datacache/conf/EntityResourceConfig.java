package edu.elearning.service.datacache.conf;

import edu.elearning.service.datacache.resources.EntityResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntityResourceConfig {



    @Bean
    public EntityResource entityResource(){
        return new EntityResource();
    }
}
