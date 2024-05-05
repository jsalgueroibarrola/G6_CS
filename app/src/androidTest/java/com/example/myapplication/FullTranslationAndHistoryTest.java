package com.example.myapplication;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FullTranslationAndHistoryTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testTranslationAndCheckHistory() {
        String originalText = "Hello";
        String expectedTranslation = "hola";
        onView(withId(R.id.multiText1)).perform(replaceText(originalText));
        onView(withId(R.id.buttonTrad)).perform(click());
        onView(withId(R.id.multiText2)).check(matches(withText(expectedTranslation)));
        onView(withId(R.id.historial)).perform(click());
        onView(withId(R.id.secondFragment)).check(matches(isDisplayed()));
        onView(withText(expectedTranslation)).check(matches(isDisplayed()));
    }
}
