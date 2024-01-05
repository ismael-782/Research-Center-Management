package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Reservation {
    public static ArrayList<Reservation> allReservation = new ArrayList<>();
    private int startTime;
    private int endTime;
    private LocalDate date;
    private Project project;
    private Machine machine;
    public Reservation(int startTime,int endTime,LocalDate date,Project project,Machine machine){
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.project = project;
        this.machine = machine;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public Machine getMachine() {
        return machine;
    }

    public Project getProject() {
        return project;
    }
    public static Reservation getReservatiionByMachineName(String machineName){
        for (int i = 0; i < allReservation.size(); i++) {
            Reservation reservation = allReservation.get(i);
            if(reservation.machine.getName().equals(machineName)) return reservation;
        }return null;
    }
    public static boolean isValid(LocalDate targetDate, int startime, int end) {


        LocalDate currentDateTime = LocalDate.now();
        if(targetDate.isAfter(currentDateTime)){
            return true;

        }
        if ((targetDate.isBefore(currentDateTime))){
            return false;
        }
        int currentHoure =Integer.valueOf(new Date().toString().substring(11,13)) ;
        if(currentHoure >= startime){
            return false;
        }
        if(currentHoure>end )
            return false;
        return true;

    }

}
