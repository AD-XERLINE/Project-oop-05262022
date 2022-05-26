import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import animatefx.animation.*;

public class ControllerLog {

    @FXML
    private Button ButtonBack;

    @FXML
    private Button ButtonLogin;

    @FXML
    private Button ButtonNext;

    @FXML
    private Button ButtonSingUp;

    @FXML
    private PasswordField EnterConfirmPassId;

    @FXML
    private TextField EnterNameId;

    @FXML
    private PasswordField EnterPassId;

    @FXML
    private ImageView ImageBack;

    @FXML
    private ImageView Logo;

    @FXML
    private ImageView MLElement1;

    @FXML
    private PasswordField PassId;

    @FXML
    private TextField UsernameId;

    @FXML
    private ImageView buttonExit;

    @FXML
    private ImageView buttonExit1;

    @FXML
    private Pane pnlLogin;

    @FXML
    private Pane pnlRegister;

    @FXML
    private Label resultShow;

    @FXML
    private Label LabelRegister;


    
    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        if(event.getSource().equals(ButtonSingUp))
        {   
            EnterNameId.clear();
            EnterPassId.clear();
            EnterConfirmPassId.clear();
            LabelRegister.setText("");
            new SlideInUp(pnlRegister).play();
            pnlRegister.toFront();
        }
        if(event.getSource() == ButtonLogin)
        {
            String username = UsernameId.getText();
            String password = PassId.getText();
            boolean isSuccess = false;
            //FXMLLoader(getClass().getResource("DataUser.txt"));
            //File file = new File("D:\member.txt");
            File file = new File("E:/KMITL/1.2/oop/projtest2/App/src/DataUser.txt");
            Scanner input = new Scanner(file);
            
            while (input.hasNext())
            {
                String[] line = (input.nextLine()).split(" ");
                //System.out.println(line[0]);
                if(username.equalsIgnoreCase(line[0])&&password.equalsIgnoreCase(line[1]))
                {
                    new Tada(resultShow).play();
                    resultShow.setText("Success");
                    isSuccess = true;
                }
            }
            if(!isSuccess)
            {
                new Shake(resultShow).play();
                resultShow.setText("Your username or password is wrong.");
            }
        }
        if(event.getSource() == ButtonNext)
        {
            File file = new File("E:/KMITL/1.2/oop/projtest2/App/src/DataUser.txt");
            String username = EnterNameId.getText();
            String password = EnterPassId.getText();
            String Cpassword = EnterConfirmPassId.getText();
            //System.out.println(password); 
            //System.out.println(Cpassword);
            Scanner in = new Scanner(file);
            boolean Usedname = false;
            while (in.hasNext())
            {
                String[] line = (in.nextLine()).split(" ");
                //System.out.println(line[0]);
                if(line[0].equals(username))
                {
                    Usedname = true;
                }
            }
            //System.out.println(Usedname);
            if(Usedname)
            {
                new Shake(LabelRegister).play();
                LabelRegister.setText("This name is already in use.");
            }
            else if(username == "" || password == "" || CheckSpace(username,BlackList))
            {
                new Shake(LabelRegister).play();
                LabelRegister.setText("Username and password must not be empty.");
            }
            else if(password.equals(Cpassword) && !CheckSpace(password,BlackList))
            {
                if(!file.exists())
                {
                    file.createNewFile();
                }
                FileWriter output = new FileWriter(file,true);
                output.append(username+" "+password+"\n");
                output.close();

                new Tada(LabelRegister).play();
                LabelRegister.setText("Success");
            }
            else
            {
                new Shake(LabelRegister).play();
                LabelRegister.setText("check your password again");
            }
            
        }

    }

    @FXML
    void handleMouseClick(MouseEvent event) {
        if(event.getSource() == buttonExit || event.getSource() == buttonExit1){
            System.exit(0);
        }
        if(event.getSource().equals(ButtonBack)){
            resultShow.setText("");
            UsernameId.clear();
            PassId.clear();
            new SlideInDown(pnlLogin).play();
            pnlLogin.toFront();
        }
    }
    String BlackList[] = {" "};
    public boolean CheckSpace(String username,String[] BlackList){
        
        for(String string : BlackList){
            if(string.equals(username)){
                return true;
            }
        }
        return false;
    }
}
