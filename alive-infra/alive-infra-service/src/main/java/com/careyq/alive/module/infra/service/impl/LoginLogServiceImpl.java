package com.careyq.alive.module.infra.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.careyq.alive.module.infra.convert.LogConvert;
import com.careyq.alive.module.infra.dto.LoginLogDTO;
import com.careyq.alive.module.infra.dto.LoginLogPageDTO;
import com.careyq.alive.module.infra.entity.LoginLog;
import com.careyq.alive.module.infra.mapper.LoginLogMapper;
import com.careyq.alive.module.infra.service.LoginLogService;
import com.careyq.alive.module.infra.vo.LoginLogVO;
import com.careyq.alive.mybatis.core.service.impl.ServiceImplX;
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
    public void createLoginLog(LoginLogDTO dto) {
        if (dto != null) {
            dto.setIpInfo(dto.getIpInfo().trim());
            LoginLog loginLog = LogConvert.INSTANCE.convert(dto);
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
