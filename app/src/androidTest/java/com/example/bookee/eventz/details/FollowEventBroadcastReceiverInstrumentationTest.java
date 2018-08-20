package com.example.bookee.eventz.details;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FollowEventBroadcastReceiverInstrumentationTest {
    private static final String EVENT_ID_KEY = "id";
    private static final String EVENT_NAME_KEY = "name";
    private static final String TEST_EVENT_ID="45097691409";
    private static final String TEST_EVENT_NAME="Migrating to Australia - Seminars in Belgrade - Serbia";

    private static final int TIMEOUT = 1000;
    private FollowEventBroadcastReceiver receiver;
    private UiDevice device;

    //I needed this test activity so that my database would be created
    @Rule
    public ActivityTestRule<DetailsActivity> testRule=new ActivityTestRule<>(DetailsActivity.class,true,false);

    @Before
    public void setUp() {
        receiver = new FollowEventBroadcastReceiver();
        device=UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    }

    @Test
    public void onBroadcastReceivedShowNotification() throws InterruptedException {
        //Given
        // progress bar in DetailsActivity can bug test on API-s below 26

        testRule.launchActivity(new Intent().putExtra(DetailsActivity.EXTRA_EVENT_ID,TEST_EVENT_ID));
        Intent testIntent = new Intent();
        testIntent.putExtra(EVENT_ID_KEY, TEST_EVENT_ID);
        testIntent.putExtra(EVENT_NAME_KEY, TEST_EVENT_NAME);

        //When
        receiver.onReceive(InstrumentationRegistry.getTargetContext(), testIntent);
        //Then
        device.openNotification();
        device.wait(Until.hasObject(By.text(TEST_EVENT_NAME)),TIMEOUT);
        UiObject2 notificationTitle=device.findObject(By.text(TEST_EVENT_NAME));
        Assert.assertEquals(notificationTitle.getText(),TEST_EVENT_NAME);
        Thread.sleep(10000);
    }
}