package info.androidhive.firebase.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.Firebase;

import java.util.ArrayList;

import info.androidhive.firebase.Database.Listiners.OnGetEmployedListDetailsListener;
import info.androidhive.firebase.Database.FirebaseConfigURL;
import info.androidhive.firebase.Database.DefaultDataProviderComponent;
import info.androidhive.firebase.R;
import info.androidhive.firebase.Utils.Utils;

public class EmployeesDetailsListActivity extends BaseActivity implements ListView.OnItemClickListener, OnGetEmployedListDetailsListener {

    public static void setValue(String value) {
        EmployeesDetailsListActivity.value = value;
    }

    private static String value;
    private ListView EmployeesListDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_find_user_value);
        DefaultDataProviderComponent.getInstance().registerListenerGetEmployedDetailsList(this);
        DefaultDataProviderComponent.getInstance().getEmployedListDetail(FirebaseConfigURL.FIREBAE_URL_EMLOYEE_DETAIL_PART_1, FirebaseConfigURL.FIREBAE_URL_EMLOYEE_DETAIL_PART_2, value);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    @Override
    public void onResponseGetEmployedDetailsListSuccess(ArrayList employedDetailsLis) {
        EmployeesListDetails = (ListView) findViewById(R.id.mainListView2);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, employedDetailsLis);
        EmployeesListDetails.setAdapter(arrayAdapter);
        DefaultDataProviderComponent.getInstance().unregisterListenerGetEmployedDetailsList(this);
        EmployeesListDetails.setOnItemClickListener(this);


    }

    @Override
    public void onResponseGetEmployedDetailsListFail() {
        DefaultDataProviderComponent.getInstance().unregisterListenerGetEmployedDetailsList(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            switch ((int) id) {
                case 1:
                    String email = EmployeesListDetails.getItemAtPosition(1).toString();
                    Utils.StartEmailActivity(this, email, "Test", "Dziala!");
                    break;
                case 3:
                    String skype = EmployeesListDetails.getItemAtPosition(3).toString();
                    Utils.startSkypeActivity(this, skype);
                    break;
                case 5:
                    String phone = EmployeesListDetails.getItemAtPosition(5).toString();
                    Utils.startPhoneActivity(this, phone);
                    break;
            }
        } catch (ActivityNotFoundException a) {
            Snackbar.make(view, R.string.sbInstallPrograms, Snackbar.LENGTH_SHORT).show();
        }


    }
}
