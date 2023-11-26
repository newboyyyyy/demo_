package com.example.demo.service.impl;

import com.example.demo.domain.Employee;
import com.example.demo.domain.SeatingChart;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.SeatingChartRepository;
import com.example.demo.service.SeatingChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class SeatingChartServiceImpl implements SeatingChartService {

    @Autowired
    private SeatingChartRepository seatingChartRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<SeatingChart> getAllChart() {
        return seatingChartRepository.getAllChart();
    }

    @Override
    @Transactional
    public void updateSeatingChart(int seatIndex, String selectedEmployee) {
        String employeeId = selectedEmployee.substring(21, 26);

//        seatingChartRepository.updateSeatingChart(seatIndex, employeeId);


        SeatingChart sc = seatingChartRepository.getOne(seatIndex);
        Employee emp = employeeRepository.getOne(employeeId);

        sc.setEmployee(emp);
        seatingChartRepository.save(sc);

        emp.setSeatingChart(sc);
        employeeRepository.save(emp);




    }
}
