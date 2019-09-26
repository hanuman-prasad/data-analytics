package edu.elearning.service.datacache;

import edu.elearning.server.rest.DefaultServerLauncher;
import edu.elearning.server.rest.RestServer;
import edu.elearning.server.rest.RestServerConfiguration;
import edu.elearning.service.datacache.conf.DataCacheApplication;

public class DataCacheServer extends DefaultServerLauncher {

    private DataCacheServer restServer;

    public static void main(String[] args) {
        new DataCacheServer().startAndDeploy();
    }

    @Override
    public RestServer configureRestServer(RestServerConfiguration conf) {
        RestServer server = RestServer.createServer(conf.host(),
                conf.port(),
                conf.ioThreads(),
                conf.workerThreads());
        server.addApplication(new DataCacheApplication());

        return server;
    }

    @Override
    protected RestServerConfiguration loadServerConfiguration() {
        return new RestServerConfiguration() {
            @Override
            public String host() {
                return "localhost";
            }

            @Override
            public String path() {
                return "/asteri";
            }

            @Override
            public int port() {
                return 33001;
            }

            @Override
            public int ioThreads() {
                return 3;
            }

            @Override
            public int workerThreads() {
                return 3;
            }
        };
    }
}
