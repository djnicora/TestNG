@tag
Feature: Window Managment
  Handle Window Managment

  @HandleNewWindow
  Scenario: Handle new window
  #Given Start Driver
    Given I am on browser-windows page
    And Open new child window within the main window
    And Get handles of the windows
    #Then Quit Driver

  @HandleMultipleWindows
  Scenario: Handle multiple windows in Selenium
  #Given Start Driver
    Given I am on browser-windows page
    And Opening all the child window
    Then To handle all new opened window
    #Then Quit Driver

  @SwitchBackToTheParentWindowTest
  Scenario: Switch back to the parent window
  #Given Start Driver
    Given I am on browser-windows page
    And Open new child window within the main window
    And Closed Child window
    When Switch back to the main window which is the parent window
    #Then Quit Driver