// 代码生成时间: 2025-08-25 13:37:51
package com.security;

import org.apache.struts2.ServletActionContext;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * SecurityAuditLogger class is responsible for logging security audit events.
 * It encapsulates the functionality to create and log security audit logs.
 */
public class SecurityAuditLogger {

    // Logger instance
    private static final Logger LOGGER = Logger.getLogger(SecurityAuditLogger.class.getName());

    /**
     * Logs a security audit event with the provided details.
     *
     * @param userName The user who performed the action
     * @param action The action that was performed
     * @param result The result of the action
     * @param additionalInfo Additional information about the event
     */
    public void logSecurityEvent(String userName, String action, String result, String additionalInfo) {
        try {
            // Create a formatted timestamp
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = dateFormat.format(new Date());

            // Create the log message
            String logMessage = String.format("[%s] - User: %s, Action: %s, Result: %s, Additional Info: %s", timestamp, userName, action, result, additionalInfo);

            // Log the security audit event
            LOGGER.log(Level.INFO, logMessage);

            // Optionally, you could write to a file or database here

        } catch (Exception e) {
            // Handle any exceptions that occur during logging
            LOGGER.log(Level.SEVERE, "Error logging security event", e);
        }
    }

    /**
     * Returns an instance of SecurityAuditLogger.
     *
     * @return SecurityAuditLogger instance
     */
    public static SecurityAuditLogger getInstance() {
        return new SecurityAuditLogger();
    }
}
