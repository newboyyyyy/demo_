package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "seating_chart")
@Data
public class SeatingChart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "floor_seat_seq")
    private Integer floorSeatSeq;

    @Column(name = "floor_no")
    private Integer floorNo;

    @Column(name = "seat_no")
    private Integer seatNo;

    @OneToOne(mappedBy = "seatingChart")
    @JoinColumn(
            name = "floor_seat_seq",
            referencedColumnName = "floorSeatSeq",
            unique = true
    )
    private Employee employee;
}