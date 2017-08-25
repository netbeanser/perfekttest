/**
 * Auxiliary class for retrieving non-entity data
 * from native query
 */
package perfekttest.model;

/**
 *
 * @author dglunts
 */
public class BookAux {

    private Integer book_id;
    private String author;
    private String title;
    private String imgpath;
    private Integer user_id;    

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }


    @Override
    public String toString() {
        return "BookImg{" + "book_id=" + book_id + ",author=" + author
        + ",title=" + title + ",imgpath=" + imgpath + ",user_id=" + user_id + '}';
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    
    public BookAux(){        
    }

    public BookAux(Integer book_id,
            String author, 
            String title, 
            String imgpath,
            Integer user_id) {
        this.book_id = book_id;
        this.author = author;
        this.title = title;
        this.imgpath = imgpath;
        this.user_id = user_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

}
