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
	
	@RequestMapping("search")
	public String searchEmp(String searchName,Double salaryBegin,
			Double salaryEnd, String dateBegin,String dateEnd,Model model) {
		log.info("名前:{},最高給与{},最低給与{},開始時間{},終了時間{}",
				searchName,salaryBegin,salaryEnd,dateBegin,dateEnd);
		searchName=searchName.trim();
		List<Employee> employeeList=employeeService.searchEmp(searchName,salaryBegin,salaryEnd,dateBegin,dateEnd);
		model.addAttribute("employeeList", employeeList);
		return "emplist";
		
	}
	
	@RequestMapping("delete")
	public String delete(Integer id) {
		log.info("削除を受け入れるID:{}",id);
		
		String delephoto = employeeService.findById(id).getPhoto();
		
		if(delephoto==null) {
			delephoto=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		}
		
		File file= new File(photoPath,delephoto);
		
		if(file.exists()) {
			file.delete();
		}
		employeeService.delete(id);
		
		return "redirect:/employee/lists";
	}
	
	@RequestMapping("update")
	public String update(Employee employee,MultipartFile img) throws IllegalStateException, IOException {
		log.info("受けた値ID:{},名前：{},月給：{},誕生日：{}",
				employee.getId(),employee.getName(),employee.getSalary(),employee.getBirthday());
		
		boolean notempty = !img.isEmpty();
		log.info("写真を更新するかどうか：{}",notempty);
		
		if(notempty) {
			//古い写真のファイル削除する処理。
			String oldphoto=employeeService.findById(employee.getId()).getPhoto();
			
			if(oldphoto==null) {
				oldphoto=new SimpleDateFormat("yyyyMMddmmssSSS").format(new Date());
			}
			
			File file = new File(photoPath,oldphoto);
			
			if(file.exists()) file.delete();
			
			//新しい写真をアプロードする処理。
			
			String fileName=img.getOriginalFilename();
			
			String newfileName=uploadPhoto(img,fileName);
			
			employee.setPhoto(newfileName);
			
		}
		
		employeeService.update(employee);
		
		return "redirect:/employee/lists";
	}
	
	
	@RequestMapping("detail")
	public String detail(Integer id,Model model) {
		log.info("受けたID：{}",id);
		
		Employee employee=employeeService.findById(id);
		
		model.addAttribute("employee", employee);
		
		return "updateEmp";
	}
	
	@RequestMapping("save")
	public String save(Employee employee,MultipartFile img) throws IllegalStateException, IOException {
		
		log.info("新規社員の名前：{},月給：{},誕生日：{}",
				employee.getName(),employee.getSalary(),employee.getBirthday());
		
		log.info("写真ファイル名：{},ファイルのサイズ：{}",img.getOriginalFilename(),img.getSize());
		
		boolean notempty = !img.isEmpty();
		if(notempty) {
			String fileName=img.getOriginalFilename();
		
		String newfileName=uploadPhoto(img,fileName);
		
		employee.setPhoto(newfileName);
		}
		
		employeeService.save(employee);
		
		return "redirect:/employee/lists";
	}
	
	@RequestMapping("lists")
	public String lists(Model model) {
		
		log.info("全て社員の情報リストを読み込む。");
		
		List<Employee> employeeList= employeeService.lists();
		
		model.addAttribute("employeeList", employeeList);
		
		return "emplist";
	}
	
	private String uploadPhoto(MultipartFile img,String fileName) throws IllegalStateException, IOException {
		
		String fileSuffix=fileName.substring(fileName.lastIndexOf("."));
		
		String filePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		
		String newfileName=filePrefix+fileSuffix;
		
		img.transferTo(new File(photoPath,newfileName));
		
		return newfileName;
	}

}
