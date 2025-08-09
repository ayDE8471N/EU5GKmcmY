// 代码生成时间: 2025-08-09 15:46:31
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * ImageResizeBatchProcessor is a Struts action class designed to batch process image resizing.
 * This class is responsible for resizing multiple images to specified dimensions.
 */
public class ImageResizeBatchProcessor extends org.apache.struts.action.Action {

    /*
     * Method execute is called when the action is triggered.
     * It processes the image resizing based on the provided form data.
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm Bean for this request
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // Retrieve the directory path and new dimensions from the request
        String directoryPath = request.getParameter("directoryPath");
        int newWidth = Integer.parseInt(request.getParameter("newWidth"));
        int newHeight = Integer.parseInt(request.getParameter("newHeight"));

        // Check if the directory path is provided
        if (directoryPath == null || directoryPath.isEmpty()) {
            ActionMessages errors = new ActionMessages();
            errors.add("error", new ActionMessage("error.directoryPath"));
            saveErrors(request, errors);
            return mapping.findForward("input");
        }

        // Check if the new dimensions are valid
        if (newWidth <= 0 || newHeight <= 0) {
            ActionMessages errors = new ActionMessages();
            errors.add("error", new ActionMessage("error.dimensions"));
            saveErrors(request, errors);
            return mapping.findForward("input");
        }

        // Process the image resizing
        try {
            resizeImages(directoryPath, newWidth, newHeight);
            request.setAttribute("message", "Images resized successfully!");
        } catch (Exception e) {
            ActionMessages errors = new ActionMessages();
            errors.add("error", new ActionMessage("error.resize"));
            saveErrors(request, errors);
            return mapping.findForward("input");
        }

        return mapping.findForward("success");
    }

    /*
     * resizeImages resizes all images in the specified directory to the new dimensions.
     * @param directoryPath The path to the directory containing images to resize
     * @param newWidth The new width for the images
     * @param newHeight The new height for the images
     * @throws IOException If an I/O error occurs during processing
     */
    private void resizeImages(String directoryPath, int newWidth, int newHeight) throws IOException {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files == null) {
            throw new IOException("Directory not found or inaccessible: " + directoryPath);
        }

        for (File file : files) {
            if (file.isFile() && isImage(file)) {
                BufferedImage originalImage = ImageIO.read(file);
                BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());

                // Perform the resize operation
                resizedImage.getGraphics().drawImage(originalImage, 0, 0, newWidth, newHeight, null);

                // Save the resized image back to the file
                ImageIO.write(resizedImage, getFileExtension(file), file);
            }
        }
    }

    /*
     * isImage checks if the file is an image based on its extension.
     * @param file The file to check
     * @return true if the file is an image, false otherwise
     */
    private boolean isImage(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".gif");
    }

    /*
     * getFileExtension retrieves the file extension of the given file.
     * @param file The file to get the extension from
     * @return The file extension
     */
    private String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return "";
    }
}