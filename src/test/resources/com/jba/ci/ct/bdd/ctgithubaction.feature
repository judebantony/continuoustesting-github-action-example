@Smoke
Feature: Test Health Check Rest API
  Scenario: Health Check Api call is successful 

    Given user wants to call health check api
    When user call health check api with the url "http://20.81.93.165/api/v1"
    Then return result
