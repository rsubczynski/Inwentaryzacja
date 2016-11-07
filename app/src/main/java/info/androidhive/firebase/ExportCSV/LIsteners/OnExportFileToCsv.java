package info.androidhive.firebase.ExportCSV.LIsteners;

/**
 * Created by radek on 03.10.16.
 Aplikacja Radosława Subczynskiego
 */

public interface OnExportFileToCsv {
    void OnExportFileToCsvSuccess(String searchUser);
    void OnExportFileToCsvFail(String FailSaveFile);
}
