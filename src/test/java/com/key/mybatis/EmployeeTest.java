package com.key.mybatis;

import com.key.mybatis.dao.DepartmentDao;
import com.key.mybatis.dao.EmployeeDao;
import com.key.mybatis.entity.Department;
import com.key.mybatis.entity.Employee;
import com.key.mybatis.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * 员工类CRUD测试
 *
 * @author Key
 * @date 2021/10/25/20:50
 **/
public class EmployeeTest {

    /**
     * 测试查询全部记录
     */
    @Test
    public void testSelectAll() {
        // 1. 先从工具类中获取SqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        // 2. 根据sqlSession对象获取mapper对象
        EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);

        // 3. 调用接口中的方法
        List<Employee> employees = mapper.getAllEmployees();

        // 4. 打印结果集
        employees.forEach(System.out :: println);

        // 5. 关闭sqlSession对象
        sqlSession.close();
    }

    /**
     * 测试查询一条记录
     */
    @Test
    public void testSelect() {
        // 获取sqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        // 根据sqlSession对象获取mapper对象
        EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);

        // 调用接口方法
        Employee emp = mapper.getEmpById(3);

        System.out.println(emp);

        // 关闭sqlSession对象
        sqlSession.close();
    }

    /**
     * 测试插入
     */
    @Test
    public void testInsert() {
        // 获取SqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        // 根据sqlSession对象获取mapper对象
        EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);

        // 创建一个新的Employee对象
        Employee newEmp = new Employee(null, "周星驰",
                "男", "zhouxingchi@qq.com");

        // 调用接口方法，传入新的员工对象
        mapper.addEmp(newEmp);

        // 提交事务，一定要提交事务，不然插入操作失败
        sqlSession.commit();

        // 最后关闭sqlSession对象
        sqlSession.close();
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate() {
        // 获取SqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        // 根据sqlSession对象获取mapper对象
        EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);

        // 创建需要更新的员工对象
        Employee updatedEmp = new Employee(2, "周润发",
                "男", "zhourunfa@gmail.com");

        // 调用接口方法，传入更新的员工对象
        mapper.updateEmpInfo(updatedEmp);

        // 提交事务
        sqlSession.commit();

        // 关闭sqlSession
        sqlSession.close();
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete() {
        // 获取SqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        // 根据sqlSession对象获取mapper对象
        EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);

        // 调用接口方法
        mapper.deleteEmpById(4);

        // 提交事务
        sqlSession.commit();

        // 关闭sqlSession
        sqlSession.close();
    }

    /**
     * 测试不同形式参数的获取
     */
    @Test
    public void testGetParam() {
        // 获取sqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        // 获取mapper对象
        EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);

        // 调用方法
        // Employee emp = mapper.getEmpByIdAndName(2, "周润发");

        Employee emp = mapper.getEmpByEmpPo(new Employee(2, "周润发", "男", "zhourunfa@gmail.com"));

//        Map<String, Object> map = new HashMap<>();
//        map.put("id", 2);
//        map.put("name", "周润发");
//        Employee emp = mapper.getEmpByMap(map);

//        List<Integer> idList = new ArrayList<>();
//        idList.add(2);
//        idList.add(3);
//        Employee emp = mapper.getEmpByIdList(idList);

        // Employee emp = mapper.getEmpByNameAndUserId("周润发", new User(2, "Key", "aaa"));

        System.out.println(emp);

        // 关闭sqlSession
        sqlSession.close();
    }

    /**
     * 测试级联查询
     */
    @Test
    public void testCascadeQuery() {
        // 获取SqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        // 获取mapper对象
        EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);

        // 调用方法
        Employee emp = mapper.getEmpAndDeptById(2);

        System.out.println("员工对象 --> " + emp);
        System.out.println("员工部门 --> " + emp.getMyDept());

        // 关闭
        sqlSession.close();
    }

    /**
     * 测试分步查询和延迟加载
     */
    @Test
    public void testStepQuery() {
        // 获取SqlSession
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        // 获取mapper
        EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);

        // 调用分步查询的方法
        Employee emp = mapper.getEmpAndDeptByIdStep(3);

        System.out.println("员工姓名 --> " + emp.getEmpName());
        //System.out.println("员工部门 --> " + emp.getMyDept());

        // 关闭
        sqlSession.close();
    }

    /**
     * 测试collection实现级联查询
     */
    @Test
    public void testCollQuery() {
        // 获取SQlSession
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        // 获取mapper
        DepartmentDao mapper = sqlSession.getMapper(DepartmentDao.class);

        // 调用部门持久层接口方法
        Department dept = mapper.getDeptAndEmpListById(3);

        System.out.println("部门信息 --> " + dept);
        System.out.println("部门下的所有员工信息");
        dept.getEmpList().forEach(System.out :: println);

        // 关闭sqlSession
        sqlSession.close();
    }

    /**
     * 测试collection分步查询和延迟加载
     */
    @Test
    public void testCollQueryStep() {
        // 获取SQlSession
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        // 获取mapper
        DepartmentDao mapper = sqlSession.getMapper(DepartmentDao.class);

        // 调用部门持久层接口方法
        Department dept = mapper.getDeptAndEesByIdStep(3);

        System.out.println("部门信息 --> " + dept);
        System.out.println("部门下的所有员工信息");
        dept.getEmpList().forEach(System.out :: println);

        // 关闭sqlSession
        sqlSession.close();
    }
}
