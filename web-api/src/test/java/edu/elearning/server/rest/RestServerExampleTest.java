package edu.elearning.server.rest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import static org.junit.Assert.assertEquals;

public class RestServerExampleTest {

    private RestServer restServer;

    @Before
    public void setUp() {
        restServer = new RestServerExample().startAndDeploy();
    }

    @After
    public void cleanUp() throws InterruptedException {
        Thread.sleep(1000 * 15);
        restServer.stop();
    }

    @Test
    public void test_rest_server() throws InterruptedException {
        Client client = ClientBuilder.newClient();
        String url = "http://localhost:33001/asteri/testapp/hello";

        Thread.sleep(1000 * 5);
        String val = client.target(url).request().get(String.class);

        assertEquals("Welcome to rest easy application.", val);
        client.close();

    }

    class RestServerExample extends DefaultServerLauncher {


        @Override
        public RestServer configureRestServer(RestServerConfiguration conf) {
            RestServer server = RestServer.createServer(conf.host(),
                    conf.port(),
                    conf.ioThreads(),
                    conf.workerThreads());
            server.addApplication(new MyTestApp());

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

}