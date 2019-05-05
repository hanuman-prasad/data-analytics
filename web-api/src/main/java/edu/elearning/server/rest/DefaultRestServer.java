package edu.elearning.server.rest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import io.undertow.Undertow;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.ListenerInfo;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.plugins.spring.SpringBeanProcessor;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public abstract class DefaultRestServer {

    private static final Logger LOG = Logger.getLogger("DefaultRestServer");

    private String contextPath = "asteri";

    private final String host;
    private int port;
    private int ioThreads;
    private int workerThreads;

    private UndertowJaxrsServer server;
    private List<String> resources = new LinkedList<>();
    private List<Class> configurations = new LinkedList<>();
    private AbstractApplicationContext applicationContext;


    public DefaultRestServer(String host, int port, int ioThreads, int workerThreads) {

        if("localhost".equals(host) || "127.0.0.1".equals(host) || null == host || host.isEmpty()) {
            LOG.info("no host specified, defaualting to actual...");
            try{
                host = InetAddress.getLocalHost().getHostName();
                LOG.info("Actual host : " + host);
            } catch (UnknownHostException e) {
                LOG.warning("Failed to resolve host name");
                host = "localhost";
            }

        }
        this.host = host;
        this.port = port;
        this.ioThreads = ioThreads;
        this.workerThreads = workerThreads;

        this.server = new UndertowJaxrsServer();
    }

    public DefaultRestServer deploy(){
            applicationContext = initialiseContext();
            return this;
    }

    public void start(){
        applicationContext.start();

        Undertow.Builder builder = Undertow.builder()
        .addHttpListener(port, host)
                .setIoThreads(ioThreads)
                .setWorkerThreads(workerThreads);

        server.start(builder);

    }

    protected AbstractApplicationContext initialiseContext(){
        ResteasyDeployment deployment = new ResteasyDeployment();
        deployment.setResourceClasses(resources);

        DeploymentInfo deploymentInfo = server.undertowDeployment(deployment)
                .setClassLoader(this.getClass().getClassLoader())
                .setContextPath("/" + contextPath)
                .setDeploymentName(contextPath);

        deploymentInfo.addListener(new ListenerInfo(RequestContextListener.class));


        Class[] configs = (Class[]) configurations.toArray();

        SpringBeanProcessor springBeanProcessor = new SpringBeanProcessor(deployment);

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.addBeanFactoryPostProcessor(springBeanProcessor);
        context.addApplicationListener(springBeanProcessor);

        if(configs.length > 0) {
            context.register(configs);
        }

        context.refresh();


        return context;
    }

    public final DefaultRestServer configuration(Class<?> config){
        this.configurations.add(config);
        return this;
    }

    public String getContextPath() {
        return contextPath;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
