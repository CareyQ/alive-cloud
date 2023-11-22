package com.careyq.alive.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 登录
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 账号密码登录 DTO")
public class LoginDTO {


    @Length(min = 4, max = 16, message = "账号长度为 4-16 位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号格式为数字以及字母")
    @NotBlank(message = "登录账号不能为空")
    @Schema(description = "账号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Length(min = 6, max = 16, message = "密码长度为 6-16 位")
    @NotBlank(message = "登录账号不能为空")
    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
