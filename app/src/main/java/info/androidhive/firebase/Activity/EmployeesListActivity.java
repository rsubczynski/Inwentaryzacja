package info.androidhive.firebase.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.ArrayList;


import info.androidhive.firebase.Database.Listiners.OnGetBaseListListener;
import info.androidhive.firebase.Database.Listiners.OnSearchStringValueListener;

import info.androidhive.firebase.Database.FirebaseConfigURL;
import info.androidhive.firebase.Database.DefaultDataProviderComponent;
import info.androidhive.firebase.Utils.Utils;
import info.androidhive.firebase.R;

public class EmployeesListActivity extends BaseActivity implements ListView.OnItemClickListener, View.OnClickListener, OnSearchStringValueListener, OnGetBaseListListener, View.OnTouchListener {
    private ListView LvEmployeesList;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employess_list_view_adapter);
        DefaultDataProviderComponent.getInstance().registerListenerGetBaseList(this);
        DefaultDataProviderComponent.getInstance().getBaseList(FirebaseConfigURL.FIRESAFE_URL_EMPLOYEE);
        Firebase.setAndroidContext(this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        View v = findViewById(R.id.activity_employess_list_view_adapter);
        v.setOnTouchListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((ProgressBar) findViewById(R.id.progressBar2))
                .getIndeterminateDrawable()
                .setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
    }


    @Override
    public void onResponseGetBaseListSuccess(ArrayList Employees) {
        progressBar.setVisibility(View.GONE);
        LvEmployeesList = (ListView) findViewById(R.id.mainListView1);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, Employees);
        LvEmployeesList.setAdapter(arrayAdapter);
        DefaultDataProviderComponent.getInstance().unregisterListenerGetBaseList(this);
        Button BtnSearchUser = (Button) findViewById(R.id.btn_search_user);
        BtnSearchUser.setOnClickListener(this);
        LvEmployeesList.setOnItemClickListener(this);
        View v = findViewById(R.id.activity_employess_list_view_adapter);
        v.setOnTouchListener(this);


    }

    @Override
    public void onResponseGetBaseListFail() {
        Toast.makeText(getBaseContext(), getString(R.string.tst_fail_response_get_devices_employed_list),
                Toast.LENGTH_SHORT).show();
        DefaultDataProviderComponent.getInstance().unregisterListenerGetBaseList(this);
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String value = LvEmployeesList.getItemAtPosition(position).toString();
        if (id == position) {
            EmployeesDetailsListActivity.setValue(value);
            Intent EmployeesDetailsListIntent = new Intent(this, EmployeesDetailsListActivity.class);
            startActivity(EmployeesDetailsListIntent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search_user:
                DefaultDataProviderComponent.getInstance().registerListenerSearchUser(this);
                EditText editText = (EditText) findViewById(R.id.et_search_items);
                String searchUser = editText.getText().toString().trim();
                DefaultDataProviderComponent.getInstance().searchString(searchUser, FirebaseConfigURL.FIRESAFE_URL_EMPLOYEE);
        }
    }

    @Override
    public void onResponseSuccessSearchString(String searchString) {
        EmployeesDetailsListActivity.setValue(searchString);
        DefaultDataProviderComponent.getInstance().unregisterListenerSearchUser(this);
        Intent EmployeesDetailsListIntent = new Intent(this, EmployeesDetailsListActivity.class);
        startActivity(EmployeesDetailsListIntent);
    }

    @Override
    public void onResponseFailSearchString() {
        DefaultDataProviderComponent.getInstance().unregisterListenerSearchUser(this);
        Toast.makeText(getBaseContext(), getString(R.string.tst_fail_response_get_search_user_fail),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Utils.hideSoftKeyboard(EmployeesListActivity.this);
        return false;
    }

}


