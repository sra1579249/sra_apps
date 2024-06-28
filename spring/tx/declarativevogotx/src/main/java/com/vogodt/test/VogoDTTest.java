package com.vogodt.test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vogodt.controller.RentBikeController;
import com.vogodt.form.BikeRentalForm;

public class VogoDTTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/vogodt/common/application-context.xml");
		RentBikeController rentBikeController = context.getBean("rentBikeController", RentBikeController.class);

		BikeRentalForm bikeRentalForm = new BikeRentalForm();
		bikeRentalForm.setFullname("Martin");
		bikeRentalForm.setDob(new Date());
		bikeRentalForm.setGender("M");
		bikeRentalForm.setMobileNo("999375323");
		bikeRentalForm.setEmailAddress("martin@yahoo.com");
		bikeRentalForm.setDrivingLicenseNo("DL0293834");
		bikeRentalForm.setBikeNo(5);

		long bikeRentalNo = rentBikeController.hire(bikeRentalForm);
		System.out.println("rental no : " + bikeRentalNo);
	}
}
