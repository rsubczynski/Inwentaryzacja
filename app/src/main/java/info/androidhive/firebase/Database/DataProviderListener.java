package info.androidhive.firebase.Database;

import java.util.ArrayList;

/**
 * Created by radek on 28.09.16.
 Aplikacja Radosława Subczynskiego
 */

public interface DataProviderListener {
    void onSearchSuccess(String searchUser);

    void onSearchFail();

    void onAddedEmployedSuccess();

    void onAddedEmployedFail(String message);

    void onAddedDevicesSuccess();

    void onAddedDevicesFail(String message);

    void onGetDevicesDetailListSuccess(ArrayList devices);

    void onGetDevicesDetailListFail();

    void onGetBaseListSuccess(ArrayList employees);

    void onGetBaseListFail();

    void onGetEmployeesDetailListSuccess(ArrayList employees);

    void onGetEmployeesDetailListFail();
}
