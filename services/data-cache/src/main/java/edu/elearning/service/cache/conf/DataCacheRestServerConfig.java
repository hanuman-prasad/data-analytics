package edu.elearning.service.cache.conf;

import edu.elearning.server.rest.RestServerConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataCacheRestServerConfig implements RestServerConfiguration {

    private String path = "/asteri/api/datacache";
    private String host = "localhost";
    private int port = 33501;
    private int ioThreads = 10;
    private int workerThreads = 10;


    @Override
    public String host() {
        return host;
    }

    @Override
    public String path() {
        return path;
    }

    @Override
    public int port() {
        return port;
    }

    @Override
    public int ioThreads() {
        return ioThreads;
    }

    @Override
    public int workerThreads() {
        return workerThreads;
    }
}
