import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.swing.Action;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

public class TeacherController implements Initializable{

    @FXML
    private ChoiceBox<String> ChoiceCovid;

    @FXML
    private ChoiceBox<String> ChoiceVaccine;

    @FXML
    private TableColumn<IdData,String> ColFName;

    @FXML
    private TableColumn<IdData,String> ColLName;

    @FXML
    private TableColumn<IdData,String> ColVacc;

    @FXML
    private TableColumn<IdData,String> Colid;

    @FXML
    private TableColumn<IdData,String> Colinf;

    @FXML
    private TableColumn<IdData,String> ColndD;

    @FXML
    private TableColumn<IdData,String> ColrdD;

    @FXML
    private TableColumn<IdData,String> ColstD;

    @FXML
    private TableColumn<IdData,String> ColthD;

    @FXML
    private Button Delete_button;

    @FXML
    private Button Edit_button;

    @FXML
    private ChoiceBox<String> FDose;

    @FXML
    private TextField FName;

    @FXML
    private ChoiceBox<String> FthDose;

    @FXML
    private TextField IdField;

    @FXML
    private TextField LName;

    @FXML
    private ChoiceBox<String> SDose;

    @FXML
    private ChoiceBox<String> TDose;

    @FXML
    private TableView<IdData> TvBook;

    public  String Covid = null;
    public  String Vaccine = null;
    public  String Dose1 = null;
    public  String Dose2 = null;
    public  String Dose3 = null;
    public  String Dose4 = null;

    private String[] QandA = {"Yes","No"};
    private String[] Vaccin = {"SinoFarm","Pfizer","AstraZeneca","Sinovec","Moderna","Other","None"};


    @FXML//ลูกรักเค้า
    void HandleButton(ActionEvent event) {

    }

    @FXML
    void Add_teacher(MouseEvent event) throws IOException {
        
        Parent root = Loadresearch.loadFXML("Add_Teacher");
        Dialog dialog = new Dialog();
        dialog.getDialogPane().setContent(root);
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.show();
    }

    Collection<IdData> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        FDose.getItems().addAll(Vaccin);
        FDose.setOnAction(this::getDose1);
        SDose.getItems().addAll(Vaccin);
        SDose.setOnAction(this::getDose2);
        TDose.getItems().addAll(Vaccin);
        TDose.setOnAction(this::getDose3);
        FthDose.getItems().addAll(Vaccin);
        FthDose.setOnAction(this::getDose4);

        ChoiceCovid.getItems().addAll(QandA);
        ChoiceCovid.setOnAction(this::getQAAC);

        ChoiceVaccine.getItems().addAll(QandA);
        ChoiceVaccine.setOnAction(this::getQAAV);
        //
        
        try {
            list = Files.readAllLines(new File("E:/KMITL/1.2/oop/Project1/Main/record.txt").toPath()).stream().map
            (line -> {
                String[] details = line.split(",");
                IdData cd = new IdData();
                cd.setId(details[0]);
                cd.setFName(details[1]);
                //System.out.println(details[1]);
                cd.setLastName(details[2]);
                //System.out.println(details[2]);
                cd.setInfected(details[8]);
                //System.out.println(details[8]);
                cd.setVaccine(details[9]);
                cd.setstDose(details[10]);
                cd.setndDose(details[11]);
                cd.setrdDose(details[12]);
                cd.setthDose(details[13]);
                return cd;
            })
            .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObservableList<IdData> details = FXCollections.observableArrayList(list);
       
        Colid.setCellValueFactory(data -> data.getValue().IdProperty());
        //System.out.println(Colid);
        ColFName.setCellValueFactory(data -> data.getValue().FNameProperty());
        ColLName.setCellValueFactory(data -> data.getValue().LastNameProperty());
        Colinf.setCellValueFactory(data -> data.getValue().InfectedProperty());
        ColVacc.setCellValueFactory(data -> data.getValue().InfectedProperty4());
        ColstD.setCellValueFactory(data -> data.getValue().InfectedProperty5());
        ColndD.setCellValueFactory(data -> data.getValue().InfectedProperty6());
        ColrdD.setCellValueFactory(data -> data.getValue().InfectedProperty7());
        ColthD.setCellValueFactory(data -> data.getValue().InfectedProperty8());

        TvBook.setItems(details);
        }

    private class IdData {
        StringProperty Id = new SimpleStringProperty();
        StringProperty FName = new SimpleStringProperty();
        StringProperty LastName = new SimpleStringProperty();
        StringProperty Infected = new SimpleStringProperty();
        StringProperty Vaccine = new SimpleStringProperty();
        StringProperty stDose = new SimpleStringProperty();
        StringProperty ndDose = new SimpleStringProperty();
        StringProperty rdDose = new SimpleStringProperty();
        StringProperty thDose = new SimpleStringProperty();
        
        public final StringProperty IdProperty() {
            return this.Id;
        }

        public final String getId() {
            return this.IdProperty().get();
        }

        public final void setId(final String details) {
            this.IdProperty().set(details);
        }

        public final StringProperty FNameProperty() {
            return this.FName;
        }

        public final java.lang.String getFName() {
            return this.FNameProperty().get();
        }

        public final void setFName(final java.lang.String FName) {
            this.FNameProperty().set(FName);
        }

        public final StringProperty LastNameProperty() {
            return this.LastName;
        }

        public final java.lang.String getLastName() {
            return this.LastNameProperty().get();
        }

        public final void setLastName(final java.lang.String LastName) {
            this.LastNameProperty().set(LastName);
        }

        public final StringProperty InfectedProperty() {
            return this.Infected;
        }

        public final java.lang.String getInfected() {
            return this.InfectedProperty().get();
        }

        public final void setInfected(final java.lang.String Infected) {
            this.InfectedProperty().set(Infected);
        }

        public final StringProperty InfectedProperty4() {
            return this.Vaccine;
        }

        public final java.lang.String getVaccine() {
            return this.InfectedProperty4().get();
        }

        public final void setVaccine(final java.lang.String Vaccine) {
            this.InfectedProperty4().set(Vaccine);
        }

        public final StringProperty InfectedProperty5() {
            return this.stDose;
        }

        public final java.lang.String getstDose() {
            return this.InfectedProperty5().get();
        }

        public final void setstDose(final java.lang.String stDose) {
            this.InfectedProperty5().set(stDose);
        }

        public final StringProperty InfectedProperty6() {
            return this.ndDose;
        }

        public final java.lang.String getndDose() {
            return this.InfectedProperty6().get();
        }

        public final void setndDose(final java.lang.String ndDose) {
            this.InfectedProperty6().set(ndDose);
        }

        public final StringProperty InfectedProperty7() {
            return this.rdDose;
        }

        public final java.lang.String getrdDose() {
            return this.InfectedProperty7().get();
        }

        public final void setrdDose(final java.lang.String rdDose) {
            this.InfectedProperty7().set(rdDose);
        }

        public final StringProperty InfectedProperty8() {
            return this.thDose;
        }

        public final java.lang.String getthDose() {
            return this.InfectedProperty8().get();
        }

        public final void setthDose(final java.lang.String thDose) {
            this.InfectedProperty8().set(thDose);
        }
    }

    ////////////////////////////////////////////////////////////////////--------------------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>
    @FXML
    void rowClicked(MouseEvent event) {
        IdData clickRow = TvBook.getSelectionModel().getSelectedItem();
        FName.setText(String.valueOf(clickRow.getFName()));
        LName.setText(String.valueOf(clickRow.getLastName()));
        FDose.setValue(String.valueOf(clickRow.getstDose()));
        SDose.setValue(String.valueOf(clickRow.getndDose()));
        TDose.setValue(String.valueOf(clickRow.getrdDose()));
        FthDose.setValue(String.valueOf(clickRow.getthDose()));
        ChoiceCovid.setValue(String.valueOf(clickRow.getInfected()));
        ChoiceVaccine.setValue(String.valueOf(clickRow.getVaccine()));
    }

    public void getDose1(ActionEvent event){
        Dose1 = FDose.getValue();
        //System.out.println(Dose1);
    }

    public void getDose2(ActionEvent event){
        Dose2 = SDose.getValue();
        //System.out.println(Dose2);
    }

    public void getDose3(ActionEvent event){
        Dose3 = TDose.getValue();
        //System.out.println(Dose3);
    }

    public void getDose4(ActionEvent event){
        Dose4 = FthDose.getValue();
        //System.out.println(Dose4);
    }

    public void getQAAC(ActionEvent event){
        Covid = ChoiceCovid.getValue();
    }

    public void getQAAV(ActionEvent event){
        Vaccine = ChoiceVaccine.getValue();
    }

    @FXML
    void EditRec(ActionEvent event) throws IOException{
        
        if(event.getSource() == Edit_button){
        System.out.println("1");
        Edit("record.txt",Colid.getText(),FName.getText(),LName.getText(), ChoiceCovid.getValue(), ChoiceVaccine.getValue(), FDose.getValue(), SDose.getValue(), TDose.getValue(), FthDose.getValue());
        }
    }

    
    public static void Edit(String filepath, String editAim, String FName  , String LName,String Covid,String Vaccine,String stDose,String ndDose,String rdDose,String thDose) {
        String tempfile = "temp.txt";
        File oldFile = new File(filepath);
        File newFile = new File(tempfile);
        String Name = "";
        String LastName = "";
        String Inf = "";
        String Vacc = "";
        String Dose1 = "";
        String Dose2 = "";
        String Dose3 = "";
        String Dose4 = "";
        try {
            FileWriter fw = new FileWriter(tempfile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(new File(filepath));
            //x.useDelimiter("[,\n]");

            while(x.hasNext()) {
               /* Id = x.next();
                Name = x.next();*/
                String[] line = (x.nextLine()).split(",");
                    Name = line[1];
                    LastName = line[2];
                    Inf = line[8];
                    Vacc = line[9];
                    Dose1 = line[10];
                    Dose2 = line[11];
                    Dose3 = line[12];
                    Dose4 = line[13];
                if (line[0].equalsIgnoreCase(editAim)) {
                    pw.println(FName+","+LName+","+Covid+","+Vaccine+","+stDose+","+ndDose+","+rdDose+","+thDose);
                }
                else
                {
                    pw.println(Name+","+LastName+","+Inf+","+Vacc+","+Dose1+","+Dose2+","+Dose3+","+Dose4);
                }
            }

            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(dump);
        } catch (Exception var13) {
            System.out.println("Error");
        }
    }

}
