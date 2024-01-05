package com.example;

import java.util.ArrayList;

public class Team {
    public static ArrayList<Team> allTeams = new ArrayList<>();

    private String name;
    private Member leader;
    private ArrayList<Member> members ;
    public static ArrayList<String> teamsname = new ArrayList<>();
    private ArrayList<Project> projects = new ArrayList<>();
    private int memberCount;

    public Team(String name) {
        this.name = name;

    }
 //   private int numOFMembers = members.size();
    public Team(String name,Member leader) {
        this.name = name;
        this.leader = leader;
        this.members=new ArrayList<>();
    }
    public int getMemberCount(){  // new
        memberCount = members.size();
        return memberCount;
    }




    public String getName() {
        return name;
    }

    public Member getLeader() {
        return this.leader;
    }
    public void incrementMemberCount() {
        memberCount++;
    }



    public ArrayList<Member> getMembers() {
        return this.members;
    }
    public void addMember(Member member){
        members.add(member);
    }
    public void addProject(Project project){
        projects.add(project);

    }
    public static Team getTeamByName(String name){
        for (int i = 0; i < allTeams.size(); i++) {
            Team team  = allTeams.get(i);
            if (team.getName().equals(name)){
                return team;
            }
        }
        return null;
    }
    public static ArrayList<String> Teamnames(){

        for (int i = 0; i <allTeams.size() ; i++) {
            teamsname.add(allTeams.get(i).getName());
        }
        return teamsname;
    }


    public ArrayList<Project> getProjects() {
        return projects;
    }

    @Override
    public String toString() {
        return name;
    }
}
