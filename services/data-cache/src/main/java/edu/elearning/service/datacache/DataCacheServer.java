package edu.elearning.service.datacache;

import edu.elearning.server.rest.DefaultServerLauncher;
import edu.elearning.server.rest.RestServer;
import edu.elearning.server.rest.RestServerConfiguration;
import edu.elearning.service.datacache.conf.DataCacheApplication;
import edu.elearning.service.datacache.conf.DataCacheRestServerConfig;
import edu.elearning.service.datacache.conf.EntityResourceConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DataCacheServer extends DefaultServerLauncher {


    public static void main(String[] args) {
        new DataCacheServer().startAndDeploy();
    }

    @Override
    public RestServer configureRestServer(RestServerConfiguration conf) {


        RestServer restServer = (RestServer) RestServer.createServer(conf.host(), conf.port(), conf.ioThreads(), conf.workerThreads())
                .contextPath(conf.path())
                .appliacationClass(DataCacheApplication.class)
                .configuration(EntityResourceConfig.class);


        return restServer;
    }

    @Override
    protected RestServerConfiguration loadServerConfiguration() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DataCacheRestServerConfig.class);
        context.refresh();

        return context.getBean(DataCacheRestServerConfig.class);
    }
}
