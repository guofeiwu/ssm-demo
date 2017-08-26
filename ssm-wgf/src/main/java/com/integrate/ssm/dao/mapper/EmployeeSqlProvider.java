package com.integrate.ssm.dao.mapper;

import com.integrate.ssm.model.Employee;
import com.integrate.ssm.model.EmployeeCriteria.Criteria;
import com.integrate.ssm.model.EmployeeCriteria.Criterion;
import com.integrate.ssm.model.EmployeeCriteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class EmployeeSqlProvider {

    public String countByExample(EmployeeCriteria example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("tbl_emp");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(EmployeeCriteria example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("tbl_emp");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(Employee record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tbl_emp");
        
        if (record.getEmpId() != null) {
            sql.VALUES("emp_id", "#{empId,jdbcType=INTEGER}");
        }
        
        if (record.getEmpName() != null) {
            sql.VALUES("emp_name", "#{empName,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            sql.VALUES("email", "#{email,jdbcType=VARCHAR}");
        }
        
        if (record.getGender() != null) {
            sql.VALUES("gender", "#{gender,jdbcType=VARCHAR}");
        }
        
        if (record.getdId() != null) {
            sql.VALUES("d_id", "#{dId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String selectByExample(EmployeeCriteria example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("emp_id");
        } else {
            sql.SELECT("emp_id");
        }
        sql.SELECT("emp_name");
        sql.SELECT("email");
        sql.SELECT("gender");
        sql.SELECT("d_id");
        sql.FROM("tbl_emp");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        Employee record = (Employee) parameter.get("record");
        EmployeeCriteria example = (EmployeeCriteria) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("tbl_emp");
        
        if (record.getEmpId() != null) {
            sql.SET("emp_id = #{record.empId,jdbcType=INTEGER}");
        }
        
        if (record.getEmpName() != null) {
            sql.SET("emp_name = #{record.empName,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            sql.SET("email = #{record.email,jdbcType=VARCHAR}");
        }
        
        if (record.getGender() != null) {
            sql.SET("gender = #{record.gender,jdbcType=VARCHAR}");
        }
        
        if (record.getdId() != null) {
            sql.SET("d_id = #{record.dId,jdbcType=INTEGER}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("tbl_emp");
        
        sql.SET("emp_id = #{record.empId,jdbcType=INTEGER}");
        sql.SET("emp_name = #{record.empName,jdbcType=VARCHAR}");
        sql.SET("email = #{record.email,jdbcType=VARCHAR}");
        sql.SET("gender = #{record.gender,jdbcType=VARCHAR}");
        sql.SET("d_id = #{record.dId,jdbcType=INTEGER}");
        
        EmployeeCriteria example = (EmployeeCriteria) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Employee record) {
        SQL sql = new SQL();
        sql.UPDATE("tbl_emp");
        
        if (record.getEmpName() != null) {
            sql.SET("emp_name = #{empName,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            sql.SET("email = #{email,jdbcType=VARCHAR}");
        }
        
        if (record.getGender() != null) {
            sql.SET("gender = #{gender,jdbcType=VARCHAR}");
        }
        
        if (record.getdId() != null) {
            sql.SET("d_id = #{dId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("emp_id = #{empId,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, EmployeeCriteria example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}