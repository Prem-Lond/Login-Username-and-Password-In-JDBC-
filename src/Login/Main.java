package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Use the correct MySQL driver and set the timezone
        Class.forName("com.mysql.jdbc.Driver");

        Scanner scanner = new Scanner(System.in);

        boolean isRun = true;

        while (isRun) {
            System.out.println("Enter 1 for Register new Account");
            System.out.println("Enter 2 for Already have an Account (Login by username And Password)");
            System.out.println("Exit for 3");
            int n = scanner.nextInt();

            if (n == 1) {
                System.out.println("Enter username:");
                String user = scanner.next();

                System.out.println("Enter Password:");
                String pass = scanner.next();

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "premz@514.");
                PreparedStatement ps = con.prepareStatement("insert into usepass (user, pass) values (?, ?)");
                ps.setString(1, user);
                ps.setString(2, pass);
                int i = ps.executeUpdate();

                if (i > 0) {
                    System.out.println("Successfully registered");
                } else {
                    System.out.println("Fail to register");
                }
            } else if (n == 2) {
                System.out.println("Enter username:");
                String user = scanner.next();
                System.out.println("Enter Password:");
                String pass = scanner.next();

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "premz@514.");

                PreparedStatement ps = con.prepareStatement("select * from usepass where user=? and pass=?");
                ps.setString(1, user);
                ps.setString(2, pass);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    System.out.println("Successfully login.");
                } else {
                    System.out.println("Fail Login");
                    System.out.println("Password and username wrong");
                }
            } else {
                isRun = false;
            }
        }
    }
}
