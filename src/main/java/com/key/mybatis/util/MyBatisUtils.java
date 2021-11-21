package com.key.mybatis.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * MyBatis工具类
 *  - 获取SqlSessionFactory对象和SqlSession对象
 *
 * @author Key
 * @date 2021/10/21/17:29
 **/
public class MyBatisUtils {
    private static SqlSessionFactory sqlSessionFactory;

    /* 静态代码块中获取SqlSessionFactory对象 */
    static {
        // 先初始化输入流
        InputStream in = null;

        try {
            // 1. 获取全局配置文件的资源路径
            String resource = "mybatis-config.xml";

            // 2. 根据全局配置文件的路径获取对应输入流
            in = Resources.getResourceAsStream(resource);

            // 3. 根据输入流获取SqlSessionFactory对象
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭输入流
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据SqlSessionFactory获取SqlSession对象
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
