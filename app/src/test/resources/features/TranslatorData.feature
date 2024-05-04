Feature: Translator data test.
  This is mainly for registering translations in the history.
  This does not test the translator functionality.

  Scenario: Store translation data from english to spanish
    Given I have a word "Hello"
    When I translate it to spanish
    Then I should see "hola"

  Scenario: Set translation data to Spanish
    Given I have a language selected "Spanish"
    When I translate to any language
    Then The data stored should have its destination language as "Spanish"


  Scenario: Set translation data from English
    Given I have a language selected "English"
    When I translate to any language
    Then The data stored should have its source language as "English"

  Scenario: set translation data original text
    Given I have a word "Hello"
    When I translate it to spanish
    Then The data stored should have its original text as "Hello"

  Scenario: set translation data translated text
    Given I have a word "Hello"
    When I translate it to spanish
    Then The data stored should have its translated text as "hola"