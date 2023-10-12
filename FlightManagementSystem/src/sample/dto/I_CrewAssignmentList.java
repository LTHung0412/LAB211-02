/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

import sample.controller.CrewAssignmentList;
import sample.controller.FlightList;

/**
 *
 * @author LENOVO
 */
public interface I_CrewAssignmentList {

    void crewManagementAndAssignments(FlightList flightList, CrewAssignmentList crewAssignmentList);
}
