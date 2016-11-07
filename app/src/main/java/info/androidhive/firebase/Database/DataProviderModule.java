package info.androidhive.firebase.Database;

import java.util.ArrayList;

import info.androidhive.firebase.Object.Devices;
import info.androidhive.firebase.Object.Employees;

/**
 * Created by radek on 28.09.16.
 Aplikacja Radosława Subczynskiego
 */

public interface DataProviderModule {

    void addUser(Employees newUser);

    void isStringExist(final String searchValue, String FIREBASE_URL);

    void getDeviceDetailsList(final String Value, String FIREBASE_URL_DEVICES_DETAILS);

    void getBaseList(String FIRESAFE_URL_EMPLOYEE);

    void getEmployedListDetail(String FIRESAFE_URL_EMPLOYEE_PART_1, String FIRESAFE_URL_EMPLOYEE_PART_2, String Employee);

    void addDevice(Devices newDevice);

    void registerListener(DataProviderListener dataProviderListener);

    void unregisterListener(DataProviderListener dataProviderListener);

    void broadcastOnSearchUserSuccess(String searchUser);

    void broadcastOnAddEmployedFail(String message);

    void broadcastOnAddEmployedSuccess();

    void broadcastOnSearchUserFail();

    void broadcastOnGetDevicesListSuccess(ArrayList Devices_List);

    void broadcastOnGetDevisesListFail();

    void broadcastOnGetBaseListSuccess(ArrayList Employed_List);

    void broadcastOnGetBaseListFail();

    void broadcastOnAddDeviceFail(String message);

    void broadcastOnAddDeviceSuccess();

    void broadcastOnGetEmployeesListSuccess(ArrayList Employed_List);

    void broadcastOnGetEmployeesDetailsListFail();


}
