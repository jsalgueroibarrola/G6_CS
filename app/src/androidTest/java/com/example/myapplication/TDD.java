package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.fail;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TDD {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testTranslationAndCheckHistory() {
        String text1 = "Hello";
        String expectedTranslation1 = "hola";
        String text2 = "World";
        String expectedTranslation2 = "mundo";

        onView(withId(R.id.multiText1)).perform(replaceText(text1));
        onView(withId(R.id.buttonTrad)).perform(click());
        onView(withId(R.id.multiText2)).check(matches(withText(expectedTranslation1)));

        onView(withId(R.id.multiText1)).perform(replaceText(text2));
        onView(withId(R.id.buttonTrad)).perform(click());
        onView(withId(R.id.multiText2)).check(matches(withText(expectedTranslation2)));

        onView(withId(R.id.historial)).perform(click());
        onView(withId(R.id.secondFragment)).check(matches(isDisplayed()));
        onView(withText(expectedTranslation1)).check(matches(isDisplayed()));
        onView(withText(expectedTranslation2)).check(matches(isDisplayed()));

        onView(withId(R.id.btnAllDelete)).perform(click());
        assertTextIsNotDisplayed(expectedTranslation1);
        assertTextIsNotDisplayed(expectedTranslation2);
    }

    public void assertTextIsNotDisplayed(String text) {
        try {
            onView(withText(text)).check(matches(isDisplayed()));
            // Si no se lanza la excepción, el texto está presente, por lo que el test debería fallar
            fail("Text '" + text + "' is displayed, but it shouldn't be.");
        } catch (NoMatchingViewException e) {
            // Si se lanza la excepción, el texto no está presente, como se esperaba
        }
    }
}