package M06;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SportDAO implements DAO<Sport> {
    private Connection conn;

    public SportDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(Sport item) {
        if (conn == null) {
            System.out.println("No está conectado a la base de datos.");
            return;
        }
        String sql = "INSERT INTO deportes (nombre) VALUES (?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, item.getName());
            statement.executeUpdate();
            System.out.println("El deporte " + item.getName() + " ha sido añadido.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Sport> getAll() {
        List<Sport> sports = new ArrayList<>();

        if (conn == null) {
            System.out.println("No está conectado a la base de datos.");
            return sports;
        }

        String sql = "SELECT cod, nombre FROM deportes";

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

        	while (resultSet.next()) {
                int id = resultSet.getInt("cod");
                String name = resultSet.getString("nombre");
                sports.add(new Sport(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sports;
    }

	@Override
	public List<Athlete> getAll(int sportId) {

		return null;
	}

	public Sport getById(int id) {
	    String query = "SELECT cod, nombre FROM deportes WHERE cod = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setInt(1, id);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return new Sport(rs.getInt("cod"), rs.getString("nombre"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}
