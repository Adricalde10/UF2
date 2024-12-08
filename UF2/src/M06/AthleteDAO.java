package M06;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AthleteDAO implements DAO<Athlete> {
    private Connection conn;

    // Constructor para inicializar la conexión
    public AthleteDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(Athlete item) {
        if (conn == null) {
            System.out.println("No está conectado a la base de datos.");
            return;
        }
        String sql = "INSERT INTO deportistas (nombre, cod_deporte) VALUES (?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, item.getName());
            statement.setInt(2, item.getSportId()); // Asegúrate de tener el deporte asignado correctamente
            statement.executeUpdate();
            System.out.println("El atleta " + item.getName() + " ha sido añadido.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Athlete> getAll(int sportId) {
        List<Athlete> athletes = new ArrayList<>();
        String query = "SELECT cod, nombre FROM deportistas WHERE cod_deporte = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, sportId);  // Filtra por el ID del deporte
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("cod");
                    String name = rs.getString("nombre");
                    athletes.add(new Athlete(id, name, sportId));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return athletes;
    }

    
    @Override
    public List<Athlete> getAll() {
        List<Athlete> athletes = new ArrayList<>();
        String query = "SELECT cod, nombre, cod_deporte FROM deportistas";

        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("cod");
                String name = rs.getString("nombre");
                int sportId = rs.getInt("cod_deporte");
                athletes.add(new Athlete(id, name, sportId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return athletes;
    }
}
