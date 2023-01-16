import java.sql.*;

public class Database {
    public static void main(String[] args) throws SQLException {
        String user = "root";
        String password = "password";
        String DatabaseName="newdb";
        ResultSet rs = null;
        try (Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306",user,password);
             Statement stmt=con.createStatement();
             Statement createStudents = con.createStatement();
             Statement insertStudents = con.createStatement();
             Statement selectStudents = con.createStatement();
             ){
            int status = stmt.executeUpdate("CREATE DATABASE " + DatabaseName);
            if(status > 0) {
                System.out.println("Database is created successfully !!!");
            }
            //creazione tabella students
            createStudents.execute("CREATE TABLE " + DatabaseName+".students (" +
                    "student_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "last_name VARCHAR(30) NOT NULL," +
                    "first_name VARCHAR(30) NOT NULL )");
            //inserimento valori all'interno della tabella students, è utile creare piu variabili Statement o era meglio fare
            //tutto con una sola variabile Statement a cui cambiamo semplicemente il codice sql da eseguire?
            insertStudents.execute("INSERT INTO " + DatabaseName+ ".students(last_name,first_name)" +
                    "VALUES " +
                    "('Sepe','Francesco')," +
                    "('Zingalese','Alvise')," +
                    "('Pollina','Alessio')," +
                    "('Coman','Maria');");

            selectStudents.execute("SELECT * FROM " + DatabaseName+".students");
            rs = selectStudents.getResultSet();
            while (rs.next()){
                System.out.print(rs.getInt("student_id")+" ");
                System.out.print(rs.getString("last_name")+" ");
                System.out.print(rs.getString("first_name")+"\n");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
        }
    }
}