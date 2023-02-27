Feature: Google Search

  Background: 
    Given User Launch Browser
    And Opens URL "https://www.google.com/"

  Scenario: Successful Search
    When User Enters Text "India" into the Search Field
    And Click on Search Button
    Then Verify the Title "India"

  Scenario: UnSuccessful Search
    When User Enters Text "India" into the Search Field
    And Click on Search Button
    Then Verify the Title "Bharat"