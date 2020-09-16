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
@Table(name="Staff")
public class Staff {
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;

    public Staff() {
        this.email = "";
        this.password = "";
    }

    public Staff(String email,String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Staff{" + "email=" + email + ","+"password=" + password + '}';
    }
    
    
}
