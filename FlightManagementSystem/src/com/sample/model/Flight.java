/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.model;

/**
 *
 * @author LENOVO
 */
public class Flight {

    private String number;  //Fxxxx (Ex: F0001)
    private String departureCity;
    private String destinationCity;
    private String departureTime;
    private String arrivalTime;
    private int availableSeats;
    private int bookedSeats;
    private Passenger[] passengerSeats;

    public Flight() {
    }

    public Flight(String number, String departureCity, String destinationCity, String departureTime, String arrivalTime, int availableSeats) {
        this.number = number;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
        this.bookedSeats = this.availableSeats;
        passengerSeats = new Passenger[availableSeats];
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(int bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public Passenger[] getPassengerSeats() {
        return passengerSeats;
    }

    public void setPassengerSeats(Passenger[] passengerSeats) {
        this.passengerSeats = passengerSeats;
    }

    public boolean checkSeatAvailability() {
        if (bookedSeats < availableSeats) {
            return true;
        } else {
            return false;
        }
    }

    public void bookSeat() {
        if (checkSeatAvailability()) {
            bookedSeats++;
        } else {
            System.out.println("No seat available !!!");
        }
    }

    @Override
    public String toString() {
        String pS = "";
        for (Passenger p : this.passengerSeats) {
            pS += p.getName()+" ";
        }
        return "Flight{" + "number=" + number + ", departureCity=" + departureCity + ", destinationCity=" + destinationCity + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", availableSeats=" + availableSeats + ", bookedSeats=" + bookedSeats + "[" + pS + "]" + '}';
    }
}
