/*
 * Christian Jordan Long
 * Group 1, Alumni Program
 */
package com.cisp1020.group1.alumniprogram;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Christian Jordan Long
 */
public class Reports {

    /**
     * Get a list of events within the given year
     *
     * @param EventDatabase event database
     * @param year year
     * @return list of events in that year
     */
    public static ArrayList<Event> eventsInYear(ArrayList<Event> EventDatabase, int year) {
        ArrayList<Event> events = new ArrayList<>();

        if (EventDatabase == null) {
            return null;
        }

        EventDatabase.stream().filter(event -> (event.getDate().contains(Integer.toString(year)))).forEachOrdered(event -> {
            events.add(event);
        });

        if (!events.isEmpty()) {
            try {
                String filename = "EventsInYear Report - " + new SimpleDateFormat("yyyyMMdd HHmm'.txt'").format(new Date());

                File file = new File(filename);
                FileWriter fw = new FileWriter(file);

                for (Event event : events) {
                    fw.write(event.toString());
                }

                fw.close();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }

        return events;
    }

    /**
     * Print all alumni in the database
     *
     * @param AlumniDatabase alumni database
     * @return all alumni represented in a string
     */
    public static String outputAllAlumni(ArrayList<Alumni> AlumniDatabase) {
        String output = "";

        if (AlumniDatabase == null) {
            return null;
        }

        output = AlumniDatabase.stream().map(alumni -> alumni.toString()).reduce(output, String::concat);

        try {
            String filename = "AllAlumni Report - " + new SimpleDateFormat("yyyyMMdd HHmm'.txt'").format(new Date());

            File file = new File(filename);
            FileWriter fw = new FileWriter(file);

            fw.write(output);

            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

        return output;
    }

    /**
     * Print all guest speakers in the database
     *
     * @param GuestSpeakerDatabase Speaker database
     * @return all guest speakers represented in a string
     */
    public static String outputAllGuestSpeakers(ArrayList<GuestSpeaker> GuestSpeakerDatabase) {
        String output = "";

        if (GuestSpeakerDatabase == null) {
            return null;
        }

        output = GuestSpeakerDatabase.stream().map(speaker -> speaker.toString()).reduce(output, String::concat);

        try {
            String filename = "AllGuestSpeakers Report - " + new SimpleDateFormat("yyyyMMdd HHmm'.txt'").format(new Date());

            File file = new File(filename);
            FileWriter fw = new FileWriter(file);

            fw.write(output);

            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

        return output;
    }

    /**
     * Print all donors in the database
     *
     * @param DonorDatabase donor database
     * @return all donors represented in a string
     */
    public static String outputAllDonors(ArrayList<Donor> DonorDatabase) {
        String output = "";

        if (DonorDatabase == null) {
            return null;
        }

        output = DonorDatabase.stream().map(donor -> donor.toString()).reduce(output, String::concat);

        try {
            String filename = "AllDonors Report - " + new SimpleDateFormat("yyyyMMdd HHmm'.txt'").format(new Date());

            File file = new File(filename);
            FileWriter fw = new FileWriter(file);

            fw.write(output);

            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

        return output;
    }

    /**
     * Print all events in the database
     *
     * @param EventDatabase event database
     * @return all events represented in a string
     */
    public static String outputAllEvents(ArrayList<Event> EventDatabase) {
        String output = "";

        if (EventDatabase == null) {
            return null;
        }

        output = EventDatabase.stream().map(event -> event.toString()).reduce(output, String::concat);

        try {
            String filename = "AllEvents Report - " + new SimpleDateFormat("yyyyMMdd HHmm'.txt'").format(new Date());

            File file = new File(filename);
            FileWriter fw = new FileWriter(file);

            fw.write(output);

            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

        return output;
    }
}
