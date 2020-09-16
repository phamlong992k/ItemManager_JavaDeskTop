/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Item;
import DTO.Supplier;
import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Asus
 */
public class FileDAO {

    public static void writeFile(Vector list) throws Exception {
        PrintWriter p = null;
        File fN;
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filter_1 = new FileNameExtensionFilter("CSV", "csv");
        FileFilter filter_2 = new FileNameExtensionFilter("TXT", "txt");
        fileChooser.setFileFilter(filter_1);
        fileChooser.setFileFilter(filter_2);
        fileChooser.showSaveDialog(null);
        File file = fileChooser.getSelectedFile();
        if (file != null) {
            if (fileChooser.getFileFilter().equals(filter_2)) {
                if (!file.getName().contains(".txt")) {
                    file = new File(file.getParent(), file.getName() + ".txt");
                }

            } else if (fileChooser.getFileFilter().equals(filter_1)) {
                if (!file.getName().contains(".csv")) {
                    file = new File(file.getParent(), file.getName() + ".csv");
                }

            }
            try {
            p = new PrintWriter(file);
           
//            p.println("Danh Sach Bao Cao");
//            SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
//            
//            p.println(sdf.format(Calendar.getInstance().toString()));
            Field[] fields = list.get(0).getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                p.print(fields[i].getName());
                if (i != fields.length - 1) {
                    p.print(",");
                }
            }
            p.print("\n");
            for (Object o : list) {
                p.println(o);

            }
            JOptionPane.showMessageDialog(null, "Da xuat bao cao thanh cong");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ghi File loi");
        } finally {
            try {
                if (p != null) {
                    p.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        }

        
    }
}
