/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Item;
import DTO.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class DBUtil {
    // ham nay check login
    //tra ve ten cua nhan vien    

    public static String checkLogin(String email, String password) {
        String name = "";
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            // b1 tao connection
            cn = MyConnection.getConnect();
            if (cn != null) {
                // b2 viet lenh sql muon thuc thi
                String sql = "select name from staff "
                        + "where staff.email like ? and staff.password like ?";
                ps = cn.prepareStatement(sql);
                ps.setObject(1, email);
                ps.setObject(2, password);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    name = rs.getString("name");
                }
                //Statement st;   
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"loi co so du lieu");
                System.exit(0);
            }
        }
        return name;
    }

    public static Vector<Supplier> loadSuppliers() {
        Vector<Supplier> result = new Vector<>();
        String sql = "Select id,name,supplying from supplier";
        PreparedStatement ps = null;
        Connection cn = null;
        try {
            cn = MyConnection.getConnect();
            if (cn != null) {
                ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Supplier sup = new Supplier(Integer.parseInt(rs.getString(1)), rs.getString(2),Integer.parseInt(rs.getString(3)));
                    result.add(sup);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"khong tim thay du lieu");
            System.exit(0);
        }
        return result;
    }

    //input ten bang can lay trong database
    public static Vector<String> loadColumnLable(String tableName) {
        Connection cn = null;
        Vector<String> v = new Vector<>();
        PreparedStatement ps = null;
        try {
            cn = MyConnection.getConnect();
            if (cn != null) {
                String sql = "select * from " + tableName;
                ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData meta = rs.getMetaData();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    
                    if(!meta.getColumnName(i).equals("supplying")){
                        v.add(meta.getColumnName(i));
                    }
                    
                }
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"khong tim thay du lieu");
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
        return v;
    }

    //chen mot record vao bang bat ky
    // return so record duoc chen
    public static int doRecord(String tableName, String sql) throws SQLException {
        Connection cn = null;
        int result = 0;

        cn = MyConnection.getConnect();
        if (cn != null) {
            PreparedStatement ps = cn.prepareStatement(sql);
            result = ps.executeUpdate();
        }

        return result;
    }

    public static Vector<Item> loadItems() {
        Vector<Item> result = new Vector<>();
        String sql = "Select id,name,price,supplierid from item";
        PreparedStatement ps = null;
        Connection cn = null;
        try {
            cn = MyConnection.getConnect();
            if (cn != null) {
                ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Item item;
                    if (rs.getString(4) == null) {
                        item = new Item(Integer.parseInt(rs.getString(1)), rs.getString(2), Integer.parseInt(rs.getString(3)), -1);
                    } else {
                        item = new Item(Integer.parseInt(rs.getString(1)), rs.getString(2), Integer.parseInt(rs.getString(3)), Integer.parseInt(rs.getString(4)));
                    }
                    if (item != null) {
                        result.add(item);
                    }

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"khong tim thay du lieu");
            System.exit(0);
        }finally{
            try {
                if(cn!=null){
                    cn.close();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

}
