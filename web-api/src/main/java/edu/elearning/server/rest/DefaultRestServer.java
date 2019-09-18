package edu.elearning.server.rest;

import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ServletContainer;
import io.undertow.servlet.api.ServletInfo;
import org.jboss.resteasy.plugins.server.servlet.HttpServlet30Dispatcher;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.jboss.resteasy.util.PortProvider;

import javax.servlet.ServletException;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import static io.undertow.servlet.Servlets.servlet;

public abstract class DefaultRestServer {

    private static final Logger LOG = Logger.getLogger("DefaultRestServer");

    private String contextPath = "/asteri";

    private final PathHandler rootPathHandler = new PathHandler();
    private final ServletContainer container = ServletContainer.Factory.newInstance();
    private final Undertow server;
    private Application application;


    public DefaultRestServer(String host, int port, int ioThreads, int workerThreads) {
      this.server = Undertow.builder()
                .addHttpListener(port, host)
                .setHandler(rootPathHandler)
                .setWorkerThreads(workerThreads)
                .setIoThreads(ioThreads)
                .build();
    }


    public DefaultRestServer start(){
        server.start();
        return this;
    }

    public DefaultRestServer deploy() {
        DeploymentInfo di = buildResteasyDeploymentInfo();
        di.setClassLoader(application.getClass().getClassLoader());
        di.setContextPath(contextPath);
        di.setDeploymentName("Resteasy" + contextPath);
        return deploy(di);
    }

    public void stop() {
        server.stop();
    }

    private DefaultRestServer deploy(DeploymentInfo deploymentInfo) {
        DeploymentManager manager = container.addDeployment(deploymentInfo);
        manager.deploy();

        try {
            rootPathHandler.addPrefixPath(deploymentInfo.getContextPath(), manager.start());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return this;
    }

    private DeploymentInfo buildResteasyDeploymentInfo() {

        String mapping = getContextPath(application);

        String prefix = mapping.equals("/*") ? null : mapping.substring(0, mapping.length() - 2);

        ResteasyDeployment resteasyDeployment = new ResteasyDeployment();
        resteasyDeployment.setApplication(application);

        ServletInfo resteasyServlet = servlet("ResteasyServlet", HttpServlet30Dispatcher.class)
                .setAsyncSupported(true)
                .setLoadOnStartup(1)
                .addMapping(mapping);
        if (prefix != null) resteasyServlet.addInitParam("resteasy.servlet.mapping.prefix", prefix);

        return new DeploymentInfo()
                .addServletContextAttribute(ResteasyDeployment.class.getName(), resteasyDeployment)
                .addServlet(resteasyServlet);
    }

    private String getContextPath(Application application) {
        ApplicationPath appContextPath = application.getClass().getAnnotation(ApplicationPath.class);
        String mapping = appContextPath != null ? appContextPath.value() : "/";

        if (!mapping.startsWith("/")) mapping = "/" + mapping;
        if (!mapping.endsWith("/")) mapping += "/";
        mapping = mapping + "*";

        return mapping;
    }

    public void addApplication(Application application) {
        this.application = application;
    }
}
