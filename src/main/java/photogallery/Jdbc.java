package photogallery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Jdbc {
    private static Connection conn;
    {
        try {
            Class.forName("org.h2.Driver");
            getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addUser(long id, String name){
        String sql = "INSERT INTO USER(ID, NAME) VALUES(?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
