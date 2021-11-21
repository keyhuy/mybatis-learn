package com.key.mybatis;

import com.key.mybatis.dao.UserDao;
import com.key.mybatis.entity.User;
import com.key.mybatis.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 动态SQl测试
 *
 * @author Key
 * @date 2021/11/05/15:34
 **/
public class DynamicSQLTest {

    /**
     * 测试if标签
     *  - where、trim标签
     */
    @Test
    public void testIfCondition() {
        // 获取SqlSession
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        // 获取mapper对象
        UserDao mapper = sqlSession.getMapper(UserDao.class);

        // 创建一个用户对象
        User user = new User(null, "周星驰", "11baa");

        // 调用持久层接口方法
        List<User> users = mapper.getUsersByIfCondition(user);

        users.forEach(System.out :: println);

        // 关闭sqlSession
        sqlSession.close();
    }

    /**
     * 测试choose标签
     */
    @Test
    public void testChooseCondition() {
        // 获取SqlSession
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        // 获取mapper对象
        UserDao mapper = sqlSession.getMapper(UserDao.class);

        // 创建一个用户对象
        User user = new User(null, "%周%", "11baa");

        // 调用dao方法
        List<User> users = mapper.getUsersByChooseCondition(user);

        users.forEach(System.out :: println);

        // 关闭sqlSession
        sqlSession.close();
    }

    /**
     * 测试set标签
     */
    @Test
    public void testSetCondition() {
        // 获取SQlSession
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        // 获取mapper
        UserDao mapper = sqlSession.getMapper(UserDao.class);

        // 创建一个用户对象
        User user = new User(5, "周海媚", null);

        // 调用dao方法
        int result = mapper.updateUserBySetCondition(user);

        if (result == 0) {
            System.out.println("更新失败！");
        } else {
            System.out.println("更新成功！");
        }

        // 提交事务
        sqlSession.commit();

        // 关闭sqlSession
        sqlSession.close();
    }

    /**
     * 测试foreach标签
     */
    @Test
    public void testForeachCondition() {
        // 获取SqlSession
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        // 获取mapper
        UserDao mapper = sqlSession.getMapper(UserDao.class);

        // 调用dao方法
        List<User> users = mapper.getUsersByForeachCondition(Arrays.asList(1, 2, 3, 4));

        users.forEach(System.out :: println);

        // 关闭sqlSession
        sqlSession.close();
    }

    /**
     * 测试批量插入
     */
    @Test
    public void testBatchInsertByForeach() {
        // 获取SqlSession
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        // 获取mapper
        UserDao mapper = sqlSession.getMapper(UserDao.class);

        // 创建一个用户集合
        List<User> users = new ArrayList<>();
        users.add(new User(null, "吴用", "wu456"));
        users.add(new User(null, "武大郎", "da123"));
        users.add(new User(null, "扈三娘", "hu123"));

        int result = mapper.batchInsertUsersByForeach(users);

        if (result == 0) {
            System.out.println("批量插入失败！");
        } else {
            System.out.println("批量插入成功！");
        }

        // 提交事务
        sqlSession.commit();

        // 关闭
        sqlSession.close();
    }

    /**
     * 测试内置参数的使用
     */
    @Test
    public void testBuiltInParam() {
        // 获取sqlSession
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        // 获取mapper
        UserDao mapper = sqlSession.getMapper(UserDao.class);

        // 调用dao接口方法
       /* List<User> users = mapper.listUsersByBuiltInParam();
        users.forEach(System.out :: println);*/

        User user = mapper.getUserByInnerParam(2, "周慧敏");
        System.out.println(user);

        // 关闭sqlSession
        sqlSession.close();
    }

    /**
     * 测试bind标签
     */
    @Test
    public void testBind() {
        // 获取SqlSession
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        // 获取mapper
        UserDao mapper = sqlSession.getMapper(UserDao.class);

        List<User> users = mapper.listUsersByBind("周");

        users.forEach(System.out :: println);

        // 关闭sqlSession
        sqlSession.close();
    }
}
