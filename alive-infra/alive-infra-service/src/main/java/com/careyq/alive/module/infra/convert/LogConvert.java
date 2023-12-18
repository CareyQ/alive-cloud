package com.careyq.alive.module.infra.convert;

import com.careyq.alive.module.infra.dto.ErrorLogDTO;
import com.careyq.alive.module.infra.dto.LoginLogDTO;
import com.careyq.alive.module.infra.entity.ErrorLog;
import com.careyq.alive.module.infra.entity.LoginLog;
import com.careyq.alive.module.infra.entity.OperateLog;
import com.careyq.alive.module.infra.vo.LoginLogVO;
import com.careyq.alive.module.infra.dto.OperateLogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 日志相关转换器
 *
 * @author CareyQ
 */
@Mapper
public interface LogConvert {

    LogConvert INSTANCE = Mappers.getMapper(LogConvert.class);

    /**
     * 日志转换为 VO
     *
     * @param loginLog LoginLog
     * @return VO
     */
    @Mapping(target = "loginTime", source = "createTime")
    LoginLogVO convert(LoginLog loginLog);

    /**
     * 转换为登录日志实体
     *
     * @param dto LoginLogDTO
     * @return LoginLog
     */
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "isDel", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    LoginLog convert(LoginLogDTO dto);

    /**
     * 操作日志转换
     *
     * @param dto DTO
     * @return 实体
     */
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "isDel", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    OperateLog convert(OperateLogDTO dto);

    /**
     * 错误日志转换
     *
     * @param dto DTO
     * @return 实体
     */
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "processUserId", ignore = true)
    @Mapping(target = "processTime", ignore = true)
    @Mapping(target = "processStatus", ignore = true)
    @Mapping(target = "isDel", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    ErrorLog convert(ErrorLogDTO dto);
}
