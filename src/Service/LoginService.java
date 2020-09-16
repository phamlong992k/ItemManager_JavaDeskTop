/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DTO.Staff;
import Repository.LoginRepository;
import Repository.SimpleJPA.SimpleJPARepository;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class LoginService{
    public void LoginService(Staff staff){
        //System.out.println(staff);
        LoginRepository loginRepository= new LoginRepository();
        Map<String,Object> properties=convertToMap(staff);
        List<Staff> result=loginRepository.findAll(properties);
        if(result!=null){
            System.out.println(result);
        }
    }
    private Map<String,Object> convertToMap(Staff staff){
        Map<String,Object> properties= new HashMap<>();
        Field[] fields= staff.getClass().getDeclaredFields();
        try{
            for(Field field:fields){
                field.setAccessible(true);
                if(field.get(staff) instanceof String){
                    properties.put(field.getName().toLowerCase(),field.get(staff));
                }
            }
        }catch(Exception e){
            
        }
        return properties;
    }
   
}
