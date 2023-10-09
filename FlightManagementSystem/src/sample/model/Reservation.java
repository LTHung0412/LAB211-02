/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.model;

import java.io.Serializable;

/**
 *
 * @author LENOVO
 */
public class Reservation implements Serializable{

    private Passenger passenger;
    private Flight flight;
    private String reservationID;

    public Reservation(Passenger passenger, Flight flight, String reservationID) {
        this.passenger = passenger;
        this.flight = flight;
        this.reservationID = reservationID;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

}
