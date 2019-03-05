import data.Database;
import java.util.Scanner;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class main
{
    public static void main(String args[]) throws Exception
    {
        //https://www.mkyong.com/java/java-how-to-get-current-date-time-date-and-calender/
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/dd/mm");
        LocalDate localDate = LocalDate.now();
        String date = localDate.toString();
        System.out.println(date);
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);

        int a = Database.searchDaypending("03", "03", "2019", "wack");
        System.out.println(a);


        Database.createTable();
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("Input Spending Type");
        String type = scanner.nextLine();
        System.out.println("Spending type is " + type);

        System.out.println();
        System.out.println("Input Spending amount (don't include $)");
        int spending = scanner.nextInt();
        System.out.println("Spending amount is $" + spending);

        Database.post(day, month, year, spending, type);
    }
}
