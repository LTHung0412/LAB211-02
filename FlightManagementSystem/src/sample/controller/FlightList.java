/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import sample.dto.Flight;
import sample.utils.Utils;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import sample.dto.I_FlightList;

/**
 *
 * @author LENOVO
 */
public class FlightList extends ArrayList<Flight> implements I_FlightList {

    @Override
    public void flightSchelduleManagement() {
        add();
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
            String departTime = Utils.getDate("Input departure time (MM/dd/yyyy-HH:mm): ", "MM/dd/yyyy-HH:mm");
            String arrivalTime = Utils.getDate("Input arrival time (MM/dd/yyyy-HH:mm): ", "MM/dd/yyyy-HH:mm");
            int avaiableSeats = Utils.getInt("Input available seats: ", 1, 100);
            this.add(new Flight(flightNum, departCity, destiCity, departTime, arrivalTime, avaiableSeats));

            quit = Utils.confirmYesNo("Do you want to continue adding (Y/N): ");
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

    @Override
    public void displayFlightInfor(List<Flight> listFlight) {
        Collections.sort(listFlight, Comparator.comparing((Flight p) -> p.getArrivalTime()).reversed());
        for (Flight f : listFlight) {
            System.out.println(f);
        }
    }
}
