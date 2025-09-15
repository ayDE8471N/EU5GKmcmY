// 代码生成时间: 2025-09-15 15:12:22
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * TextFileAnalyzer Action class that performs analysis on text files.
 * It uses Struts framework to handle HTTP requests.
 */
public class TextFileAnalyzer extends DispatchAction {

    private static final String CONTENT_KEY = "content";
    private static final String COUNT_KEY = "count";
    private static final String ERROR_KEY = "error";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
                                 throws Exception {
        try {
            String filePath = request.getParameter("filePath");
            if(filePath == null || filePath.isEmpty()) {
                request.setAttribute(ERROR_KEY, "File path is required.");
                return mapping.getInputForward();
            }

            Map<String, Object> analysisResult = analyzeTextFile(filePath);
            request.setAttribute(CONTENT_KEY, analysisResult.get(CONTENT_KEY));
            request.setAttribute(COUNT_KEY, analysisResult.get(COUNT_KEY));

            return mapping.findForward("success");
        } catch (IOException e) {
            request.setAttribute(ERROR_KEY, "Error reading file: " + e.getMessage());
            return mapping.getInputForward();
        }
    }

    /**
     * Analyzes the content of a text file and returns a map with the analyzed content
     * and the word count.
     *
     * @param filePath the path to the text file
     * @return a map with content and word count
     * @throws IOException if an I/O error occurs
     */
    private Map<String, Object> analyzeTextFile(String filePath) throws IOException {
        Map<String, Object> result = new HashMap<>();
        result.put(CONTENT_KEY, "");
        result.put(COUNT_KEY, 0);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Update content with the new line
                result.put(CONTENT_KEY, result.get(CONTENT_KEY) + line + "
");
                // Split line into words and count them
                StringTokenizer tokenizer = new StringTokenizer(line);
                while (tokenizer.hasMoreTokens()) {
                    result.put(COUNT_KEY, (Integer) result.get(COUNT_KEY) + 1);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error analyzing file: " + filePath, e);
        }

        return result;
    }
}
