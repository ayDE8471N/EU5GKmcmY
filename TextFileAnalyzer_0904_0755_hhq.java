// 代码生成时间: 2025-09-04 07:55:25
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.multipart.MultiPartRequest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// 文本文件内容分析器 Action 类
@Results({
    @Result(name = "success", location = "textAnalysisResult.jsp")
})
public class TextFileAnalyzer extends ActionSupport implements SessionAware, ServletRequestAware {

    private HttpSession session;
    private HttpServletRequest request;
    private String uploadFilePath;
    private String fileContent;
    private Map<String, Integer> wordCounts;

    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    // 上传文件并分析内容的方法
    public String upload() {
        MultiPartRequest multiPartRequest = (MultiPartRequest) request;
        try {
            // 获取上传的文件
            String fileName = multiPartRequest.getFilesystemName("file");
            uploadFilePath = multiPartRequest.getRealPath("file");
            
            // 读取文件内容
            BufferedReader reader = new BufferedReader(new FileReader(uploadFilePath));
            String line;
            this.wordCounts = new HashMap<>();
            while ((line = reader.readLine()) != null) {
                // 分析文件内容，统计单词出现次数
                String[] words = line.split("[\s]++");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        this.wordCounts.put(word, this.wordCounts.getOrDefault(word, 0) + 1);
                    }
                }
            }
            reader.close();
            this.fileContent = "Word counts: " + wordCounts.toString();
        } catch (IOException e) {
            this.fileContent = "Error reading file: " + e.getMessage();
            addActionError("Error reading file: " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    // getters 和 setters
    public String getUploadFilePath() {
        return uploadFilePath;
    }

    public void setUploadFilePath(String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public Map<String, Integer> getWordCounts() {
        return wordCounts;
    }

    public void setWordCounts(Map<String, Integer> wordCounts) {
        this.wordCounts = wordCounts;
    }
}
