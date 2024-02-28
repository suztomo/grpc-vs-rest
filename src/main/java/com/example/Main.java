package com.example;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.cloudresourcemanager.v3.CloudResourceManager;
import com.google.api.services.cloudresourcemanager.v3.CloudResourceManager.Projects;
import com.google.api.services.cloudresourcemanager.v3.CloudResourceManager.Projects.Get;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.resourcemanager.v3.Project;
import com.google.cloud.resourcemanager.v3.ProjectsClient;
import com.google.cloud.resourcemanager.v3.ProjectsSettings;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {

  /**
   * Runs client initialization and one RPC call via ProjectsClient.
   *
   * @param arguments "rest" to use HTTP backend; otherwise it uses gRPC backend.
   */
  public static void main(String[] arguments) throws Exception {
    if (arguments.length != 1) {
      System.out.println("Please specify grpc, rest, or apiary");
      System.exit(1);
    }

    String type = arguments[0];

    if ("apiary".equals(type)) {
      runApiary();
    } else {
      runGapic(type);
    }
  }

  private static void runGapic(String type) throws IOException {
    long beforeClientCreation = System.currentTimeMillis();
    // Application Default Credentials
    ProjectsSettings.Builder projectsSettings =
        "rest".equals(type) ? ProjectsSettings.newHttpJsonBuilder() : ProjectsSettings.newBuilder();
    try (ProjectsClient client = ProjectsClient.create(projectsSettings.build())) {
      long timeTakenForCreation = System.currentTimeMillis() - beforeClientCreation;
      System.out.println(
          "client initialization ("
              + type
              + "): "
              + client
              + " took "
              + timeTakenForCreation
              + " ms.");

      long beforeRpcCall = System.currentTimeMillis();
      // Replace the project ID with the project you have access to
      Project project = client.getProject("projects/cloud-java-ci-sample");
      long timeTakenForRpcCall = System.currentTimeMillis() - beforeRpcCall;
      System.out.println(
          "client.getProject ("
              + type
              + ") returned "
              + project.getDisplayName()
              + " in "
              + timeTakenForRpcCall
              + " ms.");
    }
  }

  private static void runApiary() throws GeneralSecurityException, IOException {
    long beforeClientCreation = System.currentTimeMillis();

    NetHttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
    GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
    GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
    CloudResourceManager.Builder builder =
        new CloudResourceManager.Builder(
                transport, jsonFactory, new HttpCredentialsAdapter(credentials))
            .setApplicationName("gRPC and REST comparison");
    CloudResourceManager client = builder.build();
    Projects projects = client.projects();
    Get get = projects.get("projects/cloud-java-ci-sample");
    long timeTakenForCreation = System.currentTimeMillis() - beforeClientCreation;
    System.out.println(
        "client initialization (Apiary): " + client + " took " + timeTakenForCreation + " ms.");

    long beforeRpcCall = System.currentTimeMillis();
    com.google.api.services.cloudresourcemanager.v3.model.Project project = get.execute();
    long timeTakenForRpcCall = System.currentTimeMillis() - beforeRpcCall;
    System.out.println(
        "client.getProject (Apiary) returned "
            + project.getDisplayName()
            + " in "
            + timeTakenForRpcCall
            + " ms.");
  }
}
