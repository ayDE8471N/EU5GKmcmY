// 代码生成时间: 2025-09-10 19:58:01
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

// 哈希值计算工具类
public class HashCalculator {

    // 计算哈希值的方法
    public String calculateHash(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5"); // 使用MD5算法
        byte[] messageDigest = md.digest(input.getBytes("utf-8"));
        // 将字节转换为十六进制字符串表示
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // 执行哈希值计算的主方法
    public static void main(String[] args) {
        try {
            HashCalculator calculator = new HashCalculator();
            // 假设从Struts2的Action中获取输入
            HttpServletRequest request = ServletActionContext.getRequest();
            String input = request.getParameter("input");
            String hash = calculator.calculateHash(input);
            System.out.println("Input: " + input);
            System.out.println("Hash: " + hash);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
