package perfekttest.web;

/**
 *
 * @author dglunts
 * @Description
 */
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.CloseStatus;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import perfekttest.model.FavChange;
import perfekttest.repo.UserRepository;

@Component
public class WSHandler extends TextWebSocketHandler {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private CopyOnWriteArrayList<WebSocketSession> wsSessions =
            new CopyOnWriteArrayList<>();
    
    @Autowired
    private UserRepository userRepository;    
    
    @Override
    public void handleTextMessage(WebSocketSession session,
            TextMessage message) throws IOException,Exception {
        FavChange favChange = new Gson()
                .fromJson(message.getPayload(), FavChange.class);
        
        logger.info("FavChange notification received: "+favChange.toString());
        
        session.sendMessage(new TextMessage("FavChange notification received: "
        +favChange.toString()));    
        session.sendMessage(new PongMessage());
        
        if (userRepository != null) {
            logger.info("userRepository IS NOT NULLLL!");
        } else {
            logger.info("userRepository IS REAL NULLLL!");
            return;
        }
        
        /* Adds to/removes from users' favorites list */
        if (favChange.isChecked()){
         session.sendMessage(new TextMessage("FavChange.book_id="
                 +favChange.getBook_id()));
            userRepository.addToFavorites(favChange.getBook_id(),
                    favChange.getUsername());
        } else {
            userRepository.deleteFromFavorites(favChange.getBook_id(),
                    favChange.getUsername());            
        }
        
        String op = (favChange.isChecked()) ? " added" : " removed";
        
        session.sendMessage(new TextMessage("Favorites modifyed: "
        +favChange.toString() + op));
        
        logger.info("Favorites modifyed: "
        +favChange.toString() + op);
        
    }
    
    @Override
    public void handleTransportError(WebSocketSession session,Throwable ex){
        logger.error("WSHandler error: "+ex.getMessage());
    }
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) 
        throws IOException {
        TextMessage msg = new TextMessage("WebSocketServer: Connection established with "
            +session.getPrincipal().toString()
            +" Remote address: "+session.getRemoteAddress());
        session.sendMessage(msg);
        wsSessions.add(session);
        session.sendMessage(new PongMessage());
        session.setTextMessageSizeLimit(256);       
    }
    
    @Override 
    public void afterConnectionClosed(WebSocketSession session,CloseStatus status){
        logger.info("WebSocketServer: Connection closed.Status: "+status.toString());
        wsSessions.remove(session);
    }

}

