import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class QueryServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("request query is"+request.getRequestURI());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //System.out.println("Receive Post" + request.getRequestURI());
        if(request.getRequestURI().equals("/servlet/submitQuery")){
            String query = request.getParameter("queryName");
            System.out.println("Query is: "+ query);
        }



        String path = System.getProperty("user.dir");
        File f = new File(path + "/public/html/" + "results.html");
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(f);
        while (sc.hasNext()){
            sb.append(sc.nextLine() + "\n");
        }
        sc.close();
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println(sb.toString());
        pw.flush();
    }

}
