package com.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MemberPage implements Initializable {
    private static Member memberEntered = Member.getMemberByID(logInController.id);
    //////////////////////////////////////[[[[[[[P1--User Page]]]]]]]//////////////////////////////////////
    @FXML
    private ImageView
            machineImage, projectImage, teamImage,
            userImage;
    @FXML
    private Label mainLabel;
    @FXML
    private AnchorPane userPane, teamPane, projectPane, machinePane;
    @FXML
    private AnchorPane team1Pane, team2Pane, team3Pane, team4Pane, team5Pane, team6Pane, team7Pane, team8Pane, team9Pane;
    @FXML
    private Text paneTeamName1, paneTeamName2, paneTeamName3, paneTeamName4, paneTeamName5, paneTeamName6,
            paneLeaderName1, paneLeaderName2, paneLeaderName3, paneLeaderName4, paneLeaderName5, paneLeaderName6;
    @FXML
    Label userNameLabel;
    //////////////////////////////////////[[[[[[[P2--Team Page]]]]]]]/////////////////////////////////////
    @FXML
    Label projectNameLabel1, projectNameLabel2, projectNameLabel3, projectNameLabel4, projectNameLabel5;
    @FXML
    Text leaderText, MemberNameText1, MemberNameText2, MemberNameText3, MemberNameText4, MemberNameText5, MemberNameText6, MemberNameText7, MemberNameText8, MemberNameText9,infoText;
    ArrayList<Text> MemberNameTexts = new ArrayList<>();
    ArrayList<Label> projectNameLabel = new ArrayList<>();
    @FXML
    AnchorPane ProjectPane1, ProjectPane2, ProjectPane3, ProjectPane4, ProjectPane5;
    ArrayList<AnchorPane> ProjectPane = new ArrayList<>();
    @FXML
    AnchorPane leaderPane, memberPane1, memberPane2, memberPane3, memberPane4, memberPane5, memberPane6, memberPane7, memberPane8, memberPane9;
    ArrayList<AnchorPane> membersPanesAndLeader = new ArrayList<>();
    //////////////////////////////////////[[[[[[[P3--Project Page]]]]]]]//////////////////////////////////////
    @FXML
    Text projcetDescriptionText;
    @FXML
    private Label projectNameLabelTitle;
    Project projectSelected;
    @FXML
    ImageView incompletedImage, completedImage;
    @FXML
    Label statusLabel;
    @FXML
    AnchorPane statusPaneB, statusPaneS;
    @FXML
    Label mahcinesCountLabel;
    @FXML
    TableView<Machine> projectMachinesTableView;
    @FXML
    TableColumn<Machine, String> MachinesColumn;
    ObservableList<Machine> tableMachinesList = FXCollections.observableArrayList();
    boolean flag = true;

    //////////////////////////////////////[[[[[[[P4--Machine Page]]]]]]]//////////////////////////////////////
    @FXML
    Label reservationForLabel;
    @FXML
    TextField searchingTextFieldP4, startHourTextField, EndHourTextField;
    @FXML
    DatePicker datPicker;
    @FXML
    TableView<Machine> allMachinesTableView;
    @FXML
    TableColumn<Machine, String> RIColumn,nameColumn;
    ObservableList<Machine> tableMachinesList2 = FXCollections.observableArrayList();
    //-------------------
    @FXML
    TableView<Reservation> machineReservationsTable;
    @FXML
    TableColumn<Reservation, Integer> startTimeColumn,endTimeColumn;
    @FXML
    TableColumn<Reservation, LocalDate> dateColumn;
    ObservableList<Reservation> tableMachinesList3 = FXCollections.observableArrayList();
    @FXML
    Text worningText1,worningText2,worningText3,worningText4,worningText5;
    Machine machineSelected;
    @FXML
    Label projectNameLabel_2;

    ///----------------------------------------------------------------------------------------------------//
    private ArrayList<Text> teamsNames = new ArrayList<>();
    private ArrayList<Text> leadersNames = new ArrayList<>();
    private ArrayList<AnchorPane> panes = new ArrayList<>();

    @FXML
    private TableView<Project> table;
    @FXML
    private TableColumn<Project, Integer> C_ID;

    @FXML
    private TableColumn<Project, String> C_Name;
    @FXML
    private Text IDMustBeNum, memberText, emailText, IDtext, RIText;
    @FXML
    private TextField searchField, searchField1;
    private Team teamSelected = null;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    //ObservableList<Project> projects = FXCollections.observableArrayList(
    //);


    ObservableList<Project> listCopy = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userPane.setVisible(true);
        teamPane.setVisible(false);
        projectPane.setVisible(false);
        machinePane.setVisible(false);
        userNameLabel.setText(memberEntered.getName());
        initializingObjects();
        initializingList();
        MachinesColumn.setCellValueFactory(new PropertyValueFactory<Machine, String>("name"));

        nameColumn.setCellValueFactory(new PropertyValueFactory<Machine, String>("name"));
        RIColumn.setCellValueFactory(new PropertyValueFactory<Machine, String>("researchInterest"));

        startTimeColumn.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("endTime"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Reservation, LocalDate>("date"));

        C_Name.setCellValueFactory(new PropertyValueFactory<Project, String>("name"));
        C_ID.setCellValueFactory(new PropertyValueFactory<Project, Integer>("id"));

        table.setItems(listCopy);
        projectNameLabel.addAll(Arrays.asList(projectNameLabel1, projectNameLabel2, projectNameLabel3, projectNameLabel4, projectNameLabel5));
        ProjectPane.addAll(Arrays.asList(ProjectPane1, ProjectPane2, ProjectPane3, ProjectPane4, ProjectPane5));
        for (int i = 0; i < ProjectPane.size(); i++) {
            ProjectPane.get(i).setVisible(false);
            ProjectPane.get(i).setVisible(false);
        }

        activateTeamsPanes();


    }

    public void initializingObjects() {

    }

    public void initializingList() {

        membersPanesAndLeader.addAll(Arrays.asList(memberPane1, memberPane2, memberPane3, memberPane4, memberPane5, memberPane6, memberPane7, memberPane8, memberPane9));
        MemberNameTexts.addAll(Arrays.asList(MemberNameText1, MemberNameText2, MemberNameText3, MemberNameText4, MemberNameText5, MemberNameText6, MemberNameText7, MemberNameText8, MemberNameText9));
        teamsNames.addAll(Arrays.asList(paneTeamName1, paneTeamName2, paneTeamName3, paneTeamName4, paneTeamName5, paneTeamName6));
        leadersNames.addAll(Arrays.asList(paneLeaderName1, paneLeaderName2, paneLeaderName3, paneLeaderName4, paneLeaderName5, paneLeaderName6));
        panes.addAll(Arrays.asList(team1Pane, team2Pane, team3Pane, team4Pane, team5Pane, team6Pane, team7Pane, team8Pane, team9Pane));
        for (int i = 0; i < panes.size(); i++) {
            panes.get(i).setVisible(false);
        }
    }

    public void dropSearch() {
        String input = searchingTextFieldP4.getText().toString();
        tableMachinesList2.clear();
        for (int i = 0; i < Machine.allMachines.size(); i++) {
            if (Machine.allMachines.get(i).getName().toLowerCase().startsWith(input.toLowerCase())) {
                tableMachinesList2.add(Machine.allMachines.get(i));
            }
        }
        allMachinesTableView.setItems(tableMachinesList2);

    }

    public void dropSearch2() {
//        String input = searchField1.getText();
//        listCopy.clear();
//        for (int i = 0; i < projects.size(); i++) {
//            if (projects.get(i).getId().toString().startsWith(input)) {
//                listCopy.add(projects.get(i));
//            }
//            if (input.matches("\\d+") || input.isEmpty()) IDMustBeNum.setText("");
//            else IDMustBeNum.setText("Numbers Only!");
//        }
    }

    public void activateTeamsPanes() {
        for (int i = 0; i < memberEntered.getTeams().size(); i++) {
            System.out.println(memberEntered.getTeams().get(i).getName());
        }

        for (int i = 0; i < memberEntered.getTeams().size(); i++) {
            teamsNames.get(i).setText(memberEntered.getTeams().get(i).getName());
            leadersNames.get(i).setText(memberEntered.getTeams().get(i).getLeader().getName());
            panes.get(i).setVisible(true);
        }
    }

    //////////////////////////////////////[[[[[[[ P1--User Page ]]]]]]]/////////////////////////////////////////
    @FXML
    void changeToP1(MouseEvent event) { // Move from any page to the user page
        userPane.setVisible(true);
        teamPane.setVisible(false);
        projectPane.setVisible(false);
        machinePane.setVisible(false);
        mainLabel.setText("Home");

    }

    @FXML
    void activateTeamIcon(MouseEvent event) { // Team icon
        teamImage.setFitWidth(teamImage.getFitWidth() + 5);
        teamImage.setFitHeight(teamImage.getFitHeight() + 5);

    }

    @FXML
    void deactivateTeamIcon(MouseEvent event) {// Team icon
        teamImage.setFitWidth(teamImage.getFitWidth() - 5);
        teamImage.setFitHeight(teamImage.getFitHeight() - 5);
    }


    @FXML
    void activateProjectsIcon(MouseEvent event) {// Projects icon
        projectImage.setFitWidth(projectImage.getFitWidth() + 10);
        projectImage.setFitHeight(projectImage.getFitHeight() + 10);
    }

    @FXML
    void deactivateProjectsIcon(MouseEvent event) {// Projects icon
        projectImage.setFitWidth(projectImage.getFitWidth() - 10);
        projectImage.setFitHeight(projectImage.getFitHeight() - 10);
    }

    // Machine icon
    @FXML
    void activateMachineIcon(MouseEvent event) { // Machine icon
        machineImage.setFitWidth(machineImage.getFitWidth() + 10);
        machineImage.setFitHeight(machineImage.getFitHeight() + 10);
    }

    @FXML
    void deactivateMachineIcon(MouseEvent event) { // Machine icon
        machineImage.setFitWidth(machineImage.getFitWidth() - 10);
        machineImage.setFitHeight(machineImage.getFitHeight() - 10);
    }

    //////////////////////////////////////[[[[[[[ P2--Team Pag ]]]]]]]//////////////////////////////////////////
    @FXML
    void changeToP2(MouseEvent event) {
        AnchorPane clickedPane = (AnchorPane) event.getSource();
        for (int i = 0; i < memberEntered.getTeams().size(); i++) {
            if (clickedPane.equals(panes.get(i))) {
                teamSelected = memberEntered.getTeams().get(i);
            }

        }
        if (!teamSelected.equals(null)) {
            mainLabel.setText(teamSelected.getName());
            teamPane.setVisible(true);
            userPane.setVisible(false);
            projectPane.setVisible(false);
            machinePane.setVisible(false);
            for (int i = 0; i < ProjectPane.size(); i++) {
                ProjectPane.get(i).setVisible(false);
            }
            for (int i = 0; i < membersPanesAndLeader.size(); i++) {
                membersPanesAndLeader.get(i).setVisible(false);
            }
            for (int i = 0; i < teamSelected.getProjects().size(); i++) {
                projectNameLabel.get(i).setVisible(true);
                projectNameLabel.get(i).setText(teamSelected.getProjects().get(i).getName());
                ProjectPane.get(i).setVisible(true);
            }
            //leaderText.setText(teamSelected.getLeader().getName());
            leaderText.setText(teamSelected.getLeader().getName());
            for (int i = 0; i < teamSelected.getMembers().size(); i++) {
                MemberNameTexts.get(i).setText(teamSelected.getMembers().get(i).getName());
                membersPanesAndLeader.get(i).setVisible(true);
            }
        }
    }

    @FXML
    void showInfo(MouseEvent event) {
        AnchorPane clickedPane = (AnchorPane) event.getSource();
        if (clickedPane.equals(leaderPane)) {
            infoText.setText(teamSelected.getLeader().getInfo());
        } else {
            for (int i = 0; i < teamSelected.getMembers().size(); i++) {
                if (clickedPane.equals(membersPanesAndLeader.get(i))) {
                    infoText.setText(teamSelected.getMembers().get(i).getInfo());
                }
            }
        }
    }



    //////////////////////////////////////[[[[[[[ P3--Project Page ]]]]]]]//////////////////////////////////////
    @FXML
    void changeToP3(MouseEvent event) {

        AnchorPane clickedPane = (AnchorPane) event.getSource();
        for (int i = 0; i < teamSelected.getProjects().size(); i++) {
            if (clickedPane.equals(ProjectPane.get(i))) {
                projectSelected = teamSelected.getProjects().get(i);
            }
        }
        if (projectSelected == null)return;
        projcetDescriptionText.setText(projectSelected.getDescription());
        mahcinesCountLabel.setText(String.valueOf(projectSelected.getMachines().size()));
        projectNameLabelTitle.setText(projectSelected.getName());
        projectNameLabel_2.setText(projectSelected.getName());
        tableMachinesList.clear();
        for (int i = 0; i < projectSelected.getMachines().size(); i++) {
            tableMachinesList.add(projectSelected.getMachines().get(i));
        }
        projectMachinesTableView.setItems(tableMachinesList);

        if(projectSelected.getStatus()){
            statusLabel.setText("Completed");
            completedImage.setVisible(true);
            //==========================//
            statusPaneB.setPrefWidth(418);
            //=========================//
            statusPaneS.setPrefWidth(191);
            //=========================//
            incompletedImage.setVisible(false);
        }else{
            incompletedImage.setVisible(true);
            statusLabel.setText("Incompleted");
            //==========================//
            statusPaneB.setPrefWidth(432);
            //=========================//
            statusPaneS.setPrefWidth(207);
            //=========================//
            completedImage.setVisible(false);
        }

        projectPane.setVisible(true);
        teamPane.setVisible(false);
        userPane.setVisible(false);
        machinePane.setVisible(false);

    }

    @FXML
    void changStatus(MouseEvent event) throws FileNotFoundException {
        if(!teamSelected.getLeader().equals(memberEntered)){
            return;
        }
        if (statusLabel.getText().equals("Completed")) {
            incompletedImage.setVisible(true);
            statusLabel.setText("Incompleted");
            projectSelected.setStatus(false);
            Admin.update();
            //==========================//
            statusPaneB.setPrefWidth(432);
            //=========================//
            statusPaneS.setPrefWidth(207);
            //=========================//
            completedImage.setVisible(false);


        } else {
            statusLabel.setText("Completed");
            completedImage.setVisible(true);
            projectSelected.setStatus(true);
            Admin.update();
            //==========================//
            statusPaneB.setPrefWidth(418);
            //=========================//
            statusPaneS.setPrefWidth(191);
            //=========================//
            incompletedImage.setVisible(false);


        }

    }

    //////////////////////////////////////[[[[[[[ P4--Machine Page ]]]]]]]//////////////////////////////////////
    @FXML
    void changeToP4(MouseEvent event) {
        if (projectSelected == null)return;
        tableMachinesList2.clear();
        for (int i = 0; i < Machine.allMachines.size(); i++) {
            tableMachinesList2.add(Machine.allMachines.get(i));
        }
        allMachinesTableView.setItems(tableMachinesList2);

        teamPane.setVisible(false);
        userPane.setVisible(false);
        projectPane.setVisible(false);
        machinePane.setVisible(true);

    }
    @FXML
    void choseMachine(MouseEvent event) {

        Integer index = allMachinesTableView.getSelectionModel().getSelectedIndex();
        if (index <= -1) return;
        String name = nameColumn.getCellData(index);
        Machine machine = Machine.getMachineByName(name);
        reservationForLabel.setText(machine.getName());
        machineSelected = machine;
        tableMachinesList3.clear();
        for (int i = 0; i <machine.getReservations().size(); i++) {
            tableMachinesList3.add(machine.getReservations().get(i));
            machineReservationsTable.setItems(tableMachinesList3);
        }
    }
    @FXML
    void reserveNewMachine(MouseEvent event) throws FileNotFoundException {
        worningText1.setText("");worningText2.setText("");worningText3.setText("");worningText4.setText("");worningText5.setText("");worningText5.setFill(Color.RED);
        flag = true;
        if(startHourTextField.getText().isEmpty()){
            worningText1.setText("You Must Fill this field!");
            flag = false;
        }
        if(EndHourTextField.getText().isEmpty()){
            worningText2.setText("You Must Fill this field!");
            flag = false;
        }
        if(datPicker.getValue() == null){
            worningText3.setText("You Must Fill this field!");
            flag = false;
        }
        if(machineSelected.equals(null)){
            worningText4.setText("Select A Machine From The Table!");
            flag = false;
        }
        if (!flag){
            return;
        }
        int startHour = Integer.valueOf(startHourTextField.getText());
        int endHour = Integer.valueOf(EndHourTextField.getText());
        LocalDate date = datPicker.getValue();
        if (startHour>endHour){
            worningText1.setText("Start Hour cannot be after End Hour!");
            return;
        }
        if (!Reservation.isValid(date,startHour,endHour)){
            worningText3.setText("date and time cannot be in the past!");
            return;
        }

        if(machineSelected.CheckForConflict(startHour,endHour,date)){
            Reservation reservation = new Reservation(startHour,endHour,date,projectSelected,machineSelected);
            projectSelected.addReservation(reservation);
            machineSelected.addReservation(reservation);
            Reservation.allReservation.add(reservation);
            worningText5.setText("Successfully Reserved!");
            worningText5.setFill(Color.GREEN);
            tableMachinesList3.clear();
            for (int i = 0; i <machineSelected.getReservations().size(); i++) {
                tableMachinesList3.add(machineSelected.getReservations().get(i));
                machineReservationsTable.setItems(tableMachinesList3);
            }
            Admin.update();
        }else worningText5.setText("Time Conflict!");
    }
    //--------------------------------------------------------------------------------------------------------//
    /////////////////////////////////
    //// Activating the icons   ////
    ////////////////////////////////


    @FXML
    void activateTeamPane1(MouseEvent event) {
        AnchorPane m = (AnchorPane) event.getSource();
        m.setPrefWidth(m.getPrefWidth() + 6);
        m.setPrefHeight(m.getPrefHeight() + 6);
        m.setLayoutX(m.getLayoutX() - 3);
        m.setLayoutY(m.getLayoutY() - 3);
        if (ProjectPane.contains(m))
            m.setStyle("-fx-background-color: FFC5C5; -fx-background-radius: 20; -fx-border-color: FFC5C5; -fx-border-radius: 20; -fx-border-width: 9;");
        else if (panes.contains(m)) {
            m.setStyle("-fx-background-color: #BBAB8C; -fx-background-radius: 20;");
        } else if (m.equals(leaderPane)) {
            m.setStyle("-fx-background-color: #FF6000; -fx-background-radius: 20;");
        } else {
            m.setStyle("-fx-background-color: # BBAB8C; -fx-background-radius: 20;");
        }

    }

    @FXML
    void deactivateTeamPane1(MouseEvent event) {
        AnchorPane m = (AnchorPane) event.getSource();
        m.setPrefWidth(m.getPrefWidth() - 6);
        m.setPrefHeight(m.getPrefHeight() - 6);
        m.setLayoutX(m.getLayoutX() + 3);
        m.setLayoutY(m.getLayoutY() + 3);
        if (ProjectPane.contains(m)) {
            m.setStyle("-fx-background-color: FFEBD8; -fx-background-radius: 20; -fx-border-color: FFC5C5; -fx-border-radius: 20; -fx-border-width: 9;");
        } else if (panes.contains(m)) {
            m.setStyle("-fx-background-color: #DED0B6; -fx-background-radius: 20;");
        } else if (leaderPane.equals(m)) {
            m.setStyle("-fx-background-color: #FFA732; -fx-background-radius: 20;");
        } else {
            m.setStyle("-fx-background-color: #DED0B6; -fx-background-radius: 20;");
        }


    }

    @FXML
    void UserInfoDisplay(MouseEvent event) {
        userImage.setFitHeight(userImage.getFitHeight() + 5);
        userImage.setFitWidth(userImage.getFitWidth() + 5);
        memberText.setText("Member");
        emailText.setText(logInController.getUserEmail());
        IDtext.setText(logInController.getUserEmail().substring(1, 9));
        RIText.setText("Software AI");
    }

    @FXML
    void UserInformative(MouseEvent event) {
        userImage.setFitHeight(userImage.getFitHeight() - 5);
        userImage.setFitWidth(userImage.getFitWidth() - 5);
        memberText.setText("");
        emailText.setText("");
        IDtext.setText("");
        RIText.setText("");

    }


    ////////////////////////////
    /// panes chang methods ///
    ///////////////////////////

}
