// 代码生成时间: 2025-09-22 14:11:25
package com.example.hash;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

// 哈希值计算工具类
public class HashValueCalculator extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        try {
            // 从请求中获取待计算哈希的字符串
            String inputString = request.getParameter("inputString");

            // 计算哈希值
            String hashValue = computeHash(inputString);

            // 将计算结果设置到请求属性中，以便在视图中显示
            request.setAttribute("hashValue", hashValue);

            // 转发到结果显示页面
            return mapping.findForward("success");
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            // 异常处理：将错误信息设置到请求属性中
            request.setAttribute("errorMessage", e.getMessage());

            // 转发到错误显示页面
            return mapping.findForward("error");
        }
    }

    // 计算哈希值的方法
    private String computeHash(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        // 使用SHA-256算法
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // 将输入字符串转换为字节数组
        byte[] hash = digest.digest(input.getBytes("UTF-8"));

        // 将字节数组转换为十六进制字符串
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        // 返回十六进制字符串表示的哈希值
        return hexString.toString();
    }
}