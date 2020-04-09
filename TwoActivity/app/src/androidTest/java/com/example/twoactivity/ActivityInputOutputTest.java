package com.example.twoactivity;

import android.content.Context;

import androidx.annotation.ContentView;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ActivityInputOutputTest {
    @Rule
    public ActivityTestRule mActivityTestRule=new ActivityTestRule(MainActivity.class);
    @Test
    public void activityLaunch(){
        onView(withId(R.id.send_main)).perform(click());
        onView(withId(R.id.text_header_second)).check(matches(isDisplayed()));
        onView(withId(R.id.send_second)).perform(click());
        onView(withId(R.id.text_header_main)).check(matches(isDisplayed()));
    }
    @Test
    public void textInputOutput(){
        // test
        onView(withId(R.id.message_main)).perform(typeText("test this"));
        onView(withId(R.id.send_main)).perform(click());
        onView(withId(R.id.text_message_second)).check(matches(withText("test this")));
    }




    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.twoactivity", appContext.getPackageName());
    }
}
