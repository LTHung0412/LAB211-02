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
public class CrewAssignment implements Serializable {

    private Flight flight;
    private int pilots;
    private int flightAttendants;
    private int groundStaff;

    public CrewAssignment(Flight flight, int pilots, int flightAttendants, int groundStaff) {
        this.flight = flight;
        this.pilots = pilots;
        this.flightAttendants = flightAttendants;
        this.groundStaff = groundStaff;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getPilots() {
        return pilots;
    }

    public void setPilots(int pilots) {
        this.pilots = pilots;
    }

    public int getFlightAttendants() {
        return flightAttendants;
    }

    public void setFlightAttendants(int flightAttendants) {
        this.flightAttendants = flightAttendants;
    }

    public int getGroundStaff() {
        return groundStaff;
    }

    public void setGroundStaff(int groundStaff) {
        this.groundStaff = groundStaff;
    }

}
