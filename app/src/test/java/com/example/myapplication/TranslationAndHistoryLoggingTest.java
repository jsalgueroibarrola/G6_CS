package com.example.myapplication;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import net.suuft.libretranslate.Language;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TranslationAndHistoryLoggingTest {
    @Test
    public void testTranslationAndHistoryLogging() {
        SpecificTranslator translator = new SpecificTranslator();
        // Corregimos los idiomas: Traducir "hello" del inglés al español.
        translator.translate(Language.ENGLISH, Language.SPANISH, "hello");
        // Verifica que el historial no está vacío.
        assertFalse(translator.getHistory().isEmpty());
        // Verifica que el texto original es "hello".
        assertEquals("hello", translator.getHistory().get(0).getOriginalText());
        // Asumimos que "hello" se traduce a "hola" en español.
        assertEquals("hola", translator.getHistory().get(0).getTranslatedText());
    }
}

