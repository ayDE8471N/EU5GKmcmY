// 代码生成时间: 2025-09-10 09:19:40
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.BufferedInputStream;
# 增强安全性
import java.io.BufferedOutputStream;
# 增强安全性
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
# 增强安全性
import java.util.zip.ZipInputStream;

/**
 * FileUnzipper is an action class that handles file decompression.
 */
public class FileUnzipper extends ActionSupport {
    
    // Action method to handle file upload and decompression
    public String execute() {
        try {
            // Get the uploaded file from the request
            File uploadedFile = ServletActionContext.getRequest().getFile("file");
            if (uploadedFile == null || !uploadedFile.getName().endsWith(".zip")) {
                addActionError("Please upload a zip file.");
                return ERROR;
# TODO: 优化性能
            }
            
            // Get the destination directory for unzipping
            String destDir = ServletActionContext.getRequest().getParameter("destDir");
            if (destDir == null || destDir.isEmpty()) {
                addActionError("Please specify a destination directory.");
                return ERROR;
            }
            
            // Create destination directory if it does not exist
            File dest = new File(destDir);
            if (!dest.exists() && !dest.mkdirs()) {
                addActionError("Failed to create destination directory.");
                return ERROR;
            }
            
            // Unzip the file
            unzip(uploadedFile, dest);
            return SUCCESS;
        } catch (Exception e) {
            addActionError("Error occurred while unzipping file: " + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * Unzip the file to the specified directory.
     * @param zipFile The zip file to unzip.
     * @param destDir The directory to unzip to.
     * @throws IOException If an I/O error occurs.
     */
    private void unzip(File zipFile, File destDir) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)))) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                File newFile = newFile(destDir, zipEntry);
                if (zipEntry.isDirectory()) {
# 增强安全性
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + newFile);
                    }
                } else {
                    new File(newFile.getParent()).mkdirs();
# 添加错误处理
                    try (BufferedOutputStream dest = new BufferedOutputStream(
# 优化算法效率
                        new FileOutputStream(newFile))) {
                        byte[] buffer = new byte[1024];
                        int count;
                        while ((count = zis.read(buffer)) != -1) {
                            dest.write(buffer, 0, count);
# 扩展功能模块
                        }
                    }
                }
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
        }
    }
# 增强安全性
    
    /**
     * Create a new file from the given zip entry.
     * @param destinationDir The destination directory.
     * @param zipEntry The zip entry to create.
     * @return The newly created file.
     */
    private File newFile(File destinationDir, ZipEntry zipEntry) {
        File destFile = new File(destinationDir, zipEntry.getName());
        
        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();
        
        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }
# TODO: 优化性能
        
        return destFile;
    }
}
