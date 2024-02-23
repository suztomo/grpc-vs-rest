package com.example;

import com.google.cloud.resourcemanager.v3.Project;
import com.google.cloud.resourcemanager.v3.ProjectsClient;
import com.google.cloud.resourcemanager.v3.ProjectsSettings;

public class Main {

  public static void main(String[] arguments) throws Exception {
    long beforeClientCreation = System.currentTimeMillis();
    // Application Default Credentials
    ProjectsSettings projectsSettings = ProjectsSettings.newHttpJsonBuilder().build();
    try (ProjectsClient client = ProjectsClient.create(projectsSettings)) {
      long timeTakenForCreation = System.currentTimeMillis() - beforeClientCreation;
      System.out.println(
          "client initialization: " + client + " took " + timeTakenForCreation + " ms.");

      long beforeRpcCall = System.currentTimeMillis();
      // Replace the project ID with the project you have access to
      Project project = client.getProject("projects/cloud-java-ci-sample");
      long timeTakenForRpcCall = System.currentTimeMillis() - beforeRpcCall;
      System.out.println(
          "client.getProject returned " + project.getDisplayName() + " in " + timeTakenForRpcCall
              + " ms.");
    }
  }

}
