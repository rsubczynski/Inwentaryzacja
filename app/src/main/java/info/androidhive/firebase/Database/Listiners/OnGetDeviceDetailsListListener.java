package info.androidhive.firebase.Database.Listiners;

import java.util.ArrayList;

/**
 * Created by Radek on 2016-09-24.
 Aplikacja Radosława Subczynskiego
 */
public interface OnGetDeviceDetailsListListener {
    void onResponseGetDevicesDetailsListSuccess(ArrayList devicesList);
    void onResponseGetDevicesDetailsListFail();
}
