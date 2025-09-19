// 代码生成时间: 2025-09-20 06:41:30
import java.io.File;
import java.util.Scanner;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * BatchFileRenamer is a Struts 2 action class that allows renaming multiple files
 * in a specified directory based on a pattern.
 */
public class BatchFileRenamer extends ActionSupport {

    // Directory to operate on
    private String directoryPath;
    // Pattern for renaming files
    private String renamePattern;

    // Constructor
    public BatchFileRenamer() {}

    // Setters and Getters
    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setRenamePattern(String renamePattern) {
        this.renamePattern = renamePattern;
    }

    public String getRenamePattern() {
        return renamePattern;
    }

    /**
     * Renames files in the specified directory according to the rename pattern.
     * @return A string indicating the result of the operation.
     */
    public String execute() {
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            addActionError("The specified path is not a directory.");
            return INPUT;
        }

        File[] files = directory.listFiles();
        if (files == null) {
            addActionError("Failed to list files in the directory.");
            return INPUT;
        }

        for (File file : files) {
            if (!file.isFile()) {
                continue; // Skip directories
            }

            try {
                String newFileName = renamePattern.replace("{original}", file.getName());
                File newFile = new File(directory, newFileName);
                if (!file.renameTo(newFile)) {
                    addActionError("Failed to rename " + file.getName() + " to " + newFileName + ".");
                    return INPUT;
                }
            } catch (Exception e) {
                addActionError("Error occurred while renaming file: " + e.getMessage());
                return INPUT;
            }
        }

        return SUCCESS;
    }
}
