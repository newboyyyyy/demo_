package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * 員工
 */
@NamedStoredProcedureQuery(
        name = "employee.getAllemployees",
        procedureName = "get_all_employees"
)
@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Id
    @Column(
            name = "emp_id",
            unique = true
    )
    private String emp_id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToOne
    @JsonIgnore
    @JoinColumn(
            name = "floor_seat_seq",
            foreignKey = @ForeignKey(name = "fk_employee_seating_chart_")
    )
    private SeatingChart seatingChart;
}