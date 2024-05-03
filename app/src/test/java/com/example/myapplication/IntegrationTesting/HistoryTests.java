package com.example.myapplication.IntegrationTesting;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class HistoryTests {

    @Test
    public void testHistory() {
        ActivityScenario.launch(MainActivity.class);
        Espresso.onView(ViewMatchers.withId(R.id.multiText1)).perform(ViewActions.typeText("Hello"));
        Espresso.onView(ViewMatchers.withId(R.id.buttonTrad)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.historial)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.HistorialList)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
}
