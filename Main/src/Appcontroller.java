import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;


public class Appcontroller {

    @FXML
    private BorderPane Main_pane;

    @FXML
    private ImageView compImage1;

    @FXML
    private ImageView compImage11;

    @FXML
    private ImageView compImage12;

    @FXML
    private ImageView compImage13;

    @FXML
    void Show_dash(MouseEvent event) throws IOException {
        DynamicViews.loadBorderCenter(Main_pane,"Dashbroad");

    }

    @FXML
    void Show_teacher(MouseEvent event) throws IOException{
        DynamicViews.loadBorderCenter(Main_pane,"Teacher");
    }

    @FXML
    void Show_Student(MouseEvent event) throws IOException{
        DynamicViews.loadBorderCenter(Main_pane,"Student");
    }

}
