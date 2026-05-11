package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long empNumber;

    @Column(name = "unique_emp_no")
    private String uniqueEmpNo;

    @Column(name = "full_name")
    private String fullName;
    @Column(name = "calling_name")
    private String callingName;
    @Column(name = "nic")
    private String nic;
    @Column(name = "dob")
    private String dob;
    @Column(name = "gender")
    private String gender;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "emergency_phone_number")
    private String emergencyPhoneNumber;
    @Column(name = "blood_group")
    private String bloodGroup;
    @Column(name = "employment_type")
    private String employmentType;
    @Column(name = "employee_status")
    private String employeeStatus;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "image")
    private byte[] image;
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "image_type")
    private String imageType;
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;

    public EmployeeEntity() { }

    public EmployeeEntity(long empNumber, String uniqueEmpNo, String fullName, String callingName, String nic, String dob, String gender, String address, String email, String phoneNumber, String emergencyPhoneNumber, String bloodGroup, String employmentType, String employeeStatus, String jobTitle, byte[] image, String imageName, String imageType, User user) {
        this.empNumber = empNumber;
        this.uniqueEmpNo = uniqueEmpNo;
        this.fullName = fullName;
        this.callingName = callingName;
        this.nic = nic;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.emergencyPhoneNumber = emergencyPhoneNumber;
        this.bloodGroup = bloodGroup;
        this.employmentType = employmentType;
        this.employeeStatus = employeeStatus;
        this.jobTitle = jobTitle;
        this.image = image;
        this.imageName = imageName;
        this.imageType = imageType;
        this.user = user;
    }

    public long getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(long empNumber) {
        this.empNumber = empNumber;
    }

    public String getUniqueEmpNo() {
        return uniqueEmpNo;
    }

    public void setUniqueEmpNo(String uniqueEmpNo) {
        this.uniqueEmpNo = uniqueEmpNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCallingName() {
        return callingName;
    }

    public void setCallingName(String callingName) {
        this.callingName = callingName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmergencyPhoneNumber() {
        return emergencyPhoneNumber;
    }

    public void setEmergencyPhoneNumber(String emergencyPhoneNumber) {
        this.emergencyPhoneNumber = emergencyPhoneNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
