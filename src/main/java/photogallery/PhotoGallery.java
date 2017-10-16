package photogallery;

import java.util.List;

public class PhotoGallery {
    PhotoGalleryDAO DAO = new PhotoGalleryDAO();

    public int add(Article article){
        return DAO.add(article);
    }

    public Article getById(long id){
        return DAO.getById(id);
    }

    public long size(){
        return DAO.size();
    }
    public boolean delete(long id){
        return DAO.delete(id);
    }

    public void update(Article article) {
        DAO.update(article);
    }
    public List<Article> getList(){
        return DAO.getList();
    }


}
