package com.integrate.ssm.dao.mapper;

import com.integrate.ssm.model.Employee;
import com.integrate.ssm.model.EmployeeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface EmployeeMapper {
    @SelectProvider(type=EmployeeSqlProvider.class, method="countByExample")
    long countByExample(EmployeeCriteria example);

    @DeleteProvider(type=EmployeeSqlProvider.class, method="deleteByExample")
    int deleteByExample(EmployeeCriteria example);

    @Delete({
        "delete from tbl_emp",
        "where emp_id = #{empId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer empId);

    @Insert({
        "insert into tbl_emp (emp_id, emp_name, ",
        "email, gender, d_id)",
        "values (#{empId,jdbcType=INTEGER}, #{empName,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{gender,jdbcType=VARCHAR}, #{dId,jdbcType=INTEGER})"
    })
    int insert(Employee record);

    @InsertProvider(type=EmployeeSqlProvider.class, method="insertSelective")
    int insertSelective(Employee record);

    @SelectProvider(type=EmployeeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="emp_id", property="empId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="emp_name", property="empName", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="gender", property="gender", jdbcType=JdbcType.VARCHAR),
        @Result(column="d_id", property="dId", jdbcType=JdbcType.INTEGER)
    })
    List<Employee> selectByExample(EmployeeCriteria example);

    @Select({
        "select",
        "emp_id, emp_name, email, gender, d_id",
        "from tbl_emp",
        "where emp_id = #{empId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="emp_id", property="empId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="emp_name", property="empName", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="gender", property="gender", jdbcType=JdbcType.VARCHAR),
        @Result(column="d_id", property="dId", jdbcType=JdbcType.INTEGER)
    })
    Employee selectByPrimaryKey(Integer empId);

    @UpdateProvider(type=EmployeeSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeCriteria example);

    @UpdateProvider(type=EmployeeSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeCriteria example);

    @UpdateProvider(type=EmployeeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Employee record);

    @Update({
        "update tbl_emp",
        "set emp_name = #{empName,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "gender = #{gender,jdbcType=VARCHAR},",
          "d_id = #{dId,jdbcType=INTEGER}",
        "where emp_id = #{empId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Employee record);
}