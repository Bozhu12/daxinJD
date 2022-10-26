package com.sans.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sans.exception.BusinessException;
import com.sans.model.entity.Users;
import com.sans.mapper.UsersMapper;
import com.sans.model.enums.StateCode;
import com.sans.service.UsersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.sans.constant.UserConstant.USER_LOGIN_STATE;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sans
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Resource
    private UsersMapper usersMapper;

    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "sans";

    @Override
    public long userRegister(Users user) {
        synchronized (user.getUserName().intern()) {
            // 账户不能重复
            Long count = usersMapper.selectCount(new QueryWrapper<Users>().eq("user_name", user.getUserName()));
            if (count > 0) {
                throw new BusinessException(StateCode.PARAMS_ERROR, "账号重复");
            }

            // 加密
            String encryptPassword = DigestUtils.md5DigestAsHex((SALT + user.getUserPassword()).getBytes());
            user.setUserPassword(encryptPassword);
            // 插入数据
            boolean saveResult = this.save(user);
            if (!saveResult) {
                throw new BusinessException(StateCode.SYSTEM_ERROR, "注册失败，数据库错误");
            }
            return user.getId();
        }
    }

    @Override
    public Users userLogin(String userAccount, String userPassword, HttpServletRequest request){
        // 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(StateCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 2) {
            throw new BusinessException(StateCode.PARAMS_ERROR, "账号错误");
        }
        if (userPassword.length() < 5) {
            throw new BusinessException(StateCode.PARAMS_ERROR, "密码错误");
        }
        // 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 查询用户是否存在
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userAccount);
        queryWrapper.eq("user_password", encryptPassword);
        Users user = usersMapper.selectOne(queryWrapper);
        QueryWrapper<Users> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("user_phone", userAccount);
        queryWrapper2.eq("user_password", encryptPassword);
        Users user2 = usersMapper.selectOne(queryWrapper2);
        // 用户不存在
        if (user == null && user2 == null) {
            throw new BusinessException(StateCode.PARAMS_ERROR, "用户不存在或密码错误");
        }
        if (user == null) user = user2;
        // 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, user);
        return user;
    }




}
