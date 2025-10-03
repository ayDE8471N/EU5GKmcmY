// 代码生成时间: 2025-10-03 20:39:45
package com.copyright.action;

import com.opensymphony.xwork2.ActionSupport;
import com.copyright.service.CopyrightService;
import com.copyright.exception.CopyrightException;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.convention.annotation.Action;

import java.util.HashMap;
import java.util.Map;

/**
 * Action class for copyright detection.
 */
@Results({
    @Result(name = "success", location = "success.jsp"),
    @Result(name = "error", location = "error.jsp")
})
public class CopyrightDetectionAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    // Service for copyright checking
    private CopyrightService copyrightService;

    // Input content for copyright detection
    private String content;

    // Message to display in the view
    private String message;

    // Constructor
    public CopyrightDetectionAction() {
        super();
    }

    // Setter for content
    public void setContent(String content) {
        this.content = content;
    }

    // Getter for content
    public String getContent() {
        return content;
    }

    // Setter for copyright service
    public void setCopyrightService(CopyrightService copyrightService) {
        this.copyrightService = copyrightService;
    }

    // Getter for copyright service
    public CopyrightService getCopyrightService() {
        return copyrightService;
    }

    // Getter for message
    public String getMessage() {
        return message;
    }

    // Copyright detection action method
    @Action(value = "copyrightDetection", results = {
        @Result(name = "success", location = "success.jsp"),
        @Result(name = "input", location = "input.jsp")
    })
    public String execute() {
        try {
            // Perform copyright detection
            boolean isCopyrighted = copyrightService.detectCopyright(content);

            // Set message based on the result
            if (isCopyrighted) {
                message = "The content is copyrighted.";
            } else {
                message = "The content is not copyrighted.";
            }

            // Return success if no exceptions are thrown
            return SUCCESS;
        } catch (CopyrightException e) {
            // Handle copyright exceptions
            message = e.getMessage();
            addActionError("An error occurred: " + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            // Handle other exceptions
            message = "An unexpected error occurred.";
            addActionError("An unexpected error occurred: " + e.getMessage());
            return ERROR;
        }
    }
}
