package com.example.myapplication.UnitTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.example.myapplication.SpecificTranslator;
import com.example.myapplication.TranslatorData;

import net.suuft.libretranslate.Language;

import org.junit.Test;

import java.util.ArrayList;

public class TraductorTest {

    @Test
    public void translatorCallTest() {
        SpecificTranslator translator = mock(SpecificTranslator.class);
        translator.translate(Language.ENGLISH, Language.SPANISH, "example");
        verify(translator).translate(any(Language.class), any(Language.class), any(String.class));
    }

    @Test
    public void translatorCorrectCallTest() {
        SpecificTranslator translator = mock(SpecificTranslator.class);
        translator.translate(Language.ENGLISH, Language.SPANISH, "Hello");
        verify(translator).translate(eq(Language.ENGLISH), eq(Language.SPANISH), eq("Hello"));
    }

    @Test
    public void translatorTest() {
        SpecificTranslator translator = new SpecificTranslator();
        String translatedText = translator.translate(Language.ENGLISH, Language.SPANISH, "hello");
        assertNotNull(translatedText);
        assertEquals("hola", translatedText);
    }

    @Test
    public void translatorNullTextTest() {
        SpecificTranslator translator = new SpecificTranslator();
        String translatedText = translator.translate(Language.ENGLISH, Language.SPANISH, null);
        assertEquals("", translatedText);
    }

    @Test
    public void translatorHistoryTest() {
        SpecificTranslator translator = mock(SpecificTranslator.class);
        ArrayList<TranslatorData> emptyHistory = translator.getHistory();
        translator.translate(Language.ENGLISH, Language.SPANISH, "example");
        ArrayList<TranslatorData> history = translator.getHistory();
        assertTrue(emptyHistory.isEmpty());
        assertNotNull(history);
    }

}
