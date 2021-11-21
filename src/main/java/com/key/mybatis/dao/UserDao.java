package com.key.mybatis.dao;

import com.key.mybatis.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserDao接口
 *
 * @author Key
 * @date 2021/10/21/21:22
 **/
public interface UserDao {

    /**
     * 查询所有用户信息
     * @return 返回用户集合
     */
    List<User> getAllUsers();

    /**
     * 根据if标签条件查询用户
     * @param user 用户对象
     * @return 返回结果集
     */
    List<User> getUsersByIfCondition(User user);

    /**
     * 根据choose标签条件查询用户记录
     * @param user 用户对象
     * @return 返回集合
     */
    List<User> getUsersByChooseCondition(User user);

    /**
     * 使用set标签更新用户信息
     * @param user 用户对象
     * @return 返回影响行数
     */
    int updateUserBySetCondition(User user);

    /**
     * 查询出指定id集合的对应用户信息
     * @param ids id集合
     * @return 返回对象集合
     */
    List<User> getUsersByForeachCondition(@Param("ids") List<Integer> ids);

    /**
     * 批量插入多条用户记录
     * @param users 用户集合
     * @return 返回影响行数
     */
    int batchInsertUsersByForeach(@Param("users") List<User> users);

    /**
     * 使用内置参数1.0
     * @return 返回集合
     */
    List<User> listUsersByBuiltInParam();

    /**
     * 使用内置参数2.0
     * @param userid 用户id
     * @param username 用户名
     * @return 返回用户对象
     */
    User getUserByInnerParam(Integer userid, String username);

    /**
     * 根据姓名模糊查询
     * @param nameKey 姓名的关键字
     * @return 返回集合
     */
    List<User> listUsersByBind(String nameKey);

    /**
     * 使用sql标签和include标签插入数据
     * @param user 用户对象
     * @return 返回影响行数
     */
    int insertUserBySqlAndInclude(User user);

    /**
     * 根据id查询用户信息
     * @param userId 用户id
     * @return 返回用户对象
     */
    User getUserById(Integer userId);
}