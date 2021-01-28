package com.siddarthaboddu.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.siddarthaboddu.entity.Reservation;

@Repository
public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long>{

}
