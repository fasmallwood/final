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
public class Donor extends Person implements Serializable {

    private double totalDonated;

    /**
     * Create an empty donor
     */
    public Donor() {

    }

    /**
     * Create a new donor
     *
     * @param name name
     * @param address address
     * @param phoneNumber phone number
     * @param emailAddress email address
     * @param organization organization
     * @param job job
     */
    public Donor(String name, String address, String phoneNumber, String emailAddress, String organization, String job) {
        super(name, address, phoneNumber, emailAddress, organization, job);
    }

    /**
     * Get total amount donated by this donor
     *
     * @return amount donated
     */
    public double getTotalDonated() {
        return totalDonated;
    }

    /**
     * Donate as this donor
     *
     * @param amount donation amount
     */
    public void donate(double amount) {
        totalDonated += amount;
    }

    /**
     * String representation of Donor
     *
     * @return donor as a string
     */
    @Override
    public String toString() {
        return String.format("Name: %s, Address: %s, Phone: %s, Email: %s, Organization: %s, Job: %s, Total Donated: %.2f%n", getName(), getAddress(), getPhoneNumber(), getEmailAddress(), getOrganization(), getJob(), totalDonated);
    }
}
