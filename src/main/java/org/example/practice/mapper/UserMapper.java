package org.example.practice.mapper;

import org.apache.ibatis.annotations.*;
import org.example.practice.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM Users")
    List<User> findAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(int id);

    @Select("SELECT * FROM users WHERE email = #{email}")
    User findByEmail(String email);

    @Insert("INSERT INTO users(email) VALUES(#{email})")
    void insert(User user);

    @Update("UPDATE users SET email = #{email}, name = #{name} WHERE id = #{id}")
    int updateById(@Param("id") int id, @Param("email") String email, @Param("name") String name);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void delete(int id);
}
