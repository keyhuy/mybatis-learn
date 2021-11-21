package com.key.mybatis.dao;

import com.key.mybatis.entity.Employee;
import com.key.mybatis.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * EmployeeDao接口
 *
 * @author Key
 * @date 2021/10/25/19:48
 **/
public interface EmployeeDao {

    Employee getEmpByIdAndName(@Param("empId") Integer empId, String empName);

    Employee getEmpByEmpPo(Employee empPo);

    Employee getEmpByMap(Map<String, Object> map);

    Employee getEmpByNameAndUserId(String empName, User user);

    Employee getEmpByIdList(List<Integer> idList);

    /**
     * 级联查询 - 根据员工id查询员工记录，以及员工所在部门的记录
     * @param empId 员工id
     * @return 返回员工对象
     */
    Employee getEmpAndDeptById(Integer empId);


    /**
     * 分步查询
     * @param empId 员工id
     * @return 返回
     */
    Employee getEmpAndDeptByIdStep(Integer empId);

    /**
     * 根据部门id查询该部门下所有员工信息
     * @param deptId 部门id
     * @return 返回员工集合
     */
    List<Employee> getEesByDeptId(Integer deptId);

    /**
     * 查询所有员工信息
     * @return 返回员工对象集合
     */
    List<Employee> getAllEmployees();

    /**
     * 根据id查询单个员工信息
     * @param empId 员工id
     * @return 返回一个员工对象
     */
    Employee getEmpById(Integer empId);

    /**
     * 添加一条员工记录
     * @param newEmployee 新的员工对象
     */
    void addEmp(Employee newEmployee);

    /**
     * 更新员工信息
     * @param updatedEmp 被更新的员工对象
     */
    void updateEmpInfo(Employee updatedEmp);

    /**
     * 根据id删除员工信息
     * @param empId 员工id
     */
    void deleteEmpById(Integer empId);
}