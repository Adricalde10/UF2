package M06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class View {
	static Scanner scanner = new Scanner(System.in);
	public static int menu(){
		int a=1;
		try {
			System.out.println("     Menu");
			System.out.println("1. Add Sport");
			System.out.println("2. Add Athlete");
			System.out.println("3. Search Athlete");
			System.out.println("4. List Athlete");
			System.out.println("5. Exit Database");
			a = scanner.nextInt();
			return a;
		}catch(Exception e) {
			System.err.println("Error is not a menu entry");
			return 0;
		}

	}
	public static Connection connectToDatabase() {
		try {
			File xmlFile = new File("database.xml");
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);

			String url = "jdbc:postgresql://" + doc.getElementsByTagName("host").item(0).getTextContent() + ":" + doc.getElementsByTagName("port").item(0).getTextContent() + "/" +doc.getElementsByTagName("database").item(0).getTextContent();String user = doc.getElementsByTagName("user").item(0).getTextContent();
			String password = doc.getElementsByTagName("password").item(0).getTextContent();

			System.out.println("Conexión exitosa a la base de datos.");
			return DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			System.err.println("Error al conectar a la base de datos: " + e.getMessage());
			return null;
		}
	}
	public static void sportForm(SportDAO sportDAO) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the name of the sport:");
		String sportName = scanner.nextLine();
	    Sport newSport = new Sport(0, sportName); 
	    sportDAO.add(newSport);
	}

	public static void athleteForm(AthleteDAO atheletDAO) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter athlete name:");
		String athleteName = scanner.nextLine();
		Athlete newAthlete = new Athlete(0, athleteName, 0); 
		atheletDAO.add(newAthlete);
		
		//sportid=AskAskSport()
		System.out.println("Enter athlete sport:");
		int athleteSport = scanner.nextInt();
		System.out.println(athleteName + athleteSport );
	}
	public static void AskSport(SportDAO sportDAO, AthleteDAO athleteDAO) {
	    Scanner scanner = new Scanner(System.in);
	    List<Athlete> athletes = athleteDAO.getAll();
	    // Listar todos los deportes
	    SportsList(sportDAO);

	    // Pedir al usuario el texto para la búsqueda parcial
	    System.out.println("Ingrese el nombre o parte del nombre del atleta: ");
	    String name = scanner.nextLine();

	    List<Athlete> arrayAthletes = new ArrayList<>();


	    // Filtrar los atletas según la búsqueda parcial
	    for (Athlete athlete : athletes) {
	        if (athlete.getName().toLowerCase().contains(name)) {
	        	arrayAthletes.add(athlete);
	        }
	    }

	    // Mostrar los resultados de la búsqueda
	    if (arrayAthletes.isEmpty()) {
	        System.out.println("No se encontraron atletas con el nombre o parte del nombre: " + name);
	    } else {
	        System.out.println("Resultados de la búsqueda:");
	        for (Athlete athlete : arrayAthletes) {
	            // Obtener el nombre del deporte asociado al atleta
	            Sport sport = sportDAO.getById(athlete.getSportId());
	            String sportName = (sport != null) ? sport.getName() : "Desconocido";

	            // Imprimir detalles del atleta
	            System.out.printf("Código: %d, Nombre: %s, Deporte: %s%n", athlete.getId(), athlete.getName(), sportName);
	        }
	    }
	}



	
	public static void AthleteList(SportDAO sportDAO, AthleteDAO athleteDAO) {
	    Scanner scanner = new Scanner(System.in);
		SportsList(sportDAO);

	    // Solicitar al usuario el ID del deporte
	    System.out.println("Insert ID Sports: ");
	    int sportId = scanner.nextInt();
	    List<Athlete> athletes = athleteDAO.getAll(sportId);
	    if (athletes.isEmpty()) {
	        System.out.println("No se encontraron atletas para el deporte con ID: " + sportId);
	    } else {
	        System.out.println("Atletas para el deporte con ID " + sportId + ":");
	        for (Athlete athlete : athletes) {
	            System.out.println(athlete);
	        }
	    }
	}

	public static void SportsList(SportDAO sportDAO) {
		 List<Sport> sports = sportDAO.getAll();
		    System.out.println("Deportes disponibles:");
		    for (Sport sport : sports) {
		        System.out.println(sport);
		    }
	}
}




