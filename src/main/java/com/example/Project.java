package com.example;

import java.util.ArrayList;
import java.util.Objects;

public class Project {
    public static ArrayList<Project> allProjects= new ArrayList<>();
    public static ArrayList<String> projectNamees = new ArrayList<>();
    private String name;

    private String description;
    private Team team;
    private boolean status;
    private ArrayList<Reservation> reservations;
    private ArrayList<Machine> machines;
    private int numOfResrvedMachine ;
    public Project(String name){
        this.name = name;
    }
    public Project(String name,boolean status){
        this.name = name;

        this.status = status;

        this.reservations = new ArrayList<>();
        this.machines = new ArrayList<>();
    }
    public Project(String name,String description,boolean status){
        this.name = name;
        this.description = description;
        this.status = status;
        this.reservations = new ArrayList<>();
        this.machines = new ArrayList<>();
        this.numOfResrvedMachine = getNumOfReserved();
    }



    public Project(String name,Team team,String description,boolean status){
        this.name = name;
        this.description = description;
        this.status = status;
        this.team=team;
        this.reservations = new ArrayList<>();
        this.machines = new ArrayList<>();
    }

//    public Project(String name,String x,String description,boolean status){
//        this.name = name;
//        this.description = description;
//        this.status = status;
//        this.x=x;
//        this.team=team;
//        this.reservations = new ArrayList<>();
//        this.machines = new ArrayList<>();
//    }
    private int getNumOfReserved() {
        return this.reservations.size();
    }

    public int getNumOfResrvedMachine() {
            numOfResrvedMachine = machines.size();
            return numOfResrvedMachine;
        }




    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }



    public ArrayList<Machine> getMachines() {
        return machines;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }
    public boolean getStatus(){
        return status;
    }
    public void addReservation(Reservation reservation){
        reservations.add(reservation);
    }
    public void addMachine(Machine machine){
        machines.add(machine);
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getStautAsst(){
        if(status){
            return "true";
        }
        else return "false";


    }
    public static Project getProjectByName(String name){
        for (int i = 0; i < allProjects.size(); i++) {
            Project project = allProjects.get(i);
            if(project.getName().equals(name)) return project;
        }return null;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public static ArrayList<String> ProjTeamsName(){
        for (int i = 0; i <Project.allProjects.size() ; i++) {
            if(!projectNamees.contains(allProjects.get(i).getName())){
            projectNamees.add(allProjects.get(i).getName());
        }}
        return projectNamees;
    }


    @Override
    public String toString() {
        return name;
    }




}
