
/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main{
    
    public static DatabaseConnection database;


    public static void main(String args []){
        
        JFXPanel panel = new JFXPanel();
        Platform.runLater(() -> start());
        
    }
    
    private static void start(){
        try{
            database = new DatabaseConnection("PokemonDB.db");
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("Main_Scene.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Main");
            stage.setScene(new Scene(loader.load()));
            stage.show();
            SceneController1 sc = loader.getController();
            sc.prepareStageEvents(stage);
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            kill();
        }
        
    }

    public static void kill(){
        System.out.println("Killing app (and hopefully Nigel Farage too)");
        if (database != null) database.disconnect();    // Close the connection to the database (if it exists!)
        System.exit(0);
    }
}
