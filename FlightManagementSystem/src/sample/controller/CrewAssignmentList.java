/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.util.ArrayList;
import sample.dto.I_CrewAssignmentList;
import sample.dto.CrewAssignment;
import sample.dto.Flight;
import sample.utils.Utils;

/**
 *
 * @author LENOVO
 */
public class CrewAssignmentList extends ArrayList<CrewAssignment> implements I_CrewAssignmentList {

    @Override
    public void crewManagementAndAssignments(FlightList flightList, CrewAssignmentList crewAssignmentList) {
        add(flightList, crewAssignmentList);
    }

    public void add(FlightList flightList, CrewAssignmentList crewAssignmentList) {
        boolean check = false;
        do {
            boolean hasFound = false;
            String nFlightNum = Utils.getString("Input flight number: ");
            for (Flight f : flightList) {
                if (f.getNumber().equals(nFlightNum)) {
                    hasFound = true;
                    int nPilots = Utils.getInt("Ïnput number of pilots (MAX=2): ", 1, 2);
                    int nFlightAttendants = Utils.getInt("Input number of flight attendants (MAX=10): ", 1, 10);
                    int nGroundStaffs = Utils.getInt("Input number of ground staffs (MAX=20): ", 1, 20);
                    CrewAssignment nCrewAssignment = new CrewAssignment(f, nPilots, nFlightAttendants, nGroundStaffs);
                    crewAssignmentList.add(nCrewAssignment);
                    System.out.println("Create crew assignments successfully !!!");
                }
            }
            if (!hasFound) {
                System.out.println("Cannot find Flight !!!");
            }
            check = Utils.confirmYesNo("Do you want to continue adding new Crew Assignment (Y/N): ");
        } while (check);

    }
}
