package info.androidhive.firebase.ExportCSV;

import android.os.Environment;

import java.io.File;

/**
 * Created by radek on 16.10.16.
 Aplikacja Radosława Subczynskiego
 */

public class  FileConfig {
    public static final String FILE_FORMAT = ".csv";
    public static final String FILE_URL = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "MyDir" + File.separator;
    public static final String FOLDER_CREATE_URL = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "MyDir";
    public static final String FIREBASE_URL_DEVICES_DETAILS = "https://androihave.firebaseio.com/Urzadzenia/";
}
