import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


public class Add_TeacherController implements Initializable {

    @FXML
    private Text TextD1;

    @FXML
    private Text TextD2;

    @FXML
    private Text TextD3;

    @FXML
    private Text TextD4;
    @FXML
    private Label result;

    @FXML
    private Button AddTBuuton;

    @FXML
    private TextField Agefield;

    @FXML
    private ImageView CompImage1;

    @FXML
    private ImageView CompImage214;

    @FXML
    private TextField Diseasefield;

    @FXML
    private TextField FNameField;

    @FXML
    private TextField Heightfield;

    @FXML
    private TextField LNamefield;

    @FXML
    private TextField Weightfield;

    @FXML
    private CheckBox no1ch;

    @FXML
    private CheckBox no2ch;

    @FXML
    private CheckBox yes1ch;

    @FXML
    private CheckBox yes2ch;

    @FXML
    private ChoiceBox<String> choiceGender;

    @FXML
    private ChoiceBox<String> ChioceDose1;

    @FXML
    private ChoiceBox<String> ChioceDose2;

    @FXML
    private ChoiceBox<String> ChioceDose3;

    @FXML
    private ChoiceBox<String> ChioceDose4;

    public  String gender;
    public  String Dose1 = null;
    public  String Dose2 = null;
    public  String Dose3 = null;
    public  String Dose4 = null;

    private static Scanner x;

    private String[] Gender = {"Male","female"};
    private String[] Vaccin = {"SinoFarm","Pfizer","AstraZeneca","Sinovec","Moderna","Other","None"};

    @FXML
    void Close(MouseEvent event) {
        HelperStage.close((Node) event.getSource());
    }

    public void initialize(URL argo,ResourceBundle arg1){
        choiceGender.getItems().addAll(Gender);
        choiceGender.setOnAction(this :: getGender);
        ChioceDose1.getItems().addAll(Vaccin);
        ChioceDose1.setOnAction(this::getDose1);
        ChioceDose2.getItems().addAll(Vaccin);
        ChioceDose2.setOnAction(this::getDose2);
        ChioceDose3.getItems().addAll(Vaccin);
        ChioceDose3.setOnAction(this::getDose3);
        ChioceDose4.getItems().addAll(Vaccin);
        ChioceDose4.setOnAction(this::getDose4);

    }

    public void getGender(ActionEvent event){
        gender = choiceGender.getValue();
        System.out.println(gender);
    }

    public void getDose1(ActionEvent event){
        Dose1 = ChioceDose1.getValue();
        System.out.println(Dose1);
    }

    public void getDose2(ActionEvent event){
        Dose2 = ChioceDose2.getValue();
        System.out.println(Dose2);
    }

    public void getDose3(ActionEvent event){
        Dose3 = ChioceDose3.getValue();
        System.out.println(Dose3);
    }

    public void getDose4(ActionEvent event){
        Dose4 = ChioceDose4.getValue();
        System.out.println(Dose4);
    }

    @FXML
    void AddInfo(ActionEvent event) throws IOException {

            if(event.getSource() == AddTBuuton)
            {
                File file = new File("record.txt");
                Integer Id = 0 ;
                String Name = FNameField.getText();
                String lastName = LNamefield.getText();
                String Age = Agefield.getText();
                String Gender = choiceGender.getValue();;
                String Weight = Weightfield.getText();
                String Height = Heightfield.getText();
                String Disease = Diseasefield.getText();
                String Covid = getTextch(yes1ch);
                String Vacineted =getTextch(yes2ch);
                String Dose1 = this.Dose1;
                String Dose2 = this.Dose2;
                String Dose3 = this.Dose3;
                String Dose4 = this.Dose4;
                File fileread = new File("record.txt");
                Scanner input = new Scanner(fileread);
                while(input.hasNextLine()){
                    String[] line = (input.nextLine()).split(",");
                     Id =  Integer.parseInt(line[0])+1; ;

                }

                if(!file.exists())
                {
                    file.createNewFile();
                }
                    FileWriter output = new FileWriter(file,true);
                    output.append(Id +","+Name + "," + lastName +","+Age+","+Gender+","+Weight+","+Height+","+Disease+","+Covid+","+Vacineted+","+Dose1+","+Dose2+","+Dose3+","+Dose4+ "\n");
                    output.close();
                    result.setText("Success");
                
            }
    
    }

    String getTextch(CheckBox box){
        if(box.isSelected()){
            return "Yes";
        }
        return "no";

    }

    @FXML
    void getAddicCovid(ActionEvent event) {
        if(yes1ch.isSelected()){
            no1ch.setVisible(false);
        }
        if(!yes1ch.isSelected()){
            no1ch.setVisible(true);
        }
        if(no1ch.isSelected()){
            yes1ch.setVisible(false);
        }
        if(!no1ch.isSelected()){
            yes1ch.setVisible(true);
        }
        //
        if(yes2ch.isSelected()){
            TextD1.setVisible(true);
            TextD2.setVisible(true);
            TextD3.setVisible(true);
            TextD4.setVisible(true);
            ChioceDose1.setVisible(true);
            ChioceDose2.setVisible(true);
            ChioceDose3.setVisible(true);
            ChioceDose4.setVisible(true);
            no2ch.setVisible(false);
        }
        if(!yes2ch.isSelected()){
            TextD1.setVisible(false);
            TextD2.setVisible(false);
            TextD3.setVisible(false);
            TextD4.setVisible(false);
            ChioceDose1.setVisible(false);
            ChioceDose2.setVisible(false);
            ChioceDose3.setVisible(false);
            ChioceDose4.setVisible(false);
            no2ch.setVisible(true);
        }
        if(no2ch.isSelected()){
            TextD1.setVisible(false);
            TextD2.setVisible(false);
            TextD3.setVisible(false);
            TextD4.setVisible(false);
            ChioceDose1.setVisible(false);
            ChioceDose2.setVisible(false);
            ChioceDose3.setVisible(false);
            ChioceDose4.setVisible(false);
            yes2ch.setVisible(false);
        }
        if(!no2ch.isSelected()){
            TextD1.setVisible(true);
            TextD2.setVisible(true);
            TextD3.setVisible(true);
            TextD4.setVisible(true);
            ChioceDose1.setVisible(true);
            ChioceDose2.setVisible(true);
            ChioceDose3.setVisible(true);
            ChioceDose4.setVisible(true);
            yes2ch.setVisible(true);
        }

    }


    
}

