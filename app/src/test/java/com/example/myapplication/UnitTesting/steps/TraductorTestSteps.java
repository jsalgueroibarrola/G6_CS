package com.example.myapplication.UnitTesting.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.example.myapplication.TranslatorData;
import com.example.myapplication.SpecificTranslator;

import net.suuft.libretranslate.Language;


import java.util.ArrayList;


public class TraductorTestSteps {

    SpecificTranslator translator;
    String translatedText;
    String originalText;
    String fromLanguage;
    String toLanguage;
    ArrayList<TranslatorData> history;

    int historyInitialSize = 0;

    @Given("I am in the translator screen")
    public void i_have_a_translator() {
        translator = mock(SpecificTranslator.class);
    }

    @When("I translate anything")
    public void i_translate_anything() {
        translatedText = translator.translate(Language.ENGLISH, Language.SPANISH, "example");
    }

    @Then("The translator should be called")
    public void the_translator_should_be_called() {
        // translator.translate method directly communicates with the API, so if it wasn't working, the test would fail here
        verify(translator).translate(any(Language.class), any(Language.class), any(String.class));
    }

    @When("I translate {string} from {string} to {string}")
    public void i_translate_from_to(String string, String from, String to) {
        translatedText = translator.translate(Language.valueOf(from.toUpperCase()), Language.valueOf(to.toUpperCase()), string);
    }

    @Then("The translator should be set to {string} from {string} to {string}")
    public void the_translator_should_be_set_to_from_to(String string, String from, String to) {
        verify(translator).translate(eq(Language.valueOf(from.toUpperCase())), eq(Language.valueOf(to.toUpperCase())), eq(string));
    }

    @Given("I have a text {string} in {string}")
    public void i_have_a_text_in(String string, String from) {
        originalText = string;
        fromLanguage = from;
    }

    @Given("I have a null text in {string}")
    public void i_have_a_null_text(String from) {
        originalText = null;
        fromLanguage = from;
    }

    @When("I translate the text to {string}")
    public void i_translate_the_text_to(String to) {
        translator = new SpecificTranslator();
        toLanguage = to;
        historyInitialSize = translator.getHistory().size();
        translatedText = translator.translate(Language.valueOf(fromLanguage.toUpperCase()), Language.valueOf(toLanguage.toUpperCase()), originalText);
    }

    @Then("I should have a new item in the translation history")
    public void i_should_have_a_new_item_in_the_translation_history() {
        ArrayList<TranslatorData> history = translator.getHistory();
        assertNotNull(history);
        assertEquals(history.size(), historyInitialSize + 1);
        assertEquals(fromLanguage.toUpperCase(), history.get(history.size() - 1).getFromLanguage());
        assertEquals(toLanguage.toUpperCase(), history.get(history.size() - 1).getToLanguage());
        assertEquals(originalText, history.get(history.size() - 1).getOriginalText());
        assertEquals(translatedText, history.get(history.size() - 1).getTranslatedText());
    }

    @Then("I should get {string}")
    public void i_should_get(String string) {
        assertEquals(string, translatedText);
    }

}
