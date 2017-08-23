package com.integrate.ssm.dao.mapper;

import com.integrate.ssm.model.Department;
import com.integrate.ssm.model.DepartmentCriteria;
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
import org.springframework.stereotype.Component;

@Component
public interface DepartmentMapper {
    @SelectProvider(type=DepartmentSqlProvider.class, method="countByExample")
    long countByExample(DepartmentCriteria example);

    @DeleteProvider(type=DepartmentSqlProvider.class, method="deleteByExample")
    int deleteByExample(DepartmentCriteria example);

    @Delete({
        "delete from tbl_dept",
        "where dept_id = #{deptId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer deptId);

    @Insert({
        "insert into tbl_dept (dept_id, dept_name)",
        "values (#{deptId,jdbcType=INTEGER}, #{deptName,jdbcType=VARCHAR})"
    })
    int insert(Department record);

    @InsertProvider(type=DepartmentSqlProvider.class, method="insertSelective")
    int insertSelective(Department record);

    @SelectProvider(type=DepartmentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="dept_id", property="deptId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="dept_name", property="deptName", jdbcType=JdbcType.VARCHAR)
    })
    List<Department> selectByExample(DepartmentCriteria example);

    @Select({
        "select",
        "dept_id, dept_name",
        "from tbl_dept",
        "where dept_id = #{deptId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="dept_id", property="deptId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="dept_name", property="deptName", jdbcType=JdbcType.VARCHAR)
    })
    Department selectByPrimaryKey(Integer deptId);

    @UpdateProvider(type=DepartmentSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentCriteria example);

    @UpdateProvider(type=DepartmentSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Department record, @Param("example") DepartmentCriteria example);

    @UpdateProvider(type=DepartmentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Department record);

    @Update({
        "update tbl_dept",
        "set dept_name = #{deptName,jdbcType=VARCHAR}",
        "where dept_id = #{deptId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Department record);
    List<Department> selectByExampleWithDept(DepartmentCriteria example);
    Department selectByPrimaryKeyWithDept(Integer deptId);
}