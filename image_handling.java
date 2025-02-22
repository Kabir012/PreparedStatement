import java.sql.DriverManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class image_handling {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String username = "root";
        String pass = "Kabir@4847";
        String img_path = "monkey-pictures.jpg";

        String query = "INSERT INTO image(image_data) VALUES (?);";


        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully!");

        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con = DriverManager.getConnection(url, username, pass);
            System.out.println("Connection built!");

            FileInputStream fInputStream = new FileInputStream(img_path); // ye object bnaya FileInputStream class ka
            byte[] image_data = new byte[fInputStream.available()];// ek empty byte array declare kra uska size uss object se ek available function call krke aega

            fInputStream.read(image_data);//yha par ab java m agyi image

            // ab isko database m bheejenge
            PreparedStatement pStatement = con.prepareStatement(query);
            pStatement.setBytes(1, image_data);
            int rowsAffected = pStatement.executeUpdate();
            if(rowsAffected>0){
                System.out.println("Insertion succesfull");
            }
            else{
                System.out.println("Failed!");
            }
            fInputStream.close();
            

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
