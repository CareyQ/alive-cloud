package com.careyq.alive.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.core.domain.EntryVO;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.system.dto.UserDTO;
import com.careyq.alive.system.dto.UserPageDTO;
import com.careyq.alive.system.entity.Dept;
import com.careyq.alive.system.entity.User;
import com.careyq.alive.system.mapper.UserMapper;
import com.careyq.alive.system.service.DeptService;
import com.careyq.alive.system.service.UserService;
import com.careyq.alive.system.vo.UserPageVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;

import static com.careyq.alive.system.constants.SystemResultCode.*;

/**
 * 系统用户服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final DeptService deptService;

    @Override
    public boolean mobileIsExist(String mobile, Long id) {
        if (StrUtil.isBlank(mobile)) {
            return false;
        }
        return this.lambdaQuery()
                .eq(User::getMobile, mobile)
                .ne(id != null, User::getId, id)
                .exists();
    }

    @Override
    public boolean usernameIsExist(String username, Long id) {
        if (StrUtil.isBlank(username)) {
            return false;
        }
        return this.lambdaQuery()
                .eq(User::getUsername, username)
                .ne(id != null, User::getId, id)
                .exists();
    }

    @Override
    public boolean emailIsExist(String email, Long id) {
        if (StrUtil.isBlank(email)) {
            return false;
        }
        return this.lambdaQuery()
                .eq(User::getEmail, email)
                .ne(id != null, User::getId, id)
                .exists();
    }

    @Override
    public User getByUsername(String username) {
        return this.lambdaQuery()
                .eq(User::getUsername, username)
                .one();
    }

    @Override
    public String encodePassword(String password) {
        return SecureUtil.sha256(password);
    }

    @Override
    public boolean isPasswordMatch(String password, String encodedPassword) {
        return SecureUtil.sha256(password).equals(encodedPassword);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveUser(UserDTO dto) {
        this.validUser(dto);
        this.checkUserExists(dto.getId());
        User user = BeanUtil.copyProperties(dto, User.class);
        this.saveOrUpdate(user);
        return user.getId();
    }

    /**
     * 校验用户是否存在
     *
     * @param id 用户 ID
     * @return 用户
     */
    private User checkUserExists(Long id) {
        if (id == null) {
            return null;
        }
        User user = this.getById(id);
        if (user == null) {
            throw new CustomException(USER_NOT_EXISTS);
        }
        return user;
    }

    /**
     * 校验用户名、手机、邮件是否重复
     *
     * @param dto 用户信息
     */
    private void validUser(UserDTO dto) {
        if (this.usernameIsExist(dto.getUsername(), dto.getId())) {
            throw new CustomException(USER_NAME_DUPLICATE);
        }
        if (this.emailIsExist(dto.getEmail(), dto.getId())) {
            throw new CustomException(USER_EMAIL_DUPLICATE);
        }
        if (this.mobileIsExist(dto.getMobile(), dto.getId())) {
            throw new CustomException(USER_MOBILE_DUPLICATE);
        }
    }

    @Override
    public IPage<UserPageVO> getUserPage(UserPageDTO dto) {
        IPage<User> page = this.lambdaQuery()
                .like(StrUtil.isNotBlank(dto.getUsername()), User::getUsername, dto.getUsername())
                .eq(ObjectUtil.isNotNull(dto.getStatus()), User::getStatus, dto.getStatus())
                .eq(ObjectUtil.isNotNull(dto.getDeptId()), User::getDeptId, dto.getDeptId())
                .eq(ObjectUtil.isNotNull(dto.getGender()), User::getGender, dto.getGender())
                .eq(ObjectUtil.isNotNull(dto.getMobile()), User::getMobile, dto.getMobile())
                .between(ObjectUtil.isAllNotEmpty(dto.getCreateStartDate(), dto.getCreateEndDate()), User::getCreateTime, dto.getCreateStartDate(), dto.getCreateEndDate())
                .orderByDesc(User::getId)
                .page(new Page<>(dto.getCurrent(), dto.getSize()));
        if (CollUtil.isEmpty(page.getRecords())) {
            return new Page<>();
        }
        Set<Long> deptIds = CollUtils.convertSet(page.getRecords(), User::getDeptId);
        Map<Long, Dept> deptMap = deptService.getDeptMap(deptIds);
        return page.convert(e -> {
            Dept dept = deptMap.getOrDefault(e.getDeptId(), new Dept());
            UserPageVO vo = new UserPageVO();
            vo.setId(e.getId())
                    .setUsername(e.getUsername())
                    .setNickname(e.getNickname())
                    .setDept(new EntryVO(dept.getId(), dept.getName()))
                    .setGender(e.getGender())
                    .setEmail(e.getEmail())
                    .setMobile(e.getMobile())
                    .setStatus(e.getStatus())
                    .setCreateTime(e.getCreateTime());
            return vo;
        });
    }

    @Override
    public void changeStatus(Long id, Integer status) {
        User user = this.checkUserExists(id);
        if (user.getStatus().equals(status)) {
            throw new CustomException(USER_STATUS_ALREADY);
        }
        this.lambdaUpdate()
                .set(User::getStatus, status)
                .eq(User::getId, id)
                .update();
    }

    @Override
    public UserDTO getUserDetail(Long id) {
        User user = this.checkUserExists(id);
        return BeanUtil.copyProperties(user, UserDTO.class);
    }

    @Override
    public void resetPassword(Long id, String password) {
        this.checkUserExists(id);
        this.lambdaUpdate()
                .set(User::getPassword, this.encodePassword(password))
                .eq(User::getId, id)
                .update();
    }
}
