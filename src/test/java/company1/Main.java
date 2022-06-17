package company1;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String strdate="";

        System.out.println("Enter the flag (only file or console):");
        String flag = input.nextLine();
        if(flag.equals("file")){
            System.out.println("Enter the path:");
            String src = input.nextLine();
            File file = new File(src);
            if (file.length() == 0) {
                System.out.println("The file is empty");
            } else if (file.length() != 0) {
                try {
                    strdate = Files.readString(Paths.get(String.valueOf(file)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(flag.equals("console")){

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your date");
            strdate = sc.nextLine();
        }
        else {
            System.out.println("Not an option, try again");
            System.exit(0);
        }


        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(strdate, formatter);
            LocalDate dateNewYear = LocalDate.parse(strdate, formatter);

            LocalDate plus30 = date.plusDays(30);
            LocalDate lastInMonth = date.with(TemporalAdjusters.lastInMonth(DayOfWeek.SATURDAY));
            LocalDate newYear = LocalDate.parse(date.getYear()+"-12-31");
            long daysToNewYear= ChronoUnit.DAYS.between(date,newYear);


            System.out.printf("30 days plus date is %s\n", plus30);
            System.out.printf("Last Saturday in month %s\n", lastInMonth);
            System.out.println("Days to New Year "+daysToNewYear);



        } catch (DateTimeParseException e) {
            System.out.println("Enter the correct format (yyyy-MM-dd)");
            throw e;
        }

    }
}
