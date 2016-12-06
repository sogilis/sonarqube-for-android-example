/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sogilis.example.android.app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sogilis.example.android.library.Computation;

public class MainActivity extends Activity {

    private Button computeButton;
    private TextView resultTextView;

    private BroadcastReceiver resultReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        computeButton = (Button) findViewById(R.id.compute_button);
        resultTextView = (TextView) findViewById(R.id.result_text);

        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Computation.class);
                startService(intent);
            }
        });

        resultReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int result = intent.getIntExtra(Computation.RESULT_VALUE_INTENT_EXTRA_KEY, 0);
                resultTextView.setText(ResultSerializer.serialize(result));
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(Computation.RESULT_INTENT_ACTION);
        registerReceiver(resultReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        if (resultReceiver != null) {
            unregisterReceiver(resultReceiver);
            resultReceiver = null;
        }
        super.onDestroy();
    }

}