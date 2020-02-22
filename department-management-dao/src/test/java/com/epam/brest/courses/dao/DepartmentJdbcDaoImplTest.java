package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Department;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
public class DepartmentJdbcDaoImplTest {

    @InjectMocks//создаем кземпля класса
    private DepartmentJdbcDaoImpl departmentDao;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @AfterEach//вызывается после каждого теста, проверка
    void after(){
        Mockito.verifyNoMoreInteractions(namedParameterJdbcTemplate);//проверка вызывается етот метод или нет

    }

    @Test
    public void getDepartments() {
//запрос не сраатывает нужен мок
        List<Department> departments = departmentDao.getDepartments();
        assertNotNull(departments);

        Mockito.verify(namedParameterJdbcTemplate).query(ArgumentMatchers.anyString(),// в строку грузить можно что угодно через иклс eq
                ArgumentMatchers.any(RowMapper.class));//за скобкой важно
    }

    @Test
    public void getDepartmentById() {
    }

    @Test
    public void addDepartment() {
    }

    @Test
    public void updateDepartment() {
    }

    @Test
    public void deleteDepartment() {
    }

}