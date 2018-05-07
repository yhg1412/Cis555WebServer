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
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ResultServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI().toString();
        if(uri.contains("result/")){
            HttpSession s = request.getSession(false);
            String profile = (String)s.getAttribute("profile");
            String url = uri.substring(8, uri.length());
            if(profile == null){
                profile = new String("");
            }
            if(profile.trim().length() == 0){
                profile = url;
            }else{
                profile = profile + "," + url;
            }
            System.out.println("profile"+profile);
            s.setAttribute("profile", profile);
            response.sendRedirect(url);
            return;
        }


        String startIdxStr = request.getParameter("startIdx");
        int startIdx = Integer.parseInt(startIdxStr);
        System.out.println(startIdx);
        MySQLWrapper.getHost("https://www.google.com/a/f/f");
        HttpSession s = request.getSession();
        List<UrlResult> results = (List<UrlResult>)s.getAttribute("queryResults");
        int resultsSize = results.size();
        int maxPage = resultsSize/10 + 1;

        PrintWriter pw = response.getWriter();

        String path = System.getProperty("user.dir");
        File f = new File(path + "/public/html/" + "results.html");
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(f);

        int pageCount = 0;
        int titleCount = 0;
        UrlResult result = null;
        String url = null;
        while (sc.hasNext()){
            String line = sc.nextLine();
            if(line.contains("titleholder") && titleCount+startIdx < results.size()){
                int idx = startIdx + titleCount++;
                result = results.get(idx);
                url = result.url;
//                System.out.println(url);
//                Document doc = Jsoup.connect(url).get();
//                String title = doc.title();

                String title = new URL(result.url).getHost();
                if(title.startsWith("www")){
                    System.out.println(title);
                    title = title.split("\\.")[1];
                }else{
                    title = title.split("\\.")[0];
                }
                title = title + " :";

                for(IndexingItem word: result.relatedWord){
                    title = title + " " + word.word;
                }
                line = line.replace("titleholder", title);
                line = line.replace("\"\"", "/result/"+url);

            }
            if(line.contains("urlholder") && titleCount+startIdx < results.size()){
                line = line.replace("urlholder", url);
            }
            if(line.contains("disholder") && titleCount+startIdx < results.size()){

            }
            if(line.contains("page-item")){
                if(startIdx/10 == pageCount || pageCount > maxPage){
                    line = line.replace("page-item", "page-item disabled");
                }
                pageCount++;
            }
            sb.append(line + "\n");
        }
        sc.close();
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html");

        pw.println(sb.toString());
        pw.flush();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
