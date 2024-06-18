package org.example.practice.service;

import org.example.practice.entity.User;
import org.example.practice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    public User getUserById(int id) {
        return userMapper.findById(id);
    }

    public User getUserByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    public User createUser(User user) {
        userMapper.insert(user);
        return user;
    }

    public User updateUser(int id, String email, String name) {
        // 获取用户对象
        User user = userMapper.findById(id);
        // 检查用户是否存在
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        // 检查更新的内容是否有变化
        if (user.getEmail().equals(email) && user.getName().equals(name)) {
            throw new RuntimeException("No changes detected");
        }
        // 更新用户对象的属性
        user.setEmail(email);
        user.setName(name);
        // 更新数据库中的记录
        int rowsAffected = userMapper.updateById(id, email, name);
        if (rowsAffected == 0) {
            throw new RuntimeException("Failed to update user");
        }
        // 返回更新后的用户对象
        return user;
    }
    public void deleteUser(int id) {
        userMapper.delete(id);
    }
}
