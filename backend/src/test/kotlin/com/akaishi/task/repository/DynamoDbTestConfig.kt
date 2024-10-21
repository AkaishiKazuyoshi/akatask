package com.akaishi.task.repository

import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.*
import java.net.URI

abstract class DynamoDbTestConfig {
    protected companion object {

        var dynamoDbClient: DynamoDbClient
        val TEST_TABLE_NAME = "TestDynamoDbTable"

        private val dynamoDbContainer = GenericContainer<Nothing>(
            DockerImageName.parse(
                "public.ecr.aws/aws-dynamodb-local/aws-dynamodb-local:latest",
            ).asCompatibleSubstituteFor("amazon/dynamodb-local"),
        ).apply {
            withExposedPorts(8000)
        }

        init {
            dynamoDbContainer.start()
            dynamoDbClient = DynamoDbClient.builder().region(Region.AP_NORTHEAST_1)
                .endpointOverride(URI.create("http://localhost:${dynamoDbContainer.getMappedPort(8000)}"))
                .credentialsProvider(
                    StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("dummy", "dummy")
                    )
                ).build()
            val createTableRequest = CreateTableRequest.builder().attributeDefinitions(
                    AttributeDefinition.builder().attributeName("PK").attributeType(ScalarAttributeType.S).build(),
                    AttributeDefinition.builder().attributeName("SK").attributeType(ScalarAttributeType.S).build(),
                ).keySchema(
                    KeySchemaElement.builder().attributeName("PK").keyType(KeyType.HASH).build(),
                    KeySchemaElement.builder().attributeName("SK").keyType(KeyType.RANGE).build(),
                ).provisionedThroughput(
                    ProvisionedThroughput.builder().readCapacityUnits(1).writeCapacityUnits(1).build(),
                ).tableName(TEST_TABLE_NAME).build()
            dynamoDbClient.createTable(createTableRequest)

            val describeTableRequest = DescribeTableRequest.builder().tableName(TEST_TABLE_NAME).build()
            dynamoDbClient.waiter().waitUntilTableExists(describeTableRequest)
        }
    }

    fun clearDatabase() {
        dynamoDbClient
            .scan(
                ScanRequest
                    .builder()
                    .tableName(TEST_TABLE_NAME)
                    .build(),
            )
            .items()
            .forEach {
                dynamoDbClient.deleteItem(
                    DeleteItemRequest
                        .builder()
                        .tableName(TEST_TABLE_NAME)
                        .key(mapOf("PK" to it["PK"], "SK" to it["SK"]))
                        .build(),
                )
            }
    }
}
