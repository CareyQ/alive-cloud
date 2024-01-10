package com.careyq.alive.module.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * 用户信息 VO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 用户信息 VO")
public class UserVO {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户昵称")
    private String nickname;

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
