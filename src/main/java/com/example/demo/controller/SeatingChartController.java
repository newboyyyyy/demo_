package com.example.demo.controller;

import com.example.demo.domain.SeatingChart;
import com.example.demo.service.SeatingChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/demo/chart")
public class SeatingChartController {

    @Autowired
    private SeatingChartService seatingChartService;

    @GetMapping("/seatingCharts")
    @ResponseBody
    public List<SeatingChart> getHasChairEmployees() {
        return seatingChartService.getAllChart();
    }


    @PutMapping("/seatingCharts/{seatIndex}")
    public ResponseEntity<Void> updateSeatingChart(@PathVariable int seatIndex, @RequestBody String selectedEmployee)
        {
        seatingChartService.updateSeatingChart(seatIndex, selectedEmployee);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
