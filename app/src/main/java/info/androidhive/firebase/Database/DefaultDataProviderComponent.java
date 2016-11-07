package info.androidhive.firebase.Database;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.firebase.Database.Listiners.OnAddEmployeesListener;
import info.androidhive.firebase.Database.Listiners.OnGetDeviceDetailsListListener;
import info.androidhive.firebase.Database.Listiners.OnGetEmployedListDetailsListener;
import info.androidhive.firebase.Database.Listiners.OnGetBaseListListener;
import info.androidhive.firebase.Database.Listiners.OnSearchStringValueListener;
import info.androidhive.firebase.Database.Listiners.OnAddDeviceListener;
import info.androidhive.firebase.Object.Devices;
import info.androidhive.firebase.Object.Employees;

/**
 * Created by radek on 28.09.16.
 Aplikacja Radosława Subczynskiego
 */

public class DefaultDataProviderComponent implements DataProviderComponent, DataProviderListener {


    private static DefaultDataProviderComponent defaultDataProviderComponent;

    public static DefaultDataProviderComponent getInstance() {
        if (defaultDataProviderComponent == null) {
            defaultDataProviderComponent = new DefaultDataProviderComponent();
        }
        return defaultDataProviderComponent;
    }

    private DataProviderModule dataProviderModule = new FirebaseDataProviderModule();

    private DefaultDataProviderComponent() {
        dataProviderModule.registerListener(this);
    }


    private static List<OnSearchStringValueListener> searchUserListeners = new ArrayList<>();
    private static List<OnGetDeviceDetailsListListener> searchUserVales = new ArrayList<>();
    private static List<OnGetBaseListListener> employedLists = new ArrayList<>();
    private static List<OnGetEmployedListDetailsListener> employedListsDetails = new ArrayList<>();
    private static List<OnAddEmployeesListener> addEmployees = new ArrayList<>();
    private static List<OnAddDeviceListener> addDevices = new ArrayList<>();


    public void registerListenerSearchUser(OnSearchStringValueListener OnSearchStringValueListener) {
        if (!searchUserListeners.contains(OnSearchStringValueListener)) {
            searchUserListeners.add(OnSearchStringValueListener);

        }
    }

    public void unregisterListenerSearchUser(OnSearchStringValueListener OnSearchStringValueListener) {
        if (searchUserListeners.contains(OnSearchStringValueListener)) {
            searchUserListeners.remove(OnSearchStringValueListener);

        }
    }

    private void broadcastOnSearchUserSuccess(String searchUser) {
        for (OnSearchStringValueListener OnSearchStringValueListener : searchUserListeners) {
            OnSearchStringValueListener.onResponseSuccessSearchString(searchUser);
            searchUserListeners.remove(OnSearchStringValueListener);

        }
    }

    private void broadcastOnSearchUserFail() {
        for (OnSearchStringValueListener OnSearchStringValueListener : searchUserListeners) {
            OnSearchStringValueListener.onResponseFailSearchString();

        }
    }


    public void registerListenerGetBaseList(OnGetBaseListListener OnGetBaseListListener) {
        if (!employedLists.contains(OnGetBaseListListener)) {
            employedLists.add(OnGetBaseListListener);

        }
    }

    public void unregisterListenerGetBaseList(OnGetBaseListListener OnGetBaseListListener) {
        if (employedLists.contains(OnGetBaseListListener)) {
            employedLists.remove(OnGetBaseListListener);
        }
    }

    private void broadcastOnGetBaseListSuccess(ArrayList Employees) {
        for (OnGetBaseListListener OnGetBaseListListener : employedLists) {
            OnGetBaseListListener.onResponseGetBaseListSuccess(Employees);


        }
    }

    private void broadcastOnGetBaseListFail() {
        for (OnGetBaseListListener OnGetBaseListListener : employedLists) {
            OnGetBaseListListener.onResponseGetBaseListFail();
        }
    }


    public void registerListenerGetDeviceDetailsList(OnGetDeviceDetailsListListener OnGetDeviceDetailsListListener) {
        if (!searchUserVales.contains(OnGetDeviceDetailsListListener)) {
            searchUserVales.add(OnGetDeviceDetailsListListener);

        }
    }

    public void unregisterListenerGetDeviceDetailsList(OnGetDeviceDetailsListListener OnGetDeviceDetailsListListener) {
        if (searchUserVales.contains(OnGetDeviceDetailsListListener)) {
            searchUserVales.remove(OnGetDeviceDetailsListListener);
        }
    }

    private void broadcastOnGetDevicesListSucces(ArrayList Employess) {
        for (OnGetDeviceDetailsListListener OnGetDeviceDetailsListListener : searchUserVales) {
            OnGetDeviceDetailsListListener.onResponseGetDevicesDetailsListSuccess(Employess);

        }
    }

    private void broadcastOnGetDevisesListFail() {
        for (OnGetDeviceDetailsListListener OnGetDeviceDetailsListListener : searchUserVales) {
            OnGetDeviceDetailsListListener.onResponseGetDevicesDetailsListFail();
        }
    }

    public void registerListenerGetEmployedDetailsList(OnGetEmployedListDetailsListener OnGetEmployedListDetailsListener) {
        if (!employedListsDetails.contains(OnGetEmployedListDetailsListener)) {
            employedListsDetails.add(OnGetEmployedListDetailsListener);

        }
    }

    public void unregisterListenerGetEmployedDetailsList(OnGetEmployedListDetailsListener OnGetEmployedListDetailsListener) {
        if (employedListsDetails.contains(OnGetEmployedListDetailsListener)) {
            employedListsDetails.remove(OnGetEmployedListDetailsListener);
        }
    }

    private void broadcastOnGetEmployeesListSuccess(ArrayList EmployeesDetails) {
        for (OnGetEmployedListDetailsListener OnGetEmployedListDetailsListener : employedListsDetails) {
            OnGetEmployedListDetailsListener.onResponseGetEmployedDetailsListSuccess(EmployeesDetails);
            employedListsDetails.remove(OnGetEmployedListDetailsListener);
        }
    }

    private void broadcastOnGetEmployeesDetailsListFail() {
        for (OnGetEmployedListDetailsListener OnGetEmployedListDetailsListener : employedListsDetails) {
            OnGetEmployedListDetailsListener.onResponseGetEmployedDetailsListFail();
        }
    }


    public void registerListenerAddEmployed(OnAddEmployeesListener OnAddEmployeesListener) {
        if (!addEmployees.contains(OnAddEmployeesListener)) {
            addEmployees.add(OnAddEmployeesListener);

        }
    }

    public void unregisterListenerAddEmployed(OnAddEmployeesListener OnAddEmployeesListener) {
        if (addEmployees.contains(OnAddEmployeesListener)) {
            addEmployees.remove(OnAddEmployeesListener);

        }
    }

    private void broadcastOnAddEmployedSuccess() {
        for (OnAddEmployeesListener OnAddEmployeesListener : addEmployees) {
            OnAddEmployeesListener.onAddedEmployeeSuccess();
            addEmployees.remove(OnAddEmployeesListener);

        }
    }

    private void broadcastOnAddEmployedFail(String message) {
        for (OnAddEmployeesListener OnAddEmployeesListener : addEmployees) {
            OnAddEmployeesListener.onAddedEmployeesFail(message);

        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void registerListenerAddDevice(OnAddDeviceListener OnAddDeviceListener) {
        if (!addDevices.contains(OnAddDeviceListener)) {
            addDevices.add(OnAddDeviceListener);

        }
    }

    public void unregisterListenerAddDevice(OnAddDeviceListener OnAddDeviceListener) {
        if (addDevices.contains(OnAddDeviceListener)) {
            addDevices.remove(OnAddDeviceListener);

        }
    }

    private void broadcastOnAddDeviceSuccess() {
        for (OnAddDeviceListener OnAddDeviceListener : addDevices) {
            OnAddDeviceListener.onResponseAddDeviceSuccess();
            addDevices.remove(OnAddDeviceListener);

        }
    }

    private void broadcastOnAddDeviceFail(String message) {
        for (OnAddDeviceListener OnAddDeviceListener : addDevices) {
            OnAddDeviceListener.onResponseAddDeviceFail(message);
        }
    }


    @Override
    public void searchString(String SearchValue, String FirebaseURL) {
        dataProviderModule.isStringExist(SearchValue, FirebaseURL);


    }

    @Override
    public void getDeviceDetailsList(String Value, String firebaseUrlDevicesDetails) {
        dataProviderModule.getDeviceDetailsList(Value, firebaseUrlDevicesDetails);
    }

    @Override
    public void getBaseList(String FIRESAFE_URL_BASE_VALUE) {
        dataProviderModule.getBaseList(FIRESAFE_URL_BASE_VALUE);
    }

    @Override
    public void getEmployedListDetail(String firesafeUrlEmployeePart1, String firesafeUrlEmployeePart2, String employee) {
        dataProviderModule.getEmployedListDetail(firesafeUrlEmployeePart1, firesafeUrlEmployeePart2, employee);
    }

    @Override
    public void addUser(Employees newUser) {
        dataProviderModule.addUser(newUser);

    }

    @Override
    public void addDevice(Devices newDevice) {
        dataProviderModule.addDevice(newDevice);
    }

    @Override
    public void onSearchSuccess(String searchUser) {
        broadcastOnSearchUserSuccess(searchUser);
    }

    @Override
    public void onSearchFail() {
        broadcastOnSearchUserFail();
    }

    @Override
    public void onAddedEmployedSuccess() {
        broadcastOnAddEmployedSuccess();
    }

    @Override
    public void onAddedEmployedFail(String message) {
        broadcastOnAddEmployedFail(message);
    }

    @Override
    public void onAddedDevicesSuccess() {
        broadcastOnAddDeviceSuccess();
    }

    @Override
    public void onAddedDevicesFail(String message) {
        broadcastOnAddDeviceFail(message);
    }

    @Override
    public void onGetDevicesDetailListSuccess(ArrayList devices) {
        broadcastOnGetDevicesListSucces(devices);
    }

    @Override
    public void onGetDevicesDetailListFail() {
        broadcastOnGetDevisesListFail();
    }

    @Override
    public void onGetBaseListSuccess(ArrayList employees) {
        broadcastOnGetBaseListSuccess(employees);
    }

    @Override
    public void onGetBaseListFail() {
        broadcastOnGetBaseListFail();
    }

    @Override
    public void onGetEmployeesDetailListSuccess(ArrayList employees) {
        broadcastOnGetEmployeesListSuccess(employees);
    }

    @Override
    public void onGetEmployeesDetailListFail() {
        broadcastOnGetEmployeesDetailsListFail();
    }
}
