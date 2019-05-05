package edu.elearning.server.rest;

public class RestServer extends DefaultRestServer {

    public RestServer(String host, int port, int ioThreads, int workerThreads) {
        super(host, port, ioThreads, workerThreads);
    }

    public static RestServer createServer(String host, int port, int ioThreads, int workerThreads) {
        return new RestServer(host, port, ioThreads, workerThreads);
    }
}
