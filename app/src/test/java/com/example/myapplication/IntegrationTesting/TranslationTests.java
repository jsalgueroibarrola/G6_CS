package com.example.myapplication.IntegrationTesting;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class TranslationTests {

    @Test
    public void testTranslation() {
        ActivityScenario.launch(MainActivity.class);

        //Seleccionar idiomas y meter texto
        Espresso.onView(ViewMatchers.withId(R.id.multiText1)).perform(ViewActions.typeText("Hola"));
        Espresso.onView(ViewMatchers.withId(R.id.spinner_origin_lang)).perform(ViewActions.click());
        Espresso.onData(ViewMatchers.withText("Spanish")).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.spinner_destination_lang)).perform(ViewActions.click());
        Espresso.onData(ViewMatchers.withText("English")).perform(ViewActions.click());

        //Botón Traducir
        Espresso.onView(ViewMatchers.withId(R.id.buttonTrad)).perform(ViewActions.click());

        //Mostrar por pantalla
        Espresso.onView(ViewMatchers.withId(R.id.multiText2)).check(matches(withText("Hello")));
    }
}
