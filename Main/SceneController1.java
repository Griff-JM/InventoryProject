
/**
 * Write a description of class SceneController1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
 
import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.stage.WindowEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import java.awt.event.*;
import java.util.List;

public class SceneController1 {
    private static Stage stage;
    
    @FXML
    private Label PokemonLabel;

    @FXML   
    private ListView mainListView;

    @FXML
    private Label SearchLabel;

    @FXML
    private TextField SearchField;

    @FXML
    private Button SearchButton;

    @FXML
    private Button AddButton;

    @FXML
    private Button EditButton;

    @FXML
    private Button DeleteButton;
    
    public SceneController1(){
                    System.out.println("Here we go");
    }
    
    @FXML void initialize(){
        try{
        assert PokemonLabel !=null :"PLabel Error";
        assert mainListView !=null :"ListView Error";
        assert SearchLabel !=null :"SLabel Error";
        assert SearchField !=null :"SearchLabel Error";
        assert SearchButton !=null :"SearchButton Error";
        assert AddButton !=null :"AddButton Error";
        assert EditButton !=null :"EditButton Error";
        assert DeleteButton !=null :"DeleteButton Error";
        
    }
    catch(AssertionError ae){
       System.out.println( ae.getMessage());
       Main.kill();
    }
    
     System.out.println("Populating scene with items from the database...");        
        @SuppressWarnings("unchecked")
        List<Pokemon> targetList = mainListView.getItems();  // Grab a reference to the listView's current item list.
        Pokemon.readAll(targetList);  
    
    }
    
public void prepareStageEvents(Stage stage){
    System.out.println("Preparing Stage Events...");
    this.stage = stage;
    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        public void handle(WindowEvent we){
            System.out.println("Close Button Was Clicked");
            Main.kill();
        }
    });
    
}

    @FXML
    void addClick() {
           System.out.println("Add was clicked");
    }

    @FXML
    void delClick() {
           System.out.println("Delete was clicked");
    }

    @FXML
    void editClick() {
           System.out.println("Edit was clicked");
    }

    @FXML
    void searchClick() {
           System.out.println("Search was clicked");
    }

}






