package info.androidhive.firebase.Authorized;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import info.androidhive.firebase.Activity.AddDevicesActivity;
import info.androidhive.firebase.Activity.AddUserActivity;
import info.androidhive.firebase.Activity.BaseActivity;
import info.androidhive.firebase.Activity.DevicesDetailsListActivity;
import info.androidhive.firebase.Activity.DevicesListActivity;
import info.androidhive.firebase.Activity.EmployeesListActivity;
import info.androidhive.firebase.Activity.SaveToFile;
import info.androidhive.firebase.Database.Listiners.OnSearchStringValueListener;

import info.androidhive.firebase.Database.FirebaseConfigURL;
import info.androidhive.firebase.Database.DefaultDataProviderComponent;
import info.androidhive.firebase.Utils.Utils;
import info.androidhive.firebase.R;
import info.androidhive.firebase.SimpleScannerActivity;

public class MenuActivity extends BaseActivity implements View.OnClickListener, OnSearchStringValueListener, View.OnTouchListener {

    SearchView searchView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_menu);
        Button btnCamera = (Button) findViewById(R.id.btn_camera);
        Button banEmployees = (Button) findViewById(R.id.btn_emproyess);
        Button banDevices = (Button) findViewById(R.id.btn_show_devices);
        banDevices.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
        banEmployees.setOnClickListener(this);
        ImageView image = (ImageView) findViewById(R.id.imageView2);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), (R.anim.anim_rotate));
        image.setAnimation(animation1);
        View v = findViewById(R.id.mainMenu);
        v.setOnTouchListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
//                Toast.makeText(getApplicationContext(),"textChanged :"+newText,Toast.LENGTH_LONG).show();
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                searchDevices(query.trim());
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);

        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_camera:
                Intent scannerIntent = new Intent(this, SimpleScannerActivity.class);
                startActivity(scannerIntent);
                break;
            case R.id.btn_emproyess:
                Intent employeesListActivityIntent = new Intent(this, EmployeesListActivity.class);
                startActivity(employeesListActivityIntent);
                break;
            case R.id.btn_show_devices:
                Intent devicesListActivityIntent = new Intent(this, DevicesListActivity.class);
                startActivity(devicesListActivityIntent);
                break;
        }

    }

    private void searchDevices(String query) {
        DefaultDataProviderComponent.getInstance().registerListenerSearchUser(this);
        DefaultDataProviderComponent.getInstance().searchString(query, FirebaseConfigURL.FIREBASE_URL_DEVICES);
    }

    @Override
    public void onResponseSuccessSearchString(String searchString) {
        DefaultDataProviderComponent.getInstance().unregisterListenerSearchUser(this);
        Intent intent1 = new Intent(this, DevicesDetailsListActivity.class);
        intent1.putExtra(getString(R.string.intent_device_name), searchString);
        startActivity(intent1);
    }

    @Override
    public void onResponseFailSearchString() {
        DefaultDataProviderComponent.getInstance().unregisterListenerSearchUser(this);
        Toast.makeText(getApplicationContext(), getString(R.string.fail_response_get_search_device_fail), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Utils.hideSoftKeyboard(this);
        return false;
    }

}
