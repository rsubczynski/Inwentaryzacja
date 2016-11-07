package info.androidhive.firebase.ExportCSV;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by radek on 03.10.16.
 Aplikacja Radosława Subczynskiego
 */

abstract class DefaultExportProviderModule implements ExportProviderModule {

    private List<ExportProviderListener> exportProviderListeners = new ArrayList<>();

    @Override
    public void registerListener(ExportProviderListener exportProviderListener) {
        if (!exportProviderListeners.contains(exportProviderListener)) {
            exportProviderListeners.add(exportProviderListener);
        }
    }


    @Override
    public void unregisterListener(ExportProviderListener exportProviderListener) {
        if (!exportProviderListeners.contains(exportProviderListener)) {
            exportProviderListeners.remove(exportProviderListener);

        }
    }

    @Override
    public void broadcastSaveCsvToFileSuccess(String exportSuccess) {
        for (ExportProviderListener exportProviderListener : exportProviderListeners) {
            exportProviderListener.onExportSuccess(exportSuccess);
        }
    }


    @Override
    public void broadcastSaveCsvToFileFail(String fileExist) {
        for (ExportProviderListener exportProviderListener : exportProviderListeners) {
            exportProviderListener.onExportFail(fileExist);
        }
    }
}
