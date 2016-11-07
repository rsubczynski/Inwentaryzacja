package info.androidhive.firebase.Database.Listiners;

import java.util.ArrayList;

/**
 * Created by Radek on 2016-09-25.
 Aplikacja Radosława Subczynskiego
 */
public interface OnGetEmployedListDetailsListener {
    void onResponseGetEmployedDetailsListSuccess(ArrayList Employed);

    void onResponseGetEmployedDetailsListFail();
}
