// 代码生成时间: 2025-08-13 08:25:37
package com.example.accesscontrol;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.Map;

/**
 * Action class for handling user access control.
 */
@Namespace("/accessControl")
public class AccessControlAction extends ActionSupport {

    private Map<String, String> users = new HashMap<>();
    private String username;
    private String password;
    private String accessLevel;
    private String resultMessage;

    public AccessControlAction() {
        // Initialize user data
        users.put("admin", "admin");
        users.put("user", "user");
    }

    /**
     * Login action with access control.
     * @return String
     */
    @Action(value = "login", results = {
        @Result(name = "success", location = "index.jsp"),
        @Result(name = "error", location = "error.jsp")
    })
    public String login() {
        String storedPassword = users.get(username);
        if (storedPassword != null && storedPassword.equals(password)) {
            // Simulate access level check
            accessLevel = storedPassword.equals("admin") ? "admin" : "user";
            resultMessage = "Login successful!";
            return "success";
        } else {
            resultMessage = "Invalid username or password";
            return "error";
        }
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public String getResultMessage() {
        return resultMessage;
    }
}
