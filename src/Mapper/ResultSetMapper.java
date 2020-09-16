/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapper;

import Annotation.Column;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Asus
 */
public class ResultSetMapper<T> {
    public List<T> mapRow(ResultSet rs, Class<T> zClass){
        List<T> results= new Vector<>();
        try{
            while(rs.next()){
                T object=zClass.newInstance();
                Field[] fields=zClass.getDeclaredFields();
                System.out.println("get Field");
                for(int i=0;i<fields.length;i++){
                    System.out.println(fields[i].getName());
                }
                ResultSetMetaData meta= rs.getMetaData();
                for(int i=0;i<meta.getColumnCount();i++){
                    String columnName=meta.getColumnName(i+1);
                    Object columnValue=rs.getObject(i+1);
                    for(Field field:fields){
                        if(field.isAnnotationPresent(Column.class)){
                            Column col= field.getAnnotation(Column.class);
                            if(col.name().equals(columnName)){
                                field.setAccessible(true);
                                field.set(object, columnValue);
                                
                            }
                        }
                    }
                }
                results.add(object);
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return results;
    }
}
