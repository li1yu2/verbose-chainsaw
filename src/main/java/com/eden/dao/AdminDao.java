package com.eden.dao;

import com.eden.entity.Admin;

public interface AdminDao {
	
	Admin findByAdmunName(String adminname);

	void save(Admin admin);

}
