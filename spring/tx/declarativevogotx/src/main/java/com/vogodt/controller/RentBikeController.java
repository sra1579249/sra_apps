package com.vogodt.controller;

import com.vogodt.form.BikeRentalForm;
import com.vogodt.service.RentalService;

public class RentBikeController {
	private RentalService rentalService;

	public RentBikeController(RentalService rentalService) {
		this.rentalService = rentalService;
	}

	public long hire(BikeRentalForm bikeRentalForm) {
		long bikeRentalNo = 0;

		bikeRentalNo = rentalService.rentBikeToNewCustomer(bikeRentalForm);
		return bikeRentalNo;
	}
}
