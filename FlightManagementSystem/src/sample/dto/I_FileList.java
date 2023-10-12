/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

import java.io.IOException;
import sample.controller.BoardingPassList;
import sample.controller.FlightList;
import sample.controller.PassengerList;
import sample.controller.CrewAssignmentList;
import sample.controller.ReservationList;

/**
 *
 * @author LENOVO
 */
public interface I_FileList {

    void save(FlightList flightList, PassengerList passengerList, CrewAssignmentList crewAssignmentList, ReservationList reservationList, BoardingPassList boardingPassList);

    void load(FlightList flightList, PassengerList passengerList, CrewAssignmentList crewAssignmentList, ReservationList reservationList, BoardingPassList boardingPassList) throws IOException;
}
