// 代码生成时间: 2025-08-24 06:00:28
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.validator.routines.UrlValidator;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.ResultType;
import org.apache.struts2.convention.annotation.ResultMap;
import org.apache.struts2.convention.annotation.ResultNames;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import java.net.URL;
import java.net.MalformedURLException;

/**
 * Action class for validating URL links
 */
@Namespace("/")
@ParentPackage("default")
@Results({
    @Result(name = UrlValidatorAction.SUCCESS, location = "success.jsp"),
    @Result(name = UrlValidatorAction.ERROR, location = "error.jsp")
})
public class UrlValidatorAction extends ActionSupport implements SessionAware {

    // SessionMap to store session attributes
    private SessionMap sessionMap;

    // URL to be validated
    private String url;

    // Validator for URL
    public UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Action(value = "validateUrl", results = {
        @Result(name = SUCCESS, location = "success.jsp", type = ResultType.JSP),
        @Result(name = ERROR, location = "error.jsp", type = ResultType.JSP)
    })
    public String validate() {
        try {
            // Check if the URL is valid using the UrlValidator
            if (urlValidator.isValid(url)) {
                // Store the URL in session
                sessionMap.put("url", url);
                return SUCCESS;
            } else {
                return ERROR;
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during validation
            addActionError("Error validating URL: " + e.getMessage());
            return ERROR;
        }
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.sessionMap = new SessionMap(session);
    }
}
