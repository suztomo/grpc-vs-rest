package com.example;

import com.google.cloud.resourcemanager.v3.Project;
import com.google.cloud.resourcemanager.v3.ProjectsClient;
import com.google.cloud.resourcemanager.v3.ProjectsSettings;

public class Main {

  /**
   * Runs client initialization and one RPC call via ProjectsClient.
   *
   * @param arguments "rest" to use HTTP backend; otherwise it uses gRPC backend.
   */
  public static void main(String[] arguments) throws Exception {
    Thread.sleep(2000);
    long beforeClientCreation = System.currentTimeMillis();
    // Application Default Credentials
    ProjectsSettings.Builder projectsSettings =  (arguments.length == 1 && "rest".equals(arguments[0])) ?
        ProjectsSettings.newHttpJsonBuilder() : ProjectsSettings.newBuilder();
    try (ProjectsClient client = ProjectsClient.create(projectsSettings.build())) {
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
