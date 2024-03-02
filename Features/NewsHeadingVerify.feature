Feature: NewsVerify

  Scenario: Verification of the news
    Given The User is on the OneCognizant page
    When User checks all the news headings and Tip
    And Get the news heading and tip
    Then Compare news heading and tip
    And Print the Details on the console
