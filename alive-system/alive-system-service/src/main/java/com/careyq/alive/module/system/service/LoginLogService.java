package com.careyq.alive.module.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.mybatis.core.service.IServiceX;
import com.careyq.alive.module.system.dto.LoginLogPageDTO;
import com.careyq.alive.module.system.entity.LoginLog;
import com.careyq.alive.module.system.vo.LoginLogVO;

/**
 * 登录日志服务
 *
 * @author CareyQ
 */
public interface LoginLogService extends IServiceX<LoginLog> {

    /**
     * 保存登录日志
     *
     * @param loginLog 登录日志
     */
    void saveLoginLog(LoginLog loginLog);

    /**
     * 查询登录日志分页
     *
     * @param dto 分页请求筛选项
     * @return 日志分页
     */
    IPage<LoginLogVO> getLoginLogPage(LoginLogPageDTO dto);
}
