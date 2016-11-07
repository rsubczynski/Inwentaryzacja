package info.androidhive.firebase.ExportCSV;

/**
 * Created by radek on 03.10.16.
 Aplikacja Radosława Subczynskiego
 */

public interface ExportProviderListener {
    void onExportSuccess(String exportSuccess);

    void onExportFail(String fileExist);
}
