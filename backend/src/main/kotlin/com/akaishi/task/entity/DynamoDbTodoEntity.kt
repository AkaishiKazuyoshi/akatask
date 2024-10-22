package com.akaishi.task.entity

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey

@DynamoDbBean
class DynamoDbTodoEntity(
    @get:DynamoDbAttribute("PK") @get:DynamoDbPartitionKey var pk: String,
    @get:DynamoDbAttribute("SK") @get:DynamoDbSortKey var sk: String,
    @get:DynamoDbAttribute("id") var id: String,
    @get:DynamoDbAttribute("title") var title: String,
    @get:DynamoDbAttribute("content") var content: String
) {
    constructor() : this("", "", "", "", "")
}
