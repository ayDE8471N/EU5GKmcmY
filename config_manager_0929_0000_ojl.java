// 代码生成时间: 2025-09-29 00:00:55
package com.example.config;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigManager.java - 配置文件管理器
 *
 * This class is designed to manage application configuration files.
 * It reads and updates properties files, providing a centralized
 * way to access and modify configuration settings.
 */
public class ConfigManager extends ActionSupport {

    private Properties configProperties;

    /**
     * Loads the configuration properties from a specified file.
     *
     * @param filename The name of the properties file to load.
     * @throws IOException If an I/O error occurs reading the file.
     */
    public void loadConfiguration(String filename) throws IOException {
        configProperties = new Properties();
        configProperties.load(ServletActionContext.getRequest().getServletContext().getResourceAsStream(filename));
    }

    /**
     * Updates a configuration property with a new value.
     *
     * @param key The key of the property to update.
     * @param value The new value to set for the property.
     */
    public void updateProperty(String key, String value) {
        if (configProperties != null) {
            configProperties.setProperty(key, value);
        } else {
            // Handle the case where the configuration has not been loaded
            addActionError("Configuration properties have not been loaded.");
        }
    }

    /**
     * Saves the updated configuration properties back to the file.
     *
     * @param filename The name of the properties file to save to.
     * @throws IOException If an I/O error occurs writing the file.
     */
    public void saveConfiguration(String filename) throws IOException {
        if (configProperties != null) {
            try (java.io.FileOutputStream fos = new java.io.FileOutputStream(ServletActionContext.getRequest().getServletContext().getRealPath(filename))) {
                configProperties.store(fos, "Updated configuration properties");
            }
        } else {
            // Handle the case where the configuration has not been loaded
            addActionError("Configuration properties have not been loaded.");
        }
    }

    // Getter and setter for configProperties
    public Properties getConfigProperties() {
        return configProperties;
    }

    public void setConfigProperties(Properties configProperties) {
        this.configProperties = configProperties;
    }
}
