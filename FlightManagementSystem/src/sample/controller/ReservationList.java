/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.util.ArrayList;
import sample.dto.I_ReservationList;
import sample.dto.Reservation;

/**
 *
 * @author LENOVO
 */
public class ReservationList extends ArrayList<Reservation> implements I_ReservationList {

    @Override
    public void printBookedPassengers() {
        for (Reservation r : this) {
            System.out.println(r.getPassenger().getName());
        }
    }

}
