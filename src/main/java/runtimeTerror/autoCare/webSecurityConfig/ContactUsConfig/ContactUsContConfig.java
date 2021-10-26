package runtimeTerror.autoCare.webSecurityConfig.ContactUsConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import runtimeTerror.autoCare.model.ContactUs.ContactUsModel;

import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

@Configuration
@EnableWebSocketMessageBroker
@EnableWebSocket

public class ContactUsContConfig implements WebSocketMessageBrokerConfigurer {


    @Bean
    public ServerEndpointExporter serverEndpoint() {
        return new ServerEndpointExporter();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        ServerEndpointConfig.Builder.create(ContactUsModel.class, "/echo").build();

        registry.addEndpoint("/gs-guide-websocket").withSockJS();

    }
}
