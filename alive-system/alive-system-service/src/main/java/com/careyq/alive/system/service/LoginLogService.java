package com.careyq.alive.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.alive.system.dto.LoginLogPageDTO;
import com.careyq.alive.system.entity.LoginLog;
import com.careyq.alive.system.vo.LoginLogVO;

/**
 * 登录日志服务
 *
 * @author CareyQ
 */
public interface LoginLogService extends IService<LoginLog> {

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
