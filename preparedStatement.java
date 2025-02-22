import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class preparedStatement {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Students";
        String username = "root";
        String pass = "Kabir@4847";
        String query = "SELECT * FROM student_info  WHERE id = ? AND name = ?;";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded!");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, pass);
            PreparedStatement pStatement = con.prepareStatement(query);
            pStatement.setInt(1, 15385);
            pStatement.setString(2, "Kabir");
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String field = rs.getString("field");

                System.out.println("-------------------------");
                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Field: " + field);
                System.out.println("-------------------------");

            }

            con.close();
            System.out.println("Connection closed!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}