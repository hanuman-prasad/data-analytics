package edu.elearning.service.datacache.conf;

import edu.elearning.service.datacache.resources.EntityResource;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;



public class DataCacheApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(EntityResource.class);
        return classes;
    }
}
