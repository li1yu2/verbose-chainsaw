package com.eden.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.eden.entity.Employee;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("employee")
public class EmployeeController {
	
	@Value("${file.upload.dir}")
	String photoPath;
	
	@RequestMapping("save")
	public String save(Employee employee,MultipartFile img) throws IllegalStateException, IOException{
		
		log.info("新規従業員の名前:{},給料:{},誕生日:{}",
				employee.getName(),employee.getSalary(),employee.getBirthday());
		
		log.info("写真のファイル名:{},ファイルのサイズ:{}",img.getOriginalFilename(),img.getSize());
		
		String fileName=img.getOriginalFilename();
		
		String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
		
		String filePrefix = new SimpleDateFormat("yyyMMddmmssSSS").format(new Date());
		
		String newfileName=filePrefix+fileSuffix;
		
		img.transferTo(new File(photoPath,newfileName));
		return"emplist";
	}
}
