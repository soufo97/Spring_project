package com.lsy.AwesomeCake.repository;


import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lsy.AwesomeCake.Reservation;

import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class ReservationRepository {
	
	@Autowired
	private final EntityManager em;
	
	// 저장
	public void save(Reservation reservation) {
		if(reservation.getId() == null) {
			em.persist(reservation);
		}
	}
	
	public List<Reservation> findAll() {
		return em.createQuery("select r from Reservation r", Reservation.class)
				.getResultList();
	}
	
	// 예약 하나 조회
	public Reservation findOne(Long id) {
		return em.find(Reservation.class, id);
	}
	
	// 예약 1건 삭제
	public void deleteById(Long id) {
		 em.createQuery("delete from Reservation r where r.id = :id")
				.setParameter("id", id)
				.executeUpdate();
		em.clear();
				
	}


	
	
}
