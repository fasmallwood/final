/*
 * Christian Jordan Long
 * Group 1, Alumni Program
 */
package com.cisp1020.group1.alumniprogram;

import java.io.Serializable;

/**
 *
 * @author Christian Jordan Long
 */
public class Alumni extends Person implements Serializable {

    private static int count = 0;

    private int number;
    private String major;
    private int graduationYear;

    /**
     * Create a new Alumni
     *
     * @param major major
     * @param graduationYear graduation year
     */
    public Alumni(String major, int graduationYear) {
        super();

        count++;
        number = count;

        this.major = major;
        this.graduationYear = graduationYear;
    }

    /**
     * Create a new alumni
     *
     * @param name name
     * @param address address
     * @param phoneNumber phone number
     * @param emailAddress email address
     * @param organization organization
     * @param job job
     * @param major major
     * @param graduationYear graduation year
     */
    public Alumni(String name, String address, String phoneNumber, String emailAddress, String organization, String job, String major, int graduationYear) {
        super(name, address, phoneNumber, emailAddress, organization, job);
        this.major = major;
        this.graduationYear = graduationYear;
    }

    /**
     * Get the alumni's major
     *
     * @return major
     */
    public String getMajor() {
        return major;
    }

    /**
     * Set the alumni's major
     *
     * @param major major
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * Get the graduation year
     *
     * @return graduation year
     */
    public int getGraduationYear() {
        return graduationYear;
    }

    /**
     * Set the graduation year
     *
     * @param graduationYear graduation year
     */
    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    /**
     * Get the alumni's number
     *
     * @return number
     */
    public int getNumber() {
        return number;
    }

    /**
     * String representation of Alumni
     *
     * @return alumni as a string
     */
    @Override
    public String toString() {
        return String.format("#%d: Name: %s, Address: %s, Phone: %s, Email: %s, Organization: %s, Job: %s, Major: %s, Graduation Year: %d%n", number, getName(), getAddress(), getPhoneNumber(), getEmailAddress(), getOrganization(), getJob(), major, graduationYear);
    }
}
