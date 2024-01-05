
package com.example;
import java.util.ArrayList;
import java.util.List;

public class Member implements Comparable<Member> {
    public static ArrayList<Member> allMembers = new ArrayList<>();
    private String name;
    private String password;
    private String id;
    ;
    private String researchInterests;

    private ArrayList<Team> teams = new ArrayList<>();
    private int num ;
    public Member(String name,String password,String id,String researchInterests) {
        this.name = name;
        this.password=password;
        this.id = id;
        this.researchInterests = researchInterests;
        this.teams = new ArrayList<>();

    }
    public Member(String name,String password,String researchInterests) {
        this.name = name;
        this.password=password;
        this.id = id;
        this.researchInterests = researchInterests;
        this.teams = new ArrayList<>();


    }

    public Member(String id, String password) {
        this.id = id;
        this.password = password;
    }
    public Member(String id, String name, int num) {
        this.id = id;
        this.name = name;
        this.num=num;

    }
    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }
    public int getNum(){
        return num;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }


    public String getResearchInterests() {
        return researchInterests;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setResearchInterests(String researchInterests) {
        this.researchInterests = researchInterests;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public String getPass() {
        return password;
    }

    public void setPass(String password) {
        this.password =password;
    }

    @Override
    public String toString() {
        return name;
    }
    public static Member getMemberByID(String id){
        for (int i = 0; i < allMembers.size(); i++) {
            Member m  = allMembers.get(i);
            if (m.getId().equals(id)){
                return m;
            }
        }
        return null;
    }

    @Override
    public int compareTo(Member o) {
        return Integer.compare(this.getNum(), o.getNum());
    }
    public String getInfo(){
        String info = "";
        info += "Name: " + this.name + "\n";
        info += "ID: " + this.id + "\n";
        info += "Research interest: " + this.researchInterests + "\n";
        info += "Teams: [";
        for (int i = 0 ; i < (this.teams.size() - 1) ; i++){
            info += this.teams.get(i).getName() + ",";
        }
        info += this.teams.get(this.teams.size() - 1).getName() + "]" + "\n";
        info += "Working on: " ;
        ///////
        for (int i = 0 ; i < teams.size() ; i++){
            for (int j = 0 ; j < teams.get(i).getProjects().size() ; j++){
                info += teams.get(i).getProjects().get(j).getName() + "  ";
            }
        }
        ///////
        return info;
    }
}
