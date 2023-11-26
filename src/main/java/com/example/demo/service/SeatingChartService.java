package com.example.demo.service;

import com.example.demo.domain.SeatingChart;

import java.util.List;

public interface SeatingChartService {

    List<SeatingChart> getAllChart();
    void updateSeatingChart(int seatIndex, String selectedEmployee);
}
