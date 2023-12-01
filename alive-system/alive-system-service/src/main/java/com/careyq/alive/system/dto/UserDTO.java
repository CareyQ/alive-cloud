package com.careyq.alive.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

/**
 * 用户信息 DTO
 *
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
@Schema(description = "管理后台 - 用户信息 DTO")
public class UserDTO {

    @Schema(description = "主键")
    private Long id;

    @Length(max = 20, message = "用户名长度不能超过{max}位")
    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名")
    private String username;

    @Length(max = 20, message = "用户昵称长度不能超过{max}位")
    @NotBlank(message = "用户昵称不能为空")
    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "部门")
    private Long deptId;

    @Schema(description = "岗位")
    private Set<Long> postIds;

    @Schema(description = "性别")
    private Integer gender;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "手机号")
    private String mobile;

}
