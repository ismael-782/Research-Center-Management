package com.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class AdminMainPageController implements Initializable {
  private static String username, password;
  CategoryAxis xAxis = new CategoryAxis();
  NumberAxis yAxis = new NumberAxis();
  Admin AdminEntered = new Admin(username, password);
  Machine machineSelected ;

  public static void transfare_admin_data(String u, String p) {
    username = u;
    password = p;
  }

  // Show Statistics page (1)
  @FXML
  private AnchorPane HomePage;

  @FXML
  private Button ShowStatisticsButton, LogOut, p1_ShowTeamsInfoButton, p1_ShowProjectsInfoButton;

  @FXML
  private TableView<Project> p1_ProjectTable;

  @FXML
  private TableColumn<Project, String> ProjectTable_ProjectName;

  @FXML
  private TableColumn<Project, Integer> ProjectTable_NumOfResMachines;

  @FXML
  private TableView<Team> p1_TeamsTable;

  @FXML
  private TableColumn<Team, Member> TeamsTable_LeaderName;

  @FXML
  private TableColumn<Team, Integer> TeamsTable_NumOfMembers;

  @FXML
  private TableColumn<Team, String> TeamsTable_TeamName;

  @FXML
  private Text ActiveMembers, ProjectUsesMostMachine, MostUtilizedMavhine;

  @FXML
  private BarChart<String, Number> Numbers_Chart;
  // ===============================================================

  // Manage Projects Page (2)
  @FXML
  private Button ManageProjectButton;

  @FXML
  private AnchorPane ManageProjectsPage;
  // Add new:
  @FXML
  private TextField NewProjectName;

  @FXML
  private TextField AssignedTeamForTheProject;

  @FXML
  private TextField Description;

  @FXML
  private Button CreateProjectButton;

  @FXML
  private Label WarningCreateProject , Project_created_successfully, ExistedTeamLabel;

  // ------Mark As Done-------
  @FXML
  private TextField ProjectNameFromTable;

  @FXML
  private Button MarkAsDone;

  @FXML
  private Label Mark_As_Done_warning;

  // --------------------------

  @FXML
  private Label DescriptionLabel, ProjectInfo_Status, ProjectInfo_TeamWorkOn;

  @FXML
  private ComboBox<String> ListOfProjects;

  @FXML
  private TableView<Machine> P2_ProjectInfoTable;

  @FXML
  private TableColumn<Machine, String> ProjectInfo_Machines;

  ObservableList<Machine> P2_ProjectInfoTable_OL = FXCollections.observableArrayList();

  // ============================================================

  // Manage Teams Page (3)

  @FXML
  private Button ManageTeamsButton;

  @FXML
  private AnchorPane ManageTeamsPage;

  // New Member:
  @FXML
  private TextField TeamNameToAddMemberTo;

  @FXML
  private TextField NameOfTheUserToAddToTeam;

  @FXML
  private Button AddMemberButton;

  @FXML
  private Label WarningAddNewMember;

  // Creat New:
  @FXML
  private TextField NewTeamName;

  @FXML
  private TextField NewMemberName;

  @FXML
  private Button CreateTeamButton;

  @FXML
  private TableView<Member> P3_ListOfUsers;

  @FXML
  private TableColumn<Member,String> UsersList_User;

  @FXML
  private TableColumn<Member,String> UsersList_ID;
  // ------------------------
  @FXML
  private ComboBox<String> ListOfTeams;

  @FXML
  private TableView<Member> P3_TeamInfoTable;

  ObservableList<Member> P3_TeamInfoTable_OB = FXCollections.observableArrayList();

  @FXML
  private TableView<Project> P3_Projects_Table;

  ObservableList<Project> P3_Projects_Table_OB = FXCollections.observableArrayList();


  @FXML
  private TableColumn<Member,String> TeamInfo_MemberName;

  @FXML
  private TableColumn<Member, String> TeamInfo_ID;

  @FXML
  private TableColumn<Project,String> TeamInfo_Projects;

  @FXML
  private Label Leader_Name , Team_not_found , Team_Creation_message;

  // =======================================================

  // Manage Machines Page (4)
  @FXML
  private Button ManageMachineButton;

  @FXML
  private AnchorPane ManageMachinesPage;

  @FXML
  private TableView<Machine> P4_MachineTaableView;

  ObservableList<Machine> P4_MachineTaableView_OB = FXCollections.observableArrayList();

  @FXML
  private TableColumn<Machine, String> MachineTaableView_Name, MachineTaableView_REInterest;

  // Assign to project
  @FXML
  private TextField MachineNameReservation, ProjectToAssighnMachine, StartHour, EndHour;

  @FXML
  private Label Check_reservation_label , Machine_not_Found , Project_not_Found; // to print a message

  @FXML
  private DatePicker DateProvider;

  @FXML
  private Button AssignMachineToProjectButton;

  // Current Reservations
  @FXML
  private Text MachineChoosenToSeeReservations;

  @FXML
  private TableView<Reservation> P4_CurrentReservationTable;

  ObservableList<Reservation> P4_CurrentReservationTable_OB = FXCollections.observableArrayList();

  @FXML
  private TableColumn<Reservation, LocalDate> CurrentReservationTable_Date ;

  @FXML
  private TableColumn<Reservation, Integer> CurrentReservationTable_Start , CurrentReservationTable_Finish;
  // Creat New Machine:
  @FXML
  private TextField NewMachineName, NewMachineID , ResearchIntereset;

  @FXML
  private Label Chech_machine_creation;

  @FXML
  private Button CreateNewMachineButton;


  @FXML
  void AddMember(ActionEvent event) throws FileNotFoundException { // Page 1
    if (NameOfTheUserToAddToTeam.getText().isEmpty() ||TeamNameToAddMemberTo.getText().isEmpty()) {
      WarningAddNewMember.setText("Fill All Fields");
    } else if (Member.getMemberByID(NameOfTheUserToAddToTeam.getText().toString()) == null) {
      WarningAddNewMember.setText("Member is Not Registered In Sysytem");
    } else if (Team.getTeamByName(TeamNameToAddMemberTo.getText().toString()) == null) {
      WarningAddNewMember.setText("Team is Not Found In Sysytem");
    } else {
      WarningAddNewMember.setText("Member Added Successfully");
      AdminEntered.addMembetToTeam(TeamNameToAddMemberTo.getText().toString(),NameOfTheUserToAddToTeam.getText().toString());
    }
  }

  @FXML
  void CheckAddNewMemberWarning(KeyEvent event) { // Page 1
    if (NameOfTheUserToAddToTeam.getText().isEmpty() ||TeamNameToAddMemberTo.getText().isEmpty()) {
      WarningAddNewMember.setText("Fill All Fields");
    } else {
      WarningAddNewMember.setText("");
    }

  }

  @FXML
  void CreatTeam(ActionEvent event) throws FileNotFoundException {
    if(Team.getTeamByName(NewTeamName.getText())==null){
      Team_not_found.setText("Avalible Team Name");
      if(Member.getMemberByID(NewMemberName.getText())!=null){
        Team_Creation_message.setText("Member Avalible");

        Member m = Member.getMemberByID(NewMemberName.getText());
        Team newTeam = new Team(NewTeamName.getText(),m);
        AdminEntered.creatTeam(newTeam);
      }
      else {
        Team_Creation_message.setText("Leader Dose not exsist");
      }}

      else Team_not_found.setText("Write new Team name");


    }


  @FXML
  void MarkDone(ActionEvent event) {
    Project project = Project.getProjectByName(ListOfProjects.getValue().toString());
    project.setStatus(true);
    Mark_As_Done_warning.setText("Project Marked Done");
   
  }

  @FXML
  void createNewProject(ActionEvent event) throws FileNotFoundException {
    if (NewProjectName.getText().isEmpty() || AssignedTeamForTheProject.getText().isEmpty()||
    Description.getText().isEmpty()) {
      WarningCreateProject.setText("Fill All Fields");
    }
    else {
      if (WarningCreateProject.getText().toString().equals("There is a Project With This Name!")||
              ExistedTeamLabel.getText().toString().equals("Team should be exsist, write again !")||
      ExistedTeamLabel.getText().toString().equals("Team is Full Try Another one"))
        WarningCreateProject.setText("Data Entered is Wrong!");

    else {
        WarningCreateProject.setText("");
        Team c = Team.getTeamByName(AssignedTeamForTheProject.getText());
        Project_created_successfully.setText("Project Created");
        ExistedTeamLabel.setText("");
        AdminEntered.assignteamForproject(NewProjectName.getText(), c, Description.getText());
      }}
    }


  @FXML
  void CheckForWarning(KeyEvent event) {
    if (Project.getProjectByName(NewProjectName.getText()) != null) {
      WarningCreateProject.setText("There is a Project With This Name!");
    } else {
      WarningCreateProject.setText("good");
    }
  }

  @FXML
  void TeamNotFound(KeyEvent event) throws FileNotFoundException, FileNotFoundException {
    if (Team.getTeamByName(AssignedTeamForTheProject.getText()) == null) {
      ExistedTeamLabel.setText("Team should be exsist, write again !");
    } else {
      if (Team.getTeamByName(AssignedTeamForTheProject.getText()).getProjects().size() == 5) {
        ExistedTeamLabel.setText("Team is Full Try Another one");
      }
      ExistedTeamLabel.setText("Team Avalible");
    }
  }
  @FXML
  void listOfProjectsCompo(ActionEvent event) {
    P2_ProjectInfoTable_OL.clear();
    Project project = Project.getProjectByName(ListOfProjects.getValue().toString());
    DescriptionLabel.setText(project.getDescription());
    ArrayList<Machine> machines = project.getMachines();
    for (int i = 0; i < machines.size(); i++) {
      P2_ProjectInfoTable_OL.add(machines.get(i));
    }
    P2_ProjectInfoTable.setItems(P2_ProjectInfoTable_OL);
    ProjectNameFromTable.setText(project.getName());

    String stat = "";
    if(project.getStatus() == true){
      stat = "Done";
    } else {
      stat = "Not Done";
    }
    ProjectInfo_Status.setText(stat);
    ProjectInfo_TeamWorkOn.setText(project.getTeam().toString());



  }

  @FXML
  void listOfTeamsCompo(ActionEvent event){
    P3_TeamInfoTable_OB.clear();
    P3_Projects_Table_OB.clear();
    
    Team team = Team.getTeamByName(ListOfTeams.getValue().toString());
    ArrayList<Member> members = team.getMembers();
    P3_TeamInfoTable_OB.add(team.getLeader());
    for (int i = 0; i < members.size(); i++) {
      P3_TeamInfoTable_OB.add(members.get(i));
    }
    P3_TeamInfoTable.setItems(P3_TeamInfoTable_OB);
    Leader_Name.setText(team.getLeader().toString());
    ArrayList<Project> projects = team.getProjects();

    for (int i = 0; i < projects.size(); i++) {
      P3_Projects_Table_OB.add(projects.get(i));
    }
    P3_Projects_Table.setItems(P3_Projects_Table_OB);

  }

  @FXML
    void getMachinesTebleData(MouseEvent event) { ////////////////////////////////////////////////
      P4_CurrentReservationTable_OB.clear();
      Integer index = P4_MachineTaableView.getSelectionModel().getSelectedIndex();
      if(index <= -1) return;
      String name = MachineTaableView_Name.getCellData(index);

      Machine machine = Machine.getMachineByName(name);
      MachineChoosenToSeeReservations.setText(machine.getName());
      machineSelected = machine;

      for (int i = 0; i < machineSelected.getReservations().size(); i++) {
        P4_CurrentReservationTable_OB.add(machineSelected.getReservations().get(i));
      }
      P4_CurrentReservationTable.setItems(P4_CurrentReservationTable_OB);


    }

  @FXML
  void showProjectsInfo(ActionEvent event) {
    p1_ProjectTable.setVisible(true);
    p1_TeamsTable.setVisible(false);
  }

  @FXML
  void showTeamsInfo(ActionEvent event) {
    p1_ProjectTable.setVisible(false);
    p1_TeamsTable.setVisible(true);
  }

  @FXML
  void logOut(ActionEvent event) throws IOException {
    App.setRoot("LogInPage");
  }

  @FXML
  void swichPage(ActionEvent event) {
    if (event.getSource() == ShowStatisticsButton) {
      HomePage.setVisible(true);
      ManageProjectsPage.setVisible(false);
      ManageMachinesPage.setVisible(false);
      ManageTeamsPage.setVisible(false);
    } else if (event.getSource() == ManageProjectButton) {
      HomePage.setVisible(false);
      ManageProjectsPage.setVisible(true);
      ManageMachinesPage.setVisible(false);
      ManageTeamsPage.setVisible(false);
    } else if (event.getSource() == ManageTeamsButton) {
      HomePage.setVisible(false);
      ManageProjectsPage.setVisible(false);
      ManageMachinesPage.setVisible(false);
      ManageTeamsPage.setVisible(true);
    } else if (event.getSource() == ManageMachineButton) {
      HomePage.setVisible(false);
      ManageProjectsPage.setVisible(false);
      ManageMachinesPage.setVisible(true);
      ManageTeamsPage.setVisible(false);
    }
  }

  // Page 4 Methodes:

  @FXML
  void getDate(ActionEvent event){ // to authenticate reservation
  }

  @FXML
  void AssignMachineToProject(ActionEvent event) throws FileNotFoundException {
    Check_reservation_label.setText("");
    Machine_not_Found.setText("");
    Project_not_Found.setText("");
    boolean flag = true;
    if (StartHour.getText().isEmpty()||  MachineNameReservation.getText().isEmpty()  ||EndHour.getText().isEmpty()||
    ProjectToAssighnMachine.getText().isEmpty()  ||DateProvider.getValue() == null) {
      Check_reservation_label.setText("You Must Fill All field!");
      flag = false;
    } else {
      Check_reservation_label.setText("");
      if (Machine.getMachineByName(MachineNameReservation.getText().toString()) == null) {
        Machine_not_Found.setText("Machine Not Found");
        flag = false;
      } else
        Machine_not_Found.setText("");
      if (Project.getProjectByName(ProjectToAssighnMachine.getText().toString()) == null) {
        Project_not_Found.setText("Project Not Found");
        flag = false;
      } else
        Project_not_Found.setText("");
    }

    if (!flag) {
      return;
    }
    int startHour = Integer.valueOf(StartHour.getText());
    int endHour = Integer.valueOf(EndHour.getText());
    LocalDate date = DateProvider.getValue();
    if (startHour > endHour) {
      Check_reservation_label.setText("Start Hour cannot be after End Hour!");
      return;
    }
    if (date.isBefore(LocalDate.now())) {
      Check_reservation_label.setText("date cannot be in the past!");
      return;
    }
    // Reservation.isValid(date,startHour,endHour);
    if (machineSelected.CheckForConflict(startHour, endHour, date)) {
      Reservation reservation = new Reservation(startHour, endHour, date,
              Project.getProjectByName(ProjectToAssighnMachine.getText().toString()), machineSelected);
      Project.getProjectByName(ProjectToAssighnMachine.getText().toString()).addReservation(reservation);
      machineSelected.addReservation(reservation);
      Reservation.allReservation.add(reservation);
      Check_reservation_label.setText("Successfully Reserved!");
      P4_CurrentReservationTable_OB.clear();
      for (int i = 0; i < machineSelected.getReservations().size(); i++) {
        P4_CurrentReservationTable_OB.add(machineSelected.getReservations().get(i));
      }
      P4_CurrentReservationTable.setItems(P4_CurrentReservationTable_OB);
      AdminEntered.add();
    } else
      Check_reservation_label.setText("Time Conflict!");
  }


  // Helper Methodes Field:
//  private static String username, password;
//  CategoryAxis xAxis = new CategoryAxis();
//  NumberAxis yAxis = new NumberAxis();
//  Admin AdminEntered = new Admin(username, password);
//  Machine machineSelected;

  public static void transfer_username_password(String u, String p) {
    username = u;
    password = p;

  }

  @FXML
  void CreateNewMachine(ActionEvent event) throws FileNotFoundException {
    if(Machine.getMachineByName(NewMachineName.getText())==null){
      Chech_machine_creation.setText("Machine added !");
      String machineName =NewMachineName.getText();
      Machine m = new Machine(machineName,ResearchIntereset.getText());
      AdminEntered.creatMachine(m);


      }
    else { Chech_machine_creation.setText("Machine already exsist");

    }}







  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // Set Page Code:
    HomePage.setVisible(true);
    ManageProjectsPage.setVisible(false);
    ManageMachinesPage.setVisible(false);
    ManageTeamsPage.setVisible(false);

    // ===================Read data from files====================================//
    AdminEntered.SetAll();
    //================== Top 3 Members ==========================================//
    String[] topMemberes = AdminEntered.TopThreeMembers();
    String s = "";
    for (int i = 0; i < topMemberes.length - 1; i++) {
      s = s + topMemberes[i] + " - ";
    }
    s = s + topMemberes[topMemberes.length - 1];
    ActiveMembers.setText(s);
    // ========================Most utilized machine=============================//
    String mostUtilizedMachine = AdminEntered.mostUtilizedMachine();
    MostUtilizedMavhine.setText(mostUtilizedMachine);

    // ======================== project uses most machines========================//
    String projectUsesMostMachine = AdminEntered.getProjectWithHighestMachineCount();
    ProjectUsesMostMachine.setText(projectUsesMostMachine);

    // ======================== barChart ==========================================//
    XYChart.Series<String, Number> NumOfTeams = new XYChart.Series<>();
    NumOfTeams.getData().add(new XYChart.Data<>("Teams Number", Team.allTeams.size()));
    NumOfTeams.setName("Teams Number");

    XYChart.Series<String, Number> NumOfProjects = new XYChart.Series<>();
    NumOfProjects.getData().add(new XYChart.Data<>("Project Number", Project.allProjects.size()));
    NumOfProjects.setName("Project Number");

    XYChart.Series<String, Number> NumOfMachine = new XYChart.Series<>();
    NumOfMachine.getData().add(new XYChart.Data<>("Machine Number", Machine.allMachines.size()));
    NumOfMachine.setName("Machine Number");

    XYChart.Series<String, Number> NumOfDoneProject = new XYChart.Series<>();
    NumOfDoneProject.getData()
            .add(new XYChart.Data<>("Done Projects Number", AdminEntered.DoneProjects()));
    NumOfDoneProject.setName("Done Projects Number");

    Numbers_Chart.getData().add(NumOfTeams);
    Numbers_Chart.getData().add(NumOfProjects);
    Numbers_Chart.getData().add(NumOfMachine);
    Numbers_Chart.getData().add(NumOfDoneProject);

    //

    // ================================P1 Table Views (Projects)=====================================//
    ProjectTable_ProjectName.setCellValueFactory(new PropertyValueFactory<Project, String>("name"));
    ProjectTable_NumOfResMachines
        .setCellValueFactory(new PropertyValueFactory<Project, Integer>("numOfResrvedMachine"));

        
    p1_ProjectTable.getItems().addAll((List)Project.allProjects);
    p1_ProjectTable.getColumns().addAll(ProjectTable_ProjectName, ProjectTable_NumOfResMachines);
    // ===========================P1 Table Views (Teams)=================================================//
    TeamsTable_TeamName.setCellValueFactory(new PropertyValueFactory<Team, String>("name"));
    TeamsTable_LeaderName.setCellValueFactory(new PropertyValueFactory<Team, Member>("leader"));
    TeamsTable_NumOfMembers.setCellValueFactory(new PropertyValueFactory<Team, Integer>("memberCount"));    
    p1_TeamsTable.getItems().addAll((List)Team.allTeams);
    p1_TeamsTable.getColumns().addAll(TeamsTable_TeamName, TeamsTable_LeaderName,TeamsTable_NumOfMembers);
    // =====================================Drop box page 2 Projects =======================================================//
    String[] listProject = new String[25];
    for (int j = 0; j < Project.allProjects.size(); j++) {
      listProject[j] = Project.allProjects.get(j).getName();
    }
    ObservableList<String> list = FXCollections.observableArrayList(listProject);
    ListOfProjects.setItems(list);
    //==================================== Drop box page 3 Team =============================================//
    String[] listTeams = new String[9];
    for (int i = 0; i < Team.allTeams.size(); i++) {
      listTeams[i] = Team.allTeams.get(i).getName();
    }
    ObservableList<String> list2 = FXCollections.observableArrayList(listTeams);
    ListOfTeams.setItems(list2);    
    //==================================== user Table view in User page 3 ==================================//
    UsersList_User.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
    UsersList_ID.setCellValueFactory(new PropertyValueFactory<Member,String>("id"));
    P3_ListOfUsers.getColumns().addAll(UsersList_User,UsersList_ID);
    P3_ListOfUsers.getItems().addAll((List)Member.allMembers);
    //========================================================================================================//
    ProjectInfo_Machines.setCellValueFactory(new PropertyValueFactory<Machine,String>("name"));
    //=======================================================================================================//
    TeamInfo_MemberName.setCellValueFactory(new PropertyValueFactory<Member,String>("name"));
    TeamInfo_ID.setCellValueFactory(new PropertyValueFactory<Member,String>("id"));
    TeamInfo_Projects.setCellValueFactory(new PropertyValueFactory<Project,String>("name"));
    //========================================================================================================//
    MachineTaableView_Name.setCellValueFactory(new PropertyValueFactory<Machine,String>("name"));
    MachineTaableView_REInterest.setCellValueFactory(new PropertyValueFactory<Machine,String>("researchInterest"));
    P4_MachineTaableView_OB.addAll(Machine.allMachines);
    P4_MachineTaableView.setItems(P4_MachineTaableView_OB);
    //===========================================================================================================//
    CurrentReservationTable_Start.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("startTime"));
    CurrentReservationTable_Finish.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("endTime"));
    CurrentReservationTable_Date.setCellValueFactory(new PropertyValueFactory<Reservation,LocalDate>("date"));

  }
}