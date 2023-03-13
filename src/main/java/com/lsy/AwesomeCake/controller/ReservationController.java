package com.lsy.AwesomeCake.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lsy.AwesomeCake.ProductCode;
import com.lsy.AwesomeCake.Reservation;
import com.lsy.AwesomeCake.dto.ReservationDto;
import com.lsy.AwesomeCake.service.ReservationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReservationController {
	
	private final ReservationService reservationService;
	
	// 메뉴페이지로 이동
	@GetMapping("/reservations")
	public String reservation() {
		return "reservations/reservation";
	}
	
	// 예약목록으로 이동
	@GetMapping("/reservations/list")
	public String list() {
		return "reservations/reservation_list";
	}
	
	// 예약추가 페이지로 이동
	@GetMapping("/reservations/createForm")
	public String createForm(Model model) {
		model.addAttribute("reservationDto", new ReservationDto());
		return "/reservations/reservation_create";
	}
	
	
	
	// 케이크 선택
		@ModelAttribute("productCodes")
		public List<ProductCode> productCodes(){
			List<ProductCode> productCodes = new ArrayList<ProductCode>();
			productCodes.add(new ProductCode("치즈케이크", "1. 치즈케이크"));
			productCodes.add(new ProductCode("레인보우 케이크", "2. 레인보우 케이크"));
			productCodes.add(new ProductCode("당근 케이크", "3. 당근 케이크"));
			productCodes.add(new ProductCode("초코 케이크", "4. 초코 케이크"));
			productCodes.add(new ProductCode("레이어드 케이크", "5. 레이어드 케이크"));
			productCodes.add(new ProductCode("치즈 폭탄 케이크", "6. 치즈 폭탄 케이크"));
			return productCodes;
		}
	
	// 예약 추가
	@PostMapping("/reservations/create")
	public String create(ReservationDto reservationDto) {
		
		
		
		Reservation reservation = new Reservation();
		
		reservation.setName(reservationDto.getName());
		reservation.setPhone(reservationDto.getPhone());
		reservation.setProductCode(reservationDto.getProductCode());
		reservation.setQuantity(reservationDto.getQuantity());
		reservation.setUp_date(reservationDto.getUp_date());
		
		reservationService.saveReservation(reservation);
		
		return "redirect:/";
	}
	
	// 예약후 리스트 확인
	@GetMapping("/reservations/lists")
	public String list(Model model) {
		List<Reservation> reservations = reservationService.findReservations();
		model.addAttribute("reservations", reservations);
		return "reservations/reservation_list";
	}
	
	// 예약 상세페이지
		@GetMapping("/reservations/view/{id}")
		public String view(Model model, @PathVariable("id") Long id) {
			Reservation reservation = reservationService.findOne(id);
			model.addAttribute("reservation", reservation);
			return "reservations/reservation_view";
		}
	
	// 예약 수정 페이지
	@GetMapping("/reservations/update/{id}")
	public String updateForm(@PathVariable Long id, Model model) {
		Reservation reservation = reservationService.findOne(id);
		
		ReservationDto dto = new ReservationDto();
		dto.setId(reservation.getId());
		dto.setName(reservation.getName());
		dto.setPhone(reservation.getPhone());
		dto.setProductCode(reservation.getProductCode());
		dto.setQuantity(reservation.getQuantity());
		dto.setUp_date(reservation.getUp_date());
		
		model.addAttribute("dto", dto);
		return "reservations/reservation_update";
	}

		
	// 예약 수정하기
	@PostMapping("/reservations/update/{id}")
	public String update(@PathVariable Long id, @ModelAttribute("dto") Reservation dto) {
		
		reservationService.update(id, dto.getName(), dto.getPhone(), dto.getProductCode(), dto.getQuantity(), dto.getUp_date());
		return "redirect:/reservations/view/{id}";
	}
	
	// 예약 삭제하기
	@GetMapping("/reservations/delete/{id}")
	public String delete(@PathVariable Long id) {
		reservationService.deleteById(id);
		return "redirect:/reservations/lists";
	}
	
}