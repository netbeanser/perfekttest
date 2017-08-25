package perfekttest.model;

/**
 *
 * @author dglunts
 * @Description Simple POJO reflecting changes in user's favorites
 */
public class FavChange {
    
    private Integer book_id;
    private String username;
    private boolean checked;

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "FavChange{" + "book_id=" + book_id + ", username=" + username + ", checked=" + checked + '}';
    }
    
    
    
}
