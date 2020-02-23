package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Department;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class DepartmentJdbcDaoImpl implements DepartmentDao {

    // private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentJdbcDaoImpl.class);

    static final Logger rootLogger = LogManager.getRootLogger();
    static final Logger LOGGER = LogManager.getLogger(DepartmentJdbcDaoImpl.class);

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DepartmentJdbcDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Department> getDepartments() {
        LOGGER.info("Get all dpm");
        List<Department> departments = namedParameterJdbcTemplate
                .query("SELECT d.departmentId, d.departmentName FROM department d ORDER BY d.departmentName", new DepartmentRowMapper());
        return departments;
    }
//https://www.codeflow.site/ru/article/spring-jdbc-jdbctemplate
    @Override
    public Department getDepartmentById(Integer departmentId) {

        SqlParameterSource idParameter = new MapSqlParameterSource().addValue("departmentId", departmentId);
        return namedParameterJdbcTemplate.queryForObject(
                "SELECT * FROM department WHERE departmentId = :departmentId", idParameter, new DepartmentRowMapper());
    }

    // help link https://netjs.blogspot.com/2016/11/insert-update-using-namedparameterjdbctemplate-spring.html
    @Override
    public Department addDepartment(Department department) {

        String insertSql = "insert into department (departmentName) values(:name)";

        SqlParameterSource nameParameter = new MapSqlParameterSource().addValue("departmentName", department.getDepartmentName());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertSql, nameParameter, keyHolder);
        department.setDepartmentId(keyHolder.getKey().intValue());

        return new Department();

    }

    @Override
    public void updateDepartment(Department department) {

    }

    @Override
    public void deleteDepartment(Integer departmentId) {

    }

    private class DepartmentRowMapper implements RowMapper<Department> {
        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {
            Department department = new Department();
            department.setDepartmentId(resultSet.getInt("DEPARTMENT_ID"));
            department.setDepartmentName(resultSet.getString("DEPARTMENT_NAME"));
            return department;
        }
    }
}
