package info.androidhive.firebase.ExportCSV;

import java.util.ArrayList;

/**
 * Created by radek on 03.10.16.
 Aplikacja Radosława Subczynskiego
 */

public interface ExportProviderComponent {
    void saveCSVtoFile(ArrayList devicesList, String csvName);

}
