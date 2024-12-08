package M06;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ex1 {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:postgresql://hh-pgsql-public.ebi.ac.uk:5432/pfmegrnargs";
		String username = "reader";
		String password = "NWDMCE5xdipIjRrp";
		 // Register the PostgreSQL driver

        //Class.forName("org.postgresql.Driver");

        // Connect to the database

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Close the connection
            System.out.println("OLE!");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT  upi,taxid,ac  FROM xref WHERE ac IN ('OTTHUMT00000106564.1', 'OTTHUMT00000416802.1')");
           

            while (resultSet.next()) {
            	String upi = resultSet.getString("upi");
            	 int taxid = resultSet.getInt("taxid");
                 String ac = resultSet.getString("ac");

                System.out.println("Upi:"+upi+"Taxid:"+taxid+"ac:"+ac);
            }


//int foovalue = 500;
//PreparedStatement st = conn.prepareStatement("SELECT * FROM mytable WHERE columnfoo = ?");
//st.setInt(1, foovalue);
//ResultSet rs = st.executeQuery();
//while (rs.next()) {
// System.out.print("Column 1 returned ");
// System.out.println(rs.getString(1));
//}




//CallableStatement callStmt = c.prepareCall("{call myFunc(?, ?, ?)}");
//callStmt.setString(1, "1");
//callStmt.setString(2, "2");
//callStmt.setString(3, "3");
//callStmt.execute();
//callStmt.close();

            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Perform desired database operations

    }
}