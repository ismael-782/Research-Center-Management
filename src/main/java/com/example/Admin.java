
package com.example;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.*;
import java.io.IOException;

class Admin extends User {

    private String name;
    private String email;
    public static ArrayList<Team> listOfTeamsWithLeader;
    public static ArrayList<Member> listOfMembers;

    public Admin(String username, String password) { ////////////////
        super(username, password);
    }
    public void addMembetToTeam(String teamName , String Id ) throws FileNotFoundException {

        Team.getTeamByName(teamName).addMember(Member.getMemberByID(Id));
        updateDataBase();
    }





    public static Member getMemberNamefromId(String id) {

        for (int i = 0; i < listOfMembers.size(); i++) {
            System.out.println(listOfMembers.get(i).toString() + "  " + id);
            if (listOfMembers.get(i).getId().equals(id)) {
                System.out.println("trrr");
                return listOfMembers.get(i);
            }
        }
        System.out.println(id + "dsxsdx");
        return null;
    }
    public  void add() throws FileNotFoundException {
        update();
    }



    public  void assignteamForproject(String p,Team t ,String pd) throws FileNotFoundException {


        Project P = new Project(p,t,pd,false);
        Project.allProjects.add(P);
        updateDataBase();
    }
    public void addNewMembertoTeam(Team team,Member member){

    }
    public void creatMachine(Machine machine) throws FileNotFoundException {
        Machine.allMachines.add(machine);
        updateDataBase();
    }
    public void creatTeam(Team team ) throws FileNotFoundException {
        Team.allTeams.add(team);
        updateDataBase();
    }





    public static String[] TopThreeMembers() {
        String[] top3 = new String[3];
        Map<String, Integer> dictionary = new HashMap<>();

        for (int i = 0; i < Member.allMembers.size(); i++) {
            dictionary.put(Member.allMembers.get(i).getName(),Member.allMembers.get(i).getNum());
        }
        String topKey1 = null;
        String topKey2 = null;
        String topKey3 = null;

        int maxValue1 = 0;
        int maxValue2 = 0;
        int maxValue3 = 0;

        // top 1
        for (int i = 0; i < dictionary.size(); i++) {
            if (maxValue1 <= Member.allMembers.get(i).getNum()) {
                maxValue1 = Member.allMembers.get(i).getNum();
                topKey1 = Member.allMembers.get(i).getName();
            }

        }
        // top 2
        for (int i = 0; i < dictionary.size(); i++) {
            if (maxValue2 <= Member.allMembers.get(i).getNum()) {
                if(topKey1 == Member.allMembers.get(i).getName()){
                    continue;
                } else{
                    maxValue2 = Member.allMembers.get(i).getNum();
                    topKey2 = Member.allMembers.get(i).getName();
                }
            }
        }
        // top 3
        for (int i = 0; i < dictionary.size(); i++) {
            if (maxValue3 <= Member.allMembers.get(i).getNum()) {
                if(topKey1 == Member.allMembers.get(i).getName() || topKey2 == Member.allMembers.get(i).getName()){
                    continue;
                } else{
                    maxValue3 = Member.allMembers.get(i).getNum();
                    topKey3 = Member.allMembers.get(i).getName();
                }
            }
        }
        top3[0] = topKey1;
        top3[1] = topKey2;
        top3[2] = topKey3;
        return top3;
    }

    public static String mostUtilizedMachine() { // Done do not change it
        String mostUtilizedMachie = "";
        int countUtilized = 0;

        for (int i = 0; i < Machine.allMachines.size(); i++) {
            int x = Machine.allMachines.get(i).getReservations().size();
            if (x >= countUtilized) {
                mostUtilizedMachie = Machine.allMachines.get(i).getName();
                countUtilized = x;
            }
        }
        return mostUtilizedMachie;
    }

    // ====================Project uses most
    // machines========================================//
    public String getProjectWithHighestMachineCount() { // Done do not change it
        String projectUsesMostMachines = "";
        int countMachines = 0;

        for (int i = 0; i < Project.allProjects.size(); i++) {
            int x = Project.allProjects.get(i).getMachines().size();
            if (x >= countMachines) {
                projectUsesMostMachines = Project.allProjects.get(i).getName();
                countMachines = x;
            }
        }
        return projectUsesMostMachines;
    }

    // ================================== Count numbers
    // =============================================//
    public static int countLines(String filePath) { // count number of lines in a file
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.err.println("File not found: " + filePath);
            return -1;
        }

        try {
            String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
            String[] lines = content.split("\n");
            return lines.length;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

   ///

    public static int DoneProjects() { // to count done projects
        int countDone = 0;
        for (int i = 0; i < Project.allProjects.size(); i++) {
            if(Project.allProjects.get(i).getStatus() == true){
                countDone ++;
            }
        }
        return countDone;
    }

    //////////////////// READ DATA /////////////////////
    public static void SetAll() {
        readFromMemberFile("/Users/Lenovo/Desktop/demo/memberData.file");
        readTeamsFromFile("/Users/Lenovo/Desktop/demo/teamData.file");
        readFromMachineFile("/Users/Lenovo/Desktop/demo/machineData.txt");
        readFromProjectFile("/Users/Lenovo/Desktop/demo/projectData.txt");
        readFromReservationFile("/Users/Lenovo/Desktop/demo/reservationData.txt");
    }
    /////////////////////////////////////////////////////

    public static void readFromMemberFile(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String[] elements;
            String id;
            String name;
            String pass;
            String team;
            String researchInterst;

            while ((line = reader.readLine()) != null) {

                elements = line.split("-");
                id = elements[0];
                pass = elements[1];
                name = elements[2];
                researchInterst = elements[3];
                Member member = new Member(name, pass, id, researchInterst);
                Member.allMembers.add(member);///////// Create members obeject (id , name , numOfteams )///////

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readTeamsFromFile(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String[] elements;
            String teamName;
            String leaderId;

            while ((line = reader.readLine()) != null) {
                elements = line.split("-");
                teamName = elements[0];
                leaderId = elements[1];

                String[] ids = elements[2].split(",");
                Member leader = Member.getMemberByID(leaderId);
                Team team = new Team(teamName, leader); // Create a new Team object with the name
                leader.addTeam(team);
                for (String id : ids) {
                    Member member = Member.getMemberByID(id);
                    team.addMember(member); // Add members to the Team object
                    member.addTeam(team);
                }

                Team.allTeams.add(team);



            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFromMachineFile(String fail) {
        ArrayList<Machine> machines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fail))) {
            String line;
            String[] elements;
            String name;
            String researchInterst;

            while ((line = reader.readLine()) != null) {

                elements = line.split(",");
                name = elements[0];
                researchInterst = elements[1];
                Machine.allMachines.add(new Machine(name, researchInterst));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFromProjectFile(String fail) {
        ArrayList<Project> projects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fail))) {
            String line;
            String[] elements;
            String name;
            Team team;
            String teamname;
            String description;
            boolean status;

            while ((line = reader.readLine()) != null) {

                elements = line.split(",");
                teamname= elements[2];
                name = elements[0];
                team= Team.getTeamByName(teamname);
                status = elements[1].startsWith("t");
                if(elements[3].equals("")){
                    description="ddd";
                }
                else description = elements[3];
                Project project = new Project(name,  team,description,status);
                Project.allProjects.add(project);
                project.setTeam(team);
                team.addProject(project);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void update() throws FileNotFoundException {
        updateDataBase();
    }

    public static void readFromReservationFile(String fail) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fail))) {
            String line;
            String[] elements;
            Project project;
            
            Machine machine;
            int startTime;
            int endTime;
            LocalDate date;

            while ((line = reader.readLine()) != null) {

                elements = line.split(",");
                machine = Machine.getMachineByName(elements[0]);
                project = Project.getProjectByName(elements[1]);
                startTime = Integer.valueOf(elements[2]);
                endTime = Integer.valueOf(elements[3]);
                date = LocalDate.of(Integer.valueOf(elements[4].substring(0, 4)),
                        Integer.valueOf(elements[4].substring(5, 7)), Integer.valueOf(elements[4].substring(8, 10)));



               if(Reservation.isValid(date,startTime,endTime)) {



                   Reservation r = new Reservation(startTime, endTime, date, project, machine);
                   Reservation.allReservation.add(r);
                   project.addReservation(r);
                   machine.addReservation(r);
                   project.addMachine(machine);

               }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    /////////////////// Save Data /////////////////

    public static void updateDataBase() throws FileNotFoundException, FileNotFoundException {
        // 1- Updating the file "memberData"
        ArrayList<Member> allMembers = Member.allMembers;
        PrintWriter updatedFile1 = new PrintWriter(new File("memberData.file"));
        for (int index = 0; index < allMembers.size() ; index++){
            String ID = allMembers.get(index).getId().trim();
            String password = allMembers.get(index).getPass().trim();
            String memberName = allMembers.get(index).getName().trim();
            String researchInterest = allMembers.get(index).getResearchInterests().trim();
            updatedFile1.println(ID + "-" + password + "-" + memberName + "-" + researchInterest);
        }
        updatedFile1.close();

        // 2- Updating the file "teamData"
        ArrayList<Team> allTeams = Team.allTeams;
        PrintWriter updatedFile2 = new PrintWriter(new File("teamData.file"));
        for (int index = 0; index < allTeams.size() ; index++){
            String teamName = allTeams.get(index).getName().trim();
            String leaderID = allTeams.get(index).getLeader().getId().trim();
            String membersIDs = "";

            ArrayList<Member> members = allTeams.get(index).getMembers();
            if(members.size()==0){
                updatedFile2.println(teamName + "-" + leaderID + "-" );
                break;
            }
            for (int j = 0 ; j < members.size() - 1 ; j++){
                membersIDs += members.get(j).getId().trim() + ",";        }
            membersIDs += members.get(members.size()-1).getId().trim();
            updatedFile2.println(teamName + "-" + leaderID + "-" + membersIDs);
        }
        updatedFile2.close();

        // 3- Updating the file "projectData.txt"
        ArrayList<Project> allProjects = Project.allProjects;
        PrintWriter updatedFile3 = new PrintWriter(new File("projectData.txt"));
        for (int index = 0 ; index < allProjects.size() ; index++){
            String projectName = allProjects.get(index).getName().trim();
            String booleanValue;
            if (allProjects.get(index).getStatus()){
                booleanValue = "true";        }
            else{
                booleanValue = "false";
            }
            String projectTeamName = allProjects.get(index).getTeam().getName().trim();

            String projectDescription;
            if( allProjects.get(index).getDescription().equals("") ){
                projectDescription = "No d";
            }
            else{
                projectDescription = allProjects.get(index).getDescription().trim();
            }
            updatedFile3.println(projectName + "," + booleanValue + "," + projectTeamName + "," + projectDescription);
        }
        updatedFile3.close();

        // 4- Updating the file "machineData.txt"
        ArrayList<Machine> allMachines = Machine.allMachines;
        PrintWriter updatedFile4 = new PrintWriter(new File("machineData.txt"));
        for (int index = 0 ; index < allMachines.size() ; index++){
            String machineName = allMachines.get(index).getName().trim();
            String researchInterest = allMachines.get(index).getResearchInterest().trim();
            updatedFile4.println(machineName + "," + researchInterest);
        }
        updatedFile4.close();

        // 5- Updating the file "reservationData.txt"
        ArrayList<Reservation> allReservations = Reservation.allReservation;
        PrintWriter updatedFile5= new PrintWriter(new File("reservationData.txt"));
        for (int index = 0 ; index < allReservations.size() ; index++){
            String machineName = allReservations.get(index).getMachine().getName().trim();
            String projectName = allReservations.get(index).getProject().getName().trim();
            String startTime = String.valueOf(allReservations.get(index).getStartTime());
            String endTime = String.valueOf(allReservations.get(index).getEndTime());
            String date = allReservations.get(index).getDate().toString().trim();
            updatedFile5.println(machineName + "," + projectName + "," + startTime + "," + endTime + "," + date);
        }
        updatedFile5.close();
    }

}
