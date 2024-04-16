//extra credit at line 3 with package command
//extra credit from line 74 - 165 with calculation class
package heartrate;

import java.util.*; 
import java.time.*;
import java.math.*;

public class HeartRate {
    private String firstName;
    private String lastName;
    private String birthDate;
    private String currentDate;

    public HeartRate(String firstName, String lastName, String birthDate, String currentDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.currentDate = birthDate;
    }
    // just in case
    public String namef(){return firstName;}

    public String namel(){return lastName;}
    //takes our day from a (mm/dd/yyyy) String
    public String day(String date){
        String day = "";
        if (date.contains("/")){
            String shortenedDate = date.substring(date.indexOf("/") + 1, date.length());
            day = shortenedDate.substring(0, shortenedDate.indexOf("/"));
        }
        return day;
    }
    //takes our month from a (mm/dd/yyyy) String
    public String month(String dates){
        String month = "";
        if (dates.contains("/")){
            month = dates.substring(0, dates.indexOf("/"));
        }
        return month;
    }
    //takes our year from a (mm/dd/yyyy) String
    public String year(String datess){
        String year = "";
        if (datess.contains("/")){
            String shortenedDate = datess.substring(datess.indexOf("/") + 1, datess.length());
            year = shortenedDate.substring( shortenedDate.indexOf("/") + 1, shortenedDate.length());
        }
        return year;
    }
    //runs all of our calculations from our calculation class
    public double[] calculations(int day, int month, int year, int birthday, int birthmonth, int birthyear){
        calcs calc = new calcs(day, month, year, birthday, birthmonth, birthyear);
        double age = calc.age(day,month,year,birthday,birthmonth,birthyear); //age
        if (age > 0){
            double max = calc.maxHeartRate(age); //max heart rate
            double[] range = calc.heartRateRange(max); //min and max range of heartrate (in order)
            age = Math.round(age);
            max = Math.round(max);
            range[0] = Math.round(range[0]); 
            range[1] = Math.round(range[1]);
            double[] everything = {age, max, range[0], range[1]}; // just for the return for easy printing
            return everything;
        }
        else{
            double[] fail = {0, 0, 0, 0}; // if it fails it returns all zeros 
            return fail;
        }
    }


}

class calcs{
    private int year;
    private int month;
    private int day;
    private int currentyear;
    private int currentmonth;
    private int currentday;

    public calcs(int year, int month, int day, int currentyear, int currentmonth, int currentday){
        this.year = year;
        this.month = month;
        this.day = day;
        this.currentyear = currentyear;
        this.currentmonth = currentmonth;
        this.currentday = currentday;
    }
    // does age calculation with day, month, year of birthdate and currentdate
    public double age(int day, int month, int year, int birthday, int birthmonth, int birthyear){
        double curYear = Math.floor(System.currentTimeMillis()/(1000 * 86400 * 365.25)) + 1970;
        int[] Months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // for month - day calculation 
        // leap year calculation 
        if (curYear % 4 == 0){
            if (curYear % 100 == 0){
                if (curYear % 400 == 0){
                    Months[1] = 29;
                }
                else{
                    Months[1] = 29;
                }
            }
            else{
                Months[1] = 29;
            }
        }
        double years = (double) year;
        double birthYears = (double) birthyear;
        //adds to the year value if it is only 2 digits which will change if the century changes
        if (year < 100  &&  year > (curYear - (Math.floor((curYear)/100) * 100))){ //checking if your values are from the previous century (ex: 76 = 1976)
            years += Math.floor((curYear - 100)/100) * 100;
        }
        else if (year <= (curYear - (Math.floor((curYear)/100) * 100))){ //checking if your values are from this century (ex: 12 = 2012)
            years += Math.floor((curYear)/100) * 100;
        }
        if (birthyear < 100  &&  birthyear > (curYear - (Math.floor((curYear)/100) * 100))){ //checking if your values are from the previous century (ex: 76 = 1976)
            birthYears += Math.floor((curYear - 100)/100) * 100;
        }
        else if (birthyear <= (curYear - (Math.floor((curYear)/100) * 100))){ //checking if your values are from this century (ex: 12 = 2012)
            birthYears += Math.floor((curYear)/100) * 100;
        }

        double age = 0.0;
        if (birthYears > years){ //checks for errors with the year stuff
            return -1.0;
        }
        else{ // sets first part of age
            age = years - birthYears;
        }

        double add = 0.0;
        if (month > birthmonth){ // adds or subtracts months as needed but subtracts or adds them as days
            for (int i = 0; i < month - birthmonth; i++){
                add += Months[birthmonth + i];
            }
        }
        else if (month < birthmonth){
            for (int i = 0; i < birthmonth - month; i++){
                add -= Months[(month + i)%12];
            }
        }
        //turns days to years 
        add /= 365.25;

        double addDay = 0;
        if (day > birthday){ //subtracts or adds days as needed
            addDay += day - birthday;
        }
        else if (day < birthday){
            addDay -= birthday - day;
        }
        addDay /= 365.25; //turns days to years
        age += addDay + add; //adds those changes to the final total 

        return age;
    }
    public double maxHeartRate(double age){ // just max heart rate calculation
        return 220-age;
    }

    public double[] heartRateRange(double maxHeartRate){ //calculates the heart rate range {min, max}
        double[] heartRateRange = {maxHeartRate * .5, maxHeartRate * .85};
        return heartRateRange;
    }
}