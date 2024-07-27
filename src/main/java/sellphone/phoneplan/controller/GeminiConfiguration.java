//package sellphone.phoneplan.controller;
//
//import com.google.cloud.vertexai.VertexAI;
//import com.google.cloud.vertexai.generativeai.ChatSession;
//import com.google.cloud.vertexai.generativeai.GenerativeModel;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//
//@Configuration
//public class GeminiConfiguration {
//
//    private static final Logger logger = LoggerFactory.getLogger(GeminiConfiguration.class);
//
//    @Bean
//    public VertexAI vertexAI() throws IOException {
//        String credentialsPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
//        logger.info("Using Google Cloud credentials from: {}", credentialsPath);
//        return new VertexAI("trans-realm-430614-v6", "asia-east1");
//    }
//
//    @Bean
//    public GenerativeModel geminiProVisionGenerativeModel(VertexAI vertexAI) {
//        return new GenerativeModel("gemini-pro-vision", vertexAI);
//    }
//
//    @Bean
//    public GenerativeModel geminiProGenerativeModel(VertexAI vertexAI) {
//        return new GenerativeModel("gemini-pro", vertexAI);
//    }
//
//    @Bean
//    public ChatSession chatSession(@Qualifier("geminiProGenerativeModel") GenerativeModel generativeModel) {
//        return new ChatSession(generativeModel);
//    }
//}