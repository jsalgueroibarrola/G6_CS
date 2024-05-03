package com.example.myapplication.AIUnitTesting;

import static org.junit.Assert.assertEquals;

import com.example.myapplication.TranslatorData;

import org.junit.Test;

public class TranslatorDataInitializationTest {
    @Test
    public void testTranslatorDataInitialization() {
        TranslatorData data = new TranslatorData("Inglés", "Español", "Hola", "Hello");
        assertEquals("Inglés", data.getFromLanguage());
        assertEquals("Español", data.getToLanguage());
        assertEquals("Hola", data.getOriginalText());
        assertEquals("Hello", data.getTranslatedText());
    }

}
