package com.api.parkingcontrol.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.parkingcontrol.dtos.ParkingSpotDTO;
import com.api.parkingcontrol.entities.ParkingSpotEntity;
import com.api.parkingcontrol.services.ParkingService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {
	
	@Autowired
	ParkingService parkingService;
	
	public ResponseEntity<Object> saveParkingSpot (@RequestBody @Valid ParkingSpotDTO parkingSpotDTO){
		var parkingSpotEntity = new ParkingSpotEntity();
		BeanUtils.copyProperties(parkingSpotDTO, parkingSpotEntity);
		parkingSpotEntity.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(parkingService.save(parkingSpotEntity));
	}

}
