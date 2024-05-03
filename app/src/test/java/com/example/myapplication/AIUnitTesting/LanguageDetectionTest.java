package com.example.myapplication.AIUnitTesting;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.example.myapplication.LanguageDetection;
import com.github.pemistahl.lingua.api.Language;

import org.junit.Before;
import org.junit.Test;

public class LanguageDetectionTest {

    private LanguageDetection defaultLanguageDetection;
    private LanguageDetection customLanguageDetection;
    private Language[] customLanguages = {Language.ENGLISH, Language.FRENCH};

    @Before
    public void setUp() {
        // Inicialización de objetos LanguageDetection
        defaultLanguageDetection = new LanguageDetection();
        customLanguageDetection = new LanguageDetection(customLanguages);
    }

    @Test
    public void testDefaultInitialization() {
        // Verificar que los idiomas predeterminados están correctamente establecidos
        assertArrayEquals(defaultLanguageDetection.defaultLanguages, defaultLanguageDetection.getLanguages());
    }

    @Test
    public void testCustomInitialization() {
        // Verificar que los idiomas personalizados están correctamente establecidos
        assertArrayEquals(customLanguages, customLanguageDetection.getLanguages());
    }

    @Test
    public void testLanguageDetectionAccuracy() {
        // Verificar la precisión de la detección del idioma para un texto en inglés
        String text = "Hello world!";
        assertEquals("ENGLISH", defaultLanguageDetection.detect(text));
    }

    @Test
    public void testUnknownLanguageDetection() {
        // Verificar que un texto en un idioma no soportado no se detecta incorrectamente
        String text = "これはテストです";  // Texto en japonés
        assertNotEquals("JAPANESE", customLanguageDetection.detect(text));  // customLanguageDetection solo tiene inglés y francés
    }

    @Test
    public void testLanguageDetectorNotNull() {
        // Verificar que el detector de idiomas no es nulo después de la inicialización
        assertNotNull(defaultLanguageDetection.detect("test"));
    }
}
