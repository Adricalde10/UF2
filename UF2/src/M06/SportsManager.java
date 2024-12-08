package M06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SportsManager {
	//SportDAO sportDAO = new SportDAO();
	//AthleteDAO....
    public static void main(String[] args) {
 
    Connection connection = View.connectToDatabase();
    if (connection != null) {
             System.out.println("Conexión establecida correctamente.");
         } else {
             System.out.println("No se pudo establecer conexión.");
         }
    Scanner scanner = new Scanner(System.in);
    boolean bucle = false;
    while(bucle=true) {
    int choice = View.menu();
    SportDAO sportDAO = new SportDAO(connection);
    AthleteDAO atheletDAO = new AthleteDAO(connection);
    AthleteDAO athleteDAO = new AthleteDAO(connection);
 
      if (choice == 1) { 
          View.sportForm(sportDAO);
      } else if(choice == 2) {
          View.athleteForm(atheletDAO);
      } else if(choice == 3) {
          View.AskSport(sportDAO, athleteDAO);
      } else if(choice == 4) {
          View.AthleteList(sportDAO, athleteDAO);
      } else if(choice == 5) {
          System.out.println("exit");
          break;
      } else if(choice > 5 || choice <= 0) {
          System.err.println("Error not a valid option");
      }
    }
    }
    
    
}
