package sample;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Controller {

    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    // Variable for seconds until shutdown time
    private int diffSecs;

    // Variables for Current Time
    private LocalTime currTime;
    private String strCurrTime;


    // Variables for Input Time
    private LocalTime inputTime;
    private String strInputTime;

    public void SetCurrTime(){

        currTime = LocalTime.now();
        strCurrTime = currTime.format(timeFormatter);
        currTime = LocalTime.parse(strCurrTime, timeFormatter);
        strCurrTime = currTime.toString();
    }

    public String GetCurrTime(){

        return strCurrTime;
    }

    public void SetInputTime(String pInputTime){

        try {
            inputTime = LocalTime.parse(pInputTime, timeFormatter);
            strInputTime = inputTime.toString();
        } catch (java.time.format.DateTimeParseException e){
            strInputTime = "";
        }
    }

    public String GetInputTime(){

        return strInputTime;
    }

    public void ComputeTimeDifference() {

        int currInMins;
        int inputInMins;
        diffSecs = 0;

        currInMins = (currTime.getHour() * 60) + currTime.getMinute();
        inputInMins = (inputTime.getHour() *60) + inputTime.getMinute();


        if(inputTime.isBefore(currTime))
            diffSecs = ((inputInMins - currInMins) + 1440) * 60;   // Input time is next day
        else
            diffSecs = (inputInMins - currInMins) * 60;            // Input time is same day

    }

    public void SetShutdown(){

        ComputeTimeDifference();

        Runtime runtime = Runtime.getRuntime();
        try {
            Process proc = runtime.exec("shutdown -s -t " + diffSecs);
        } catch (java.io.IOException e){
            System.out.println("Error in SetShutdown during Proc.");
        }
    }

    public void AbortShutdown(){
        Runtime runtime = Runtime.getRuntime();
        try {
            Process proc = runtime.exec("shutdown -a");
        } catch (java.io.IOException e){
            System.out.println("Error in AbortShutdown during Proc.");
        }
    }

}
