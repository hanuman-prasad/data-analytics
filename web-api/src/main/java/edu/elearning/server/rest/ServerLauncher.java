package edu.elearning.server.rest;

public interface ServerLauncher {

    RestServer startAndDeploy();

    RestServer configureRestServer(RestServerConfiguration serverConfiguration);
}
