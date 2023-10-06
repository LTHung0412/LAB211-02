/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.main;

import com.sample.dao.FlightDAOImpl;

/**
 *
 * @author LENOVO
 */
public class FlightManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FlightDAOImpl fdi = new FlightDAOImpl();
        fdi.createALayout();
    }

}
