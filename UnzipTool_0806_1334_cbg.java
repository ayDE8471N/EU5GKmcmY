// 代码生成时间: 2025-08-06 13:34:51
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.convention.annotation.ResultType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * UnzipTool action class using Struts 2 framework.
 * This class handles file upload and unzipping.
 */
@Namespace("/tools")
@Results({
    @Result(name = "success", type = ResultType.JSON),
    @Result(name = "error", type = ResultType.JSON)
})
public class UnzipTool extends ActionSupport {

    private String filePath;
    private String destinationPath;

    public String execute() {
        try {
            unzipFiles(filePath, destinationPath);
            return SUCCESS;
        } catch (IOException e) {
            addActionError("Error unzipping files: " + e.getMessage());
            return ERROR;
        }
    }

    /**
     * Unzips files from the given zip file path to the destination directory.
     *
     * @param zipFilePath The path to the zip file.
     * @param destDirectory The destination directory to unzip files to.
     * @throws IOException If an I/O error occurs.
     */
    private void unzipFiles(String zipFilePath, String destDirectory) throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }

        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                extractFile(zipIn, filePath);
            } else {
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }

    /**
     * Extracts a file from the zip input stream to the given file path.
     *
     * @param zipIn The zip input stream.
     * @param filePath The file path to extract to.
     * @throws IOException If an I/O error occurs.
     */
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[4096];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }
}
