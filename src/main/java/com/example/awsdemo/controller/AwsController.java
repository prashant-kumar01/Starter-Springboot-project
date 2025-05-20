package com.example.awsdemo.controller;

import com.example.awsdemo.service.DynamoDBService;
import com.example.awsdemo.service.LambdaService;
import com.example.awsdemo.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class AwsController {

    @Autowired
    private S3Service s3Service;

    @Autowired
    private DynamoDBService dynamoDBService;

    @Autowired
    private LambdaService lambdaService;

    @PostMapping("/s3/upload")
    public String uploadFileToS3(@RequestParam("bucket") String bucket,
                                 @RequestParam("key") String key,
                                 @RequestParam("file") MultipartFile file) throws Exception {
        s3Service.uploadFile(bucket, key, file.getBytes());
        return "File uploaded successfully to S3!";
    }

    @PostMapping("/dynamodb/put")
    public String putItemToDynamoDB(@RequestParam String table,
                                    @RequestParam String userId,
                                    @RequestParam String value) {
        dynamoDBService.putItem(table, userId, value);
        return "Item inserted into DynamoDB.";
    }

    @PostMapping("/lambda/invoke")
    public String invokeLambda(@RequestParam String functionName,
                               @RequestBody String payload) {
        return lambdaService.invokeFunction(functionName, payload);
    }
}