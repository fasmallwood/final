/*
 * Christian Jordan Long
 * Group 1, Alumni Program
 */
package com.cisp1020.group1.alumniprogram;

/**
 *
 * @author Christian Jordan Long
 */
public class GuestSpeaker extends Person {

    private String topicOfExpertise;

    /**
     * Create a guest speaker
     *
     * @param topicOfExpertise topic of expertise
     */
    public GuestSpeaker(String topicOfExpertise) {
        this.topicOfExpertise = topicOfExpertise;
    }

    /**
     * Create a new Guest Speaker
     *
     * @param name name
     * @param address address
     * @param phoneNumber phone number
     * @param emailAddress email address
     * @param organization organization
     * @param job job
     * @param topicOfExpertise topic of expertise
     */
    public GuestSpeaker(String name, String address, String phoneNumber, String emailAddress, String organization, String job, String topicOfExpertise) {
        super(name, address, phoneNumber, emailAddress, organization, job);
        this.topicOfExpertise = topicOfExpertise;
    }

    /**
     * Get topic of expertise
     *
     * @return topic of expertise
     */
    public String getTopicOfExpertise() {
        return topicOfExpertise;
    }

    /**
     * Set the topic of expertise
     *
     * @param topicOfExpertise topic of expertise
     */
    public void setTopicOfExpertise(String topicOfExpertise) {
        this.topicOfExpertise = topicOfExpertise;
    }

    /**
     * String representation of Guest Speaker
     *
     * @return speaker as a string
     */
    @Override
    public String toString() {
        return String.format("Name: %s, Address: %s, Phone: %s, Email: %s, Organization: %s, Job: %s, Topic of Expertise: %s%n", getName(), getAddress(), getPhoneNumber(), getEmailAddress(), getOrganization(), getJob(), topicOfExpertise);
    }
}
