package com.key.mybatis;

import com.key.mybatis.dao.UserDao;
import com.key.mybatis.entity.User;
import com.key.mybatis.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * 第一个MyBatis测试
 *
 * @author Key
 * @date 2021/10/21/17:39
 **/
public class HelloWordTest {

    /**
     * HelloWord测试1.0
     */
    @Test
    public void testMyBatis01() {
        // 1. 从MyBatis工具类中获取SqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        // 2. 根据sqlSession对象执行映射文件中对应的SQL语句，并获取结果集对象
        User user =  sqlSession.selectOne(
                "com.key.mybatis.dao.UserDao.selectUser",
                1);

        // 3. 打印获取到的结果集对象
        System.out.println(user);

        // 4. 关闭sqlSession对象
        sqlSession.close();
    }

    /**
     * HelloWord测试2.0
     */
    @Test
    public void testMyBatis02() {
        // 1. 从工具类中获取SqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        // 2. 加载EmployDao接口获取接口对应的代理实现类对象
        UserDao mapper = sqlSession.getMapper(UserDao.class);

        // 3. 通过mapper接口的方法，获取结果集对象
        List<User> users = mapper.getAllUsers();

        // 4. 打印结果
        users.forEach(System.out :: println);

        // 5. 关闭sqlSession对象
        sqlSession.close();
    }
}
