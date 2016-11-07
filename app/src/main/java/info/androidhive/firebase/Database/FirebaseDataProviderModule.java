package info.androidhive.firebase.Database;

import android.annotation.TargetApi;
import android.os.Build;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import info.androidhive.firebase.Object.Devices;
import info.androidhive.firebase.Object.Employees;


/**
 * Created by radek on 28.09.16.
 Aplikacja Radosława Subczynskiego
 */
public class FirebaseDataProviderModule extends DefaultDataProviderModule {

    private  Firebase ref;
    private  boolean findValueExample;
    private  String message;


    @Override
    public void addUser(Employees newUser) {

        if (isEmptyEmployees(newUser)) {
            message = "żadne pole nie moze byc puste";
            broadcastOnAddEmployedFail(message);
        } else

        {
            pushUserToServer(newUser);
        }

    }

    private boolean isEmptyEmployees(Employees newUser) {
        return newUser.getEmployeesEmail().isEmpty() || newUser.getEmployeesSkype().isEmpty() || newUser.getEmployeesPhoneNumber().isEmpty();
    }

    private void pushUserToServer(Employees newUser) {
        ref = new Firebase(FirebaseConfigURL.FIBREASE_URL_BASE);
        ref.child("Pracownicy").child(newUser.getEmployeesName()).child("Dane uzytkownika").child("E-mail").setValue(newUser.getEmployeesEmail());
        ref.child("Pracownicy").child(newUser.getEmployeesName()).child("Dane uzytkownika").child("Skype").setValue(newUser.getEmployeesSkype());
        ref.child("Pracownicy").child(newUser.getEmployeesName()).child("Dane uzytkownika").child("Telefon").setValue(newUser.getEmployeesPhoneNumber());
        broadcastOnAddEmployedSuccess();
    }


    @Override
    public void isStringExist(final String searchValue, String FIREBASE_URL) {
        Firebase ref = new Firebase(FIREBASE_URL);

        ref.addValueEventListener(new ValueEventListener() {

            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                findValueExample = false;
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if (Objects.equals(postSnapshot.getKey(), searchValue)) {
                        findValueExample = true;
                    }
                }
                isUserExistHelper(findValueExample, searchValue);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());

            }


        });

    }

    private void isUserExistHelper(boolean FIND_USER_EXAMPLE, String SearchUser) {
        if (FIND_USER_EXAMPLE) {

            broadcastOnSearchUserSuccess(SearchUser);
        } else {
            broadcastOnSearchUserFail();

        }

    }

    private ArrayList<String> devices = new ArrayList<>();

    @Override
    public void getDeviceDetailsList(final String value, String FIREBASE_URL_DEVICES_DETAILS) {

        Firebase ref = new Firebase(FIREBASE_URL_DEVICES_DETAILS + value);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                devices.clear();
                boolean FIND_VALUE_DETAILS = false;
                devices.add(value);
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if (postSnapshot.getValue() != null) {
                        String DataBaseKey = postSnapshot.getKey();
                        String DatabaseValue = postSnapshot.getValue().toString();
//                        devices.add(DatabaseValue);
                        devices.add(DataBaseKey + ": " + DatabaseValue);
                        FIND_VALUE_DETAILS = true;

                    }
                }
                getDevicesListHelper(FIND_VALUE_DETAILS, devices);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }

        });
    }

    private void getDevicesListHelper(boolean findDevices, ArrayList devicesList) {

        if (findDevices) {
            broadcastOnGetDevicesListSuccess(devicesList);
        }
        else {
            broadcastOnGetDevisesListFail();

        }
    }


    private ArrayList<String> valueList = new ArrayList<>();

    @Override
    public void getBaseList(String FIRESAFE_URL_BASE) {

        Firebase ref = new Firebase(FIRESAFE_URL_BASE);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                valueList.clear();
                boolean findValueDetails = false;
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if (postSnapshot.getValue() != null) {
                        Employees employees = new Employees();
                        employees.setEmployeesName(postSnapshot.getKey());
                        valueList.add(employees.getEmployeesName());
                        findValueDetails = true;

                    }
                }
                getEmployedListHelper(findValueDetails, valueList);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
                broadcastOnGetBaseListFail();
            }

        });
    }

    private void getEmployedListHelper(boolean findDevices, ArrayList employedList) {
        if (findDevices)
            broadcastOnGetBaseListSuccess(employedList);
        else {
            broadcastOnGetBaseListFail();

        }
    }

    private ArrayList<String> employedListDetails = new ArrayList<>();

    @Override
    public void getEmployedListDetail(String FIRESAFE_URL_EMPLOYEE_PART_1, String FIRESAFE_URL_EMPLOYEE_PART_2, final String Employee) {

        Firebase ref = new Firebase(FIRESAFE_URL_EMPLOYEE_PART_1 + Employee + FIRESAFE_URL_EMPLOYEE_PART_2);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                employedListDetails.clear();
                boolean findValueDetails = false;
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if (postSnapshot.getValue() != null) {
                        String Key = postSnapshot.getKey();
                        String Value = postSnapshot.getValue().toString();
                        employedListDetails.add(Key);
                        employedListDetails.add("   " + Value);
                        findValueDetails = true;
                    }
                }
                getEmployedListDetailHelper(findValueDetails, employedListDetails);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }

        });
    }


    private void getEmployedListDetailHelper(boolean findDevices, ArrayList employedList) {
        if (findDevices)
            broadcastOnGetEmployeesListSuccess(employedList);
        else {
            broadcastOnGetEmployeesDetailsListFail();

        }
    }

    @Override
    public void addDevice(Devices newDevice) {

        if (isEmptyDevices(newDevice)) {
            message = "Żadne pole nie moze byc puste";
            broadcastOnAddDeviceFail(message);
        } else

        {
            pushDevicesToServer(newDevice);
        }

    }

    private boolean isEmptyDevices(Devices newDevice) {
        return newDevice.getDeviceName().isEmpty() || newDevice.getStocktakingData().isEmpty() || newDevice.getLocation().isEmpty() ||
                newDevice.getStocktakingPerson().isEmpty() || newDevice.getModel().isEmpty() || newDevice.getMark().isEmpty() ||
                newDevice.getSerialNumber().isEmpty() || newDevice.getItemListEmployees().isEmpty();
    }

    private void pushDevicesToServer(Devices newDevice) {
        ref = new Firebase(FirebaseConfigURL.FIBREASE_URL_BASE);
        ref.child("Urzadzenia").child(newDevice.getDeviceName()).child("Data inwentaryzacji").setValue(newDevice.getStocktakingData());
        ref.child("Urzadzenia").child(newDevice.getDeviceName()).child("Gdzie się znajduje").setValue(newDevice.getLocation());
        ref.child("Urzadzenia").child(newDevice.getDeviceName()).child("Kto inwentaryzowal").setValue(newDevice.getStocktakingPerson());
        ref.child("Urzadzenia").child(newDevice.getDeviceName()).child("Marka").setValue(newDevice.getMark());
        ref.child("Urzadzenia").child(newDevice.getDeviceName()).child("Model").setValue(newDevice.getModel());
        ref.child("Urzadzenia").child(newDevice.getDeviceName()).child("Numer seryjny").setValue(newDevice.getSerialNumber());
        ref.child("Urzadzenia").child(newDevice.getDeviceName()).child("Rodzaj").setValue(newDevice.getTypeDevice());
        ref.child("Urzadzenia").child(newDevice.getDeviceName()).child("Użytkownik").setValue(newDevice.getItemListEmployees());
        broadcastOnAddDeviceSuccess();
    }

}
