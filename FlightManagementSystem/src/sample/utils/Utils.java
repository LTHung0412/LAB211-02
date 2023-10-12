/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author LENOVO
 */
public class Utils {

    public static String getDate(String welcome, String dateFormat) {
        boolean validDate = false;
        String result = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

        Scanner scanner = new Scanner(System.in);

        do {
            try {
                System.out.print(welcome);
                result = scanner.nextLine();
                LocalDate.parse(result, formatter); // Attempt to parse the input as a date
                validDate = true;
            } catch (Exception e) {
                System.err.println("Incorrect date! Please enter again!");
            }
        } while (!validDate);

        return result;
    }

    public static String getDate(String welcome, String dateFormat, String oldData) {
        boolean validDate = false;
        String result = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        Scanner scanner = new Scanner(System.in);

        do {
            try {
                System.out.print(welcome);
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    result = oldData; // Keep the old date if input is empty
                } else {
                    result = input;
                    LocalDate.parse(result, formatter); // Attempt to parse the input as a date
                }
                validDate = true;
            } catch (Exception e) {
                System.err.println("Incorrect date! Please enter again.");
            }
        } while (!validDate);

        return result;
    }

    public static String getString(String welcome) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Input text!!!");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String getString(String welcome, String oldData) {
        String result = oldData;
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }

    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static int getInt(String welcome, int min, int max, int oldData) {
        boolean check = true;
        int number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static boolean confirmYesNo(String welcome) {
        boolean result = false;
        String confirm = Utils.getString(welcome);
        if ("Y".equalsIgnoreCase(confirm)) {
            result = true;
        }
        return result;
    }
}
