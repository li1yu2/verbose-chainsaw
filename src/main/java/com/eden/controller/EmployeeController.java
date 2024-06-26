package com.eden.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.eden.entity.Employee;
import com.eden.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Value("${file.upload.dir}")
	private String photoPath;
	
	@RequestMapping("update")
	public String update(Employee employee) {
		log.info("ID:{},名前:{},給料:{},誕生日:{}",
				employee.getId(),employee.getName(),employee.getSalary(),employee.getBirthday());
		return "redirectr:/employee/lists";
	}
	
	@RequestMapping("detail")
	public String detail(Integer id,Model model) {
		log.info("受けたID:{}",id);
		Employee employee=employeeService.findById(id);
		model.addAttribute("employee",employee);
		return "updateEmp";
	}
	
	@RequestMapping("save")
	public String save(Employee employee,MultipartFile img) throws IllegalStateException, IOException{
		
		log.info("新規従業員の名前:{},給料:{},誕生日:{}",
				employee.getName(),employee.getSalary(),employee.getBirthday());
		
		log.info("写真のファイル名:{},ファイルのサイズ:{}",img.getOriginalFilename(),img.getSize());
		
		String fileName=img.getOriginalFilename();
		
		String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
		
		String filePrefix = new SimpleDateFormat("yyyyMMddmmssSSS").format(new Date());
		
		String newfileName=filePrefix+fileSuffix;
		
		img.transferTo(new File(photoPath,newfileName));
		
		employee.setPhoto(newfileName);
		
		employeeService.save(employee);
		return "redirect:/employee/lists";
	}
	@RequestMapping("lists")
	public String lists(Model model) {
		log.info("表示されるすべての情報");
		List<Employee> employeeList=employeeService.lists();
		model.addAttribute("employeeList",employeeList);		
		return "emplist";
	}
}
