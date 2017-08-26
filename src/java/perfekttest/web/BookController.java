package perfekttest.web;
/**
 *
 * @author dglunts
 */

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import perfekttest.model.BookAux;
import perfekttest.model.User;
import perfekttest.repo.BookRepository;
import perfekttest.repo.UserRepository;

@Controller
@RequestMapping("/")
public class BookController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());    
    
    @Autowired
    private BookRepository bookRepository;    
    
    @Autowired
    private UserRepository userRepository;    
    
    /* Generates List<BookAux> from List<Object[]>*/
    
    /*Native SELECT Query,especially in case when two 
        or more table  joins,
        returns an Object[], or a List thereof.
        Not an Entity. 
        So we need to 1) know exactly the structure of returned data
        and 2)generate List of objects of known structure.
        This is where the auxilliary class BookAux comes into play
        We will use List<BookAux> in jsp page
    */       
    private List<BookAux> objListToBookAuxList(List<Object[]> bookRepoList){
    
        List<BookAux> bookList = new ArrayList<>(); 
        
        for (Object[] obj : bookRepoList){
            Integer user_id; //never null
            if (obj[4] != null){ //user_id, may bw null in this case
                user_id = Integer.valueOf(obj[4].toString());
            } else {
                user_id = 0;
            }
            Integer book_id = Integer.valueOf(obj[0].toString());
            BookAux bookDesc = new BookAux(book_id,
                    obj[1].toString(),
                    obj[2].toString(),
                    obj[3].toString(),
                    user_id);
    //        logger.info("BookListItem: "+bookDesc.toString() );
            bookList.add(bookDesc);
        }   
        return bookList;
    }
    //List all books
    @RequestMapping(value = "/books/all",method = RequestMethod.GET)
    public String getBookListAll(@RequestParam("offset") Integer offset,
            Model model){
        
        logger.info("OFFSET IS "+ offset);

     
        List<Object[]> bookRepoList = new ArrayList<>();
        
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();
        
        User user = userRepository.findByUsername(username);
        
        if (user != null) {
            
            if(offset<0){
                bookRepoList = bookRepository.getBookAuxListAll(username);
            } else {
                bookRepoList = bookRepository.getBookAuxListAllRange10(username,offset);                
            } 
            
            List<BookAux> bookList = objListToBookAuxList(bookRepoList);
        
            model.addAttribute("bookList", bookList);
            model.addAttribute("username",username);
            model.addAttribute("logged_in_user_id", user.getId());
            model.addAttribute("totalRows",bookRepository.count());            

        } else {
            return "login";                                
        }

        return "books";
    }      

    @RequestMapping(value = "/books/fav",method = RequestMethod.GET)
    public String getBookListFav(@RequestParam("offset") Integer offset,
            Model model){
        
        String username = SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal()
            .toString();
        
        User user = userRepository.findByUsername(username);
        List<Object[]> bookRepoList = new ArrayList<>();
        
        if (user != null){   
            
            if(offset<0){
                bookRepoList = bookRepository.getBookAuxListFav(username);
            } else {
                bookRepoList = bookRepository.getBookAuxListFavRange10(username,offset);                
            }
            
            List<BookAux> bookList = objListToBookAuxList(bookRepoList);        
        
            model.addAttribute("bookList", bookList);
            model.addAttribute("username", username);
            model.addAttribute("logged_in_user_id", user.getId());
            model.addAttribute("fav", 1);    
            model.addAttribute("totalRows",bookRepository.getFavCount(username));
            
        }
        return "books";
    } 
    
}
