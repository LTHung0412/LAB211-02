/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.view;

import java.io.IOException;
import sample.controller.FlightList;
import sample.controller.Menu;
import sample.dto.I_Menu;

/**
 *
 * @author LENOVO
 */
public class FlightManagement {

    /**
     * @param args the command line arguments
     */
    static FlightList flightList = new FlightList();

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        flightList.load();
        I_Menu menu = new Menu();
        menu.addItem("1. Flight schedule management.");
        menu.addItem("2. Passenger reservation and booking.");
        menu.addItem("3. Passenger check-in and seat allocation.");
        menu.addItem("4. Crew management and assignments.");
        menu.addItem("5. Data storage for flight details, reservations, and assignments.");
        menu.addItem("6. Quit.");
        int choice;
        boolean cont = false;
        do {
            System.out.println("========== FLIGHT MANAGEMENT SYSTEM ==========");
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    flightList.flightSchelduleManagement();
                    break;
                case 2:
                    flightList.passengerReservationAndBooking();
                    break;
                case 3:
                    flightList.passengerCheckInAndSeatAllocation();
                    break;
                case 4:
                    flightList.crewManagementAndAssignments();
                    break;
                case 5:
                    flightList.save();
                    break;
                case 6:
                    cont = menu.confirmYesNo("Do you want to quit? (Y/N)");
                    break;
            }
        } while (!cont);
    }
}
