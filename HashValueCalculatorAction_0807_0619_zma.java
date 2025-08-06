// 代码生成时间: 2025-08-07 06:19:13
<!DOCTYPE struts-config PUBLIC "-//Apache Software Foundation//DTD Struts Configuration 1.2//EN" "http://struts.apache.org/dtds/struts-config_1_2.dtd">
<struts-config>
    <form-beans>
        <form-bean name="hashForm" type="com.example.action.HashForm" />
    </form-beans>
    <action-mappings>
        <action path="/hashAction" type="com.example.action.HashValueCalculatorAction" name="hashForm" scope="request" input="/hashValueInput.jsp" validate="true">
            <exception handler="com.example.action.HashException" bundle="messages" key="error.hash" />
        </action>
    </action-mappings>
</struts-config>

package com.example.action;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;

public class HashValueCalculatorAction extends org.apache.struts.action.Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request) throws Exception {
        HashForm hashForm = (HashForm) form;
        String input = hashForm.getInput();

        // Calculate hash value
        String hashValue = calculateHash(input);

        // Store hash value in request scope
        request.setAttribute("hashValue", hashValue);

        return mapping.findForward("success");
    }

    private String calculateHash(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // Use SHA-256 algorithm for hashing
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = md.digest(input.getBytes("UTF-8"));

        // Convert byte array to hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}

package com.example.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;

public class HashForm extends ActionForm {
    private String input;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        if (input == null || input.trim().isEmpty()) {
            errors.add("input", new ActionError("error.required", "Input is required"));
        }

        return errors;
    }
}

package com.example.action;

import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts.action.ActionErrors;

public class HashException extends ExceptionHandler {
    public void execute(ExceptionConfig aec, HttpServletRequest request, String attributeName) {
        try {
            Exception e = aec.getExceptionInstance();
            request.setAttribute("error", e.getMessage());
            // Log exception details here
        } catch (Exception e) {
            // Handle exception handling exception
        }
    }
}

<!-- hashValueInput.jsp -->
<%@ taglib prefix="s" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="sx" uri="http://struts.apache.org/tags-x" %>
<%@ taglib prefix="sj" uri="http://struts.apache.org/tags-jstl" %>
<html>
<body>
    <h1>Hash Value Calculator</h1>
    <s:form action="/hashAction" method="post" validate="true" enctype="multipart/form-data">
        <s:textfield name="input" label="Input" />
        <s:submit value="Calculate Hash" />
        <s:actionerror />
        <s:fielderror fieldName="input" />
    </s:form>
    <s:if test="%{#request.hashValue != null}">
        <p>Hash Value: <s:property value="%{#request.hashValue}" /></p>
    </s:if>
</body>
</html>