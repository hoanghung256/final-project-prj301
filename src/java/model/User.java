/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.Gender;
import enums.Role;
import java.time.LocalDate;

/**
 *
 * @author hoang hung
 */
public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Gender gender;
    private LocalDate dob;
    private LocalDate joinAt;
    private String avatarUrl;
    private String address;
    private Role role;
    private int balance;

    public User() {
    }

    public User(int id, String username, String password, String email, String phone, Gender gender, LocalDate dob,
            LocalDate joinAt, String avatarUrl, String address, Role role, int balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.dob = dob;
        this.joinAt = joinAt;
        this.avatarUrl = avatarUrl;
        this.address = address;
        this.role = role;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getJoinAt() {
        return joinAt;
    }

    public void setJoinAt(LocalDate joinAt) {
        this.joinAt = joinAt;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
                + ", phone=" + phone + ", gender=" + gender + ", dob=" + dob + ", joinAt=" + joinAt + ", avatarUrl="
                + avatarUrl + ", address=" + address + ", role=" + role + ", balance=" + balance + '}';
    }
}
