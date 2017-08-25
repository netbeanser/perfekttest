package perfekttest.repo;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import perfekttest.model.Book;

import java.util.List;

/**
 *
 * @author dglunts
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {    
    
    /* This query uses CTE (Common Table Expression) name fav */
    static final String BOOK_SELECT = 
    "WITH fav AS ("
        +"SELECT "
            +"f.book_id,"
            +"f.user_id "
        +"FROM  favorites f "
        +"WHERE "
            +"f.user_id="
            +"(SELECT u.id FROM users u "
            +"WHERE  u.username=:username) "
    +") "
    +" SELECT " 
       +"b.id,"
       +"b.author,"
       +"b.title,"
       +"i.imgpath,"
       +"fav.user_id "
    +"FROM "
       +"book b "
    +"JOIN img i ON i.id=b.img_id "
    +"LEFT OUTER JOIN fav "
    +"ON "
       +"fav.book_id=b.id";
              
    @Query(value="INSERT INTO BOOK (author,title,img_id) "+
        "VALUES(:author,:title,:img_id)"+
        " ON CONFLICT (name) DO NOTHINGT",
        nativeQuery=true )
    @Modifying(clearAutomatically=false)
    @Transactional        
    public void safeInsertBook(@Param("author") String author,
        @Param("title") String title,
        @Param("img_id") Integer img_id);  

    
    @Query(value=BOOK_SELECT,nativeQuery=true)
    public List<Object[]> getBookAuxListAll(@Param("username") String username);
    
    @Query(value=BOOK_SELECT + " WHERE fav.user_id IS NOT NULL ",nativeQuery=true)
    public List<Object[]> getBookAuxListFav(@Param("username") String username);
    
    @Query(value=BOOK_SELECT + " OFFSET :offset LIMIT 10",
            nativeQuery=true)
    public List<Object[]> getBookAuxListAllRange10(@Param("username") String username,
            @Param("offset") Integer offset);
    
    @Query(value=BOOK_SELECT + " WHERE fav.user_id IS NOT NULL "
            +" OFFSET :offset LIMIT 10",nativeQuery=true)
    public List<Object[]> getBookAuxListFavRange10(@Param("username") String username,
            @Param("offset") Integer offset);
    
}
