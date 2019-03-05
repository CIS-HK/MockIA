package data;

import expenditures.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database {

    private static Connection getConnection() throws Exception
    {
        try
        {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/Database";
            String username = "root";
            String password = "root";
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");
            return conn;
        }
        catch (Exception e)
        {
            System.out.println("Connection Error: " + e);
        }
        return null;
    }

    public static void createTable() throws Exception
    {
        try
        {
            Connection con = getConnection();
            PreparedStatement create = con.prepareStatement
                ("CREATE TABLE IF NOT EXISTS dailySpending(id int NOT NULL AUTO_INCREMENT, day varchar(5), " +
                        "month varchar(5), year varchar(5), amountSpent int, type varchar(255), PRIMARY KEY (id))");
            create.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println("Table Error: " + e);
        }
        finally
        {
            //System.out.println("Table Created");
        }

    }

    public static void post(String day, String month, String year, int amountSpent, String type) throws Exception
    {
        try
        {
            Connection con = getConnection();
            PreparedStatement post = con.prepareStatement
                    ("INSERT INTO dailySpending(day, month, year, amountSpent, type) VALUES(?, ?, ?, ?, ?)");
            post.setString(1, day);
            post.setString(2, month);
            post.setString(3, year);
            post.setInt(4, amountSpent);
            post.setString(5, type);
            post.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println("Post Error: " + e);
        }
        finally
        {
            System.out.println("Post complete");
        }
    }

    public static ArrayList<Expenditures> createObjects() throws Exception
    {
        ArrayList<Expenditures> listOfSpending = new ArrayList<Expenditures>();
        try
        {
            Connection con = getConnection();
            PreparedStatement get = con.prepareStatement
                    ("SELECT day, month, year, type, amountSpent FROM dailySpending");

            ResultSet result = get.executeQuery();

            while (result.next())
            {
                switch (result.getString("type"))
                {
                    case "Bills" :
                        Expenditures bills = new Bills(
                                result.getString("type"),
                                result.getString("day"),
                                result.getString("month"),
                                result.getString("year"),
                                result.getInt("value"));
                        listOfSpending.add(bills);
                        break;
                    case "Transport" :
                        Expenditures transport = new Transport(
                                result.getString("type"),
                                result.getString("day"),
                                result.getString("month"),
                                result.getString("year"),
                                result.getInt("value"));
                        listOfSpending.add(transport);
                        break;
                    case "Lunch" :
                        Expenditures lunch = new Lunch(
                                result.getString("type"),
                                result.getString("day"),
                                result.getString("month"),
                                result.getString("year"),
                                result.getInt("value"));
                        listOfSpending.add(lunch);
                        break;
                    case "Groceries" :
                        Expenditures groceries = new Groceries(
                                result.getString("type"),
                                result.getString("day"),
                                result.getString("month"),
                                result.getString("year"),
                                result.getInt("value"));
                        listOfSpending.add(groceries);
                        break;
                    case "OccasionalExpenses" :
                        Expenditures occasionalExpenses = new Bills(
                                result.getString("type"),
                                result.getString("day"),
                                result.getString("month"),
                                result.getString("year"),
                                result.getInt("value"));
                        listOfSpending.add(occasionalExpenses);
                        break;
                }

            }

        }

    }

    public static int searchDaypending(String day, String month, String year, String type) throws Exception
    {
        try
        {
            int total = 0;
            Connection con = getConnection();
            PreparedStatement get = con.prepareStatement
                    ("SELECT day, month, year, type, amountSpent FROM dailySpending");

            ResultSet result = get.executeQuery();

            while (result.next())
            {
                if (result.getString("type").equals("all"))
                {
                    {
                        if (result.getString("day").equals(day)
                                && result.getString("month").equals(month)
                                && result.getString("year").equals(year))
                        {
                            total += total + result.getInt("amountSpent");
                        }
                    }
                }
                else
                {
                    if (result.getString("day").equals(day)
                                && result.getString("month").equals(month)
                                && result.getString("year").equals(year)
                                && result.getString("type").equals(type))
                    {
                        total += result.getInt("amountSpent");
                    }
                }
            }
            return total;
        }
        catch (Exception e)
        {
            System.out.println("Search Date Spending Error: " + e);
            return 0;
        }
    }

    public static int searchMonthSpending(String month, String year, String type) throws Exception
    {
        try
        {
            int total = 0;
            Connection con = getConnection();
            PreparedStatement get = con.prepareStatement
                    ("SELECT day, month, year, type, amountSpent FROM dailySpending");
            ResultSet result = get.executeQuery();
            if (!result.getString("type").equals("all"))
            {
                while (result.next())
                {
                    if (result.getString("month").equals(month)
                            && result.getString("year").equals(year)
                            && result.getString("type").equals(type))
                        total += total + result.getInt("amountSpent");
                }
                return total;
            }
            else
            {
                while (result.next())
                {
                    if (result.getString("month").equals(month)
                            && result.getString("year").equals(year))
                        total += total + result.getInt("amountSpent");
                }

                return total;
            }
        }
        catch (Exception e)
        {
            System.out.println("Search Date Spending Error: " + e);
            return 0;
        }
    }
}
