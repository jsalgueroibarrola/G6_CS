package com.example.myapplication.IntegrationTesting;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import org.junit.Rule;
import org.junit.Test;

public class TranslateButtonTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testTranslateButton() {
        Espresso.onView(ViewMatchers.withId(R.id.multiText1)).perform(ViewActions.typeText("Hola"));

        //Spinners
        Espresso.onView(ViewMatchers.withId(R.id.spinner_origin_lang)).perform(ViewActions.click());
        Espresso.onData(withText("Spanish")).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.spinner_destination_lang)).perform(ViewActions.click());
        Espresso.onData(withText("English")).perform(ViewActions.click());

        //Botón de Traducción
        Espresso.onView(ViewMatchers.withId(R.id.buttonTrad)).check(matches(isEnabled()));
        Espresso.onView(withId(R.id.buttonTrad)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.multiText2)).check(matches(withText("Hello")));
    }
}