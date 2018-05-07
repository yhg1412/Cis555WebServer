import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class QueryServlet extends HttpServlet {
    public MySQLWrapper mysql;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("request query is"+request.getRequestURI());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //System.out.println("Receive Post" + request.getRequestURI());
        String query = request.getParameter("queryName");
        System.out.println("Query is: "+ query);
        HttpSession session = request.getSession();
        String username=null;
        String profile = null;
        if(session==null){
            System.out.println("Internal Error, Query null session");
            response.sendRedirect("/home/login");
            return;
        }
        profile = (String)session.getAttribute("profile");
        List<UrlResult> results = mysql.evaluateQuery(query, profile);
        session.setAttribute("queryResults",results);

        //save to session
        session.setAttribute("queryResults", results);
        response.sendRedirect("/result?startIdx=0");
    }
    public void init(){
        System.out.println("Init from QueryServlet");
        mysql = MiniJettyServer.mysql;
    }

}
