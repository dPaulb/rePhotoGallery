package photogallery;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhotoGalleryDAO {

    public Connection getConnection() {
        Connection conn = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/DevDB");
            conn = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javatest", "javauser", "javadude");
                System.out.println(conn);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return conn;
    }

    public int add(Article article) {
        Connection conn = getConnection();
        PreparedStatement pstmt;


        try {
            String sql = "insert into photogallery (writer, title, content, filename, savename, filesize) values(?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, article.getWriter());
            pstmt.setString(2, article.getTitle());
            pstmt.setString(3, article.getContent());
            pstmt.setString(4, article.getFilename());
            pstmt.setString(5, article.getSaveName());
            pstmt.setLong(6, article.getFileSize());
            int i = pstmt.executeUpdate();
            pstmt.close();
            if (i == 1) {
                pstmt = conn.prepareStatement("select max(id) from photogallery");
                ResultSet resultSet = pstmt.executeQuery();
                if (resultSet.next()) {
                    i = resultSet.getInt(1);

                }
                resultSet.close();
                pstmt.close();
                conn.close();
            }
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Article getById(long id) {
        Connection conn = getConnection();
        PreparedStatement pstmt;
        ResultSet resultSet;
        Article article = null;
        try {
            String sql = "select * from photogallery where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                article = new Article();
                article.setId(resultSet.getLong("id"));
                article.setWriter(resultSet.getString("writer"));
                article.setTitle(resultSet.getString("title"));
                article.setContent(resultSet.getString("content"));
                article.setFilename(resultSet.getString("filename"));
                article.setSaveName(resultSet.getString("saveName"));
                article.setFileSize(resultSet.getLong("fileSize"));
                resultSet.close();
                pstmt.close();
                conn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return article;
    }

    public long size() {
        Connection conn = getConnection();
        PreparedStatement pstmt;
        ResultSet rs;
        Long count = null;
        try {
            String sql = "select count(*) from photogallery";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getLong(1);
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean delete(long id) {
        Connection conn = getConnection();
        PreparedStatement pstmt;
        int cnt = 0;
        try {
            String sql = "delete from photogallery where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            cnt = pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt == 1;
    }

    public void update(Article article) {
        Connection conn = getConnection();
        PreparedStatement pstmt;
        try {
            String sql = "update photogallery set writer = ?, title = ?, content = ?, filename = ?, saveName = ?, fileSize = ? where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, article.getWriter());
            pstmt.setString(2, article.getTitle());
            pstmt.setString(3, article.getContent());
            pstmt.setString(4, article.getFilename());
            pstmt.setString(5, article.getSaveName());
            pstmt.setLong(6, article.getFileSize());
            pstmt.setLong(7, article.getId());
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Article> getList(){
        Connection conn = getConnection();
        PreparedStatement pstmt;
        ResultSet rs;
        List<Article> list = new ArrayList<Article>();
        String sql = "Select * from photogallery order by id desc";
        try{
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                Article article = new Article();
                article.setId(rs.getLong("id"));
                article.setWriter(rs.getString("writer"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setFilename(rs.getString("filename"));
                article.setSaveName(rs.getString("savename"));
                article.setFileSize(rs.getLong("filesize"));
                list.add(article);
            }
            rs.close();
            pstmt.close();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
