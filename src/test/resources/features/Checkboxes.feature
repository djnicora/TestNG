@tag
Feature: Checkboxes
  I want to use this template for my feature file

  @CheckboxesTest
  Scenario: Handle new window
  #Given Start Driver
    Given I am on browser-windows page
    And Open new child window within the main window
    And Get handles of the windows
    #Then Quit Driver