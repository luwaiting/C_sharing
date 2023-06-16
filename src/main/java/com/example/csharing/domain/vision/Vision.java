package com.example.csharing.domain.vision;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Vision {
    public String analyzeImage(String fileName){
        String x="";
        try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {
            Path path = Paths.get(fileName);
            byte[] data = Files.readAllBytes(path);
            ByteString imgBytes = ByteString.copyFrom(data);

            // Builds the image annotation request
            List<AnnotateImageRequest> requests = new ArrayList<>();
            Image img = Image.newBuilder().setContent(imgBytes).build();
            Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
            AnnotateImageRequest request =
                    AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
            requests.add(request);

            // Performs label detection on the image file
            BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();
            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    System.out.format("Error: %s%n", res.getError().getMessage());
                }
                x+=res.getFullTextAnnotation().getText();
            }

            x=x.replace("\n", "");
            x=x.replace("大学", "大學");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return x;
    }
    public List<String> confirm(String result,String school,String major){
        List<String>list=new ArrayList<String>();
        System.out.println(result);
        if(result.contains(school)){
            list.add(school);
            if(result.contains(major)){
                list.add(school+" "+major);
                list.add(major);
            }
        }else{
            if(result.contains(major)){
                list.add(major);
            }
        }
        return list;
    }

}
