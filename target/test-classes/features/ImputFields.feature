@tag
Feature: Perform simple tests

  @PerformSimpleTests
  Scenario: Input Fields verify
    #Given Start Driver
    Given I am on Input Fields page
    And I am entering field
    When I click on show message button
    Then I validate the message
    #And I am entering the two fields
    #When I click on Get Total button
    #Then I validate the total
 #		Then Quit Driver
    

    
  