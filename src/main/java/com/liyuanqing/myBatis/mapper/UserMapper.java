package com.liyuanqing.myBatis.mapper;

import com.liyuanqing.myBatis.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert sys_user (username,phone) value (#{username},#{phone})")
    @Options(useGeneratedKeys = true)
    int insert(User user);

    @Select("select * from sys_user where id = #{id}")
    User selectById(Long id);

    @Select("select * from sys_user")
    List<User> selectList();
}
