/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.dao;

import com.sample.model.BoardingPass;
import com.sample.model.CrewAssignment;
import com.sample.model.Flight;
import com.sample.model.Passenger;
import com.sample.model.Reservation;
import com.sample.model.User;
import com.sample.utils.Utils;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class FlightDAOImpl extends ArrayList<Flight> implements FlightDAO {

    List<Passenger> passengerList = new ArrayList<>();
    List<Reservation> reservationList = new ArrayList<>();
    List<BoardingPass> boardingPassList = new ArrayList<>();
    List<CrewAssignment> crewAssignmentList = new ArrayList<>();
    User admin = new User("admin@123", true);
    boolean loginCheck = false;
    String fileName = "Product.dat";

//==============================================================================
    @Override
    public void flightSchelduleManagement() {
        if (loginCheck) {
            add();
        } else if (!loginCheck) {
            System.out.println("You have no authority to access this function.");
        }

    }

    public void add() {
        boolean quit;
        do {
            boolean check = true;
            String flightNum;
            String flightNumPattern = "F\\d{4}";
            do {
                flightNum = Utils.getString("Input flight number (Fxxxx): ");
                int index = this.find(flightNum);
                if (index == -1 && flightNum.matches(flightNumPattern)) {
                    check = false;
                }
            } while (check);

            String departCity = Utils.getString("Input departure city: ");
            String destiCity = Utils.getString("Input destination city: ");
            String departTime = Utils.getDate("Input departure time: ", "MM/dd/yyyy");
            String arrivalTime = Utils.getDate("Input arrival time: ", "MM/dd/yyyy");
            int avaiableSeats = Utils.getInt("Input available seats: ", 1, 100);
            this.add(new Flight(flightNum, departCity, destiCity, departTime, arrivalTime, avaiableSeats));

            quit = Utils.confirmYesNo("Do you want to continue add (Y or N): ");
        } while (quit);
    }

    public int find(String num) {
        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getNumber().equals(num)) {
                index = i;
                break;
            }
        }
        return index;
    }
//==============================================================================

    @Override
    public void passengerReservationAndBooking() {
        if (!loginCheck) {
            List<Flight> availableFlights = findAvailableFlights();
            if (!availableFlights.isEmpty()) {
                displayFlightInfor(availableFlights);
                makeReservation(availableFlights);
            }
        } else if (loginCheck) {
            System.out.println("You have no authority to access this function.");
        }
    }

    public List<Flight> findAvailableFlights() {
        String findFlight = Utils.getString("Input departure, arrival locations or date to find: ");
        List<Flight> listFlights = new ArrayList<>();
        for (Flight f : this) {
            if (f.getDepartureCity().equals(findFlight) || f.getDepartureTime().equals(findFlight) || f.getDestinationCity().equals(findFlight) || f.getArrivalTime().equals(findFlight)) {
                listFlights.add(f);
            }
        }
        if (listFlights.isEmpty()) {
            System.out.println("Not find !!!");
        }
        return listFlights;
    }

    public void makeReservation(List<Flight> availableFlights) {
        System.out.println("Please choose your flight to make the reservation: ");
        int index;
        for (Flight f : availableFlights) {
            index = 1;
            System.out.println(index + ". " + "Flight: " + f.getNumber());
            index++;
        }
        int choice = Utils.getInt("Input choice: ", 1, availableFlights.size());
        //this.get(choice - 1).setBookedSeats(this.get(choice - 1).getBookedSeats() + 1);
        this.get(choice - 1).bookSeat();

        String passengerName = Utils.getString("Input name: ");
        String passengerContactDetail = Utils.getString("Input contact detail: ");
        Passenger nPassenger = new Passenger(passengerName, passengerContactDetail);
        passengerList.add(nPassenger);
        //this.get(choice - 1).setPassenger(this.get(choice - 1).getBookedSeats() - 1, nPassenger);

        String reservationID = "";
        reservationID += "R";
        int end_code = reservationList.size() + 1;
        int number_zero = 4 - (end_code + "").length();
        for (int i = 1; i <= number_zero; i++) {
            reservationID += "0";
        }
        reservationID += end_code;

        Reservation nReservation = new Reservation(nPassenger, this.get(choice - 1), reservationID);
        reservationList.add(nReservation);

        System.out.println(nPassenger + " ,ReservationID = " + reservationID + "}");
    }
//==============================================================================

    @Override
    public void passengerCheckInAndSeatAllocation() {
        if (!loginCheck) {
            passengerCheckInFlights();
        } else if (loginCheck) {
            System.out.println("You have no authority to access this function.");
        }
    }

    private void passengerCheckInFlights() {
        boolean check = true;
        String providedPassengerName, providedReservationID;
        do {
            providedPassengerName = Utils.getString("Input your name: ");
            providedReservationID = Utils.getString("Ïnput reservation ID for checking: ");
            for (Reservation r : reservationList) {
                if ((r.getPassenger().getName().equals(providedPassengerName) && !r.getReservationID().equals(providedReservationID))
                        || r.getFlight().findPassenger(r.getPassenger(), r) == -1) {
                    //System.out.println(r.getPassenger().getName() + r.getReservationID());
                    check = false;
                    break;
                }
            }
            if (!check) {
                System.out.println("You have inputted wrong your NAME or your RESERVATION ID. Please input again: ");
            }
        } while (!check);

        for (Reservation r : reservationList) {
            if (r.getPassenger().getName().equals(providedPassengerName) && r.getReservationID().equals(providedReservationID)) {
                //System.out.println(r.getFlight().getNumber());
                BoardingPass newBoardingPass = new BoardingPass(r.getPassenger(), r.getFlight());
                boardingPassList.add(newBoardingPass);
                allocateSeats(r.getPassenger(), r.getFlight());
            }
        }
    }

    private void allocateSeats(Passenger p, Flight f) {
        Passenger[] pList = f.getPassengerSeats();
        List<String> optionsList = new ArrayList<>();
        System.out.println("\t-----FLIGHT: " + f.getNumber() + " -----");
        for (int i = 0; i < f.getAvailableSeats() - 3; i += 4) {
            Passenger p1 = pList[i], p2 = pList[i + 1], p3 = pList[i + 2], p4 = pList[i + 3];
            String s1 = "", s2 = "", s3 = "", s4 = "";
            if (p1 == null) {
                s1 = String.format("%d", i + 1);
                optionsList.add(s1);
            } else {
                s1 = "X";
            }
            if (p2 == null) {
                s2 = String.format("%d", i + 2);
                optionsList.add(s2);
            } else {
                s2 = "X";
            }
            if (p3 == null) {
                s3 = String.format("%d", i + 3);
                optionsList.add(s3);
            } else {
                s3 = "X";
            }
            if (p4 == null) {
                s4 = String.format("%d", i + 4);
                optionsList.add(s4);
            } else {
                s4 = "X";
            }
            String output = String.format("\t[%s]\t[%s]\t[%s]\t[%s]", s1, s2, s3, s4);
            System.out.println(output);
        }

        boolean choiceCheck = false;
        String seatChoice;
        do {
            seatChoice = Utils.getString("Input seat number: ");
            for (String s : optionsList) {
                if (seatChoice.equals(s)) {
                    System.out.println("Choose successfully !!!");
                    pList[Integer.parseInt(seatChoice) - 1] = p;
                    choiceCheck = true;
                    break;
                }
            }
            if (!choiceCheck) {
                System.out.println("Invalid seat !!!");
            }
        } while (!choiceCheck);
    }
//==============================================================================

    @Override
    public void crewManagementAndAssignments() {
        if (loginCheck) {
            addCrewAssignments();
        } else if (!loginCheck) {
            System.out.println("You have no authority to access this function.");
        }
    }

    public void addCrewAssignments() {
        String nFlightNum = Utils.getString("Input flight number: ");
        for (Flight f : this) {
            if (f.getNumber().equals(nFlightNum)) {
                int nPilots = Utils.getInt("Ïnput number of pilots: ", 1, 10);
                int nFlightAttendants = Utils.getInt("Input number of flight attendants: ", 1, 10);
                int nGroundStaffs = Utils.getInt("Input number of ground staffs: ", 1, 10);
                CrewAssignment nCrewAssignment = new CrewAssignment(f, nPilots, nFlightAttendants, nGroundStaffs);
                crewAssignmentList.add(nCrewAssignment);
            }
        }
    }
//==============================================================================

    @Override
    public void administratorAccessForSystemManagement() {
        login();
    }

    private void login() {
        boolean check = false;
        String loginChoice = Utils.getString("Do you want to login as Admin/User (A/U): ");
        if (loginChoice.toUpperCase().equals("A")) {
            do {
                String logPassWord = Utils.getString("Input password: ");
                if (logPassWord.equals(admin.getPassword()) && admin.isAdmin()) {
                    loginCheck = true;
                    System.out.println("Login with adminstator successfully !!!");
                } else if (!logPassWord.equals(admin.getPassword())) {
                    System.out.println("Incorrect password !!!");
                    check = Utils.confirmYesNo("Do you want to continue login (Y/N): ");
                }
            } while (check);

        } else if (loginChoice.toUpperCase().equals("U")) {
            loginCheck = false;
        }
    }

    public void displayFlightInfor(List<Flight> listFlight) {
        Collections.sort(listFlight, Comparator.comparing((Flight p) -> p.getArrivalTime()).reversed());
        for (Flight f : listFlight) {
            System.out.println(f);
        }
    }
//==============================================================================

    @Override
    public void saveData() {
        if (loginCheck) {
            File file = new File(fileName);
            if (file.exists()) {

            } else {
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(FlightDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (Flight f : this) {
                    oos.writeObject(f);
                }
                System.out.println("Save to file successfully !!!");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FlightDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FlightDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (!loginCheck) {
            System.out.println("You have no authority to access this function.");
        }
    }

    public void loadFlightInfor() throws IOException {
        File file = new File(fileName);
        if (file.exists()) {

        } else {
            file.createNewFile();
        }
        FileInputStream fis = new FileInputStream(file);

        if (file.length() > 0) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {
                    Flight nFlight = (Flight) ois.readObject();
                    this.add(nFlight);
                } catch (ClassNotFoundException e) {
                    System.out.println(e);
                } catch (EOFException e) {
                    break;
                }

            }
        }
    }
//==============================================================================

    @Override
    public void createALayout() {
        List<String> menu = new ArrayList<>();
        menu.add("1. Flight schedule management.");
        menu.add("2. Passenger reservation and booking.");
        menu.add("3. Passenger check-in and seat allocation.");
        menu.add("4. Crew management and assignments.");
        menu.add("5. Administrator access for system management.");
        menu.add("6. Data storage for flight details, reservations, and assignments");
        menu.add("7. Quit.");
        int choice;
        boolean cont = false;
        do {
            System.out.println("========== FLIGHT MANAGEMENT SYSTEM ==========");
            showMenu(menu);
            choice = getChoice(menu);
            switch (choice) {
                case 1:
                    flightSchelduleManagement();
                    break;
                case 2:
                    passengerReservationAndBooking();
                    break;
                case 3:
                    passengerCheckInAndSeatAllocation();
                    break;
                case 4:
                    crewManagementAndAssignments();
                    break;
                case 5:
                    administratorAccessForSystemManagement();
                    break;
                case 6:
                    saveData();
                    break;
                case 7:
                    cont = confirmYesNo("Do you want to quit? (Y/N)");
                    break;
            }
        } while (!cont);
    }

    public int getChoice(List<String> menu) {
        return Utils.getInt("Input your choice: ", 1, menu.size());
    }

    public void showMenu(List<String> menu) {
        for (int i = 0; i < menu.size(); i++) {
            System.out.println(menu.get(i));
        }
    }

    public boolean confirmYesNo(String welcome) {
        boolean result = false;
        result = Utils.confirmYesNo(welcome);
        return result;
    }
}
