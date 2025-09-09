// 代码生成时间: 2025-09-10 03:02:03
package com.example.memoryanalysis;

import com.opensymphony.xwork2.ActionSupport;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

/**
 * Struts 2 Action class to analyze memory usage.
 * This class provides functionality to display memory usage statistics.
 */
@Results({
    @Result(name = "success", location = "memory_usage.jsp")
})
public class MemoryUsageAnalysisAction extends ActionSupport {
    
    /**
     * Method to execute when the action is invoked.
     * It retrieves the memory usage information and sets it to the value stack.
     * @return The result string indicating the next action to be performed.
     */
    public String execute() {
        try {
            // Get the memory MX bean for accessing memory usage statistics
            MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
            
            // Get the heap memory usage
            MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
            
            // Get the non-heap memory usage
            MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
            
            // Set the memory usage statistics to the value stack
            // These values can then be accessed in the JSP page for display
            addActionMessage("Heap Memory Usage: " + heapMemoryUsage.toString());
            addActionMessage("Non-Heap Memory Usage: " + nonHeapMemoryUsage.toString());
            
            return SUCCESS;
        } catch (Exception e) {
            // Handle any exceptions that may occur during memory usage retrieval
            addActionError("Error retrieving memory usage statistics: " + e.getMessage());
            return ERROR;
        }
    }
}