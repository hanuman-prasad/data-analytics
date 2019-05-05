package edu.elearning.service.cache;

import edu.elearning.server.rest.DefaultServerLauncher;
import edu.elearning.server.rest.RestServer;
import edu.elearning.server.rest.RestServerConfiguration;
import edu.elearning.service.cache.conf.DataCacheRestServerConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DataCacheServer extends DefaultServerLauncher {


    public static void main(String[] args) {
        new DataCacheServer().startAndDeploy();
    }

    @Override
    public RestServer configureRestServer(RestServerConfiguration conf) {


        RestServer restServer = (RestServer) RestServer.createServer(conf.host(), conf.port(), conf.ioThreads(), conf.workerThreads())
                .contextPath(conf.path());


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
