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
@Table(name="Item")
public class Item {
   @Column(name="id")
   private int id;
   @Column(name="name")
   private String name;
   @Column(name="price")
   private int price;
   @Column(name="supplierid")
   private int supplierId;
   

    public Item(int id, String name,int price,int supplierId) {
        this.id = id;
        this.name = name;
        this.supplierId = supplierId;
        this.price = price;
    }

    public int getPrice() {
        return price;
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

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return id + "," + name+","+ price+ "," +supplierId;
    }
   
}
