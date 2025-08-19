// 代码生成时间: 2025-08-19 22:00:25
// FormValidator.java
// 这是一个基于Java和Struts框架的表单数据验证器的实现。

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.IntegerValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

// 定义表单类，包含表单字段
@ParentPackage("default")
@Namespace("/form")
@Action(value = "validateForm", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/WEB-INF/views/formSuccess.jsp"),
        @Result(name = ActionSupport.INPUT, location = "/WEB-INF/views/formInput.jsp")
    })
public class FormValidator extends ActionSupport {

    // 表单字段
    private String username;
    private int age;

    // 用户名的getter和setter方法
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // 年龄的getter和setter方法
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // 验证方法
    private boolean validate() {
        // 验证用户名是否为空
        if (username == null || username.trim().isEmpty()) {
            addFieldError("username", "Username is required.");
            return false;
        }

        // 验证年龄是否是一个有效的整数
        if (age <= 0 || age > 100) {
            addFieldError("age", "Age must be a positive integer between 1 and 100.");
            return false;
        }

        return true;
    }

    // Struts2的execute方法，执行表单验证
    @Override
    public String execute() {
        if (validate()) {
            return SUCCESS;
        } else {
            return INPUT;
        }
    }
}
