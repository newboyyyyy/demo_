package com.example.demo.service.impl;

import com.example.demo.domain.Employee;
import com.example.demo.domain.SeatingChart;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.SeatingChartRepository;
import com.github.javafaker.Faker;
import lombok.Setter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;


@Transactional
@SpringBootTest(properties = {
        "spring.jpa.generate-ddl=true", "jodconverter.local.enabled=false"
})
public class EmployeeServiceImplTest {

    @Setter(onMethod_ = @Autowired)
    private EmployeeRepository employeeRepository;

    @Setter(onMethod_ = @Autowired)
    private SeatingChartRepository seatingChartRepository;

    @Test
    public void creatPerson() {
        Employee employee = new Employee();

        Faker faker = new Faker(new Locale("zh-tw"));
        employee.setName(faker.name().fullName());
        employee.setEmail(faker.internet().emailAddress());
        employee.setEmp_id("10001");

        SeatingChart seatingChart = new SeatingChart();
        seatingChart.setFloorSeatSeq(1);
        seatingChart.setFloorNo(1);
        seatingChart.setSeatNo(1);

        employee.setSeatingChart(seatingChart);
        seatingChart.setEmployee(employee);

        employeeRepository.save(employee);
        seatingChartRepository.save(seatingChart);

    }
}