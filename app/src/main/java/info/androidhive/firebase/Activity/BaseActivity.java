package info.androidhive.firebase.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.firebase.client.Firebase;

import info.androidhive.firebase.Authorized.SettingUserActivity;
import info.androidhive.firebase.R;
import info.androidhive.firebase.Utils.Utils;

/**
 * Created by Radek on 2016-09-26.
 * Aplikacja Radosława Subczynskiego
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        if (!Utils.isOnline(this)) {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.dialog_internet_title))
                    .setMessage(getString(R.string.dialog_internet_message))
                    .setPositiveButton(getString(R.string.dialog_internet_positive), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        }
                    })

                    .setNeutralButton(getString(R.string.dialog_internet_negative), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    })

                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.action_add_devices:
                Intent addDevicesActivity = new Intent(this, AddDevicesActivity.class);
                addDevicesActivity.putExtra(getString(R.string.intent_device_name), "");
                startActivity(addDevicesActivity);
                break;

            case R.id.action_add_user:
                Intent addUserActivity = new Intent(this, AddUserActivity.class);
                startActivity(addUserActivity);
                break;

            case R.id.action_save_to_file:
                Intent addSaveActivity = new Intent(this, SaveToFile.class);
                startActivity(addSaveActivity);
                break;
            case R.id.action_settings:
                Intent settingUserActivityIntent = new Intent(this, SettingUserActivity.class);
                startActivity(settingUserActivityIntent);
                break;
        }


        return super.onOptionsItemSelected(item);
    }

}

