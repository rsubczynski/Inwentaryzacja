package info.androidhive.firebase.Database;

import info.androidhive.firebase.Object.Devices;
import info.androidhive.firebase.Object.Employees;

/**
 * Created by radek on 28.09.16.
 Aplikacja Radosława Subczynskiego
 */

public interface DataProviderComponent {

    void searchString(final String SearchValue, String firebaseURL);

    void getDeviceDetailsList(final String Value, String firebaseUrlDevicesDetails);

    void getBaseList(String FIRESAFE_URL_EMPLOYEE);

    void getEmployedListDetail(String firesafeUrlEmployeePart1, String firesafeUrlEmployeePart2, String employee);

    void addUser(Employees newUser);

    void addDevice(Devices newDevice);
}
