/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Annotation.Column;
import Annotation.Table;

/**
 *
 * @author Asus
 */
@Table(name = "supplier")
public class Supplier {

    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name="supplying")
    private int supplying;
    public Supplier(){
        this.id = 0;
        this.name ="";
        this.supplying=0;
    }
    public Supplier(int id, String name,int supplying) {
        this.id = id;
        this.name = name;
        this.supplying=supplying;
    }

    public int getSupplying() {
        return supplying;
    }

    public void setSupplying(int supplying) {
        this.supplying = supplying;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String supplyingInforUser="";
        if(this.supplying==0){
            supplyingInforUser="khong cung cap";
        }
        else{
            supplyingInforUser="dang cung cap";
        }
      
        return  id +"," +name+","+supplyingInforUser;
    }

}
