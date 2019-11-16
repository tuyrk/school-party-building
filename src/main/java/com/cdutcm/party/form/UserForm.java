package com.cdutcm.party.form;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 15:59 星期日
 * Description:
 */
@Data
public class UserForm {
    /* 用户名 */
    @NotEmpty(message = "用户名不能为空")
    private String username;

    /* 用户手机号 */
    @NotEmpty(message = "手机号不能为空")
    private String phone;

    /* 用户邮箱号 */
    @Email
    @NotEmpty(message = "邮箱不能为空")
    private String mail;

    /* 用户密码 */
    @NotEmpty(message = "密码不能为空")
    private String password;
}