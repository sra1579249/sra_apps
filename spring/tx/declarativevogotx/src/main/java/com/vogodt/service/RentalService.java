package com.vogodt.service;

import com.vogodt.bo.CustomerBo;
import com.vogodt.dao.BikeDao;
import com.vogodt.dao.CustomerDao;
import com.vogodt.form.BikeRentalForm;

public class RentalService {
	private CustomerDao customerDao;
	private BikeDao bikeDao;

	public long rentBikeToNewCustomer(BikeRentalForm bikeRentalForm) {
		long bikeRentalNo = 0;
		long customerNo = 0;
		CustomerBo customerBo = null;

		customerBo = new CustomerBo();
		customerBo.setFullName(bikeRentalForm.getFullname());
		customerBo.setDob(bikeRentalForm.getDob());
		customerBo.setGender(bikeRentalForm.getGender());
		customerBo.setMobileNo(bikeRentalForm.getMobileNo());
		customerBo.setEmailAddress(bikeRentalForm.getEmailAddress());
		customerBo.setDrivingLicenseNo(bikeRentalForm.getDrivingLicenseNo());

		customerNo = customerDao.saveCustomer(customerBo);
		bikeRentalNo = bikeDao.saveBikeRental(bikeRentalForm.getBikeNo(), customerNo);

		return bikeRentalNo;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public void setBikeDao(BikeDao bikeDao) {
		this.bikeDao = bikeDao;
	}

}
