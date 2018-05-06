import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;
public class MasterServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        System.out.println(request.getRequestURI());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Receive Post" + request.getRequestURI());
        if(request.getRequestURI().equals("/servlet/submitQuery")){
            String query = request.getParameter("queryName");
            System.out.println("Query is: "+ query);
        }

    }

}
