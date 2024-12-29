package uoc.edu.docdeskapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uoc.edu.docdeskapp.config.ChatGPTConfiguration;
import uoc.edu.docdeskapp.dto.ChatGPTRequest;
import uoc.edu.docdeskapp.dto.ChatGPTResponse;

import java.util.List;

@Service
public class ChatGPTService {

    @Autowired
    private ChatGPTConfiguration chatGPTConfiguration;

    private final RestTemplate restTemplate;

    public ChatGPTService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getChatGPTResponse(String userMessage) {
        String url = chatGPTConfiguration.getUrl();

        // Prepare the request
        ChatGPTRequest.Message message = new ChatGPTRequest.Message("user", userMessage);
        ChatGPTRequest request = new ChatGPTRequest("gpt-4o", List.of(message));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + chatGPTConfiguration.getKey());
        headers.set("Content-Type", "application/json");

        HttpEntity<ChatGPTRequest> entity = new HttpEntity<>(request, headers);

        // Send the request
        ResponseEntity<ChatGPTResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                ChatGPTResponse.class
        );

        // Extract and return the response
        return response.getBody().getChoices().get(0).getMessage().getContent();
    }
}
