package com.careyq.alive.module.infra.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.module.infra.dto.*;
import com.careyq.alive.module.infra.vo.*;

/**
 * 日志服务
 *
 * @author CareyQ
 */
public interface LogService {

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
    IPage<LoginLogPageVO> getLoginLogPage(LoginLogPageDTO dto);

    /**
     * 创建操作日志
     *
     * @param dto 操作日志
     */
    void createOperateLog(OperateLogDTO dto);

    /**
     * 创建错误日志
     *
     * @param dto 错误日志
     */
    void createErrorLog(ErrorLogDTO dto);

    /**
     * 查询操作日志分页
     *
     * @param dto 分页请求筛选项
     * @return 日志分页
     */
    IPage<OperateLogPageVO> getOperateLogPage(OperateLogPageDTO dto);

    /**
     * 查询操作日志详情
     *
     * @param id 日志编号
     * @return 日志详情
     */
    OperateLogVO getOperateLogDetail(Long id);

    /**
     * 查询错误日志分页
     *
     * @param dto 分页请求筛选项
     * @return 日志分页
     */
    IPage<ErrorLogPageVO> getErrorLogPage(ErrorLogPageDTO dto);

    /**
     * 查询错误日志详情
     *
     * @param id 日志编号
     * @return 日志详情
     */
    ErrorLogVO getErrorLogDetail(Long id);

    /**
     * 更新错误日志处理状态
     *
     * @param id            日志编号
     * @param processStatus 处理状态
     */
    void updateErrorProcessStatus(Long id, Integer processStatus);
}
