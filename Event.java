/*
 * Felicity Smallwood
 * Group 1, Alumni Program
 */
package com.cisp1020.group1.alumniprogram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felicity Smallwood
 */
public class Event  implements Serializable{

    private String name;
    private String description;
    private ArrayList<GuestSpeaker> presenters;
    private String date;
    private String time;
    private String duration;
    private String room;
    private int totalSeats;
    private ArrayList<Person> participants;

    /**
     * 
     * @param name
     * @param description
     * @param presenters
     * @param date
     * @param time
     * @param duration
     * @param room
     * @param totalSeats 
     */
    public Event(String name, String description, ArrayList<GuestSpeaker> presenters, String date, String time, String duration, String room, int totalSeats) {
        this.name = name;
        this.description = description;
        this.presenters = presenters;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.room = room;
        this.totalSeats = totalSeats;

        this.participants = new ArrayList<>();
    }

    /**
     * 
     * @param name
     * @param description
     * @param presenters
     * @param date
     * @param time
     * @param duration
     * @param room
     * @param totalSeats
     * @param participants 
     */
    public Event(String name, String description, ArrayList<GuestSpeaker> presenters, String date, String time, String duration, String room, int totalSeats, ArrayList<Person> participants) {
        this.name = name;
        this.description = description;
        this.presenters = presenters;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.room = room;
        this.totalSeats = totalSeats;
        this.participants = participants;
    }
    
    /**
     * 
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return 
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return 
     */
    public ArrayList<GuestSpeaker> getPresenters() {
        return presenters;
    }

    /**
     * 
     * @param presenters 
     */
    public void setPresenters(ArrayList<GuestSpeaker> presenters) {
        this.presenters = presenters;
    }

    /**
     * 
     * @return 
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date 
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 
     * @return 
     */
    public String getTime() {
        return time;
    }

    /**
     * 
     * @param time 
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 
     * @return 
     */
    public String getDuration() {
        return duration;
    }

    /**
     * 
     * @param duration 
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * 
     * @return 
     */
    public String getRoom() {
        return room;
    }

    /**
     * 
     * @param room 
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * 
     * @return 
     */
    public int getTotalSeats() {
        return totalSeats;
    }

    /**
     * 
     * @param totalSeats 
     */
    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    /**
     * 
     * @return 
     */
    public List<Person> getParticipants() {
        return participants;
    }

    /**
     * 
     * @param participant
     * @return 
     */
    public boolean addParticipant(Person participant) {
        if (areSeatsAvailable()) {
            participants.add(participant);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * @param presenter 
     */
    public void addPresenter(GuestSpeaker presenter) {
        presenters.add(presenter);
    }

    /**
     * 
     * @return 
     */
    public int getNumberOfParticipants() {
        return participants.size();
    }

    /**
     * 
     * @return 
     */
    public int getNumberOfPresenters() {
        return presenters.size();
    }

    /**
     * 
     * @return 
     */
    public int getAvailableSeats() {
        return totalSeats - participants.size();
    }

    /**
     * 
     * @return 
     */
    public boolean areSeatsAvailable() {
        return (participants.size() <= totalSeats);
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        String presentersAsString = "";

        for(GuestSpeaker p : presenters) presentersAsString += (p.getName() + "\n" + "   ");

        return String.format("%s - %s at %s in Room %s - %d Seats Available - Duration: %s - %n   Description: %s%n   Presenters: %s%n", name, date, time, room, getAvailableSeats(), duration, description, presentersAsString);
    }
}
