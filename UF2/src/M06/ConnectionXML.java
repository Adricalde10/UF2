package M06;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionXML {
	private String host;

	private String port;

	private String user;

	private String password;

	private String database;



	public  ConnectionXML(String config_file) {
		File file=new File(config_file);
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			
			host = doc.getElementsByTagName("host").item(0).getTextContent();
			port = doc.getElementsByTagName("port").item(0).getTextContent();
			user = doc.getElementsByTagName("user").item(0).getTextContent();
			password = doc.getElementsByTagName("password").item(0).getTextContent();
			database = doc.getElementsByTagName("database").item(0).getTextContent();

		}catch(Exception e){}					
	}

	public Connection dbConnect(){
		Connection connection = null;
		
		// Posa el codi per connectar a la DB
		System.out.println("ATENCIO: No estàs connectat");
		System.out.println("Dades de connexió: " + host +" - " + port+" - "  + user+" - " + password +" - " + database);


		return connection;
	}
}

