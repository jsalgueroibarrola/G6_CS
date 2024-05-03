package com.example.myapplication.IntegrationTesting;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ImageButtonTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testImageButtonFunctions() {
        Espresso.onView(ViewMatchers.withId(R.id.multiText1)).perform(ViewActions.typeText("Hello"));

        Espresso.onView(ViewMatchers.withId(R.id.spinner_origin_lang)).perform(ViewActions.click());
        Espresso.onData(withText("English")).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.spinner_destination_lang)).perform(ViewActions.click());
        Espresso.onData(withText("Spanish")).perform(ViewActions.click());

        Espresso.onView(withId(R.id.buttonTrad)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.imageButton)).check(matches(isEnabled()));
        Espresso.onView(withId(R.id.imageButton)).check(matches(isCompletelyDisplayed()));

        Espresso.onView(withId(R.id.imageButton)).perform(ViewActions.click());

        ClipboardManager clipboard = (ClipboardManager) activityScenarioRule.getScenario().getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
        assertNotNull(clipboard);
        ClipData clipData = clipboard.getPrimaryClip();
        assertNotNull(clipData);
        assertNotNull(clipData.getItemAt(0).getText());
        assertEquals("Hola", clipData.getItemAt(0).getText().toString());

        //Comprueba que el botón esté habilitado
        Espresso.onView(withId(R.id.imageButton)).check(matches(isEnabled()));
        //Vuelve a pulsar el botón
        Espresso.onView(withId(R.id.imageButton)).perform(ViewActions.click());
        //Vuelve a comprobar que el botón esté habilitado
        Espresso.onView(withId(R.id.imageButton)).check(matches(isEnabled()));
    }
}
