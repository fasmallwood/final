/*
 * Christian Jordan Long
 * Group 1, Alumni Program
 */
package com.cisp1020.group1.alumniprogram;

import java.io.*;

/**
 *
 * @author Christian Jordan Long
 */
public class Person implements Serializable {

    private String name;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private String organization;
    private String job;

    /**
     * Create an empty person
     */
    public Person() {

    }

    /**
     * Create a new person
     *
     * @param name name
     * @param address address
     * @param phoneNumber phone number
     * @param emailAddress email address
     * @param organization organization
     * @param job job
     */
    public Person(String name, String address, String phoneNumber, String emailAddress, String organization, String job) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.organization = organization;
        this.job = job;
    }

    /**
     * Get the person's name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the person's name
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the person's address
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the person's address
     *
     * @param address address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the person's phone number
     *
     * @return phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the person's phone number
     *
     * @param phoneNumber phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the person's email address
     *
     * @return email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Set the person's email address
     *
     * @param emailAddress email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Get the person's organization
     *
     * @return organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * Set the person's organization
     *
     * @param organization organization
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * Get the person's job
     *
     * @return job
     */
    public String getJob() {
        return job;
    }

    /**
     * Set the person's job
     *
     * @param job job
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * String representation of Person
     *
     * @return person as a string
     */
    @Override
    public String toString() {
        return String.format("%s - Address: %s, Phone: %s, Email: %s, Organization: %s, Job: %s%n", name, address, phoneNumber, emailAddress, organization, job);
    }
}
