// 代码生成时间: 2025-09-23 01:27:33
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.convention.annotation.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * This is a simple WebContentCrawler using Struts2 framework.
 * It fetches content from a given URL and returns it as a string.
 */
@Results({"success": "/success.jsp", "input": "/error.jsp"})
public class WebContentCrawler extends ActionSupport {

    private String url;
    private String content;

    /**
     * Default constructor.
     */
    public WebContentCrawler() {
    }

    /**
     * Sets the URL from which to fetch content.
     *
     * @param url The URL to fetch content from.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets the content fetched from the URL.
     *
     * @return The content fetched from the URL.
     */
    public String getContent() {
        return content;
    }

    /**
     * Performs the web content crawling action.
     *
     * @return A string indicating the action result.
     */
    @Action(value = "crawl", results = {"success": "success", "input": "input"})
    public String execute() {
        try {
            // Encode the URL to handle special characters
            URL encodedUrl = new URL(URLEncoder.encode(url, StandardCharsets.UTF_8.toString()));

            // Create an HttpClient instance to perform HTTP requests
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet(encodedUrl.toURI());

            // Execute the request and get the response
            HttpResponse response = httpClient.execute(request);

            // Check if the response is successful
            if (response.getStatusLine().getStatusCode() == 200) {
                // Get the content from the response entity
                HttpEntity entity = response.getEntity();
                content = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            } else {
                // Handle non-200 responses
                addActionError("Failed to fetch content from URL: " + url);
                return ERROR;
            }
        } catch (IOException e) {
            // Handle I/O errors
            addActionError("An error occurred while fetching content: " + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            // Handle other exceptions
            addActionError("An unexpected error occurred: " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
}
