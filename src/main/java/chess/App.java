package chess;

import board.Position;
import com.google.common.collect.Maps;
import manager.GameManager;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    private static Map<Integer, GameManager> gameManagerMap = Maps.newHashMap();

    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();

        tomcat.setPort(8080);
        tomcat.getConnector();

        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(".").getAbsolutePath());

        WebResourceRoot resources = new StandardRoot(ctx);

        ctx.setResources(resources);

        Tomcat.addServlet(ctx, "hello", new HttpServlet() {
            protected void service(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
                Writer w = resp.getWriter();
                w.write(gameManagerMap.get(0).CB.printBoard());
                w.flush();
            }
        });

        ctx.addServletMappingDecoded("/abc", "hello");

        tomcat.start();

        App app = new App();
        gameManagerMap.put(0, new GameManager());

        app.play(0, new Position(1,0), new Position(2,0));

        tomcat.getServer().await();
    }

    // Public Web API
    public void play(Integer gameId, Position initPos, Position finPos) {
        if (gameManagerMap.containsKey(gameId)) {
            gameManagerMap.get(gameId).play(initPos, finPos);
        } else {
            System.err.println("STUB: Log error: Invalid game ID.");
        }
    }
}