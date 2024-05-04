package com.example.myapplication.UnitTesting.steps;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.example.myapplication.TranslatorData;

public class DatosTraductorTestSteps {
    private String originLanguage;
    private String destinationLanguage;
    private String originalText;
    private String translatedText;

    @Given("I have a word {string}")
    public void i_have_a_word(String string) {
        originalText = string;
    }

    @When("I translate it to spanish")
    public void i_translate_it_from_to_spanish() {
        originLanguage = "English";
        destinationLanguage = "Spanish";
    }

    @Then("I should see {string}")
    public void i_should_see(String string) {
        translatedText = string;
        TranslatorData translatorData = new TranslatorData(originLanguage, destinationLanguage, originalText, translatedText);
        assertEquals(originLanguage, translatorData.getFromLanguage());
        assertEquals(destinationLanguage, translatorData.getToLanguage());
        assertEquals(originalText, translatorData.getOriginalText());
        assertEquals(translatedText, translatorData.getTranslatedText());
    }

    @Given("I have a language selected {string}")
    public void i_have_a_language_selected(String string) {
        originLanguage = string;
    }

    @When("I translate to any language")
    public void i_translate_to_any_language() {
        destinationLanguage = "Spanish";
        originalText = "Hello";
        translatedText = "Hola";
    }

    @Then("The data stored should have its destination language as {string}")
    public void the_data_stored_should_have_its_destination_language_as(String string) {
        TranslatorData translatorData = new TranslatorData(originLanguage, destinationLanguage, originalText, translatedText);
        assertEquals(string, translatorData.getToLanguage());
    }

    @Then("The data stored should have its source language as {string}")
    public void the_data_stored_should_have_its_source_language_as(String string) {
        TranslatorData translatorData = new TranslatorData(originLanguage, destinationLanguage, originalText, translatedText);
        assertEquals(string, translatorData.getFromLanguage());
    }

    @Then("The data stored should have its original text as {string}")
    public void the_data_stored_should_have_its_original_text_as(String string) {
        TranslatorData translatorData = new TranslatorData(originLanguage, destinationLanguage, originalText, translatedText);
        assertEquals(string, translatorData.getOriginalText());
    }

    @Then("The data stored should have its translated text as {string}")
    public void the_data_stored_should_have_its_translated_text_as(String string) {
        TranslatorData translatorData = new TranslatorData(originLanguage, destinationLanguage, originalText, "hola");
        assertEquals(string, translatorData.getTranslatedText());
    }


}
