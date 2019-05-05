package edu.elearning.server.rest;

public interface RestServerConfiguration {

    String host();
    String path();
    int port();
    int ioThreads();
    int workerThreads();
}
