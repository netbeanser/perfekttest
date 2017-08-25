package perfekttest.web;

/**
 *
 * @author dglunts
 * @Description class name is self-explanatory.
 * This is some kind of controller, "@adviced" :-)
 * Here we can add more RuntimeException classes to be handled
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.core.NestedRuntimeException;
import org.springframework.web.util.NestedServletException;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;


@ControllerAdvice(basePackages="perfekttest.web")
public class GlobalExceptionHandler {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @ExceptionHandler(NestedRuntimeException.class)
    public String handleRuntimeException(HttpServletRequest req,
            NestedRuntimeException ex,
            Model model) {
        logger.error("Exception: "
        +ex.getMessage()+" MostSpecific: "
        +ex.getMostSpecificCause().getMessage()
        +" Url: "+req.getRequestURI());
        model.addAttribute("message",ex.getMessage());
        model.addAttribute("s_message", ex.getMostSpecificCause().getMessage());
        return "runtime_error";
    }   
    
    @ExceptionHandler(NestedServletException.class)
    public String handleServletException(HttpServletRequest req,
            NestedRuntimeException ex,
            Model model) {
        logger.error("SrvletException: "
        +ex.getMessage()
        +" Url: "+req.getRequestURI());
        model.addAttribute("message",ex.getMessage());

        return "servlet_error";        
    }
    
    
}
