Feature: Google Search

  Scenario: Search Functionality of Google
    Given User Launch Browser
    And Opens URL "https://www.google.com/"
    When User Enters Text "India" into the Search Field
    And Click on Search Button
    Then Verify the Title "India"
