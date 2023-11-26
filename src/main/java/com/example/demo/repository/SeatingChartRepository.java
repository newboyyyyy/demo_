package com.example.demo.repository;

import com.example.demo.domain.SeatingChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatingChartRepository extends JpaRepository<SeatingChart, Integer> {

    @Query(value = "call get_seating_charts_with_employees", nativeQuery = true)
    public List<SeatingChart> getAllChart();

//    @Query(value = "call update_seat_chart(:p_seat_index, :p_selected_employee)",nativeQuery = true)
//    public void updateSeatingChart( @Param("p_seat_index") int seatIndex,
//                                    @Param("p_selected_employee") String selectedEmployee);


}
