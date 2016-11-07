package info.androidhive.firebase.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import info.androidhive.firebase.Authorized.MenuActivity;
import info.androidhive.firebase.Database.DefaultDataProviderComponent;
import info.androidhive.firebase.Database.FirebaseConfigURL;
import info.androidhive.firebase.Database.Listiners.OnGetBaseListListener;
import info.androidhive.firebase.R;

public class DevicesListActivity extends BaseActivity implements OnGetBaseListListener, ListView.OnItemClickListener {
    private ListView LvDevicesList;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        DefaultDataProviderComponent.getInstance().registerListenerGetBaseList(this);
        DefaultDataProviderComponent.getInstance().getBaseList(FirebaseConfigURL.FIREBASE_URL_DEVICES);
        progressBar = (ProgressBar) findViewById(R.id.progressBar4);
        progressBar.setVisibility(View.VISIBLE);

    }


    @Override
    public void onResponseGetBaseListSuccess(ArrayList devicesList) {
        DefaultDataProviderComponent.getInstance().unregisterListenerGetBaseList(this);
        LvDevicesList = (ListView) findViewById(R.id.devicesListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, devicesList);
        LvDevicesList.setAdapter(arrayAdapter);
        progressBar.setVisibility(View.GONE);
        LvDevicesList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String value = LvDevicesList.getItemAtPosition(position).toString();
        if (id == position) {
            Intent deviceDetailsListIntent = new Intent(this, DevicesDetailsListActivity.class);
            deviceDetailsListIntent.putExtra(getString(R.string.intent_device_name), value);
            startActivity(deviceDetailsListIntent);
        }
    }

    @Override
    public void onResponseGetBaseListFail() {
        Toast.makeText(getBaseContext(), getString(R.string.tst_fail_response_get_devices_list),
                Toast.LENGTH_SHORT).show();
        DefaultDataProviderComponent.getInstance().unregisterListenerGetBaseList(this);
    }
}
