// 代码生成时间: 2025-09-21 18:26:47
import com.opensymphony.xwork2.ActionSupport;
import java.net.URL;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import javax.servlet.http.HttpServletRequest;

/**
 * A Struts 2 action class that validates the URL link validity.
 */
@Namespace("/url")
@Results({
    @Result(name = URLValidatorAction.SUCCESS, location = "urlValidationSuccess.jsp"),
    @Result(name = URLValidatorAction.INVALID, location = "urlValidationFailure.jsp")
})
public class URLValidatorAction extends ActionSupport implements ServletRequestAware {

    public static final String SUCCESS = "success";
    public static final String INVALID = "invalid";
    private HttpServletRequest request;
    private String urlToCheck;

    /**
     * Validate URL link.
     * @return Struts 2 action result.
     * @throws Exception if an error occurs during URL validation.
     */
    @Action(value = "validateURL")
    public String validateURL() throws Exception {
        try {
            // Check if the URL is not empty
            if (urlToCheck == null || urlToCheck.trim().isEmpty()) {
                addFieldError("urlToCheck", "URL cannot be empty.");
                return INVALID;
            }

            // Create a URL instance and try to open the connection.
            URL url = new URL(urlToCheck);
            url.openConnection().connect();

            // If we reach this point, the URL is valid.
            return SUCCESS;
        } catch (Exception e) {
            // Handle exceptions, set error message and return invalid result.
            addFieldError("urlToCheck", "URL is invalid: " + e.getMessage());
            return INVALID;
        }
    }

    // Getters and setters
    public String getUrlToCheck() {
        return urlToCheck;
    }

    public void setUrlToCheck(String urlToCheck) {
        this.urlToCheck = urlToCheck;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
}
