// 代码生成时间: 2025-09-14 02:49:28
package com.example.organizer;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * FolderStructureOrganizer is an action class that organizes the files in a given directory.
 * It sorts files into subdirectories based on a specific criterion, for example, file extension.
 */
public class FolderStructureOrganizer extends ActionSupport {

    private String sourceDirectory;
    private String targetDirectory;

    /**
     * Sets the source directory where the files are located.
     *
     * @param sourceDirectory The path to the source directory.
     */
    public void setSourceDirectory(String sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

    /**
     * Sets the target directory where the files will be organized.
     *
     * @param targetDirectory The path to the target directory.
     */
    public void setTargetDirectory(String targetDirectory) {
        this.targetDirectory = targetDirectory;
    }

    /**
     * The execute method is called when the action is executed.
     * It organizes the files from the source directory to the target directory.
     *
     * @return The result of the action execution.
     */
    @Override
    public String execute() {
        try {
            File sourceDir = new File(sourceDirectory);
            File targetDir = new File(targetDirectory);

            if (!sourceDir.exists() || !sourceDir.isDirectory()) {
                addActionError("The source directory does not exist or is not a directory.");
                return INPUT;
            }

            if (!targetDir.exists() && !targetDir.mkdirs()) {
                addActionError("Failed to create the target directory.");
                return INPUT;
            }

            organizeFiles(sourceDir, targetDir);

            return SUCCESS;

        } catch (Exception e) {
            addActionError("An error occurred while organizing the folder structure: " + e.getMessage());
            return ERROR;
        }
    }

    /**
     * Organizes files in the source directory into subdirectories within the target directory.
     *
     * @param sourceDir The source directory containing the files to be organized.
     * @param targetDir The target directory where files will be organized.
     */
    private void organizeFiles(File sourceDir, File targetDir) {
        File[] files = sourceDir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                String fileExtension = getFileExtension(fileName);
                File targetSubDir = new File(targetDir, fileExtension);

                if (!targetSubDir.exists() && !targetSubDir.mkdirs()) {
                    continue; // Skip if subdirectory cannot be created
                }

                File targetFile = new File(targetSubDir, fileName);
                file.renameTo(targetFile);
            } else if (file.isDirectory()) {
                // Recursively organize files in subdirectories
                organizeFiles(file, targetDir);
            }
        }
    }

    /**
     * Extracts the file extension from a file name.
     *
     * @param fileName The name of the file.
     * @return The file extension or an empty string if no extension is found.
     */
    private String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf('.');
        return index == -1 ? "" : fileName.substring(index + 1).toLowerCase();
    }
}
