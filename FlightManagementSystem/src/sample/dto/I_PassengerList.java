/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

import sample.controller.BoardingPassList;
import sample.controller.FlightList;
import sample.controller.ReservationList;

/**
 *
 * @author LENOVO
 */
public interface I_PassengerList {

    void passengerReservationAndBooking(FlightList flightList, ReservationList reservationList);

    void passengerCheckInAndSeatAllocation(ReservationList reservationList, BoardingPassList boardingPassList);
}
