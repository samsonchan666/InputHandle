/**
 * Created by Samson on 2/11/2017.
 */

import java.net.URISyntaxException;
import java.sql.*;


public class Main {
    public static void main(String[] args) throws URISyntaxException, SQLException{
        DatabaseEngine databaseEngine = new DatabaseEngine();
        databaseEngine.addTour();
        databaseEngine.addCustomer();
    }
}
