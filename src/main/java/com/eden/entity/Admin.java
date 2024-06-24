package com.eden.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
	private Integer id;
	private String adminname;
	private String realname;
	private String password;
	private Boolean gender;
	
}
