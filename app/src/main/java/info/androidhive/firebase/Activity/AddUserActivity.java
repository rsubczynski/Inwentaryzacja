package info.androidhive.firebase.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import info.androidhive.firebase.Database.DefaultDataProviderComponent;
import info.androidhive.firebase.Database.FirebaseConfigURL;
import info.androidhive.firebase.Database.Listiners.OnAddEmployeesListener;
import info.androidhive.firebase.Database.Listiners.OnSearchStringValueListener;
import info.androidhive.firebase.Object.Employees;
import info.androidhive.firebase.R;
import info.androidhive.firebase.Utils.Utils;

public class AddUserActivity extends BaseActivity implements View.OnClickListener, OnAddEmployeesListener, OnSearchStringValueListener, View.OnTouchListener {

    private EditText EtInputEmployedName;
    private EditText EtInputPhoneNumber;
    private EditText EtInputSkypeLogin;
    private EditText EtInputEMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        Button BtnAddEmployed = (Button) findViewById(R.id.btn_added_user);
        EtInputEmployedName = (EditText) findViewById(R.id.et_name);
        EtInputPhoneNumber = (EditText) findViewById(R.id.et_phone_number);
        EtInputSkypeLogin = (EditText) findViewById(R.id.et_skype);
        EtInputEMail = (EditText) findViewById(R.id.et_email);
        BtnAddEmployed.setOnClickListener(this);
        View v = findViewById(R.id.add_empoyed_activity);
        v.setOnTouchListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_added_user:
                String person = EtInputEmployedName.getText().toString().trim();
                DefaultDataProviderComponent.getInstance().registerListenerSearchUser(this);
                DefaultDataProviderComponent.getInstance().searchString(person, FirebaseConfigURL.FIRESAFE_URL_EMPLOYEE);
        }
    }

    @Override
    public void onAddedEmployeeSuccess() {
        Toast.makeText(getBaseContext(), getString(R.string.tst_employed_success),
                Toast.LENGTH_SHORT).show();
        DefaultDataProviderComponent.getInstance().unregisterListenerAddEmployed(this);
    }

    @Override
    public void onAddedEmployeesFail(String message) {
        Toast.makeText(getBaseContext(), message,
                Toast.LENGTH_SHORT).show();
        DefaultDataProviderComponent.getInstance().unregisterListenerAddEmployed(this);

    }

    @Override
    public void onResponseSuccessSearchString(String searchString) {
        Toast.makeText(getBaseContext(), searchString + getString(R.string.tst_is_exist),
                Toast.LENGTH_SHORT).show();
        DefaultDataProviderComponent.getInstance().unregisterListenerSearchUser(this);

    }

    @Override
    public void onResponseFailSearchString() {
        Employees newUser = new Employees();
        newUser.setEmployeesName(EtInputEmployedName.getText().toString().trim());
        newUser.setEmployeesEmail(EtInputEMail.getText().toString().trim());
        newUser.setEmployeesSkype(EtInputSkypeLogin.getText().toString().trim());
        newUser.setEmployeesPhoneNumber(EtInputPhoneNumber.getText().toString().trim());
        DefaultDataProviderComponent.getInstance().registerListenerAddEmployed(this);
        DefaultDataProviderComponent.getInstance().addUser(newUser);
        DefaultDataProviderComponent.getInstance().unregisterListenerSearchUser(this);
        cleanerEditText();
    }

    private void cleanerEditText() {
        EtInputEmployedName.setText("");
        EtInputPhoneNumber.setText("");
        EtInputSkypeLogin.setText("");
        EtInputEMail.setText("");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Utils.hideSoftKeyboard(this);
        return false;
    }
}
