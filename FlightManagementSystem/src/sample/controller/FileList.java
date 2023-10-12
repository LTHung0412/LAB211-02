/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import sample.dto.I_FileList;
import sample.model.CrewAssignment;
import sample.model.Flight;
import sample.model.Passenger;
import sample.model.Reservation;
import sample.model.BoardingPass;

/**
 *
 * @author LENOVO
 */
public class FileList implements I_FileList {

    String fileName = "Product.dat";

    @Override
    public void save(FlightList flightList, PassengerList passengerList, CrewAssignmentList crewAssignmentList, ReservationList reservationList, BoardingPassList boardingPassList) {
        File file = new File(fileName);
        if (file.exists()) {

        } else {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FlightList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (Flight f : flightList) {
                oos.writeObject(f);
            }
            for (Passenger p : passengerList) {
                oos.writeObject(p);
            }
            for (CrewAssignment c : crewAssignmentList) {
                oos.writeObject(c);
            }
            for (Reservation r : reservationList) {
                oos.writeObject(r);
            }
            for (BoardingPass b : boardingPassList) {
                oos.writeObject(b);
            }
            System.out.println("Save to file successfully !!!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlightList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FlightList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void load(FlightList flightList, PassengerList passengerList, CrewAssignmentList crewAssignmentList, ReservationList reservationList, BoardingPassList boardingPassList) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        try (FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof Flight) {
                        Flight loadedFlight = (Flight) obj;
                        flightList.add(loadedFlight);
                    } else if (obj instanceof Passenger) {
                        Passenger loadedPassenger = (Passenger) obj;
                        passengerList.add(loadedPassenger);
                    } else if (obj instanceof CrewAssignment) {
                        CrewAssignment loadedCrewAssignment = (CrewAssignment) obj;
                        crewAssignmentList.add(loadedCrewAssignment);
                    } else if (obj instanceof Reservation) {
                        Reservation loadedReservation = (Reservation) obj;
                        reservationList.add(loadedReservation);
                    } else if (obj instanceof BoardingPass) {
                        BoardingPass loadedBoardingPass = (BoardingPass) obj;
                        boardingPassList.add(loadedBoardingPass);
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println("Class not found: " + e);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException e) {
            // Handle IO exceptions here, e.g., log or throw a custom exception.
            System.err.println("");
        }
    }
}
