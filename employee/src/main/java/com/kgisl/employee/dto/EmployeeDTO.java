package com.kgisl.employee.dto;

import java.util.List;

import com.kgisl.employee.entity.TechnicalSkill;

public class EmployeeDTO {
	private int id;
    private String employeeName;
    private String mobile;
    private String gender;
    private String address;
    private String employeeCode;
    private String department;
    private String designation;
    private String email;
    private List<TechnicalSkill> technicalSkills;
    private byte[] employeeFile; 
    
    
	public byte[] getEmployeeFile() {
		return employeeFile;
	}
	public void setEmployeeFile(byte[] employeeFile) {
		this.employeeFile = employeeFile;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<TechnicalSkill> getTechnicalSkills() {
		return technicalSkills;
	}
	public void setTechnicalSkills(List<TechnicalSkill> technicalSkills) {
		this.technicalSkills = technicalSkills;
	}
	@Override
	public String toString() {
		return "EmployeeDTO [id=" + id + ", employeeName=" + employeeName + ", mobile=" + mobile + ", gender=" + gender
				+ ", address=" + address + ", employeeCode=" + employeeCode + ", department=" + department
				+ ", designation=" + designation + ", email=" + email + ", technicalSkills=" + technicalSkills + "]";
	}
    
}
