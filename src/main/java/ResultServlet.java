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

public class ResultServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String startIdxStr = request.getParameter("startIdx");
        int startIdx = Integer.parseInt(startIdxStr);
        System.out.println(startIdx);

        HttpSession s = request.getSession();
        Object queryResults = (List<Hashtable<String, String>>)s.getAttribute("queryResults");
        //System.out.println("test"+ test);



        String path = System.getProperty("user.dir");
        File f = new File(path + "/public/html/" + "results.html");
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(f);

        int pageCount = 0;
        while (sc.hasNext()){
            String line = sc.nextLine();
            if(line.contains("page-item")){
                if(startIdx/10 == pageCount){
                    line = line.replace("page-item", "page-item disabled");
                }
                pageCount++;
            }
            sb.append(line + "\n");
        }
        sc.close();
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println(sb.toString());
        pw.flush();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
