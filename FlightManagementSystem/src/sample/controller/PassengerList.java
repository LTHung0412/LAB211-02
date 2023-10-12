/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.util.ArrayList;
import java.util.List;
import sample.dto.I_PassengerList;
import sample.model.BoardingPass;
import sample.model.Flight;
import sample.model.Passenger;
import sample.model.Reservation;
import sample.utils.Utils;

/**
 *
 * @author LENOVO
 */
public class PassengerList extends ArrayList<Passenger> implements I_PassengerList {

    @Override
    public void passengerReservationAndBooking(FlightList flightList, ReservationList reservationList) {
        List<Flight> availableFlights = findAvailableFlights(flightList);
        if (!availableFlights.isEmpty()) {
            for (Flight f : availableFlights) {
                System.out.println(f);
            }
            makeReservation(availableFlights, reservationList);
        }
    }

    public void makeReservation(List<Flight> availableFlights, ReservationList reservationList) {
        System.out.println("Please choose your flight to make the reservation: ");
        int index = 1;
        for (Flight f : availableFlights) {
            System.out.println(index + ". " + "Flight: " + f.getNumber());
            index++;
        }
        int choice = Utils.getInt("Input choice: ", 1, availableFlights.size());
        //availableFlights.get(choice - 1).bookSeat();

        String passengerName = Utils.getString("Input name: ");
        String passengerContactDetail = Utils.getString("Input contact detail: ");
        Passenger nPassenger = new Passenger(passengerName, passengerContactDetail);
        this.add(nPassenger);

        String reservationID = "";
        reservationID += "R";
        int end_code = reservationList.size() + 1;
        int number_zero = 4 - (end_code + "").length();
        for (int i = 1; i <= number_zero; i++) {
            reservationID += "0";
        }
        reservationID += end_code;

        Reservation nReservation = new Reservation(nPassenger, availableFlights.get(choice - 1), reservationID);
        reservationList.add(nReservation);

        System.out.println(nPassenger + " ,ReservationID = " + reservationID);
    }

    public List<Flight> findAvailableFlights(FlightList flightList) {
        String findFlight = Utils.getString("Input departure, arrival locations or date to find: ");
        List<Flight> listFlights = new ArrayList<>();
        for (Flight f : flightList) {
            if (f.getDepartureCity().toUpperCase().equals(findFlight.toUpperCase()) || f.getDepartureTime().toUpperCase().equals(findFlight.toUpperCase()) || f.getDestinationCity().toUpperCase().equals(findFlight.toUpperCase()) || f.getArrivalTime().equals(findFlight)) {
                listFlights.add(f);
            }
        }
        if (listFlights.isEmpty()) {
            System.out.println("Not find !!!");
        }
        return listFlights;
    }

    @Override
    public void passengerCheckInAndSeatAllocation(ReservationList reservationList, BoardingPassList boardingPassList) {
        boolean check = false;
        String providedReservationID;
        do {
            providedReservationID = Utils.getString("Ïnput reservation ID for checking: ");
            boolean hasFound = false;
            for (Reservation r : reservationList) {
                if (r.getReservationID().equals(providedReservationID)) {
                    BoardingPass newBoardingPass = new BoardingPass(r.getPassenger(), r.getFlight());
                    boardingPassList.add(newBoardingPass);
                    allocateSeats(r.getPassenger(), r.getFlight());
                    hasFound = true;
                }
            }
            if (!hasFound) {
                System.out.println("Not find Reservation ID !!!");
            }
            check = Utils.confirmYesNo("Do you want to continue check-in again (Y/N): ");
        } while (check);
    }

    private void allocateSeats(Passenger p, Flight f) {
        Passenger[] pList = f.getPassengerSeats();
        List<String> optionsList = new ArrayList<>();
        System.out.print("\t-----FLIGHT: " + f.getNumber() + "-----");
        for (int i = 0; i < f.getSeatSize(); i++) {
            Passenger passenger = pList[i];
            String s = "";
            if (passenger == null) {
                s = String.format("%d", i + 1);
                optionsList.add(s);
            } else {
                s = "X";
            }
            if ((i + 1) % 4 == 1) {
                System.out.print(String.format("\n\t[%s]", s));
            } else {
                System.out.print(String.format("\t[%s]", s));
            }
        }

        if (f.checkSeatAvailability()) {
            boolean choiceCheck = false;
            String seatChoice;
            do {
                seatChoice = Utils.getString("\nInput seat number: ");
                for (String s : optionsList) {
                    if (seatChoice.equals(s)) {
                        System.out.println("Choose successfully !!!");
                        pList[Integer.parseInt(seatChoice) - 1] = p;
                        f.bookSeat();
                        choiceCheck = true;
                        break;
                    }
                }
                if (!choiceCheck) {
                    System.out.println("Invalid seat !!!");
                }
            } while (!choiceCheck);
        }
    }
}
