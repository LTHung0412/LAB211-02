/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

import java.io.Serializable;

/**
 *
 * @author LENOVO
 */
public class Passenger implements Serializable {

    private String id;
    private String name;
    private String contactDetail;

    public Passenger() {

    }

    public Passenger(String id, String name, String contactDetail) {
        this.id = id;
        this.name = name;
        this.contactDetail = contactDetail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(String contactDetail) {
        this.contactDetail = contactDetail;
    }

    @Override
    public String toString() {
        return "Passenger{" + "id=" + id + ", name=" + name + ", contactDetail=" + contactDetail + '}';
    }

}
