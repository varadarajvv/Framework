Feature: Google Search

  Scenario Outline: Search Functionality of Google
    Given User Launch Browser
    And Opens URL "https://www.google.com/"
    When User Performs Search by Entering Text "<SearchText>" and Verify Title

    Examples: 
      | SearchText |
      |          1 |
      |          2 |
      |          3 |