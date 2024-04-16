// Conner Wilson
// this is my main that starts everything running
// i have some extra credit with using the package command look at HeartRate line: 3
// i also have extra credit for making a class for calculations look at HeartRate lines: 72-163

import heartrate.HeartRate;
import heartrate.calc;
import java.util.Scanner;

public class heartrates{
    public static void main(String[] args){
        // 13 - 21 are all setting up our intial values
        Scanner Scan = new Scanner(System.in);
        System.out.println("what's your first Name?");
        String fName = Scan.nextLine();
        System.out.println("what's your last Name?");
        String lName = Scan.nextLine();
        System.out.println("when were you born (mm/dd/yyyy) or (mm/dd/yy)");
        String birthdate = Scan.nextLine();
        System.out.println("what is the current date (mm/dd/yyyy) or (mm/dd/yy less than current year)");
        String currentDate = Scan.nextLine();
        HeartRate heart = new HeartRate(fName, lName, birthdate, currentDate); //calls class HeartRate with previous values

        // 25 - 30 is setting up our variables to be used in our calculations
        Integer x = Integer.parseInt(heart.day(currentDate));
        Integer y = Integer.parseInt(heart.month(currentDate));
        Integer z = Integer.parseInt(heart.year(currentDate));
        Integer a = Integer.parseInt(heart.day(birthdate));
        Integer b = Integer.parseInt(heart.month(birthdate));
        Integer c = Integer.parseInt(heart.year(birthdate));

        // runs the calculations for age, max heartrate, max Heart rate range, min heart rate range, all in a double list 
        double[] blank = heart.calculations(x,y,z,a,b,c);
        // prints out our output if correct if not it will just say error (don't know if mutiple errors could cause the same thing so it is just error)
        if (blank[0] != 0){
            System.out.printf("First Name: %s%nLast Name: %s%nAge: %.0f%nMax Heart Rate: %.0f%nTarget Heart Rate Range:%n   min: %.0f%n   max: %.0f",fName, lName, blank[0], blank[1], blank[2], blank[3]);
        }
        else{
            System.out.println("error");
        }
        

    }
}