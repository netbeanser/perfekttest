package perfekttest.config;

/**
 *
 * @author dglunts
 * @Description:  class name is self-explanatory
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.WebSocketHandler;

import perfekttest.web.WSHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(wsHandler(), "/favchange");
    }

    /*This handler will react to user' click on 
    "Favorites" checkbox
    */
    @Bean
    public WebSocketHandler wsHandler() {
        WSHandler wsHandler =  new WSHandler();
        return wsHandler;
    }  
    
}
