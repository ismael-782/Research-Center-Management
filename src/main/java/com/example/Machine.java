package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Machine {
    public static ArrayList<Machine> allMachines = new ArrayList<>();
    public static ArrayList<String> machineNames =new ArrayList<>();
    private String name;
    private String Id;
    private String researchInterest;
    private ArrayList<Reservation> reservations;

    public Machine(String name, String researchInterest) {
        this.name = name;
        this.researchInterest = researchInterest;
        this.reservations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getResearchInterest() {
        return researchInterest;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public boolean CheckForConflict(int startTime, int endTime, LocalDate date) {
        for (int i = 0; i < this.reservations.size(); i++) {
            if (reservations.get(i).getDate().isEqual(date)) {
                if (reservations.get(i).getStartTime() >= startTime && reservations.get(i).getStartTime() <= endTime) {
                    return false;
                } else if (reservations.get(i).getEndTime() >= startTime && reservations.get(i).getStartTime() <=  endTime) {
                    return false;
                }
            }

        }
        return true;
    }

    public String getId() {
        return Id;
    }

    public static Machine getMachineByName(String name){
        for (int i = 0; i < allMachines.size(); i++) {
            Machine machine = allMachines.get(i);
            if(machine.getName().equals(name)) return machine;
        }return null;
    }
    ///new
    public static ArrayList<String> MachineName(){
        for (int i = 0; i <allMachines.size() ; i++) {
            if(!machineNames.contains(allMachines.get(i).getName())){
                System.out.println(machineNames.get(i));
            machineNames.add(allMachines.get(i).getName());
        }}
        return machineNames;
    }
//    public static ArrayList<String> Teamnames(){
//
//        for (int i = 0; i <allTeams.size() ; i++) {
//            teamsname.add(allTeams.get(i).getname());
//        }
//        return teamsname;
//    }
}