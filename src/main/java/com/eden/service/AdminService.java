package com.eden.service;

import com.eden.entity.Admin;

public interface AdminService {
	
	void register(Admin admin);

	Admin login(String adminname, String password);

}
