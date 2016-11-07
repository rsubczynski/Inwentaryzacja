package info.androidhive.firebase.Database;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by radek on 28.09.16.
 Aplikacja Radosława Subczynskiego
 */
abstract class DefaultDataProviderModule implements DataProviderModule {

     private List<DataProviderListener> dataProviderListeners = new ArrayList<>();


    public void registerListener(DataProviderListener dataProviderListener) {
        if (!dataProviderListeners.contains(dataProviderListener)) {
            dataProviderListeners.add(dataProviderListener);
        }
    }

    public void unregisterListener(DataProviderListener dataProviderListener) {
        if (!dataProviderListeners.contains(dataProviderListener)) {
            dataProviderListeners.remove(dataProviderListener);

        }
    }

    public void broadcastOnSearchUserSuccess(String searchUser) {
        for (DataProviderListener dataProviderListener : dataProviderListeners) {
            dataProviderListener.onSearchSuccess(searchUser);
        }
    }

    public void broadcastOnAddEmployedFail(String message) {
        for (DataProviderListener dataProviderListener : dataProviderListeners) {
            dataProviderListener.onAddedEmployedFail(message);
        }
    }

    public void broadcastOnAddEmployedSuccess() {
        for (DataProviderListener dataProviderListener : dataProviderListeners) {
            dataProviderListener.onAddedEmployedSuccess();
        }
    }

    public void broadcastOnSearchUserFail() {
        for (DataProviderListener dataProviderListener : dataProviderListeners) {
            dataProviderListener.onSearchFail();
        }
    }

     public void broadcastOnGetDevicesListSuccess(ArrayList Devices_List) {
        for (DataProviderListener dataProviderListener : dataProviderListeners) {
            dataProviderListener.onGetDevicesDetailListSuccess(Devices_List);
        }
    }

    public void broadcastOnGetDevisesListFail() {
        for (DataProviderListener dataProviderListener : dataProviderListeners) {
            dataProviderListener.onGetDevicesDetailListFail();
        }
    }

     public void broadcastOnGetBaseListSuccess(ArrayList Employed_List) {
        for (DataProviderListener dataProviderListener : dataProviderListeners) {
            dataProviderListener.onGetBaseListSuccess(Employed_List);
        }
    }

     public void broadcastOnGetBaseListFail() {
        for (DataProviderListener dataProviderListener : dataProviderListeners) {
            dataProviderListener.onGetBaseListFail();
        }
    }

     public void broadcastOnAddDeviceFail(String message) {
        for (DataProviderListener dataProviderListener : dataProviderListeners) {
            dataProviderListener.onAddedDevicesFail(message);
        }
    }

     public void broadcastOnAddDeviceSuccess() {
        for (DataProviderListener dataProviderListener : dataProviderListeners) {
            dataProviderListener.onAddedDevicesSuccess();
        }
    }

     public void broadcastOnGetEmployeesListSuccess(ArrayList Employed_List) {
        for (DataProviderListener dataProviderListener : dataProviderListeners) {
            dataProviderListener.onGetEmployeesDetailListSuccess(Employed_List);
        }
    }

     public void broadcastOnGetEmployeesDetailsListFail() {
        for (DataProviderListener dataProviderListener : dataProviderListeners) {
            dataProviderListener.onGetEmployeesDetailListFail();
        }
    }

}
