package com.key.mybatis;

import com.key.mybatis.dao.UserDao;
import com.key.mybatis.entity.User;
import com.key.mybatis.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * 缓存机制测试
 *
 * @author Key
 * @date 2021/11/09/15:15
 **/
public class CacheTest {

    /**
     * 测试一级缓存
     */
    @Test
    public void testFirstLevelCache() {
        // 获取SqlSession对象（代表一次会话）
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        // 获取mapper对象
        UserDao mapper = sqlSession.getMapper(UserDao.class);

        // 第一次查询员工user1
        User user1 = mapper.getUserById(2);
        System.out.println(user1);

        // 再次查询员工user2
        User user2 = mapper.getUserById(2);
        System.out.println(user2);

        // 比较两个对象是否相同
        System.out.println("查询的两个对象是否相同 --> " + (user1 == user2));

        // 关闭sqlSession
        sqlSession.close();
    }

    /**
     * 测试一级缓存失效清空
     */
    @Test
    public void testFirstLevelCacheLose() {
        // 获取SqlSession01
        SqlSession sqlSession01 = MyBatisUtils.getSqlSession();

        UserDao mapper = sqlSession01.getMapper(UserDao.class);

        // 直接查询对象user01
        User user01 = mapper.getUserById(1);
        System.out.println(user01);

        // 获取SqlSession02
        // SqlSession sqlSession02 = MyBatisUtil.getSqlSession();

        // 添加一条数据
//        int result = mapper.insertUserBySqlAndInclude(new User(null, "刘唐", "liu123"));
//        if (result != 0) {
//            System.out.println("插入成功！");
//        }

        // 清空本地缓存
        sqlSession01.clearCache();
        System.out.println("本地缓存已清空...");

        // 直接查询对象user02
        User user02 = mapper.getUserById(1);
        System.out.println(user02);

        // 比较两个对象是否相同
        System.out.println("查询的两个对象是否相同 --> " + (user01 == user02));

        // 关闭两个sqlSession
        sqlSession01.close();
        // sqlSession02.close();
    }

    /**
     * 测试二级缓存
     */
    @Test
    public void testSecondLevelCache() {
        // 获取两个sqlSession
        SqlSession sqlSession01 = MyBatisUtils.getSqlSession();
        SqlSession sqlSession02 = MyBatisUtils.getSqlSession();

        // 使用sqlSession01查询数据
        User user01 = sqlSession01.getMapper(UserDao.class).getUserById(4);
        System.out.println(user01);

        // 关闭sqlSession01
        sqlSession01.close();

        // 使用sqlSession02查询相同的数据
        User user02 = sqlSession02.getMapper(UserDao.class).getUserById(4);
        System.out.println(user02);

        // 再关闭sqlSession02
        sqlSession02.close();

        // 比较获取的两个对象是否是同一个
        System.out.println("两个数据是否相同 --> " + (user01 == user02));
    }
}
