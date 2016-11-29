package com.sogilis.example.android.library;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowBroadcastReceiver;
import org.robolectric.util.ServiceController;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, shadows = {ShadowBroadcastReceiver.class})
public class ComputationTest {

    private ComputationWrapper service;
    private ServiceController<ComputationWrapper> controller;

    @Before
    public void setUp() {
        controller = Robolectric.buildService(ComputationWrapper.class);
        service = controller.attach().create().get();
    }

    @After
    public void tearDown() {
        controller.destroy();
    }

    @Test
    public void computation_should_send_intent_with_computer_result() {
        final List<Intent> receivedIntents = new ArrayList<>();
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                receivedIntents.add(intent);
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(Computation.RESULT_INTENT_ACTION);

        RuntimeEnvironment.application.registerReceiver(broadcastReceiver, filter);

        service.onHandleIntent(new Intent());

        assertEquals(1, receivedIntents.size());
        Intent intent = receivedIntents.get(0);
        assertTrue(intent.hasExtra(Computation.RESULT_VALUE_INTENT_EXTRA_KEY));
    }

    public static class ComputationWrapper extends Computation {
        @Override
        public void onHandleIntent(Intent intent) {
            super.onHandleIntent(intent);
        }
    }
}