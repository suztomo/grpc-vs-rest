# grpc-vs-rest
Measurement of GAPIC client libraries backed by gRPC and REST

# How to Build

```
mvn package
```

# How to Run

By default, the program uses the gRPC backend.

```
java -jar ./target/grpc-vs-rest-1.0-SNAPSHOT-jar-with-dependencies.jar
client initialization: com.google.cloud.resourcemanager.v3.ProjectsClient@6a55299e took 504 ms.
client.getProject returned cloud-java-ci-sample in 701 ms.
```

When you pass "rest" as the program arguments, it uses the REST backend.

```
java -jar ./target/grpc-vs-rest-1.0-SNAPSHOT-jar-with-dependencies.jar rest
client initialization: com.google.cloud.resourcemanager.v3.ProjectsClient@6d763516 took 316 ms.
client.getProject returned cloud-java-ci-sample in 795 ms.
```

## Logging

To enable logging, pass the logging.properties file to the java command.

```
java -Djava.util.logging.config.file=src/main/resources/logging.properties -jar ./target/grpc-vs-rest-1.0-SNAPSHOT-jar-with-dependencies.jar rest

...

2024-02-26 10:22:46,639 CONFIG   com.google.api.client.http.HttpTransport           - Total: 293 bytes 
2024-02-26 10:22:46,640 CONFIG   com.google.api.client.http.HttpTransport           - {
  "name": "projects/615621127317",
  "parent": "folders/1017439825540",
  "projectId": "cloud-java-ci-sample",
  "state": 1,
  "displayName": "cloud-java-ci-sample",
  "createTime": "2024-02-13T23:39:17.407Z",
  "updateTime": "2024-02-13T23:39:17.407Z",
  "etag": "W/\"83fbfcef65a8253a\""
}
 
client.getProject returned cloud-java-ci-sample in 777 ms.
```
