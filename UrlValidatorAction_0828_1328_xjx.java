// 代码生成时间: 2025-08-28 13:28:52
package com.example.struts;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.net.MalformedURLException;

/**
 * Struts 2 Action class to validate URL links.
 */
@ParentPackage("default")
@Namespace("/urlValidator")
public class UrlValidatorAction extends ActionSupport implements ServletRequestAware {

    private HttpServletRequest request;
    private String urlToValidate;
    private String validationResult;

    // Setter method for urlToValidate
    public void setUrlToValidate(String urlToValidate) {
        this.urlToValidate = urlToValidate;
    }

    // Getter method for urlToValidate
    public String getUrlToValidate() {
        return urlToValidate;
    }

    // Getter method for validationResult
    public String getValidationResult() {
        return validationResult;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * Validate the provided URL.
     * @return String - The result of the validation.
     */
    @Action(value = "validateUrl", results = {
        @Result(name = "success", location = "/urlValidator/validateUrlSuccess.jsp"),
        @Result(name = "error", location = "/urlValidator/validateUrlError.jsp")
    })
    public String validateUrl() {
        try {
            // Attempt to create a URL object to validate the format
            new URL(urlToValidate);
            validationResult = "URL is valid.";
        } catch (MalformedURLException e) {
            // If an exception is caught, the URL is not valid
            validationResult = "URL is invalid.";
        }
        return validationResult.equals("URL is valid.") ? "success" : "error";
    }
}
