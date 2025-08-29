// 代码生成时间: 2025-08-29 16:20:48
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import java.net.URL;
import javax.validation.constraints.NotNull;

/**
 * Action class for validating URL links
 */
@ParentPackage("default")
@Namespace("/urlValidator")
public class UrlValidatorAction extends ActionSupport {

    /**
     * The URL to be validated
     */
    @NotNull(message = "URL cannot be null.")
    private String url;

    /**
     * Flag indicating whether the URL is valid or not
     */
    private boolean isValid;

    /**
     * The error message if the URL is invalid
     */
    private String errorMessage;

    /**
     * Getter for the URL
     * @return the URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter for the URL
     * @param url the URL to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Getter for the validity of the URL
     * @return whether the URL is valid
     */
    public boolean isIsValid() {
        return isValid;
    }

    /**
     * Getter for the error message
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Action method to validate the URL
     * @return String
     * @throws Exception if any error occurs during validation
     */
    @Action(value = "validate", results = {
            @Result(name = SUCCESS, location = "urlValidatorSuccess.jsp"),
            @Result(name = ERROR, location = "urlValidatorError.jsp")
    })
    public String validate() {
        try {
            URL urlObj = new URL(this.url);
            urlObj.toURI();
            isValid = true;
        } catch (Exception e) {
            isValid = false;
            errorMessage = "Invalid URL: " + e.getMessage();
            addActionError(errorMessage);
        }
        return isValid ? SUCCESS : ERROR;
    }
}
