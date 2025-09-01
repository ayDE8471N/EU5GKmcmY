// 代码生成时间: 2025-09-01 22:24:48
package com.example.struts.formvalidator;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.DynaActionForm;
import java.util.Iterator;

/**
 * FormValidator 是一个 Struts 表单验证器，用于验证表单数据是否符合特定的规则。
 */
public class FormValidator extends org.apache.struts.action.ValidatorAction {

    /**
     * 执行表单验证
     * 
     * @param mapping ActionMapping 对象，包含与此 Action 相关联的信息
     * @param form 表单数据
     * @return ActionErrors 对象，包含验证过程中发现的所有错误
     */
    public ActionErrors validate(ActionMapping mapping, ActionForm form) {
        ActionErrors errors = new ActionErrors();
        DynaActionForm dynaForm = (DynaActionForm) form;
        
        // 验证字段: username
        String username = dynaForm.getString("username");
        if (username == null || username.trim().length() == 0) {
            errors.add("username", new ActionError("error.username.required"));
        } else if (username.length() < 5 || username.length() > 20) {
            errors.add("username", new ActionError("error.username.length"));
        }
        
        // 验证字段: email
        String email = dynaForm.getString("email");
        if (email == null || email.trim().length() == 0) {
            errors.add("email", new ActionError("error.email.required"));
        } else if (!email.contains("@") || !email.contains(".")) {
            errors.add("email", new ActionError("error.email.invalid"));
        }
        
        // 如果存在错误，返回错误对象
        if (!errors.isEmpty()) {
            return errors;
        }
        
        // 没有发现错误，返回 null
        return null;
    }
}
