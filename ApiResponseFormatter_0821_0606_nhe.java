// 代码生成时间: 2025-08-21 06:06:28
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import java.util.HashMap;
import java.util.Map;

/**
 * ApiResponseFormatter 是一个 Struts2 Action 类，用于格式化 API 响应。
 * 它实现了基本的错误处理以及提供了一个简单的接口来输出
 * 标准化的成功或错误响应。
 */
@Results({
    @Result(name = "success", type = "json"),
    @Result(name = "error", type = "json\)
})
public class ApiResponseFormatter extends ActionSupport {

    // 定义响应状态枚举
    public enum ResponseStatus {
        SUCCESS,
        ERROR
    }

    // 响应消息模板
    private static final String RESPONSE_TEMPLATE = "{"status":"%s","message":"%s"}";

    // 响应状态和消息
    private String status;
    private String message;

    // 默认构造函数
    public ApiResponseFormatter() {
    }

    // 设置状态和消息的公共方法
    public void setResponse(ResponseStatus status, String message) {
        this.status = status.name();
        this.message = message;
    }

    // 格式化成功的响应
    public void formatSuccess() {
        setResponse(ResponseStatus.SUCCESS, "Operation successful");
    }

    // 格式化错误的响应
    public void formatError(String message) {
        setResponse(ResponseStatus.ERROR, message);
    }

    // Struts2 Action 方法，返回成功响应
    public String success() {
        formatSuccess();
        return SUCCESS;
    }

    // Struts2 Action 方法，返回错误响应
    public String error() {
        String errorMessage = "An error occurred"; // 默认错误消息
        formatError(errorMessage);
        return ERROR;
    }

    // Getter 和 Setter 方法
    public String getStatus() {
        return String.format(RESPONSE_TEMPLATE, status, message);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
