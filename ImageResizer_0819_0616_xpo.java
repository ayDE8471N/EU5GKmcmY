// 代码生成时间: 2025-08-19 06:16:12
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class ImageResizer extends ActionForm {

    // 属性定义
    private FormFile[] files;
    private int width = 300; // 默认宽度
    private int height = 300; // 默认高度

    // 获取上传的文件数组
    public FormFile[] getFiles() {
        return files;
    }

    // 设置上传的文件数组
    public void setFiles(FormFile[] files) {
        this.files = files;
    }

    // 获取宽度
    public int getWidth() {
        return width;
    }

    // 设置宽度
    public void setWidth(int width) {
        this.width = width;
    }

    // 获取高度
    public int getHeight() {
        return height;
    }

    // 设置高度
    public void setHeight(int height) {
        this.height = height;
    }

    // 方法用于调整图片尺寸
    public void resizeImages() {
        for (int i = 0; files != null && i < files.length; i++) {
            try {
                // 读取图片
                BufferedImage sourceImage = ImageIO.read(files[i].getInputStream());

                // 创建目标图片缓冲区
                BufferedImage resizedImage = new BufferedImage(width, height, sourceImage.getType());

                // 调整图片尺寸
                resizedImage.getGraphics().drawImage(sourceImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH), 0, 0, null);

                // 创建新的图片文件
                File resizeImageFile = new File("resized_" + files[i].getFileName());
                boolean isSaved = ImageIO.write(resizedImage, "JPEG", resizeImageFile);
                if (!isSaved) {
                    throw new IOException("Failed to save the resized image.");
                }
            } catch (IOException e) {
                // 错误处理
                e.printStackTrace();
            }
        }
    }

    // ActionMapping中调用此方法以实现图片尺寸调整
    public ActionForward resize(ActionMapping mapping, ActionForm form,
                              javax.servlet.http.HttpServletRequest request,
                              javax.servlet.http.HttpServletResponse response)
            throws Exception {
        ImageResizer imageResizer = (ImageResizer) form;
        imageResizer.resizeImages();
        return mapping.findForward("success");
    }
}
