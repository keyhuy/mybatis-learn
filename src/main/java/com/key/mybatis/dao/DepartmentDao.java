package com.key.mybatis.dao;

import com.key.mybatis.entity.Department;

/**
 * 持久层接口-部门
 *
 * @author Key
 * @date 2021/11/04/12:56
 **/
public interface DepartmentDao {

    /**
     * 根据id查询部门信息
     * @param deptId 部门id
     * @return 返回部门对象
     */
    Department getDeptById(Integer deptId);

    /**
     * 根据id查询部门信息及其所有员工信息
     * @param deptId 部门id
     * @return 返回部门对象
     */
    Department getDeptAndEmpListById(Integer deptId);

    /**
     * 分步查询
     * @param deptId 部门id
     * @return 返回部门对象
     */
    Department getDeptAndEesByIdStep(Integer deptId);
}