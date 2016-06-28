
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

/* Each table you wish to access in your database requires a model class, like this example: */
public class Pokemon
{
    /* First, map each of the fields (columns) in your table to some public variables. */
    public int id;
    public String name;
    public String type1;
    public String type2;

    /* Next, prepare a constructor that takes each of the fields as arguements. */
    public Pokemon(int id, String name, String type1, String type2)
    {
        this.id = id;        
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
    }

    /* A toString method is vital so that your model items can be sensibly displayed as text. */
    @Override public String toString()
    {
        return (id+ " | " + name + " | Type(s): " + type1 + " | " + type2);
    }

    /* Different models will require different read and write methods. Here is an example 'loadAll' method 
     * which is passed the target list object to populate. */
    public static void readAll(List<Pokemon> list)
    {
        list.clear();       // Clear the target list first.

        /* Create a new prepared statement object with the desired SQL query. */
        PreparedStatement statement = Main.database.newStatement("SELECT id, name, type1, type2 FROM Pokemon ORDER BY id"); 

        if (statement != null)      // Assuming the statement correctly initated...
        {
            ResultSet results = Main.database.runQuery(statement);       // ...run the query!

            if (results != null)        // If some results are returned from the query...
            {
                try {                               // ...add each one to the list.
                    while (results.next()) {                                               
                        list.add( new Pokemon(results.getInt("id"), results.getString("name"), results.getString("type1"), results.getString("type2")));
                    }
                }
                catch (SQLException resultsexception)       // Catch any error processing the results.
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        }

    }

    public static Pokemon getById(int id)
    {
        Pokemon thing = null;

        PreparedStatement statement = Main.database.newStatement("SELECT id, name, type1. type2 FROM Pokemon WHERE id = ?"); 

        try 
        {
            if (statement != null)
            {
                statement.setInt(1, id);
                ResultSet results = Main.database.runQuery(statement);

                if (results != null)
                {
                    thing = new Pokemon(results.getInt("id"), results.getString("name"), results.getString("type1"), results.getString("type2"));
                }
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

        return thing;
    }

    public static void deleteById(int id)
    {
        try 
        {

            PreparedStatement statement = Main.database.newStatement("DELETE FROM Pokemon WHERE id = ?");             
            statement.setInt(1, id);

            if (statement != null)
            {
                Main.database.executeUpdate(statement);
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

    }
    
    public void save()    
    {
        PreparedStatement statement;

        try 
        {

            if (id == 0)
            {

                statement = Main.database.newStatement("SELECT id FROM Pokemon ORDER BY id DESC");             

                if (statement != null)  
                {
                    ResultSet results = Main.database.runQuery(statement);
                    if (results != null)
                    {
                        id = results.getInt("id") + 1;
                    }
                }

                statement = Main.database.newStatement("INSERT INTO Pokemon (id, name, type1, type2) VALUES (?, ?, ?, ?)");             
                statement.setInt(1, id);
                statement.setString(2, name);
                statement.setString(3, type1);              
                statement.setString(4, type2);         

                
            }
            else
            {
                statement = Main.database.newStatement("UPDATE things SET name = ?, type1 = ?, type2 = ?, WHERE id = ?");             
                statement.setString(1, name);
                statement.setString(2, type1);   
                statement.setString(3, type2);   
                statement.setInt(4, id);
            }

            if (statement != null)
            {
                Main.database.executeUpdate(statement);
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

    }

}

