package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class MainActivity3Test {
    @Rule
    public ActivityScenarioRule<MainActivity> scenario = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void testScreenChange() {

        //Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        //click on the first city
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());
        //checks that the textview contained in the second screen is displaying on the phone which proves a screen change
        onView(withId(R.id.chosen_city)).check(matches(isDisplayed()));
    }

    @Test
    public void testNameMatch() {

        //Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        //click on the first city
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        //checks that the city displayed on the second screen  is the one we added and clicked on
        onView(withId(R.id.chosen_city)).check(matches((withText("Edmonton"))));
    }

    @Test
    public void testBackButton() {

        //Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        //click on the first city
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        //click on the back button
        onView(withId(R.id.back_button)).perform(click());
        //check that the add city button is showing which would mean we are back on the first screen
        onView(withId(R.id.button_add)).check(matches(isDisplayed()));
    }
}

