package com.vogodt.dao;

import java.sql.PreparedStatement;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.vogodt.bo.CustomerBo;

public class CustomerDao {
	private final String SQL_INSERT_CUSTOMER = "insert into customer (full_nm, dob, gender, mobile_no, email_address, driving_license_no) values (?, ?, ?, ?, ?, ?)";
	private JdbcTemplate jdbcTemplate;

	public CustomerDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public long saveCustomer(final CustomerBo customerBo) {
		KeyHolder kh = null;
		long customerNo = 0;

		kh = new GeneratedKeyHolder();
		jdbcTemplate.update((con) -> {
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(SQL_INSERT_CUSTOMER, new String[] { "customer_no" });
			pstmt.setString(1, customerBo.getFullName());
			pstmt.setDate(2, new java.sql.Date(customerBo.getDob().getTime()));
			pstmt.setString(3, customerBo.getGender());
			pstmt.setString(4, customerBo.getMobileNo());
			pstmt.setString(5, customerBo.getEmailAddress());
			pstmt.setString(6, customerBo.getDrivingLicenseNo());
			return pstmt;
		}, kh);
		customerNo = kh.getKey().longValue();
		return customerNo;
	}

}
