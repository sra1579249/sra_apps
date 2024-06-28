package com.vogodt.dao;

import java.sql.Date;
import java.sql.PreparedStatement;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class BikeDao {
	private final String SQL_INSERT_BIKE_RENTAL = "insert into bike_rental (bike_no, customer_no, rented_dt, duration, amount_paid) values (?, ?, ?, '0', '0')";
	private JdbcTemplate jdbcTemplate;

	public BikeDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public long saveBikeRental(int bikeNo, long customerNo) {
		long bikeRentalNo = 0;
		KeyHolder kh = null;

		kh = new GeneratedKeyHolder();
		jdbcTemplate.update((con) -> {
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(SQL_INSERT_BIKE_RENTAL, new String[] { "bike_rental_no" });
			pstmt.setInt(1, bikeNo);
			pstmt.setLong(2, customerNo);
			pstmt.setDate(3, new Date(new java.util.Date().getTime()));
			return pstmt;
		}, kh);

		bikeRentalNo = kh.getKey().longValue();
		return bikeRentalNo;
	}

}
