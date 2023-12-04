package com.careyq.alive.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Override
    public void saveLoginLog(LoginLog loginLog) {
        if (loginLog != null) {
            loginLog.setIpInfo(loginLog.getIpInfo().trim());
            this.save(loginLog);
        }
    }

    @Override
    public IPage<LoginLogVO> getLoginLogPage(LoginLogPageDTO dto) {
        IPage<LoginLog> page = this.lambdaQuery()
                .like(StrUtil.isNotBlank(dto.getUsername()), LoginLog::getUsername, dto.getUsername())
                .eq(StrUtil.isNotBlank(dto.getIp()), LoginLog::getIp, dto.getIp())
                .between(ObjectUtil.isAllNotEmpty(dto.getStartDate(), dto.getEndDate()), LoginLog::getCreateTime, dto.getStartDate().atStartOfDay(), dto.getEndDate().plusDays(1).atStartOfDay())
                .orderByDesc(LoginLog::getIp)
                .page(new Page<>(dto.getCurrent(), dto.getSize()));
        return page.convert(e -> {
            LoginLogVO vo = new LoginLogVO();
            vo.setType(e.getType())
                    .setUsername(e.getUsername())
                    .setResult(e.getResult())
                    .setIp(e.getIp())
                    .setIpInfo(e.getIpInfo())
                    .setDevice(e.getDevice())
                    .setLoginTime(e.getCreateTime());
            return vo;
        });
    }
}
