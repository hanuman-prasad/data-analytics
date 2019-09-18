package edu.elearning.server.rest;

import java.util.logging.Logger;

public abstract class DefaultServerLauncher implements ServerLauncher {

    private static final Logger LOG = Logger.getLogger("DefaultServerLauncher");

    RestServer server;

    @Override
    public RestServer startAndDeploy() {
        RestServerConfiguration config = loadServerConfiguration();
        server = configureRestServer(config);
        server.start().deploy();
        return server;
    }

    @Override
    public abstract RestServer configureRestServer(RestServerConfiguration serverConfiguration);

    protected abstract RestServerConfiguration loadServerConfiguration();
}
