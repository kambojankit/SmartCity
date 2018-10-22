package com.tomtom.hackathon.smartcity.repositories;

import com.tomtom.hackathon.smartcity.models.BookingRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookingRepository extends CrudRepository<BookingRequest,Long>{

    @Modifying
    @Query("update BookingRequest booking set booking.requestStatus = ?1 where booking.id = ?2")
    void updateBookingRequest(String requestStatus, int userId);

    @Query("SELECT b FROM BookingRequest b WHERE b.tokenNumber = ?1 AND b.dateOfRequest = ?2 AND b.mobileNumber = ?3")
    List<BookingRequest> findBookingByTokenAndDate(String tokenNumber, String dateOfRequest,String mobileNumber);
}
