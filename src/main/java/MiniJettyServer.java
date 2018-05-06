
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class MiniJettyServer  {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        WebAppContext context = new WebAppContext();

        context.setDescriptor("conf/web.xml");
        context.setContextPath("/");
        context.setResourceBase("./public/");
        context.setParentLoaderPriority(false);
        server.setHandler(context);

        server.start();
        server.join();
    }
}
