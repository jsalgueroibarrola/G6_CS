Feature: Translator test (API connection and data sharing test)

  Scenario: Initialize translator and check if API returns data
    Given I am in the translator screen
    When I translate anything
    Then The translator should be called

  Scenario: Initialize translator and check if stores configuration
    Given I am in the translator screen
    When I translate "Hello" from "English" to "Spanish"
    Then The translator should be set to "Hello" from "English" to "Spanish"
