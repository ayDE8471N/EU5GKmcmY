// 代码生成时间: 2025-09-08 01:02:49
package com.example.struts.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 主题切换功能Action类
@Namespace("/theme")
public class ThemeChangeAction extends ActionSupport {
    // 定义主题属性
    private String theme;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    // 执行主题切换的方法
    @Action(value = "change", results = {
            @Result(name = SUCCESS, type = "redirectAction", params = {"namespace", "/", "actionName", "index"}),
            @Result(name = ERROR, type = "dispatcher", params = {"/WEB-INF/views/error.jsp"})})
    public String changeTheme() {
        try {
            // 获取HTTP会话对象
            HttpSession session = ServletActionContext.getRequest().getSession();
            // 存储主题到会话中
            session.setAttribute("theme", theme);
            // 重定向到首页
            return SUCCESS;
        } catch (Exception e) {
            // 异常处理
            addActionError("Error changing theme: " + e.getMessage());
            return ERROR;
        }
    }
}
