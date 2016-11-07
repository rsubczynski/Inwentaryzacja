package info.androidhive.firebase.ExportCSV;

import java.util.ArrayList;

/**
 * Created by radek on 03.10.16.
 Aplikacja Radosława Subczynskiego
 */

 interface ExportProviderModule {
    void registerListener(ExportProviderListener exportProviderListener);

    void unregisterListener(ExportProviderListener exportProviderListener);

    void saveCSVtoFile(ArrayList devicesList, String csvName);

    void broadcastSaveCsvToFileSuccess(String exportSuccess);

    void broadcastSaveCsvToFileFail(String fileExist);
}
