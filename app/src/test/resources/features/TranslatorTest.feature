Feature: Translator test
  This is the real translator test, it will test the translation functionality.

  Scenario: Translate a text
    Given I have a text "hello" in "English"
    When I translate the text to "Spanish"
    Then I should get "hola"

  Scenario: Translate a null text
    Given I have a null text in "English"
    When I translate the text to "Spanish"
    Then I should get ""
