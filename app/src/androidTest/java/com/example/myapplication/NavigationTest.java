package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static androidx.test.espresso.assertion.ViewAssertions.matches;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class NavigationTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void navigateToSecondActivity() {
        onView(withId(R.id.historial)).perform(click());
        onView(withId(R.id.secondFragment)).check(matches(isDisplayed()));
        onView(withId(R.id.ajustes)).perform(click());
        onView(withId(R.id.settings_fragment)).check(matches(isDisplayed()));
        onView(withId(R.id.traductor)).perform(click());
        onView(withId(R.id.translator_fragment)).check(matches(isDisplayed()));
    }
}
