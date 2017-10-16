package photogallery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JdbcTest {

    private Connection conn;

    @Test
    public void getConnection() {
        assertNotNull(conn);
    }

    @Before
    public void setUp(){
        String sql = "CREATE TABLE USER(ID INT, NAME VARCHAR(50))";
        execute(sql);
    }

    @After
    public void tearDown(){
        String sql = "DROP TABLE USER";
        execute(sql);
    }

    private void execute(String sql) {
        conn = Jdbc.getConnection();
        PreparedStatement pstmt;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addUser(){
        long id = 1L;
        String name = "dpaulb";
        Jdbc.addUser(id, name);

        String sqlSelect = "SELECT * FROM USER WHERE ID = 1";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlSelect);
            ResultSet resultSet = pstmt.executeQuery();
            resultSet.next();
            String name2 = resultSet.getString(2);
            assertEquals(name, name2);
            resultSet.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
