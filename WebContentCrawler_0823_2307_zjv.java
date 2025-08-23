// 代码生成时间: 2025-08-23 23:07:57
package crawler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * A simple web content crawler using Java and Struts framework.
 * It fetches the content of a webpage and extracts specified elements.
 */
public class WebContentCrawler {

    /**
     * Fetches the HTML content of a webpage using Apache HttpClient.
     *
     * @param url The URL of the webpage to fetch.
     * @return The HTML content of the webpage as a String.
     * @throws IOException If an I/O error occurs.
     */
    private static String fetchHtmlContent(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        return entity != null ? EntityUtils.toString(entity) : "";
    }

    /**
     * Extracts and returns the specified elements from the webpage content using Jsoup.
     *
     * @param htmlContent The HTML content of the webpage.
     * @param query The Jsoup query to select the elements.
     * @return A list of extracted elements as Strings.
     */
    private static Elements extractElements(String htmlContent, String query) {
        Document doc = Jsoup.parse(htmlContent);
        return doc.select(query);
    }

    /**
     * The main method to run the web content crawler.
     * It fetches the content of a webpage and extracts the title element.
     *
     * @param args Command line arguments.
     * @throws IOException If an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {
        try {
            String url = "http://example.com"; // Replace with the target URL
            String htmlContent = fetchHtmlContent(url);
            Elements titleElements = extractElements(htmlContent, "title"); // Extract title element
            titleElements.forEach(element -> System.out.println(element.text())); // Print the title
        } catch (Exception e) {
            System.err.println("Error while crawling web content: " + e.getMessage());
        }
    }
}
