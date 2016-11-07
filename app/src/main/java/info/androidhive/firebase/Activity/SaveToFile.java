package info.androidhive.firebase.Activity;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import info.androidhive.firebase.Database.DefaultDataProviderComponent;
import info.androidhive.firebase.Database.FirebaseConfigURL;
import info.androidhive.firebase.Database.Listiners.OnGetBaseListListener;
import info.androidhive.firebase.ExportCSV.DefaultExportProviderComponent;
import info.androidhive.firebase.ExportCSV.LIsteners.OnExportFileToCsv;
import info.androidhive.firebase.R;
import info.androidhive.firebase.Utils.Utils;

public class SaveToFile extends BaseActivity implements OnGetBaseListListener, View.OnClickListener, OnExportFileToCsv, View.OnTouchListener {

    private EditText EtFileName;
    private String fileName;
    private ProgressBar progressBar;
    private TextView tv_fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_to_csv);
        EtFileName = (EditText) findViewById(R.id.et_file_name);
        progressBar = (ProgressBar) findViewById(R.id.progressBarNew);
        Utils.getPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Button btnSaveFile = (Button) findViewById(R.id.btn_save);
        tv_fileUri = (TextView) findViewById(R.id.tv_file_uri);
        btnSaveFile.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        View v = findViewById(R.id.save_to_file_activity);
        v.setOnTouchListener(this);
    }


    private final String FILE_URL = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "MyDir" + File.separator;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                fileName = EtFileName.getText().toString().trim();
                String FILE_FORMAT = ".csv";
                File file1 = new File(FILE_URL + fileName + FILE_FORMAT);
                if (file1.exists()) {
                    Snackbar.make(view, R.string.sb_file_is_exist, Snackbar.LENGTH_SHORT).show();
                } else if (fileName.isEmpty()) {
                    Snackbar.make(view, R.string.sb_file_is_empty, Snackbar.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    DefaultDataProviderComponent.getInstance().registerListenerGetBaseList(this);
                    DefaultDataProviderComponent.getInstance().getBaseList(FirebaseConfigURL.FIREBASE_URL_DEVICES);
                }
        }
    }


    @Override
    public void onResponseGetBaseListSuccess(ArrayList devicesList) {
        DefaultDataProviderComponent.getInstance().unregisterListenerGetBaseList(this);
        DefaultExportProviderComponent.getInstance().registerListenerSaveCsvToFile(this);
        DefaultExportProviderComponent.getInstance().saveCSVtoFile(devicesList, fileName);

    }

    @Override
    public void onResponseGetBaseListFail() {
        DefaultDataProviderComponent.getInstance().unregisterListenerGetBaseList(this);
    }


    @Override
    public void OnExportFileToCsvSuccess(String searchUser) {
        tv_fileUri.setText(FILE_URL);
        Toast.makeText(getBaseContext(), "Zapisano " + searchUser, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        DefaultExportProviderComponent.getInstance().unregisterListenerSaveCsvToFile(this);

    }

    @Override
    public void OnExportFileToCsvFail(String FailSaveFile) {
        Toast.makeText(getBaseContext(), FailSaveFile, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        DefaultExportProviderComponent.getInstance().unregisterListenerSaveCsvToFile(this);


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Utils.hideSoftKeyboard(this);
        return false;
    }


}

