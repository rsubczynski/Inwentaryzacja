package info.androidhive.firebase.Utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import info.androidhive.firebase.Authorized.MenuActivity;

public class Utils {
    public static void getPermission(@NonNull Activity activity, @NonNull String permission) {
        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{permission}, 1);
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        View focusedView = activity.getCurrentFocus();
        if (focusedView != null) {
            inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static void startPhoneActivity(Context context,String phone) {
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        context.startActivity(phoneIntent);
    }

    public static void startSkypeActivity(Context context,String skype) {
        Uri skypeUri = Uri.parse("skype:" + skype + "?call");
        Intent skypeIntent = new Intent(Intent.ACTION_VIEW, skypeUri);
        skypeIntent.setComponent(new ComponentName("com.skype.raider", "com.skype.raider.Main"));
        skypeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(skypeIntent);
    }


    public static void StartEmailActivity(Context context, String email, String subject, String text) {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setType("plain/text");
        sendIntent.setData(Uri.parse(email));
        sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(sendIntent);
    }


// Maybe i using this in future

//    public final List<String[]> readCsv(Context context) {
//        List<String[]> questionList = new ArrayList<String[]>();
//        AssetManager assetManager = context.getAssets();
//
//        try {
//            InputStream csvStream = getResources().openRawResource(R.raw.stats);
//            InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
//            CSVReader csvReader = new CSVReader(csvStreamReader);
//            String[] line;
//
//            // throw away the header
//            csvReader.readNext();
//
//            while ((line = csvReader.readNext()) != null) {
//                questionList.add(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return questionList;
//    }

        // Maybe i using this in future
//    public static String unixDateToString(Context context, long unixDate) {
//        return new SimpleDateFormat(context.getString(R.string.date_format)).format(new Date(unixDate * 1000));
//    }


    }
