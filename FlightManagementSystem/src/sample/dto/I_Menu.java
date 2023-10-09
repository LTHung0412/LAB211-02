/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

/**
 *
 * @author LENOVO
 */
public interface I_Menu {

    void addItem(String s);

    void showMenu();

    boolean confirmYesNo(String welcome);

    int getChoice();
}
