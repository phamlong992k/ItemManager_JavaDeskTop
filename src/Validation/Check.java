/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validation;

/**
 *
 * @author Asus
 */


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Check {

	public static int checkInteger(String input) {
		Scanner sr = new Scanner(System.in);
		int n;
		while (true) {
			try {
				sr = new Scanner(System.in);
				System.out.println(input);
				n = sr.nextInt();
				return n;
			} catch (InputMismatchException e) {
				System.out.println("Invalid");
			}
		}
	}

	public static int checkInteger(String input, int down, int up) {
		Scanner sr = new Scanner(System.in);
		int n;
		while (true) {
			try {
				sr = new Scanner(System.in);
				System.out.println(input);
				n = sr.nextInt();
				if (n <= up && n >= down) {
					return n;
				} else {
					System.out.println("Invalid");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid");
			}
		}
	}

	public static int checkInteger(String input, int down) {
		Scanner sr = new Scanner(System.in);
		int n;
		while (true) {
			try {
				sr = new Scanner(System.in);
				System.out.println(input);
				n = sr.nextInt();
				if (n > down) {
					return n;
				} else {
					System.out.println("Invalid");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid");
			}
		}
	}
        

	public static String checkString(String input) {
		Scanner sr = new Scanner(System.in);
		String s;
		while (true) {
			sr = new Scanner(System.in);
			System.out.println(input);
			s = sr.nextLine().trim();
			if (s == null || s.length() == 0 || s.isEmpty()) {
				System.out.println("Invalid");
			} else {
				return s;
			}
		}
	}

	public static boolean checkStringInput(String stringCheck) {
		if (stringCheck == null || stringCheck.length() == 0 || stringCheck.isEmpty()) {
			System.out.println("Invalid");
			return false;
		} else {
			return true;
		}
	}

	public static boolean checkStringInput(String stringCheck, String format) {

		if (stringCheck == null || stringCheck.length() == 0 || stringCheck.isEmpty() || !stringCheck.matches(format)) {
			System.out.println("Invalid");
			return false;
		} else {
			return true;
		}
	}

	public static String checkString(String input, String[] list) {
		Scanner sr = new Scanner(System.in);
		String s;
		while (true) {
			sr = new Scanner(System.in);
			System.out.println(input);
			s = sr.nextLine().trim();
			if (s == null || s.length() == 0 || s.isEmpty()) {
				System.out.println("Invalid");
			} else {
				int flag = 0;
				for (String list1 : list) {
					if (s.toLowerCase().equals(list1.toLowerCase())) {
						flag = 1;
					}
				}
				if (flag == 1) {
					return s;
				} else {
					System.out.println("Invalid");
				}
			}
		}
	}

	public static String checkString(String input, String format) {
		String s;
		Scanner sr = new Scanner(System.in);
		while (true) {
			System.out.println(input);
			sr = new Scanner(System.in);
			s = sr.nextLine();
			if (s == null || s.length() == 0 || s.isEmpty() || !s.matches(format)) {
				System.out.println("Invalid");
			} else {
				return s;
			}
		}
	}

	public static double checkDouble(String input) {
		Scanner sr = new Scanner(System.in);
		double db;
		while (true) {
			try {
				sr = new Scanner(System.in);
				System.out.println(input);
				db = sr.nextDouble();
				return db;
			} catch (InputMismatchException e) {
				System.out.println("Invalid");
			}
		}
	}
	public static boolean checkDoubleInput(String db) {
		try {
			if(Double.parseDouble(db)<0) {
				return false;
			}
			else {
				return true;
			}
		}catch(Exception e) {
			return false;
		}
	}

	public static double checkDouble(String input, double down, double up) {
		Scanner sr = new Scanner(System.in);
		double db;
		while (true) {
			try {
				sr = new Scanner(System.in);
				System.out.println(input);
				db = sr.nextDouble();
				if (db >= down && db <= up) {
					return db;
				} else {
					System.out.println("Invalid");
				}

			} catch (InputMismatchException e) {
				System.out.println("Invalid");
			}
		}
	}

	public static double checkDouble(String input, double down) {
		Scanner sr = new Scanner(System.in);
		double db;
		while (true) {
			try {
				sr = new Scanner(System.in);
				System.out.println(input);
				db = sr.nextDouble();
				if (db >= down) {
					return db;
				} else {
					System.out.println("Invalid");
				}

			} catch (InputMismatchException e) {
				System.out.println("Invalid");
			}
		}
	}
    public static String formartAsTable(ArrayList<List<String>>rows){
        int maxLengths[]= new int[rows.get(0).size()];
        rows.stream().forEach((row) -> {
            for(int i=0;i<row.size();i++){
                maxLengths[i]=Math.max(maxLengths[i],row.get(i).length());
            }
        });
        StringBuilder formatBuilder= new StringBuilder();
        for(int maxLenghth:maxLengths){
            formatBuilder.append("%-").append(maxLenghth+2).append("s");
        }
        String format= formatBuilder.toString();
        StringBuilder result= new StringBuilder();
        rows.stream().forEach((row) -> {
            result.append(String.format(format,row.toArray())).append("\n");
        });
        return result.toString();
    }
   
    public static Date checkDate(String input){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        Scanner sr= new Scanner(System.in);
        String date="";
        Date result= new Date();
        int flag=1;
        do{
            flag=1;
            try {
                sr= new Scanner(System.in);
                System.out.println("Enter Date: ");
                date=sr.nextLine();
                result=format.parse(date);
            } catch (ParseException ex) {
                System.out.println("Invalid");
                flag=0;
            }
        }while(flag==0);
        return result;
    }
    

}

