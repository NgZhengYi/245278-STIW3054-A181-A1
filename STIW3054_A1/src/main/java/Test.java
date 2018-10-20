import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        WebScraping webScraping = new WebScraping();
        Excel excel = new Excel();

        webScraping.extractTable();
        webScraping.printArrayRecord();
        excel.writeExcel(webScraping.getTriviaArrayList());
    }
}
