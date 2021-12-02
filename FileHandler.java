/*
 * Wesley Cook
 * Group 1, Alumni Program
 */

package com.cisp1020.group1.alumniprogram;

import java.io.*;
import java.util.ArrayList;

/* Methods for writing and reading serialized data stored in their respective .txt files
   of the program folder.

   @author Wesley Cook
*/

public class FileHandler {

    // Lines 13-xx for testing method only
    // ArrayList<Person> personList = new ArrayList<>();
    //Person p1 = new Person("Joey", "111 broad street", "big company", "janitor");
    //personList.add(p1);

        // Fx for writing Alumni data from folder
        public static void writeAlumniData(ArrayList<Alumni> alumni){
            try{
                FileOutputStream writeData = new FileOutputStream("alumniTexts.txt");
                ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
    
                writeStream.writeObject(alumni); // ArrayList of object passed in
                writeStream.flush();
                writeStream.close();
            }catch (IOException e)
            {
                System.out.println(e.toString());
            }
        }
        
        // Fx for reading alumni data from folder alumniTexts.txt
        public static ArrayList<Alumni> readAlumniData(){
            try{
                FileInputStream readData = new FileInputStream("alumniTexts.txt");
                ObjectInputStream readStream = new ObjectInputStream(readData);

                ArrayList newList = (ArrayList<Alumni>) readStream.readObject();
                
                readStream.close();
                
                return newList;
            }catch (IOException | ClassNotFoundException e) 
            {
                System.out.println(e.toString());
            }
            
            return null;
        }

        // Fx for writing GuestSpeakers data
        public static void writeGuestSpeakerData(ArrayList<GuestSpeaker> guestSpeakers){
            try{
                FileOutputStream writeData = new FileOutputStream("speakerTexts.txt");
                ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

                writeStream.writeObject(guestSpeakers); // ArrayList of object passed in
                writeStream.flush();
                writeStream.close();
            }catch (IOException e)
            {
                System.out.println(e.toString());
            }
        }

        // Fx for reading GuestSpeaker data
        public static ArrayList<GuestSpeaker> readSpeakerData(){
            try{
                FileInputStream readData = new FileInputStream("speakerTexts.txt");
                ObjectInputStream readStream = new ObjectInputStream(readData);

                ArrayList newList = (ArrayList<GuestSpeaker>) readStream.readObject();
                
                readStream.close();

                return newList;
            }catch (IOException | ClassNotFoundException e) 
            {
                System.out.println(e.toString());
            }
            
            return null;
        }

        // Fx for writing donors data
        public static void writeDonorData(ArrayList<Donor> donors){
            try{
                FileOutputStream writeData = new FileOutputStream("donorTexts.txt");
                ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
    
                writeStream.writeObject(donors); // ArrayList of object passed in
                writeStream.flush();
                writeStream.close();
            }catch (IOException e)
            {
                System.out.println(e.toString());
            }
        }

            // Fx for reading donor data
        public static ArrayList<Donor> readDonorData(){
            try{
                FileInputStream readData = new FileInputStream("donorTexts.txt");
                ObjectInputStream readStream = new ObjectInputStream(readData);
    
                ArrayList newList = (ArrayList<Donor>) readStream.readObject();
                readStream.close();
    
                return newList;
            }catch (IOException | ClassNotFoundException e) 
            {
                System.out.println(e.toString());
            }
            
            return null;
        }
            // Fx for writing event data
        public static void writeEventData(ArrayList<Event> event){
            try{
                FileOutputStream writeData = new FileOutputStream("eventTexts.txt");
                ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
        
                writeStream.writeObject(event); // ArrayList of object passed in
                writeStream.flush();
                writeStream.close();
            }catch (IOException e)
            {
                System.out.println(e.toString());
            }
        }

        // Fx for reading event data
        public static ArrayList<Event> readEventData(){
            try{
                FileInputStream readData = new FileInputStream("eventTexts.txt");
                ObjectInputStream readStream = new ObjectInputStream(readData);
        
                ArrayList newList = (ArrayList<Event>) readStream.readObject();
                readStream.close();
        
                return newList;
            }catch (IOException | ClassNotFoundException e) 
            {
                System.out.println(e.toString());
            }
            
            return null;
        }
}