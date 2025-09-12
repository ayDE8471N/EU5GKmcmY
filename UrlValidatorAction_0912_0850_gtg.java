// 代码生成时间: 2025-09-12 08:50:19
package com.example.struts.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import java.net.URL;
import java.net.MalformedURLException;

/**
 * This class is a Struts 2 action class that validates the URL link.
 * It checks whether a given URL is valid or not.
 */
@Namespace("/urlValidator")
public class UrlValidatorAction extends ActionSupport {

    // Request parameter for the URL to be validated
    private String urlToValidate;

    // Getter and setter for urlToValidate
    public String getUrlToValidate() {
        return urlToValidate;
    }

    public void setUrlToValidate(String urlToValidate) {
        this.urlToValidate = urlToValidate;
    }

    /**
     * Validates the provided URL.
     *
     * @return The result of the URL validation.
     * @throws Exception If any error occurs during the validation process.
     */
    @Action(value = "validate", results = {
        @Result(name = SUCCESS, location = "success.jsp"),
        @Result(name = ERROR, location = "error.jsp")
    })
    public String validate() {
        try {
            // Attempt to create a new URL object to validate the provided URL string
            new URL(urlToValidate);
            // If no exception is thrown, the URL is considered valid
            addActionMessage("The URL is valid.");
        } catch (MalformedURLException e) {
            // If a MalformedURLException is caught, the URL is invalid
            addActionError("The URL is invalid: " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
}
