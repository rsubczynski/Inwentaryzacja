package info.androidhive.firebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import info.androidhive.firebase.Activity.DevicesDetailsListActivity;
import info.androidhive.firebase.Activity.AddDevicesActivity;
import info.androidhive.firebase.Database.Listiners.OnSearchStringValueListener;
import info.androidhive.firebase.Database.FirebaseConfigURL;
import info.androidhive.firebase.Database.DefaultDataProviderComponent;
import info.androidhive.firebase.Utils.Utils;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SimpleScannerActivity extends Activity implements ZXingScannerView.ResultHandler, OnSearchStringValueListener {
    private ZXingScannerView mScannerView;
    private String Device;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        Utils.getPermission(this, android.Manifest.permission.CAMERA);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);
        // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.stopCamera();         // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        DefaultDataProviderComponent.getInstance().registerListenerSearchUser(this);
        Toast.makeText(getBaseContext(), rawResult.getText(), Toast.LENGTH_SHORT).show();
        Device = rawResult.getText().trim();
        DefaultDataProviderComponent.getInstance().searchString(rawResult.getText().trim(), FirebaseConfigURL.FIREBASE_URL_DEVICES);

    }

    @Override
    public void onResponseSuccessSearchString(String searchString) {
        DefaultDataProviderComponent.getInstance().unregisterListenerSearchUser(this);
        Intent deviceDetailsListIntent = new Intent(this, DevicesDetailsListActivity.class);
        deviceDetailsListIntent.putExtra(getString(R.string.intent_device_name), Device);
        startActivity(deviceDetailsListIntent);
        finish();
    }

    @Override
    public void onResponseFailSearchString() {
        Toast.makeText(getApplicationContext(), getString(R.string.tst_validation_empty_filed), Toast.LENGTH_SHORT).show();
        DefaultDataProviderComponent.getInstance().unregisterListenerSearchUser(this);
        Intent AddDevicesIntent = new Intent(getApplicationContext(), AddDevicesActivity.class);
        AddDevicesIntent.putExtra(getString(R.string.intent_device_name), Device);
        startActivity(AddDevicesIntent);
        finish();


    }
}
