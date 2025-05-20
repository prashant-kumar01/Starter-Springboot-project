package com.example.awsdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;

@Service
public class DynamoDBService {

    @Autowired
    private DynamoDbClient dynamoDbClient;

    public void putItem(String tableName, String userId, String value) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("userId", AttributeValue.builder().s(userId).build());  // Partition key
        item.put("value", AttributeValue.builder().s(value).build());    // Any other attribute
    
        PutItemRequest request = PutItemRequest.builder()
            .tableName(tableName)
            .item(item)
            .build();
    
        dynamoDbClient.putItem(request);
    }
    
}
