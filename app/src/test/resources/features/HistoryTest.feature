Feature: Translation history.

  Scenario: Translate a text and see the translation history
    Given I have a text "hello" in "English"
    When I translate the text to "Spanish"
    Then I should have a new item in the translation history