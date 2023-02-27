Feature: Google Search

  Scenario Outline: Search Functionality of Google
    Given User Launch Browser
    And Opens URL "https://www.google.com/"
    When User Enters Text "<SearchText>" into the Search Field
    And Click on Search Button
    Then Verify the Title "<SearchText>"

    Examples: 
      | SearchText |
      | India      |
      | USA        |
      | Europe     |