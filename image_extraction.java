import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class image_extraction {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String username = "root";
        String pass = "Kabir@4847";
        String folder_path = "D:\\JAVA Development\\PreparedStatement\\";

        String query = "SELECT image_data FROM image WHERE image_id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers installed!");

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, pass);
            System.out.println("Connection built!");

            PreparedStatement pStatement = con.prepareStatement(query);
            pStatement.setInt(1, 1);

            ResultSet rs = pStatement.executeQuery();
            if (rs.next()) {

                byte[] image_data = rs.getBytes("image_data");
                String image_path = folder_path + "extractedImage.jpg";
                FileOutputStream fileOutputStream = new FileOutputStream(image_path);
                fileOutputStream.write(image_data);
                System.out.println("Image extracted successfully!");
                fileOutputStream.close();
            } else {
                System.out.println("No image extracted!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
}
