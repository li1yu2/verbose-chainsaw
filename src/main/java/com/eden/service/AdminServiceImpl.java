package com.eden.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.eden.dao.AdminDao;
import com.eden.entity.Admin;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDao adminDao ;

	@Override
	public void register(Admin admin) {
		
		Admin adminDB = adminDao.findByAdmunName(admin.getAdminname());
		
		 if(!ObjectUtils.isEmpty(adminDB)) {
			throw new RuntimeException("管理者に存在した");
			
		 }
		 
		 adminDao.save(admin);
		
	}

}
