package com.careyq.alive.module.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 发送短信验证码
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 发送短信验证码 DTO")
public class SendSmsDTO {


    @Length(min = 4, max = 16, message = "账号长度为 4-16 位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号格式为数字以及字母")
    @NotBlank(message = "登录账号不能为空")
    @Schema(description = "账号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String mobile;

    @Length(min = 6, max = 16, message = "密码长度为 6-16 位")
    @NotBlank(message = "登录账号不能为空")
    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
