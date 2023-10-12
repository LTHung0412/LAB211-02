/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.service;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sample.controller.BoardingPassList;
import sample.controller.CrewAssignmentList;
import sample.controller.FileList;
import sample.controller.FlightList;
import sample.controller.Menu;
import sample.controller.PassengerList;
import sample.controller.ReservationList;
import sample.dto.I_Menu;

/**
 *
 * @author LENOVO
 */
public class ServiceManager implements I_Service {

    FlightList flightList = new FlightList();
    PassengerList passengerList = new PassengerList();
    CrewAssignmentList crewAssignmentList = new CrewAssignmentList();
    ReservationList reservationList = new ReservationList();
    BoardingPassList boardingPassList = new BoardingPassList();
    FileList fileList = new FileList();
    I_Menu menu = new Menu();

    @Override
    public void flightSchelduleManagement() {
        flightList.flightSchelduleManagement();
    }

    @Override
    public void passengerReservationAndBooking() {
        passengerList.passengerReservationAndBooking(flightList, reservationList);
    }

    @Override
    public void passengerCheckInAndSeatAllocationAndAvailability() {
        passengerList.passengerCheckInAndSeatAllocation(reservationList, boardingPassList);
    }

    @Override
    public void crewManagement() {
        crewAssignmentList.crewManagementAndAssignments(flightList, crewAssignmentList);
    }

    @Override
    public void saveToFile() {
        fileList.save(flightList, passengerList, crewAssignmentList, reservationList, boardingPassList);
    }

    @Override
    public void printAllListsFromFile() {
        flightList.displayFlightInfor(flightList);
    }

    @Override
    public void createLayout() {
        try {
            fileList.load(flightList, passengerList, crewAssignmentList, reservationList, boardingPassList);
        } catch (IOException ex) {
            Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        I_Menu menu = new Menu();
        menu.addItem("1. Flight schedule management.");
        menu.addItem("2. Passenger reservation and booking.");
        menu.addItem("3. Passenger check-in and seat allocation.");
        menu.addItem("4. Crew management and assignments.");
        menu.addItem("5. Data storage for flight details, reservations, and assignments.");
        menu.addItem("6. Print all Flight information: ");
        menu.addItem("7. Quit.");
        int choice;
        boolean cont = false;
        do {
            System.out.println("========== FLIGHT MANAGEMENT SYSTEM ==========");
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    flightSchelduleManagement();
                    break;
                case 2:
                    passengerReservationAndBooking();
                    break;
                case 3:
                    passengerCheckInAndSeatAllocationAndAvailability();
                    break;
                case 4:
                    crewManagement();
                    break;
                case 5:
                    saveToFile();
                    break;
                case 6:
                    printAllListsFromFile();
                    break;
                case 7:
                    cont = menu.confirmYesNo("Do you want to quit? (Y/N): ");
                    break;
            }
        } while (!cont);
    }

}
