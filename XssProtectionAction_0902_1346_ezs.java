// 代码生成时间: 2025-09-02 13:46:57
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.dispatcher.multipart.MultiPartWrapper;
import org.apache.struts2.dispatcher.multipart.UploadedFile;
import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.octo.captcha.engine.CaptchaEngineException;
import com.octo.captcha.image.simple.SimpleImageCaptcha;
import org.apache.commons.io.IOUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for handling user input to prevent XSS attacks.
 */
public class XssProtectionAction extends ActionSupport {

    /**
     * Constructor for the class.
     */
    public XssProtectionAction() {
        super();
    }

    @Action(value = "/xssProtection", results = {
        @Result(name = "success", type = "stream", params = {"contentType", "text/html", "inputName", "inputStream"})
    })
    public String execute() {
        HttpServletRequest request = ServletActionContext.getRequest();
        MultiPartWrapper multiPartWrapper = new MultiPartRequestWrapper(request);
        try {
            // Check for XSS attack in user input
            if (isXssAttack(multiPartWrapper)) {
                addActionError("XSS attack detected. Please enter valid data.");
                return ERROR;
            }
            // Process the request and return success
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Error processing request: " + e.getMessage());
            return ERROR;
        }
    }

    /**
     * This method checks if the user input contains XSS attack patterns.
     *
     * @param multiPartWrapper The wrapper for the request
     * @return true if XSS attack is detected, false otherwise
     */
    private boolean isXssAttack(MultiPartWrapper multiPartWrapper) {
        Map<String, String[]> parameterMap = multiPartWrapper.getParameterMap();
        for (String[] values : parameterMap.values()) {
            for (String value : values) {
                // Check for XSS patterns in the input
                if (value != null && (value.contains("<script>") || value.contains("</script>") || value.contains("href"))) {
                    return true;
                }
            }
        }
        for (UploadedFile file : multiPartWrapper.getFiles()) {
            // Add more checks for file content if needed
        }
        return false;
    }
}
