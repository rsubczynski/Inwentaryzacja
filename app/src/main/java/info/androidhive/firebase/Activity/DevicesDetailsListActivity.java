package info.androidhive.firebase.Activity;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

import info.androidhive.firebase.Database.Listiners.OnGetDeviceDetailsListListener;

import info.androidhive.firebase.Database.FirebaseConfigURL;
import info.androidhive.firebase.Database.DefaultDataProviderComponent;
import info.androidhive.firebase.R;


public class DevicesDetailsListActivity extends BaseActivity implements OnGetDeviceDetailsListListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user);
        String deviceName = getIntent().getExtras().getString(getString(R.string.intent_device_name));
        DefaultDataProviderComponent.getInstance().registerListenerGetDeviceDetailsList(this);
        DefaultDataProviderComponent.getInstance().getDeviceDetailsList(deviceName, FirebaseConfigURL.FIREBASE_URL_DEVICES_DETAILS);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onResponseGetDevicesDetailsListSuccess(ArrayList devicesList) {
        ListView LVEmployeesList = (ListView) findViewById(R.id.lv_employess_list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, devicesList);
        LVEmployeesList.setAdapter(arrayAdapter);
        DefaultDataProviderComponent.getInstance().unregisterListenerGetDeviceDetailsList(this);
    }

    @Override
    public void onResponseGetDevicesDetailsListFail() {
        Toast.makeText(getBaseContext(), getString(R.string.tst_fail_response_get_devices_employed_list),
                Toast.LENGTH_SHORT).show();
        DefaultDataProviderComponent.getInstance().unregisterListenerGetDeviceDetailsList(this);
    }
}

