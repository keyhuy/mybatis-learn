package com.key.mybatis.entity;

/**
 * 员工实体类
 *
 * @author Key
 * @date 2021/10/25/19:46
 **/
public class Employee {

    private Integer empId;
    private String empName;
    private String gender;
    private String email;
    private Department myDept;

    public Employee() {
    }

    public Employee(Integer empId, String empName, String gender, String email) {
        this.empId = empId;
        this.empName = empName;
        this.gender = gender;
        this.email = email;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getMyDept() {
        return myDept;
    }

    public void setMyDept(Department myDept) {
        this.myDept = myDept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
