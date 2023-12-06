package com.careyq.alive.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.careyq.alive.mybatis.core.service.impl.ServiceImplX;
import com.careyq.alive.system.convert.LogConvert;
import com.careyq.alive.system.dto.LoginLogPageDTO;
import com.careyq.alive.system.entity.LoginLog;
import com.careyq.alive.system.mapper.LoginLogMapper;
import com.careyq.alive.system.service.LoginLogService;
import com.careyq.alive.system.vo.LoginLogVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 登录日志服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class LoginLogServiceImpl extends ServiceImplX<LoginLogMapper, LoginLog> implements LoginLogService {

    @Override
    public void saveLoginLog(LoginLog loginLog) {
        if (loginLog != null) {
            loginLog.setIpInfo(loginLog.getIpInfo().trim());
            this.save(loginLog);
        }
    }

    @Override
    public IPage<LoginLogVO> getLoginLogPage(LoginLogPageDTO dto) {
        IPage<LoginLog> page = this.lambdaQueryX()
                .likeIfPresent(LoginLog::getUsername, dto.getUsername())
                .eqIfPresent(LoginLog::getIp, dto.getIp())
                .dateTimeBetween(LoginLog::getCreateTime, dto.getStartDate(), dto.getEndDate())
                .orderByDesc(LoginLog::getId)
                .page(new Page<>(dto.getCurrent(), dto.getSize()));
        return page.convert(LogConvert.INSTANCE::convert);
    }
}
