/*
 * Christian Jordan Long
 * Group 1, Alumni Program
 */
package com.cisp1020.group1.alumniprogram;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Main entry point for Group 1's AlumniProgram
 *
 * @author Christian Jordan Long
 */
public class AlumniProgram {

    /**
     * Enum for easy use of the various types of Person
     */
    public static enum PersonType {
        Alumni,
        GuestSpeaker,
        Donor
    }

    // Databases
    public static ArrayList<Alumni> AlumniDatabase = new ArrayList<>();
    public static ArrayList<GuestSpeaker> GuestSpeakerDatabase = new ArrayList<>();
    public static ArrayList<Donor> DonorDatabase = new ArrayList<>();
    public static ArrayList<Event> EventDatabase = new ArrayList<>();

    public static Scanner in = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AlumniDatabase = FileHandler.readAlumniData();
        GuestSpeakerDatabase = FileHandler.readSpeakerData();
        DonorDatabase = FileHandler.readDonorData();
        EventDatabase = FileHandler.readEventData();

        if (AlumniDatabase == null) {
            AlumniDatabase = new ArrayList<>();
        }
        if (GuestSpeakerDatabase == null) {
            GuestSpeakerDatabase = new ArrayList<>();
        }
        if (DonorDatabase == null) {
            DonorDatabase = new ArrayList<>();
        }
        if (EventDatabase == null) {
            EventDatabase = new ArrayList<>();
        }

        String input = null;

        while (!"6".equals(input)) {
            System.out.printf(
                    "\n------- Alumni Program -------%n%n"
                    + "1) Alumni Registry%n"
                    + "2) Guest Speaker Registry%n"
                    + "3) Donor Registry%n"
                    + "4) Event Registry%n"
                    + "5) Reports%n"
                    + "6) Exit%n%n"
            );

            input = in.nextLine();

            if (input != null) {
                switch (input) {
                    case "1" : {
                        viewRegistry(PersonType.Alumni);
                        break;
                    }
                    case "2" : {
                        viewRegistry(PersonType.GuestSpeaker);
                        break;
                    }
                    case "3" : {
                        viewRegistry(PersonType.Donor);
                        break;
                    }
                    case "4" : {
                        eventRegistry();
                        break;
                    }
                    case "5" : {
                        viewReports();
                        break;
                    }
                    case "6" : {
                        break;
                    }
                    default : {
                        System.out.printf("Invalid input. Please enter a value between 1 and 6.%n%n");
                    }
                }
            }
        }
    }

    /**
     * View the list of reports available
     *
     */
    public static void viewReports() {
        String input = null;

        while (!"6".equals(input)) {
            System.out.printf("%n----- Reports -----%n");

            System.out.printf(
                    "%n1) View all Alumni%n"
                    + "2) View all Guest Speakers%n"
                    + "3) View all Donors%n"
                    + "4) View all Events%n"
                    + "5) View all Events in a given year%n"
                    + "6) Return to Main Menu%n%n"
            );

            input = in.nextLine();

            if (null != input) {
                switch (input) {
                    case "1" :
                        System.out.println("\nAll Alumni in the databse:\n\n" + Reports.outputAllAlumni(AlumniDatabase));
                    case "2" :
                        System.out.println("\nAll Guest Speakers in the databse:\n\n" + Reports.outputAllGuestSpeakers(GuestSpeakerDatabase));
                    case "3" :
                        System.out.println("\nAll Donors in the databse:\n\n" + Reports.outputAllDonors(DonorDatabase));
                    case "4" : {
                        System.out.println("\nAll Events in the databse:\n\n" + Reports.outputAllEvents(EventDatabase));
                        break;
                    }
                    case "5" : {
                        System.out.println("Enter a year for events: ");
                        String year = in.nextLine();

                        while (!isNumericLong(year)) {
                            System.out.println("Invalid input. Please enter numerical input only.\n\nEnter a year for events: ");
                            year = in.nextLine();
                        }

                        ArrayList<Event> foundEvents = Reports.eventsInYear(EventDatabase, Integer.parseInt(year));

                        if (foundEvents == null || foundEvents.isEmpty()) {
                            System.out.printf("No events found in %s%n", year);
                        }
                        else{
                            System.out.printf("%nFound the following events in the year %s:%n%n", year);
                            foundEvents.forEach(event -> {
                                System.out.println(event.toString());
                            });
                        }

                        break;
                    }
                    case "6" : {
                        break;
                    }
                    default : {
                        System.out.printf("Invalid input. Please enter a value between 1 and 6.%n%n");
                    }
                }
            }
        }
    }

    /**
     * View the registry of a given Person type Generalizes the registry menu to
     * be (mostly) type agnostic
     *
     * @param type Type of person to view the registry for
     */
    public static void viewRegistry(PersonType type) {
        String typeString = getTypeAsString(type);

        String input = null;

        if (type != PersonType.Donor) {
            while (!"4".equals(input)) {
                System.out.printf("%n----- %s Registry -----%n", typeString);

                System.out.printf(
                        "%n1) Register new %s%n"
                        + "2) Edit existing %s%n"
                        + "3) Delete existing %s%n"
                        + "4) Return to Main Menu%n%n",
                        typeString,
                        typeString,
                        typeString
                );

                input = in.nextLine();

                if (null != input) {
                    switch (input) {
                        case "1" :
                            registerPerson(type);
                        case "2" :
                            editExistingPerson(type);
                        case "3" :
                            deleteExistingPerson(type);
                        case "4" : {
                            break;
                        }
                        default : {
                            System.out.printf("Invalid input. Please enter a value between 1 and 4.%n%n");
                        }
                    }
                }
            }
        } else {
            while (!"5".equals(input)) {
                System.out.printf("%n----- %s Registry -----%n", typeString);

                System.out.printf(
                        "%n1) Register new %s%n"
                        + "2) Edit existing %s%n"
                        + "3) Delete existing %s%n"
                        + "4) Donate%n"
                        + "5) Return to Main Menu%n%n",
                        typeString,
                        typeString,
                        typeString
                );

                input = in.nextLine();

                if (null != input) {
                    switch (input) {
                        case "1" :
                            registerPerson(type);
                        case "2" :
                            editExistingPerson(type);
                        case "3" :
                            deleteExistingPerson(type);
                        case "4" :
                            processDonation();
                        case "5" : {
                            break;
                        }
                        default : {
                            System.out.printf("Invalid input. Please enter a value between 1 and 5.%n%n");
                        }
                    }
                }
            }
        }
    }

    /**
     * Register or update a Person in the database associated with their Type
     *
     * @param type Type of person to register
     */
    public static void registerPerson(PersonType type) {
        // TODO: Verify all input
        String typeString = getTypeAsString(type);
        System.out.printf("%n----- Register/Update %s -----%n", typeString);

        System.out.println("\nEnter your name: ");
        String name = in.nextLine();

        System.out.println("Enter your address: ");
        String address = in.nextLine();

        System.out.println("Enter your phone number (numeric only, e.x. 4235550055): ");
        String phone = in.nextLine();

        while (!isNumericLong(phone)) {
            System.out.println("Invalid input. Please enter numerical input only.\n\nEnter your phone number (numeric only, e.x. 4235550055): ");
            phone = in.nextLine();
        }

        System.out.println("Enter your email address (e.x. michael@gmail.com");
        String email = in.nextLine();

        while (!isValidEmail(email)) {
            System.out.println("Invalid input. Please enter a valid email address.\n\nEnter your email address (e.x. michael@gmail.com): ");
            email = in.nextLine();
        }

        System.out.println("Enter your organization: ");
        String organization = in.nextLine();

        System.out.println("Enter your job title: ");
        String job = in.nextLine();

        // Additional I/O based on PersonType used
        switch (type) {
            case Alumni : {
                System.out.println("Enter your major: ");
                String major = in.nextLine();

                System.out.println("Enter your graduation year: ");
                String gradYear = in.nextLine();

                while (!isNumericLong(gradYear)) {
                    System.out.println("Invalid input. Please enter numerical input only.\n\nEnter your graduation year: ");
                    gradYear = in.nextLine();
                }

                Alumni alumni = new Alumni(name, address, phone, email, organization, job, major, Integer.parseInt(gradYear));
                AlumniDatabase.add(alumni);

                FileHandler.writeAlumniData(AlumniDatabase);
            }
            case GuestSpeaker : {
                System.out.println("Enter your topic of expertise: ");
                String topic = in.nextLine();

                GuestSpeaker speaker = new GuestSpeaker(name, address, phone, email, organization, job, topic);
                GuestSpeakerDatabase.add(speaker);

                FileHandler.writeGuestSpeakerData(GuestSpeakerDatabase);
            }
            case Donor : {
                Donor donor = new Donor(name, address, phone, email, organization, job);

                DonorDatabase.add(donor);

                FileHandler.writeDonorData(DonorDatabase);
            }
            default : {

            }
        }

        System.out.printf("%s has been registered to the %s database.%n", name, typeString);
    }

    public static void editExistingPerson(PersonType type) {
        String typeString = getTypeAsString(type);

        System.out.printf("%n----- Edit %s's Records -----%n", typeString);

        System.out.printf("%nEnter %s's name: %n", typeString);

        String name = in.nextLine();

        switch (type) {
            case Alumni : {
                Alumni alumni = null;

                for (Alumni a : AlumniDatabase) {
                    if (a.getName().equalsIgnoreCase(name)) {
                        alumni = a;
                    }
                }

                if (null != alumni) {
                    editExistingAlumni(alumni);

                    System.out.println("\nRecords for " + alumni.getName() + " have been updated.");
                } else {
                    System.out.printf("%nNo %s named %s found in the registry.%n", typeString, name);
                }
            }
            case GuestSpeaker : {
                GuestSpeaker speaker = null;

                for (GuestSpeaker s : GuestSpeakerDatabase) {
                    if (s.getName().equalsIgnoreCase(name)) {
                        speaker = s;
                    }
                }

                if (null != speaker) {
                    editExistingGuestSpeaker(speaker);

                    System.out.println("\nRecords for " + speaker.getName() + " have been updated.");
                } else {
                    System.out.printf("%nNo %s named %s found in the registry.%n", typeString, name);
                }
            }
            case Donor : {
                Donor donor = null;

                for (Donor d : DonorDatabase) {
                    if (d.getName().equalsIgnoreCase(name)) {
                        donor = d;
                    }
                }

                if (null != donor) {
                    editExistingDonor(donor);

                    System.out.println("\nRecords for " + donor.getName() + " have been updated.");
                } else {
                    System.out.printf("%nNo %s named %s found in the registry.%n", typeString, name);
                }
            }
            default : {
                // ?
            }
        }
    }

    /**
     * Edit an existing alumni
     *
     * @param alumni the alumni to edit
     */
    public static void editExistingAlumni(Alumni alumni) {
        int index = AlumniDatabase.indexOf(alumni);

        String input = null;

        while (!"9".equals(input)) {
            System.out.printf("%nModifying %s's Records%n", alumni.getName());

            System.out.printf(
                    "%n1) Change name%n"
                    + "2) Change address%n"
                    + "3) Change phone number%n"
                    + "4) Change email address%n"
                    + "5) Change organization%n"
                    + "6) Change job%n"
                    + "7) Change major%n"
                    + "8) Change graduation year%n"
                    + "9) Return to Main Menu%n%n"
            );

            input = in.nextLine();

            if (null != input) {
                switch (input) {
                    case "1" : {
                        System.out.println("Enter new name: ");
                        String eventName = in.nextLine();
                        alumni.setName(eventName);
                        System.out.println("Alumni name has been updated.");
                    }
                    case "2" : {
                        System.out.println("Enter new address: ");
                        String address = in.nextLine();
                        alumni.setAddress(address);
                        System.out.println("Alumni address has been updated.");
                    }
                    case "3" : {
                        System.out.println("Enter your phone number (numeric only, e.x. 4235550055): ");
                        String phone = in.nextLine();

                        while (!isNumericLong(phone)) {
                            System.out.println("Invalid input. Please enter numerical input only.\n\nEnter your phone number (numeric only, e.x. 4235550055): ");
                            phone = in.nextLine();
                        }

                        alumni.setPhoneNumber(phone);
                        System.out.println("Alumni phone number has been updated.");
                    }
                    case "4" : {
                        System.out.println("Enter your email address (e.x. michael@gmail.com");
                        String email = in.nextLine();

                        while (!isValidEmail(email)) {
                            System.out.println("Invalid input. Please enter a valid email address.\n\nEnter your email address (e.x. michael@gmail.com): ");
                            email = in.nextLine();
                        }
                        alumni.setEmailAddress(email);
                        System.out.println("Alumni email address has been updated.");
                    }
                    case "5" : {
                        System.out.println("Enter new organization: ");
                        String organization = in.nextLine();
                        alumni.setOrganization(organization);
                        System.out.println("Alumni organization has been updated.");
                    }
                    case "6" : {
                        System.out.println("Enter new job: ");
                        String job = in.nextLine();
                        alumni.setJob(job);
                        System.out.println("Alumni job has been updated.");
                    }
                    case "7" : {
                        System.out.println("Enter new major: ");
                        String major = in.nextLine();
                        alumni.setMajor(major);
                        System.out.println("Alumni major has been updated.");
                    }
                    case "8" : {
                        System.out.println("Enter your graduation year: ");
                        String gradYear = in.nextLine();

                        while (!isNumericLong(gradYear)) {
                            System.out.println("Invalid input. Please enter numerical input only.\n\nEnter your graduation year: ");
                            gradYear = in.nextLine();
                        }
                        alumni.setGraduationYear(Integer.parseInt(gradYear));
                        System.out.println("Alumni graduation year has been updated.");
                    }
                    case "9" : {
                        break;
                    }
                    default : {
                        System.out.printf("Invalid input. Please enter a value between 1 and 9.%n%n");
                    }
                }
            }
        }

        AlumniDatabase.set(index, alumni);

        FileHandler.writeAlumniData(AlumniDatabase);
    }

    /**
     * Edit an existing GuestSpeaker
     *
     * @param guestSpeaker the GuestSpeaker to edit
     */
    public static void editExistingGuestSpeaker(GuestSpeaker guestSpeaker) {
        int index = GuestSpeakerDatabase.indexOf(guestSpeaker);

        String input = null;

        while (!"8".equals(input)) {
            System.out.printf("%nModifying %s's Records%n", guestSpeaker.getName());

            System.out.printf(
                    "%n1) Change name%n"
                    + "2) Change address%n"
                    + "3) Change phone number%n"
                    + "4) Change email address%n"
                    + "5) Change organization%n"
                    + "6) Change job%n"
                    + "7) Change topic of expertise%n"
                    + "8) Return to Main Menu%n%n"
            );

            input = in.nextLine();

            if (null != input) {
                switch (input) {
                    case "1" : {
                        System.out.println("Enter new name: ");
                        String eventName = in.nextLine();
                        guestSpeaker.setName(eventName);
                        System.out.println("Speaker name has been updated.");
                        break;
                    }
                    case "2" : {
                        System.out.println("Enter new address: ");
                        String address = in.nextLine();
                        guestSpeaker.setAddress(address);
                        System.out.println("Speaker address has been updated.");
                        break;
                    }
                    case "3" : {
                        System.out.println("Enter your phone number (numeric only, e.x. 4235550055): ");
                        String phone = in.nextLine();

                        while (!isNumericLong(phone)) {
                            System.out.println("Invalid input. Please enter numerical input only.\n\nEnter your phone number (numeric only, e.x. 4235550055): ");
                            phone = in.nextLine();
                        }
                        guestSpeaker.setPhoneNumber(phone);
                        System.out.println("Speaker phone number has been updated.");
                        break;
                    }
                    case "4" : {
                        System.out.println("Enter your email address (e.x. michael@gmail.com");
                        String email = in.nextLine();

                        while (!isValidEmail(email)) {
                            System.out.println("Invalid input. Please enter a valid email address.\n\nEnter your email address (e.x. michael@gmail.com): ");
                            email = in.nextLine();
                        }
                        guestSpeaker.setEmailAddress(email);
                        System.out.println("Speaker email address has been updated.");
                        break;
                    }
                    case "5" : {
                        System.out.println("Enter new organization: ");
                        String organization = in.nextLine();
                        guestSpeaker.setOrganization(organization);
                        System.out.println("Speaker organization has been updated.");
                        break;
                    }
                    case "6" : {
                        System.out.println("Enter new job: ");
                        String job = in.nextLine();
                        guestSpeaker.setJob(job);
                        System.out.println("Speaker job has been updated.");
                        break;
                    }
                    case "7" : {
                        System.out.println("Enter new topic of expertise: ");
                        String topic = in.nextLine();
                        guestSpeaker.setTopicOfExpertise(topic);
                        System.out.println("Speaker topic of expertise has been updated.");
                        break;
                    }
                    case "8" : {
                        break;
                    }
                    default : {
                        System.out.printf("Invalid input. Please enter a value between 1 and 8.%n%n");
                    }
                }
            }
        }

        GuestSpeakerDatabase.set(index, guestSpeaker);

        FileHandler.writeGuestSpeakerData(GuestSpeakerDatabase);
    }

    /**
     * Edit an existing Donor
     *
     * @param donor the Donor to edit
     */
    public static void editExistingDonor(Donor donor) {
        int index = DonorDatabase.indexOf(donor);

        String input = null;

        while (!"7".equals(input)) {
            System.out.printf("%nModifying %s's Records%n", donor.getName());

            System.out.printf(
                    "%n1) Change name%n"
                    + "2) Change address%n"
                    + "3) Change phone number%n"
                    + "4) Change email address%n"
                    + "5) Change organization%n"
                    + "6) Change job%n"
                    + "7) Return to Main Menu%n%n"
            );

            input = in.nextLine();

            if (null != input) {
                switch (input) {
                    case "1" : {
                        System.out.println("Enter new name: ");
                        String eventName = in.nextLine();
                        donor.setName(eventName);
                        System.out.println("Donor name has been updated.");
                        break;
                    }
                    case "2" : {
                        System.out.println("Enter new address: ");
                        String address = in.nextLine();
                        donor.setAddress(address);
                        System.out.println("Donor address has been updated.");
                        break;
                    }
                    case "3" : {
                        System.out.println("Enter your phone number (numeric only, e.x. 4235550055): ");
                        String phone = in.nextLine();

                        while (!isNumericLong(phone)) {
                            System.out.println("Invalid input. Please enter numerical input only.\n\nEnter your phone number (numeric only, e.x. 4235550055): ");
                            phone = in.nextLine();
                        }
                        donor.setPhoneNumber(phone);
                        System.out.println("Donor phone number has been updated.");
                        break;
                    }
                    case "4" : {
                        System.out.println("Enter your email address (e.x. michael@gmail.com");
                        String email = in.nextLine();

                        while (!isValidEmail(email)) {
                            System.out.println("Invalid input. Please enter a valid email address.\n\nEnter your email address (e.x. michael@gmail.com): ");
                            email = in.nextLine();
                        }
                        donor.setEmailAddress(email);
                        System.out.println("Donor email address has been updated.");
                        break;
                    }
                    case "5" : {
                        System.out.println("Enter new organization: ");
                        String organization = in.nextLine();
                        donor.setOrganization(organization);
                        System.out.println("Donor organization has been updated.");
                        break;
                    }
                    case "6" : {
                        System.out.println("Enter new job: ");
                        String job = in.nextLine();
                        donor.setJob(job);
                        System.out.println("Donor job has been updated.");
                        break;
                    }
                    case "7" : {
                        break;
                    }
                    default : {
                        System.out.printf("Invalid input. Please enter a value between 1 and 7.%n%n");
                    }
                }
            }
        }

        DonorDatabase.set(index, donor);

        FileHandler.writeDonorData(DonorDatabase);
    }

    /**
     * Delete an existing person from the databases
     *
     * @param type Type of person to delete
     */
    public static void deleteExistingPerson(PersonType type) {
        String typeString = getTypeAsString(type);

        System.out.printf("%n----- Remove %s -----%n", typeString);

        System.out.printf("%nEnter %s's name: ", typeString);

        String name = in.nextLine();

        switch (type) {
            case Alumni : {
                Alumni alumni = null;

                for (Alumni a : AlumniDatabase) {
                    if (a.getName().equalsIgnoreCase(name)) {
                        alumni = a;
                    }
                }

                if (null != alumni) {
                    AlumniDatabase.remove(alumni);

                    FileHandler.writeAlumniData(AlumniDatabase);

                    System.out.println("\n" + alumni.getName() + " has been removed from the registry.");
                } else {
                    System.out.printf("%nNo %s named %s found in the registry.%n", typeString, name);
                }
                break;
            }
            case GuestSpeaker : {
                GuestSpeaker speaker = null;

                for (GuestSpeaker s : GuestSpeakerDatabase) {
                    if (s.getName().equalsIgnoreCase(name)) {
                        speaker = s;
                    }
                }

                if (null != speaker) {
                    GuestSpeakerDatabase.remove(speaker);

                    FileHandler.writeGuestSpeakerData(GuestSpeakerDatabase);

                    System.out.println("\n" + speaker.getName() + " has been removed from the registry.");
                } else {
                    System.out.printf("%nNo %s named %s found in the registry.%n", typeString, name);
                }
                break;
            }
            case Donor : {
                Donor donor = null;

                for (Donor d : DonorDatabase) {
                    if (d.getName().equalsIgnoreCase(name)) {
                        donor = d;
                    }
                }

                if (null != donor) {
                    DonorDatabase.remove(donor);

                    FileHandler.writeDonorData(DonorDatabase);

                    System.out.println("\n" + donor.getName() + " has been removed from the registry.");
                } else {
                    System.out.printf("%nNo %s named %s found in the registry.%n", typeString, name);
                }
                break;
            }
            default : {
                // ?
            }
        }
    }

    /**
     * Process a donation for a Donor
     */
    public static void processDonation() {
        System.out.printf("%n----- Donate -----%n");

        System.out.printf("%nEnter donor's name: ");

        String name = in.nextLine();

        Donor donor = null;

        for (Donor d : DonorDatabase) {
            if (d.getName().equalsIgnoreCase(name)) {
                donor = d;
            }
        }

        if (null != donor) {
            System.out.printf("%nEnter your donation amount (e.x. $500.00): ");

            // Parse out double from input, removing $ sign if entered
            String donation = in.nextLine().replace("$", "");

            while (!isNumericDouble(donation) || Double.parseDouble(donation) <= 0) {
                System.out.println("Please enter a valid numeric donation input, and ensure it is greater than zero.\n\nEnter your donation amount (e.x. $500.00): ");
                donation = in.nextLine().replace("$", "");
            }

            donor.donate(Double.parseDouble(donation));

            FileHandler.writeDonorData(DonorDatabase);

            System.out.printf("%n%s has donated $%.2f to the foundation.%n", donor.getName(), Double.parseDouble(donation));
        } else {
            System.out.printf("%nNo donor named %s found in the registry.%n", name);
        }
    }

    /**
     * Handles the Event menu navigation
     */
    public static void eventRegistry() {
        String input = null;

        while (!"5".equals(input)) {
            System.out.printf("%n----- Event Registry -----%n");

            System.out.printf(
                    "%n1) Register for an event%n"
                    + "2) Create a new event%n"
                    + "3) Modify an existing event%n"
                    + "4) Cancel an existing event%n"
                    + "5) Return to Main Menu%n%n"
            );

            input = in.nextLine();

            if (null != input) {
                switch (input) {
                    case "1" :
                        registerForEvent();
                    case "2" :
                        createNewEvent();
                    case "3" :
                        modifyEvent();
                    case "4" :
                        cancelEvent();
                    case "5" : {
                        break;
                    }
                    default : {
                        System.out.printf("Invalid input. Please enter a value between 1 and 5.%n%n");
                    }
                }
            }
        }
    }

    /**
     * Register for an event
     */
    public static void registerForEvent() {
        System.out.printf("%n----- Register for an Event -----%n%n");

        if (EventDatabase.size() > 0) {
            for (int i = 0; i < EventDatabase.size(); i++) {
                Event e = EventDatabase.get(i);
                System.out.printf("%d) %s", i, e.toString());
            }

            boolean loop = true;

            while (loop) {
                String numStr = null;
                int number = -1;

                System.out.println("Enter the number of the event you would like to register for: ");

                while (number < 0 || number >= EventDatabase.size()) {
                    numStr = in.nextLine();

                    if (null == numStr || !isNumericLong(numStr)) {
                        System.out.println("Please enter numeric input only.\n\nEnter the number of the event you would like to register for: ");
                        continue;
                    }

                    number = Integer.parseInt(numStr);

                    if (number < 0 || number >= EventDatabase.size()) {
                        System.out.println("Invalid event number. Please enter a number from the list shown: ");
                    }
                }

                if (EventDatabase.get(number).getAvailableSeats() < 1) {
                    System.out.println("Selected event has no available seats. Please select a different event.");
                    continue;
                }

                Event event = EventDatabase.get(number);

                System.out.println("Would you like to register as an Attendee (A), Speaker (S), or Donor (D)? ");

                String pType = null;

                while (!"A".equals(pType) && !"S".equals(pType) && !"D".equals(pType)) {
                    pType = in.nextLine().toUpperCase();

                    if (null != pType) {
                        if (!"A".equals(pType) && !"S".equals(pType) && !"D".equals(pType)) {
                            System.out.println("Invalid input. Please enter A, S, or D: ");
                        }
                    }
                }

                System.out.printf("%nEnter your name: ");

                String name = in.nextLine();

                switch (pType) {
                    case "A" : {
                        Alumni alumni = null;

                        for (Alumni a : AlumniDatabase) {
                            if (a.getName().equalsIgnoreCase(name)) {
                                alumni = a;
                            }
                        }

                        if (null != alumni) {
                            event.addParticipant(alumni);

                            System.out.printf("%s has been registered for %s.%n", alumni.getName(), event.getName());

                            loop = false;
                        } else {
                            System.out.printf("%nNo alumni named %s found in the registry.%n", name);
                        }

                        break;
                    }
                    case "S" : {
                        GuestSpeaker speaker = null;

                        for (GuestSpeaker s : GuestSpeakerDatabase) {
                            if (s.getName().equalsIgnoreCase(name)) {
                                speaker = s;
                            }
                        }

                        if (null != speaker) {
                            event.addPresenter(speaker);

                            System.out.printf("%s has been registered for %s.%n", speaker.getName(), event.getName());

                            loop = false;
                        } else {
                            System.out.printf("%nNo Guest Speaker named %s found in the registry.%n", name);
                        }

                        break;
                    }
                    case "D" : {
                        Donor donor = null;

                        for (Donor d : DonorDatabase) {
                            if (d.getName().equalsIgnoreCase(name)) {
                                donor = d;
                            }
                        }

                        if (null != donor) {
                            event.addParticipant(donor);

                            System.out.printf("%s has been registered for %s.%n", donor.getName(), event.getName());

                            loop = false;
                        } else {
                            System.out.printf("%nNo donor named %s found in the registry.%n", name);
                        }

                        break;
                    }
                }
            }
        } else {
            System.out.println("No events have been created. Please create an event before attempting to register.");
        }
    }

    /**
     * Create a new event
     */
    public static void createNewEvent() {
        System.out.printf("%n----- Create an Event -----%n%n");

        System.out.println("Enter the first Guest Speaker's name: ");
        String speakerName = in.nextLine();

        GuestSpeaker speaker = null;

        for (GuestSpeaker s : GuestSpeakerDatabase) {
            if (s.getName().equalsIgnoreCase(speakerName)) {
                speaker = s;
            }
        }

        if (null != speaker) {
            System.out.println("Enter a name for the event: ");
            String eventName = in.nextLine();

            System.out.println("Enter a description for the event: ");
            String description = in.nextLine();

            System.out.println("Enter the date of the event (e.x. 12/25/2021): ");
            String date = in.nextLine();

            System.out.println("Enter the time of the event (e.x. 10:30 AM): ");
            String time = in.nextLine();

            System.out.println("Enter the duration of the event: ");
            String duration = in.nextLine();

            System.out.println("Enter the room number the event will be held in: ");
            String room = in.nextLine();

            while (!isNumericLong(room)) {
                System.out.println("Please enter numeric input only.\n\nEnter the room number the event will be held in: ");
                room = in.nextLine();
            }

            System.out.println("Enter the number of available seats: ");
            String totalSeats = in.nextLine();

            while (!isNumericLong(totalSeats)) {
                System.out.println("Please enter numeric input only.\n\nEnter the number of available seats: ");
                totalSeats = in.nextLine();
            }

            ArrayList<GuestSpeaker> presenters = new ArrayList<>();
            presenters.add(speaker);

            Event event = new Event(eventName, description, presenters, date, time, duration, room, Integer.parseInt(totalSeats));
            EventDatabase.add(event);

            FileHandler.writeEventData(EventDatabase);

            System.out.printf("Event \"%s\" been created.%n", eventName);
        } else {
            System.out.printf("%nNo Guest Speaker named %s found in the registry. Only a registered Guest Speaker can be selected for an event.%n", speakerName);
        }
    }

    /*
        Modify an Event
     */
    public static void modifyEvent() {
        System.out.printf("%n----- Modify an Event -----%n%n");

        if (EventDatabase.size() > 0) {
            for (int i = 0; i < EventDatabase.size(); i++) {
                Event e = EventDatabase.get(i);
                System.out.printf("%d) %s", i, e.toString());
            }

            String numStr = null;
            int number = -1;
            System.out.println("Enter the number of the event you would like to modify: ");

            while (number < 0 || number >= EventDatabase.size()) {
                numStr = in.nextLine();

                if (numStr == null || !isNumericLong(numStr)) {
                    System.out.println("Please enter numeric input only.\n\nEnter the number of the event you would like to modify: ");
                    continue;
                }

                number = Integer.parseInt(numStr);

                if (number < 0 || number >= EventDatabase.size()) {
                    System.out.println("Invalid event number. Please enter a number from the list shown: ");
                }
            }

            Event event = EventDatabase.get(number);

            String input = null;
            boolean netbeansConsoleBugFix = true;

            while (!"8".equals(input)) {
                System.out.printf("%nModifying \"%s\"%n", event.getName());

                System.out.printf(
                        "%n1) Change event name%n"
                        + "2) Change event description%n"
                        + "3) Change event date%n"
                        + "4) Change event time%n"
                        + "5) Change event duration%n"
                        + "6) Change event room%n"
                        + "7) Change total seats%n"
                        + "8) Return to Main Menu%n%n"
                );

                input = in.nextLine();

                if (netbeansConsoleBugFix) {
                    input = in.nextLine();
                    netbeansConsoleBugFix = false;
                }

                if (null != input) {
                    switch (input) {
                        case "1" : {
                            System.out.println("Enter new event name: ");
                            String eventName = in.nextLine();
                            event.setName(eventName);
                            System.out.println("Event name has been updated.");
                            break;
                        }
                        case "2" : {
                            System.out.println("Enter new event description: ");
                            String description = in.nextLine();
                            event.setDescription(description);
                            System.out.println("Event description has been updated.");
                            break;
                        }
                        case "3" : {
                            System.out.println("Enter new event date: ");
                            String date = in.nextLine();
                            event.setDate(date);
                            System.out.println("Event date has been updated.");
                            break;
                        }
                        case "4" :{
                            System.out.println("Enter new event time: ");
                            String time = in.nextLine();
                            event.setTime(time);
                            System.out.println("Event time has been updated.");
                            break;
                        }
                        case "5" : {
                            System.out.println("Enter new event duration: ");
                            String duration = in.nextLine();
                            event.setDuration(duration);
                            System.out.println("Event duration has been updated.");
                            break;
                        }
                        case "6" : {
                            System.out.println("Enter the room number the event will be held in: ");
                            String room = in.nextLine();

                            while (!isNumericLong(room)) {
                                System.out.println("Please enter numeric input only.\n\nEnter the room number the event will be held in: ");
                                room = in.nextLine();
                            }

                            event.setRoom(room);
                            System.out.println("Event room has been updated.");
                            break;
                        }
                        case "7" : {
                            System.out.println("Enter new total seats for event: ");

                            System.out.println("Enter new total seats for event: ");
                            String totalSeats = in.nextLine();

                            while (!isNumericLong(totalSeats)) {
                                System.out.println("Please enter numeric input only.\n\nEnter new total seats for event: ");
                                totalSeats = in.nextLine();
                            }

                            int seats = Integer.parseInt(totalSeats);

                            if (seats >= event.getNumberOfParticipants()) {
                                event.setTotalSeats(seats);
                                System.out.println("Event's total seats have been updated.");
                            } else {
                                System.out.printf("%nEvent currently has %d participants registered. Total seats cannot be set to %d because that is less than the number of already registered participants.%n%n", event.getNumberOfParticipants(), seats);
                            }
                            break;
                        }
                        case "8" : {
                            break;
                        }
                        default : {
                            System.out.printf("Invalid input. Please enter a value between 1 and 8.%n%n");
                        }
                    }
                }
            }

            EventDatabase.set(number, event);

            FileHandler.writeEventData(EventDatabase);

            System.out.printf("Event \"%s\" has been modified.", event.getName());
        } else {
            System.out.println("No events have been created. Please create an event before attempting to modify one.");
        }
    }

    /*
        Cancel an Event
     */
    public static void cancelEvent() {
        System.out.printf("%n----- Cancel an Event -----%n%n");

        if (EventDatabase.size() > 0) {
            for (int i = 0; i < EventDatabase.size(); i++) {
                Event e = EventDatabase.get(i);
                System.out.printf("%d) %s", i, e.toString());
            }

            String numStr = null;
            int number = -1;
            System.out.println("Enter the number of the event you would like to cancel: ");

            while (number < 0 || number >= EventDatabase.size()) {
                numStr = in.nextLine();

                if (numStr == null || !isNumericLong(numStr)) {
                    System.out.println("Please enter numeric input only.\n\nEnter the number of the event you would like to modify: ");
                    continue;
                }

                number = Integer.parseInt(numStr);

                if (number < 0 || number >= EventDatabase.size()) {
                    System.out.println("Invalid event number. Please enter a number from the list shown: ");
                }
            }

            Event event = EventDatabase.get(number);

            EventDatabase.remove(event);

            FileHandler.writeEventData(EventDatabase);

            System.out.printf("Event \"%s\" has been cancelled.", event.getName());
        } else {
            System.out.println("No events have been created. Please create an event before attempting to cancel one.");
        }
    }

    /**
     * Convert the PersonType enum to a string for UI output
     *
     * @param type PersonType value
     * @return String representation of the Type enum
     */
    public static String getTypeAsString(PersonType type) {
        if (null != type) {
            switch (type) {
                default : {
                    return "Unknown";
                }
                case Alumni : {
                    return "Alumni";
                }
                case GuestSpeaker : {
                    return "Guest Speaker";
                }
                case Donor : {
                    return "Donor";
                }
            }
        }

        return "Unknown";
    }

    /**
     * Verify string is numeric only (for phone number validation, etc)
     *
     * @param strNum string to verify
     * @return true if numeric, false if alphanumeric or null
     */
    public static boolean isNumericLong(String strNum) {
        if (strNum == null) {
            return false;
        }

        try {
            long i = Long.parseLong(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }

    /**
     * Verify string is numeric only (for phone number validation, etc)
     *
     * @param strNum string to verify
     * @return true if numeric, false if alphanumeric or null
     */
    public static boolean isNumericDouble(String strNum) {
        if (strNum == null) {
            return false;
        }

        try {
            double i = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }

    /**
     * Simple email validation regex
     *
     * @param email email string to validate
     * @return true if valid email, false if not
     */
    public static boolean isValidEmail(String email) {
        String regex = "^(.+)@(.+)$";

        return Pattern.compile(regex).matcher(email).matches();
    }
}
