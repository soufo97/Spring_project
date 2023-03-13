package com.lsy.AwesomeCake.service;



import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsy.AwesomeCake.Reservation;
import com.lsy.AwesomeCake.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {
	
	private final ReservationRepository reservationRepository;

	@Transactional
	public void saveReservation(Reservation reservation) {
		reservationRepository.save(reservation);
	}
	
	public List<Reservation> findReservations() {
		return reservationRepository.findAll();
	}
	
	public Reservation findOne(Long id) {
		return reservationRepository.findOne(id);
	}
	
	@Transactional
	public void update(Long id, String name, String phone, String productCode, int quantity, LocalDate up_date) {
		Reservation findReservation = reservationRepository.findOne(id);
		findReservation.setName(name);
		findReservation.setPhone(phone);
		findReservation.setProductCode(productCode);
		findReservation.setQuantity(quantity);
		findReservation.setUp_date(up_date);
	}
	
	@Transactional
	public void deleteById(Long id) {
		reservationRepository.deleteById(id);
	}
	
	
	
	
}
