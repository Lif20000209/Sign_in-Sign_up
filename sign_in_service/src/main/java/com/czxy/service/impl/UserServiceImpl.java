package com.czxy.service.impl;

import com.czxy.dao.UserMapper;
import com.czxy.domain.User;
import com.czxy.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 登陆
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        List<User> userList = userMapper.findUserByUser(user);
        if (userList.isEmpty()) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @Override
    public Boolean register(User user) {
        List<User> users = userMapper.selectAll();
        for (User u : users) {
            if (user.getPhone().equals(u.getPhone()) && user.getEmail().equals(u.getEmail()) && !user.getEmail().equals(u.getEmail().endsWith("@qq.com"))) {
                return false;
            }
        }
        userMapper.insert(user);
        return true;
    }

    /**
     * 校验手机号
     * @param phone
     * @return
     */
    @Override
    public Boolean findUserByPhone(String phone) {

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("phone", phone);
        List<User> users = userMapper.selectByExample(example);
        if (users.isEmpty() && phone.length() == 11) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 校验邮箱
     * @param email
     * @return
     */
    @Override
    public Boolean findUserByEmail(String email) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("email", email);
        List<User> users = userMapper.selectByExample(example);
        if (users.isEmpty() && email.endsWith("@qq.com")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查询性别
     * @return
     */
    @Override
    public List<User> findUserBySex() {
        return userMapper.findUserBySex();
    }


}
