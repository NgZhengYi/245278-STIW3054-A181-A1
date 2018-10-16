import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class WebScraping {
    private ArrayList<Trivia> triviaArrayList = new ArrayList<Trivia>();

    public void extractTable() {
        String link = "https://ms.wikipedia.org/wiki/Malaysia";
        try {
            Document document = Jsoup.connect(link).userAgent("Chrome/69.0.3497.100").get();
            String title = document.title();
            System.out.println("Title : " + title);

            Element element = document.getElementById("Trivia").parent().nextElementSibling();
            //Element parent = element.parent(); //Referring H2 TAG
            //Element sibling = parent.nextElementSibling(); //Next Element from H2 TAG
            Elements tableRows = element.select("tr");

            for (int i = 0; i < tableRows.size(); i++) {
                //Table Rows size : 24
                String c1 = tableRows.get(i).select("th").text();
                String c2 = tableRows.get(i).select("td").text();
                //System.out.println("--------------- Record ---------------");
                //System.out.println((i+1) + ". " + C1 + " : " + C2);
                triviaArrayList.add(new Trivia(c1, c2));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printArrayRecord() {
        int i = 1;
        System.out.println("--------------- Table ---------------");
        for (Trivia t : triviaArrayList) {
            System.out.println(i + ". " + t.getC1() + " : " + t.getC2());
            i++;
        }
    }

    public ArrayList<Trivia> passArray() {
        return triviaArrayList;
    }

}
