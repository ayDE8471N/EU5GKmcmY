// 代码生成时间: 2025-09-05 20:51:13
package com.example.errorlogcollector;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ErrorLogCollector extends Action {

    private static final Logger logger = Logger.getLogger(ErrorLogCollector.class.getName());
    private static final String ERROR_LOG_FILE_PATH = "logs/error.log";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) {
        try {
            // Retrieve the error message from the request
            String errorMessage = request.getParameter("errorMessage");

            // Write the error message to the log file
            logError(errorMessage);

            // Clear the error message from the request
            request.removeAttribute("errorMessage");

            // Forward to the success page
            return mapping.findForward("success");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error processing error log collection", e);
            return mapping.findForward("error");
        }
    }

    /**
     * Logs the given error message to the error log file.
     * 
     * @param errorMessage The error message to log.
     * @throws IOException If an I/O error occurs.
     */
    private void logError(String errorMessage) throws IOException {
        if (errorMessage != null && !errorMessage.isEmpty()) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String logEntry = timestamp + " - " + errorMessage + "
";

            // Append the log entry to the error log file
            try (FileWriter writer = new FileWriter(new File(ERROR_LOG_FILE_PATH), true)) {
                writer.write(logEntry);
            }
        }
    }
}
