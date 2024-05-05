package com.example.myapplication;

import net.suuft.libretranslate.Language;
import net.suuft.libretranslate.Translator;

import java.util.ArrayList;

public class SpecificTranslator {

    private final ArrayList<TranslatorData> history;

    public SpecificTranslator() {
        history = new ArrayList<>();
    }

    public String translate(Language fromLanguage, Language toLanguage, String text) {
        String translatedText;
        if (text == null || fromLanguage == null || toLanguage == null) {
            return "";
        }
        if (fromLanguage.equals(Language.NONE)) {
            translatedText = Translator.translate(toLanguage, text);
        } else {
            translatedText = Translator.translate(fromLanguage, toLanguage, text);
        }
        //Save to history
        TranslatorData translatorData = new TranslatorData(fromLanguage.toString(), toLanguage.toString(), text, translatedText);
        history.add(translatorData);
        return translatedText;
    }

    public ArrayList<TranslatorData> getHistory() {
        return history;
    }

    public void clearHistory() {
        history.clear();
    }
}
