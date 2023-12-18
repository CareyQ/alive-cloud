package com.careyq.alive.module.infra.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.module.infra.dto.LoginLogDTO;
import com.careyq.alive.module.infra.dto.LoginLogPageDTO;
import com.careyq.alive.module.infra.entity.LoginLog;
import com.careyq.alive.module.infra.vo.LoginLogVO;
import com.careyq.alive.mybatis.core.service.IServiceX;

/**
 * 登录日志服务
 *
 * @author CareyQ
 */
public interface LoginLogService extends IServiceX<LoginLog> {

    /**
     * 保存登录日志
     *
     * @param dto 登录日志
     */
    void createLoginLog(LoginLogDTO dto);

    /**
     * 查询登录日志分页
     *
     * @param dto 分页请求筛选项
     * @return 日志分页
     */
    IPage<LoginLogVO> getLoginLogPage(LoginLogPageDTO dto);
}
