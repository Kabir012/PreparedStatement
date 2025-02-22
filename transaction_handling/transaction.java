package transaction_handling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class transaction {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String username = "root";
        String pass = "Kabir@4847";

        String withdrawlQuery = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";

        String depositQuery = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver installed!");
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, pass);
            System.out.println("Connection Established!");
            con.setAutoCommit(false);


            PreparedStatement withdrawlStatement = con.prepareStatement(withdrawlQuery);
            PreparedStatement deposiStatement = con.prepareStatement(depositQuery);

            withdrawlStatement.setDouble(1, 500.00);
            withdrawlStatement.setString(2, "account456");
            deposiStatement.setDouble(1, 500);
            deposiStatement.setString(2, "account123");

            int rowsAffectedWithdrawl = withdrawlStatement.executeUpdate();
            int rowsAffectedDeposit = deposiStatement.executeUpdate();

            if(rowsAffectedDeposit>0 && rowsAffectedWithdrawl>0){
                con.commit();
                System.out.println("Transaction successfull!");
            }
            else{
                con.rollback();
                System.out.println("Transaction failed!");
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
