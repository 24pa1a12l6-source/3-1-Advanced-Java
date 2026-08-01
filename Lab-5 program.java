import java.sql.*;

public class UResultSet {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "testuser";
        String password = "asma";

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to Database
            Connection con = DriverManager.getConnection(url, user, password);

            // Create Updatable and Scrollable ResultSet
            Statement st = con.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            // Execute Query
            ResultSet rs = st.executeQuery("SELECT * FROM Student");

            // Delete the Last Record
            rs.last();
            rs.deleteRow();
            System.out.println("Last student record deleted successfully.");

            // Insert a New Record
            rs.moveToInsertRow();
            rs.updateInt("RollNo", 105);
            rs.updateString("Name", "John Doe");
            rs.updateString("Address", "Hyderabad");
            rs.insertRow();
            rs.moveToCurrentRow();

            System.out.println("New student record inserted successfully.");

            // Display Final Records
            rs = st.executeQuery("SELECT * FROM Student");

            System.out.println("\nFinal Records:");
            System.out.println("RollNo\tName\t\tAddress");
            System.out.println("-------------------------------------");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("RollNo") + "\t"
                        + rs.getString("Name") + "\t\t"
                        + rs.getString("Address"));
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
