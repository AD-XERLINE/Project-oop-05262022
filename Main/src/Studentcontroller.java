import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

public class Studentcontroller implements Initializable{

    
    @FXML
    private ChoiceBox<String> ChoiceCovid;

    @FXML
    private ChoiceBox<String> ChoiceVaccine;

    @FXML
    private TableColumn<CountryData,String> ColFName;

    @FXML
    private TableColumn<CountryData,String> ColLName;

    @FXML
    private TableColumn<CountryData,String> ColVacc;

    @FXML
    private TableColumn<CountryData,String> Colid;

    @FXML
    private TableColumn<CountryData,String> Colinf;

    @FXML
    private TableColumn<CountryData,String> ColndD;

    @FXML
    private TableColumn<CountryData,String> ColrdD;

    @FXML
    private TableColumn<CountryData,String> ColstD;

    @FXML
    private TableColumn<CountryData,String> ColthD;

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
    private TableView<CountryData> TvBook;

    @FXML
    void HandleButton(ActionEvent event) {

    }

    @FXML
    void Add_student(MouseEvent event) throws IOException {
        Parent root = Loadresearch.loadFXML("Add_Student"); //ตรงชื่อาาจะผิด อาจจะต้องไปแก้ไฟล์ก่อน
        Dialog dialog = new Dialog();
        dialog.getDialogPane().setContent(root);

        root.setStyle("-fx-background-color:transparent");
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.show();
    }

    Collection<CountryData> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        try {
            list = Files.readAllLines(new File("E:/KMITL/1.2/oop/Project1/Main/recordStu.txt").toPath()).stream().map
            (line -> {
                String[] details = line.split(",");
                CountryData cd = new CountryData();
                cd.setCountry(details[0]);
                System.out.println(details[0]);
                cd.setCapital(details[1]);
                System.out.println(details[1]);
                cd.setPopulation(details[2]);
                System.out.println(details[2]);
                cd.setDemocracy(details[8]);
                System.out.println(details[8]);
                cd.setDemocracy4(details[9]);
                cd.setDemocracy5(details[10]);
                cd.setDemocracy6(details[11]);
                cd.setDemocracy7(details[12]);
                cd.setDemocracy8(details[13]);
                return cd;
            })
            .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObservableList<CountryData> details = FXCollections.observableArrayList(list);
       
        Colid.setCellValueFactory(data -> data.getValue().countryProperty());
        //System.out.println(Colid);
        ColFName.setCellValueFactory(data -> data.getValue().capitalProperty());
        ColLName.setCellValueFactory(data -> data.getValue().populationProperty());
        Colinf.setCellValueFactory(data -> data.getValue().democracyProperty());
        ColVacc.setCellValueFactory(data -> data.getValue().democracyProperty4());
        ColstD.setCellValueFactory(data -> data.getValue().democracyProperty5());
        ColndD.setCellValueFactory(data -> data.getValue().democracyProperty6());
        ColrdD.setCellValueFactory(data -> data.getValue().democracyProperty7());
        ColthD.setCellValueFactory(data -> data.getValue().democracyProperty8());

        TvBook.setItems(details);
    }
    

    private class CountryData {
        StringProperty country = new SimpleStringProperty();
        StringProperty capital = new SimpleStringProperty();
        StringProperty population = new SimpleStringProperty();
        StringProperty democracy = new SimpleStringProperty();
        StringProperty democracy4 = new SimpleStringProperty();
        StringProperty democracy5 = new SimpleStringProperty();
        StringProperty democracy6 = new SimpleStringProperty();
        StringProperty democracy7 = new SimpleStringProperty();
        StringProperty democracy8 = new SimpleStringProperty();
        
        public final StringProperty countryProperty() {
            return this.country;
        }

        public final String getCountry() {
            return this.countryProperty().get();
        }

        public final void setCountry(final String details) {
            this.countryProperty().set(details);
        }

        public final StringProperty capitalProperty() {
            return this.capital;
        }

        public final java.lang.String getCapital() {
            return this.capitalProperty().get();
        }

        public final void setCapital(final java.lang.String capital) {
            this.capitalProperty().set(capital);
        }

        public final StringProperty populationProperty() {
            return this.population;
        }

        public final java.lang.String getPopulation() {
            return this.populationProperty().get();
        }

        public final void setPopulation(final java.lang.String population) {
            this.populationProperty().set(population);
        }

        public final StringProperty democracyProperty() {
            return this.democracy;
        }

        public final java.lang.String getDemocracy() {
            return this.democracyProperty().get();
        }

        public final void setDemocracy(final java.lang.String democracy) {
            this.democracyProperty().set(democracy);
        }

        public final StringProperty democracyProperty4() {
            return this.democracy4;
        }

        public final java.lang.String getDemocracy4() {
            return this.democracyProperty4().get();
        }

        public final void setDemocracy4(final java.lang.String democracy4) {
            this.democracyProperty4().set(democracy4);
        }

        public final StringProperty democracyProperty5() {
            return this.democracy5;
        }

        public final java.lang.String getDemocracy5() {
            return this.democracyProperty5().get();
        }

        public final void setDemocracy5(final java.lang.String democracy5) {
            this.democracyProperty5().set(democracy5);
        }

        public final StringProperty democracyProperty6() {
            return this.democracy6;
        }

        public final java.lang.String getDemocracy6() {
            return this.democracyProperty6().get();
        }

        public final void setDemocracy6(final java.lang.String democracy6) {
            this.democracyProperty6().set(democracy6);
        }

        public final StringProperty democracyProperty7() {
            return this.democracy7;
        }

        public final java.lang.String getDemocracy7() {
            return this.democracyProperty7().get();
        }

        public final void setDemocracy7(final java.lang.String democracy7) {
            this.democracyProperty7().set(democracy7);
        }

        public final StringProperty democracyProperty8() {
            return this.democracy8;
        }

        public final java.lang.String getDemocracy8() {
            return this.democracyProperty8().get();
        }

        public final void setDemocracy8(final java.lang.String democracy8) {
            this.democracyProperty8().set(democracy8);
        }
    }
}

