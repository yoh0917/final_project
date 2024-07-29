package sellphone.phoneplan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.PartMaker;
import com.google.cloud.vertexai.generativeai.ResponseHandler;

import java.io.IOException;

@Controller
@RequestMapping("/gemini-pro-vision")
public class GeminiProVisionController {

    private final GenerativeModel generativeModel;

    @Autowired
    public GeminiProVisionController(@Qualifier("geminiProVisionGenerativeModel") GenerativeModel generativeModel) {
        this.generativeModel = generativeModel;
    }

    @PostMapping
    @ResponseBody
    public String file(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam String question) throws IOException {
        GenerateContentResponse generateContentResponse;
        if (file != null && !file.isEmpty()) {
            generateContentResponse = this.generativeModel.generateContent(
                ContentMaker.fromMultiModalData(
                    PartMaker.fromMimeTypeAndData(file.getContentType(), file.getBytes()),
                    question
                )
            );
        } else {
            generateContentResponse = this.generativeModel.generateContent(
                ContentMaker.fromString(question)
            );
        }
        return ResponseHandler.getText(generateContentResponse);
    }

}
