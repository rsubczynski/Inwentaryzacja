package info.androidhive.firebase.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import info.androidhive.firebase.Database.DefaultDataProviderComponent;
import info.androidhive.firebase.Database.FirebaseConfigURL;
import info.androidhive.firebase.Database.Listiners.OnAddDeviceListener;
import info.androidhive.firebase.Database.Listiners.OnGetBaseListListener;
import info.androidhive.firebase.Object.Devices;
import info.androidhive.firebase.R;
import info.androidhive.firebase.Utils.Utils;

public class AddDevicesActivity extends BaseActivity implements OnGetBaseListListener, View.OnTouchListener, View.OnClickListener, OnAddDeviceListener {


    private EditText EtInputDeviceName;
    private EditText EtInputStocktakingData;
    private EditText EtInputLocation;
    private EditText EtInputStocktakingPerson;
    private EditText EtInputMark;
    private EditText EtInputModel;
    private EditText EtInputSerialNumber;
    private EditText EtInputTypeDevice;
    private Spinner SpInputListEmployees;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_devices);
        DefaultDataProviderComponent.getInstance().registerListenerGetBaseList(this);
        DefaultDataProviderComponent.getInstance().getBaseList(FirebaseConfigURL.FIRESAFE_URL_EMPLOYEE);
        View v = findViewById(R.id.add_device_activity);
        EtInputDeviceName = (EditText) findViewById(R.id.et_Device_name);
        EtInputStocktakingData = (EditText) findViewById(R.id.et_data_stocktacking);
        EtInputLocation = (EditText) findViewById(R.id.et_location);
        EtInputStocktakingPerson = (EditText) findViewById(R.id.stocktacking_person);
        EtInputMark = (EditText) findViewById(R.id.et_mark);
        EtInputModel = (EditText) findViewById(R.id.et_model);
        EtInputSerialNumber = (EditText) findViewById(R.id.et_serial_number);
        SpInputListEmployees = (Spinner) findViewById(R.id.spinner);
        Button btnAddDevice = (Button) findViewById(R.id.btn_add_device);
        EtInputTypeDevice = (EditText) findViewById(R.id.et_type_device);
        String deviceName = getIntent().getExtras().getString(getString(R.string.intent_device_name));
        btnAddDevice.setOnClickListener(this);
        v.setOnTouchListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        if (!deviceName.isEmpty()) {
            EtInputDeviceName.setText(deviceName);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy - MM - dd");
            String currentDateAndTime = sdf.format(new Date());
            EtInputStocktakingData.setText(currentDateAndTime);
            EtInputDeviceName.setFocusable(false);
            EtInputStocktakingData.setFocusable(false);
        }

    }


    @Override
    public void onResponseGetBaseListSuccess(ArrayList Employees) {
        Spinner SpDropdownDevices = (Spinner) findViewById(R.id.spinner);
        Employees.add(0, "");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Employees);
        SpDropdownDevices.setAdapter(adapter);
        DefaultDataProviderComponent.getInstance().unregisterListenerGetBaseList(this);
    }

    @Override
    public void onResponseGetBaseListFail() {
        Toast.makeText(getBaseContext(), getString(R.string.tst_added_devices_fail),
                Toast.LENGTH_SHORT).show();
        DefaultDataProviderComponent.getInstance().unregisterListenerGetBaseList(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Utils.hideSoftKeyboard(this);
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_device:
                DefaultDataProviderComponent.getInstance().registerListenerAddDevice(this);
                Devices newDevice = createDeviceObject();
                DefaultDataProviderComponent.getInstance().addDevice(newDevice);
                break;
        }
    }

    @NonNull
    private Devices createDeviceObject() {
        Devices newDevice = new Devices();
        newDevice.setDeviceName(EtInputDeviceName.getText().toString());
        newDevice.setStocktakingData(EtInputStocktakingData.getText().toString());
        newDevice.setLocation(EtInputLocation.getText().toString());
        newDevice.setStocktakingPerson(EtInputStocktakingPerson.getText().toString());
        newDevice.setMark(EtInputMark.getText().toString());
        newDevice.setModel(EtInputModel.getText().toString());
        newDevice.setSerialNumber(EtInputSerialNumber.getText().toString());
        newDevice.setTypeDevice(EtInputTypeDevice.getText().toString());
        if (SpInputListEmployees.getSelectedItem() != null) {
            newDevice.setItemListEmployees(SpInputListEmployees.getSelectedItem().toString());
        }
        return newDevice;
    }

    @Override
    public void onResponseAddDeviceSuccess() {
        Toast.makeText(getBaseContext(), getString(R.string.tst_added_devices_success),
                Toast.LENGTH_SHORT).show();

        DefaultDataProviderComponent.getInstance().unregisterListenerAddDevice(this);
        CleanerEditText();

    }

    private void CleanerEditText() {
        EtInputDeviceName.setText("");
        EtInputStocktakingData.setText("");
        EtInputLocation.setText("");
        EtInputStocktakingPerson.setText("");
        EtInputMark.setText("");
        EtInputModel.setText("");
        EtInputSerialNumber.setText("");
        EtInputTypeDevice.setText("");
    }

    @Override
    public void onResponseAddDeviceFail(String message) {
        Toast.makeText(getBaseContext(), message,
                Toast.LENGTH_SHORT).show();
        DefaultDataProviderComponent.getInstance().unregisterListenerAddDevice(this);
    }
}
