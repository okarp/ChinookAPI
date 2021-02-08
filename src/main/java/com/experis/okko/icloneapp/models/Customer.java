package com.experis.okko.icloneapp.models;

/*
 * Okko Partanen
 * Experis Academy Task 4
 *
 * Data access class for Customers.
 *
 */

public class Customer {

    private String firstName;
    private String lastName;
    private int customerId;
    private String postalCode;
    private String country;
    private String phoneNumber;
    private String email;

    public Customer(){

    }

    public Customer(String firstName, String lastName, int customerId, String postalCode, String country, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerId = customerId;
        this.postalCode = postalCode;
        this.country = country;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
