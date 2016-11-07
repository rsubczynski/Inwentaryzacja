package info.androidhive.firebase.ExportCSV;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.firebase.ExportCSV.LIsteners.OnExportFileToCsv;

/**
 * Created by radek on 03.10.16.
 Aplikacja Radosława Subczynskiego
 */

public class DefaultExportProviderComponent implements ExportProviderComponent,ExportProviderListener{

    private static DefaultExportProviderComponent defaultExportProviderComponent;

    public static DefaultExportProviderComponent getInstance() {
        if (defaultExportProviderComponent == null) {
            defaultExportProviderComponent = new DefaultExportProviderComponent();
        }
        return defaultExportProviderComponent;
    }
    private ExportProviderModule exportProviderModule = new CSVProviderModule();

    private DefaultExportProviderComponent() {
        exportProviderModule.registerListener(this);
    }

    private static List<OnExportFileToCsv> exportFileToCsv = new ArrayList<>();

    public void registerListenerSaveCsvToFile(OnExportFileToCsv onExportFileToCsv) {
        if (!exportFileToCsv.contains(onExportFileToCsv)) {
            exportFileToCsv.add(onExportFileToCsv);

        }
    }

    public void unregisterListenerSaveCsvToFile(OnExportFileToCsv OnExportFileToCsv) {
        if (exportFileToCsv.contains(OnExportFileToCsv)) {
            exportFileToCsv.remove(OnExportFileToCsv);

        }
    }

    private void broadcastOnSaveCsvToFileSuccess(String fileIsExist) {
        for (OnExportFileToCsv OnSearchStringValueListener : exportFileToCsv) {
            OnSearchStringValueListener.OnExportFileToCsvSuccess(fileIsExist);


        }
    }

    private void broadcastOnSaveCsvToFileFail(String Fail) {
        for (OnExportFileToCsv OnSearchStringValueListener : exportFileToCsv) {
            OnSearchStringValueListener.OnExportFileToCsvFail(Fail);

        }
    }


    @Override
    public void saveCSVtoFile(ArrayList devicesList, String csvName) {
        exportProviderModule.saveCSVtoFile(devicesList,csvName);
    }

    @Override
    public void onExportSuccess(String success) {
        broadcastOnSaveCsvToFileSuccess(success);
    }

    @Override
    public void onExportFail(String fileExist) {
        broadcastOnSaveCsvToFileFail(fileExist);
    }
}
