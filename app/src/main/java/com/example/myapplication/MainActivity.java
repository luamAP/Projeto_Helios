package com.example.myapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;

import com.example.myapplication.R;

public class MainActivity extends Activity {


    // Variaveis em comum
    Button button;
    IntentFilter intentfilter;
    int batteryLevel;

    // Variaveis do GetBatteryInfoApp
    TextView textviewStatus;
    int deviceStatus;
    String currentBatteryStatus = "Battery Info";

    // Variaveis do CheckBatteryHealthApp
    TextView textviewHealth;
    int deviceHealth;
    String currentBatteryHealth = "Battery Health";

    // Variaveis do GetBatteryVoltageApp
    TextView textviewVoltage;
    int batteryVol;
    float fullVoltage;
    String currentBatteryVol = "Current Battery Voltage :";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.buttonBatteryStatus);
        textviewStatus = (TextView) findViewById(R.id.textViewBatteryStatus);
        textviewHealth = (TextView) findViewById(R.id.textViewBatteryHealth);
        textviewVoltage = (TextView) findViewById(R.id.textViewBatteryVol);

        intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.this.registerReceiver(broadcastreceiver, intentfilter);

            }
        });

    }

    private BroadcastReceiver broadcastreceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            // Instancia do GetBatteryVoltageApp
            batteryVol = (int)(intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,0));
            fullVoltage = (float) (batteryVol * 0.001);
            textviewVoltage.setText(currentBatteryVol +" "+fullVoltage+" volt");


            // Instancias do GetBatteryInfoApp
            deviceStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int batteryLevel=(int)(((float)level / (float)scale) * 100.0f);

            if(deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING){

                textviewStatus.setText(currentBatteryStatus+" = Charging at "+batteryLevel+" %");

            }

            if(deviceStatus == BatteryManager.BATTERY_STATUS_DISCHARGING){

                textviewStatus.setText(currentBatteryStatus+" = Discharging at "+batteryLevel+" %");

            }

            if (deviceStatus == BatteryManager.BATTERY_STATUS_FULL){

                textviewStatus.setText(currentBatteryStatus+" = Battery Full at "+batteryLevel+" %");

            }

            if(deviceStatus == BatteryManager.BATTERY_STATUS_UNKNOWN){

                textviewStatus.setText(currentBatteryStatus+" = Unknown at "+batteryLevel+" %");
            }


            if (deviceStatus == BatteryManager.BATTERY_STATUS_NOT_CHARGING){

                textviewStatus.setText(currentBatteryStatus+" = Not Charging at "+batteryLevel+" %");

            }

            //Instancia do CheckBatteryHealthApp
            deviceHealth = intent.getIntExtra(BatteryManager.EXTRA_HEALTH,0);

            if(deviceHealth == BatteryManager.BATTERY_HEALTH_COLD){

                textviewHealth.setText(currentBatteryHealth+" = Cold");
            }

            if(deviceHealth == BatteryManager.BATTERY_HEALTH_DEAD){

                textviewHealth.setText(currentBatteryHealth+" = Dead");
            }

            if (deviceHealth == BatteryManager.BATTERY_HEALTH_GOOD){

                textviewHealth.setText(currentBatteryHealth+" = Good");
            }

            if(deviceHealth == BatteryManager.BATTERY_HEALTH_OVERHEAT){

                textviewHealth.setText(currentBatteryHealth+" = OverHeat");
            }

            if (deviceHealth == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE){

                textviewHealth.setText(currentBatteryHealth+" = Over voltage");
            }

            if (deviceHealth == BatteryManager.BATTERY_HEALTH_UNKNOWN){

                textviewHealth.setText(currentBatteryHealth+" = Unknown");
            }
            if (deviceHealth == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE){

                textviewHealth.setText(currentBatteryHealth+" = Unspecified Failure");
            }


        }
    };

}
