package com.lsy.AwesomeCake.dto;


import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReservationDto {

	private Long id;
	@NotBlank(message = "이름을 적어주세요.")
	private String name;
	
	@NotBlank(message = "연락처를 적어주세요.")
	private String phone;
	
	private String productCode;
	private int quantity;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate up_date;
}
