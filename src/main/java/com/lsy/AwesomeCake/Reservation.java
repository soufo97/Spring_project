package com.lsy.AwesomeCake;



import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@SequenceGenerator(
		name = "RESERVATION_SEQ",
		initialValue=1,
		allocationSize=1)

@ToString
public class Reservation {
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "RESERVATION_SEQ")
	@Id @Column
	private Long id;
	
	private String name;
	private String phone;
	private String productCode;
	private int quantity;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate up_date;
		
	}


