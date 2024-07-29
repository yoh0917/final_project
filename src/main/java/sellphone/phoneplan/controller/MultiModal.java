//package sellphone.phoneplan.controller;
//
//import com.google.cloud.vertexai.VertexAI;
//import com.google.cloud.vertexai.api.Blob;
//import com.google.cloud.vertexai.api.Content;
//import com.google.cloud.vertexai.api.GenerateContentResponse;
//import com.google.cloud.vertexai.api.GenerationConfig;
//import com.google.cloud.vertexai.api.HarmCategory;
//import com.google.cloud.vertexai.api.Part;
//import com.google.cloud.vertexai.api.SafetySetting;
//import com.google.cloud.vertexai.generativeai.ContentMaker;
//import com.google.cloud.vertexai.generativeai.GenerativeModel;
//import com.google.cloud.vertexai.generativeai.PartMaker;
//import com.google.cloud.vertexai.generativeai.ResponseStream;
//import com.google.protobuf.ByteString;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class MultiModal {
//  public static void main(String[] args) throws IOException {
//    try (VertexAI vertexAi = new VertexAI("trans-realm-430614-v6", "asia-east1"); ) {
//      GenerationConfig generationConfig =
//          GenerationConfig.newBuilder()
//              .setMaxOutputTokens(8192)
//              .setTemperature(1F)
//              .setTopP(0.95F)
//              .build();
//      List<SafetySetting> safetySettings = Arrays.asList(
//        SafetySetting.newBuilder()
//            .setCategory(HarmCategory.HARM_CATEGORY_HATE_SPEECH)
//            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
//            .build(),
//        SafetySetting.newBuilder()
//            .setCategory(HarmCategory.HARM_CATEGORY_DANGEROUS_CONTENT)
//            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
//            .build(),
//        SafetySetting.newBuilder()
//            .setCategory(HarmCategory.HARM_CATEGORY_SEXUALLY_EXPLICIT)
//            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
//            .build(),
//        SafetySetting.newBuilder()
//            .setCategory(HarmCategory.HARM_CATEGORY_HARASSMENT)
//            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
//            .build()
//      );
//      GenerativeModel model =
//        new GenerativeModel.Builder()
//            .setModelName("gemini-1.5-flash-001")
//            .setVertexAi(vertexAi)
//            .setGenerationConfig(generationConfig)
//            .setSafetySettings(safetySettings)
//            .build();
//
//
//      var content = ContentMaker.fromMultiModalData();
//      ResponseStream<GenerateContentResponse> responseStream = model.generateContentStream(content);
//
//      // Do something with the response
//      responseStream.stream().forEach(System.out::println);
//    }
//  }
//}
