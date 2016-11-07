package info.androidhive.firebase.ExportCSV;


import android.support.annotation.NonNull;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



/**
 * Created by radek on 03.10.16.
 Aplikacja Radosława Subczynskiego
 */

public class CSVProviderModule extends DefaultExportProviderModule {


    @Override
    public void saveCSVtoFile(final ArrayList devicesList, final String csvName) {
        //TODO async task
        Thread t = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < devicesList.size(); i++) {
                    getDeviceDetailsList(devicesList.get(i).toString(), FileConfig.FIREBASE_URL_DEVICES_DETAILS, csvName);
                }

            }

        });

        t.start();
        broadcastSaveCsvToFileSuccess(csvName + FileConfig.FILE_FORMAT);
    }

    private ArrayList<String> devicesDetails = new ArrayList<>();


    private void getDeviceDetailsList(final String value, String firebaseUrlDevicesDetails, final String csvName) {

        Firebase ref = new Firebase(firebaseUrlDevicesDetails + value);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                devicesDetails.clear();
                boolean findValueDetails = false;
                devicesDetails.add(value);
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if (postSnapshot.getValue() != null) {
                        String DataBaseKey = postSnapshot.getKey();
                        String DatabaseValue = postSnapshot.getValue().toString();
                        devicesDetails.add(DataBaseKey + "," + DatabaseValue);
                        findValueDetails = true;

                    }
                }
                if (findValueDetails) {
                    SaveFile(devicesDetails, csvName);
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }

        });
    }


    private void SaveFile(ArrayList devicesList, String csvName) {
        File file = createFile(FileConfig.FILE_URL + csvName + FileConfig.FILE_FORMAT);
        createdFolder(FileConfig.FOLDER_CREATE_URL);
        saveDataToFile(file, devicesList);
    }

    private void saveDataToFile(File file, ArrayList devicesList) {
        if (file.exists()) {
            try {
                FileWriter fileWriter = new FileWriter(file, true);
                BufferedWriter bfWriter = new BufferedWriter(fileWriter);
                bfWriter.write(devicesList.toString() + "\n");
                bfWriter.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            broadcastSaveCsvToFileFail("Blad zapisu pliku");
        }
    }

    private void createdFolder(String folderCreateUrl) {
        File fileDir = new File(folderCreateUrl);
        if (!fileDir.exists()) {
            try {
                fileDir.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @NonNull
    private File createFile(String fileUrl) {
        File file = new File(fileUrl);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return file;
    }

}
