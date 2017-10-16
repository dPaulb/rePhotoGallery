package photogallery;

import junit.framework.TestCase;

public class PhotoGalleryTest extends TestCase {

    public void testAdd(){
        Article article = new Article();
        article.setWriter("dongho");
        article.setTitle("dongho");
        article.setContent("dongho~~");

        PhotoGallery photoGallery = new PhotoGallery();

        int add = photoGallery.add(article);
        assertTrue(add > 0);

    }
}
