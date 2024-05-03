package com.example.myapplication.UnitTesting;

import static org.junit.Assert.assertEquals;

import com.example.myapplication.TranslatorData;

import org.junit.Test;

public class DatosTraductorTest {

    @Test
    public void translatorData() {
        String fromLanguage = "English";
        String toLanguage = "Spanish";
        String originalText = "Hello";
        String translatedText = "Hola";
        TranslatorData translatorData = new TranslatorData(fromLanguage, toLanguage, originalText, translatedText);
        assertEquals(fromLanguage, translatorData.getFromLanguage());
        assertEquals(toLanguage, translatorData.getToLanguage());
        assertEquals(originalText, translatorData.getOriginalText());
        assertEquals(translatedText, translatorData.getTranslatedText());
    }

    @Test
    public void translatorDataToLanguage() {
        String toLanguage = "Spanish";
        TranslatorData translatorData = new TranslatorData("English", toLanguage, "Hello", "Hola");
        String result = translatorData.getToLanguage();
        assertEquals(toLanguage, result);
    }

    @Test
    public void translatorDataFromLanguage() {
        String fromLanguage = "English";
        TranslatorData translatorData = new TranslatorData(fromLanguage, "Spanish", "Hello", "Hola");
        String result = translatorData.getFromLanguage();
        assertEquals(fromLanguage, result);
    }

    @Test
    public void translatorDataOriginalText() {
        String originalText = "Hello";
        TranslatorData translatorData = new TranslatorData("English", "Spanish", originalText, "Hola");
        String result = translatorData.getOriginalText();
        assertEquals(originalText, result);
    }

    @Test
    public void translatorDataTranslatedText() {
        String translatedText = "Hola";
        TranslatorData translatorData = new TranslatorData("English", "Spanish", "Hello", translatedText);
        String result = translatorData.getTranslatedText();
        assertEquals(translatedText, result);
    }
}
