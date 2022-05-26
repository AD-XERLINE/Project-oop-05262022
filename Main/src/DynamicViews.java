import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class DynamicViews {
    private DynamicViews(){

    }
    public static void loadBorderCenter(BorderPane borderPane,String resource) throws IOException {
        Parent dashboard = FXMLLoader.load(new DynamicViews().getClass().getResource(resource+".fxml"));
        borderPane.setCenter(dashboard);
    
    }
}
