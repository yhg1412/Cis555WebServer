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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("request query is"+request.getRequestURI());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //System.out.println("Receive Post" + request.getRequestURI());
        if(request.getRequestURI().equals("/query")){
            String query = request.getParameter("queryName");
            System.out.println("Query is: "+ query);
        }

        HttpSession s = request.getSession();
        //compute query
        List<Hashtable<String, String>> queryResults = null;

        //save to session
        s.setAttribute("queryResults", queryResults);


        response.sendRedirect("/result?startIdx=0");


    }

}
