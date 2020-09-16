/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository.SimpleJPA;

import Annotation.Column;
import Annotation.Table;
import DAO.DBUtil;
import DAO.MyConnection;
import Mapper.ResultSetMapper;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Asus
 */
public class SimpleJPARepository<T> {
    private Class zClass;
    public SimpleJPARepository(){
        Type tp = getClass().getGenericSuperclass();    
        ParameterizedType pt = (ParameterizedType)tp;  
        System.out.println(pt.getActualTypeArguments()[0]);
        zClass= (Class) pt.getActualTypeArguments()[0];

    }
    public List<T> findAll(Map<String,Object> params){
        ResultSetMapper rsMapper= new ResultSetMapper();
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<T> result= new Vector<>();
        try{
            cn=MyConnection.getConnect();
            if(zClass.isAnnotationPresent(Table.class)){
                Table table=(Table) zClass.getAnnotation(Table.class);
                StringBuilder sql= new StringBuilder();
                sql.append("select * from "+table.name()+" o where 1=1 ");
                sql=createSQLSelect(sql, params);
                System.out.println(sql.toString());
                
                ps=cn.prepareStatement(sql.toString());
                rs=ps.executeQuery();
                result=rsMapper.mapRow(rs, zClass);
                return result;
            }
        }catch(Exception e){
            e.printStackTrace();
            
        }
        return null;
    }
    public void insert(Object object){
        Connection cn= null;
        PreparedStatement ps= null;
        try{
            cn=MyConnection.getConnect();
            cn.setAutoCommit(false);
            String sql=createSQLInsert();
            ps=cn.prepareStatement(sql);
            Field[]fields= zClass.getDeclaredFields();
            int i=1;
            for(Field field:fields){
                field.setAccessible(true);
                ps.setObject(i,field.get(object));
                i+=1;
            }
            ps.executeUpdate();
            cn.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                if(cn!=null){
                    cn.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
    }
    private String createSQLInsert(){
        String sql="";
        if(zClass.isAnnotationPresent(Table.class)){
            Table table= (Table) zClass.getAnnotation(Table.class);
            
            
            Field[] fields=zClass.getDeclaredFields();
            StringBuilder fieldsInSQL= new StringBuilder();
            StringBuilder params= new StringBuilder();
            for(Field field:fields){
                if(fieldsInSQL.length()>0){
                    fieldsInSQL.append(",");
                    params.append(",");
                }
                if(field.isAnnotationPresent(Column.class)){
                    Column column=field.getAnnotation(Column.class);
                    fieldsInSQL.append(column.name());
                    params.append("?");
                }
            }
            sql="insert into "+table.name()+"("+fieldsInSQL+")"+"values("+params+")";
            System.out.println(sql);
        }
        return sql;
    }
    private StringBuilder createSQLSelect(StringBuilder sql,Map<String,Object> params){
        if(params!=null&&params.size()>0){
            String [] keys=new String[params.size()];
            Object[] values= new Object[params.size()];
            int i=0;
            for(Map.Entry<String,Object> key:params.entrySet()){
                keys[i]=key.getKey();
                values[i]=key.getValue();
                i++;
            }
            for(int j=0;j<keys.length;j++){
                if(values[j]!=null){
                    if(values[j] instanceof String){
                        sql.append("and o."+keys[j]+" like"+"'%"+values[j]+"%'");
                    }
                    else{
                        sql.append("and o."+keys[j]+"="+values[j]);
                    }
                }
            }
            
        }
        return sql;
    }
}
