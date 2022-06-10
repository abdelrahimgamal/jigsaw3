package myapp.uitest.puzzlejigsaw;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class GlobalPreferences {

    private static final String PREFS_NAME = "Puzzle";
    private static final String TIMER_STATUS = "timer_status";
    private static final String TIMER_MINUTES = "timer_minutes";
    Context context;
    private SharedPreferences prefs;
    private final SharedPreferences.Editor prefsEditor;


    public GlobalPreferences(Context context) {
        this.context = context;
        prefs = null;
        prefs = context.getSharedPreferences(PREFS_NAME, 0);
        prefsEditor = prefs.edit();
    }


    public void setTimeStatus(boolean timeStatus) {
        prefsEditor.putBoolean(TIMER_STATUS, timeStatus);
        prefsEditor.commit();
    }
    public void setTimeMinutes(int minutes) {
        prefsEditor.putInt(TIMER_MINUTES, minutes);
        prefsEditor.commit();
    }


    public boolean getTimeStatus() {
        return prefs.getBoolean(TIMER_STATUS, true);
    }

    public int getMinutes() {
        return prefs.getInt(TIMER_MINUTES, 1);
    }



    public void storeScore(int score) {
        Gson gson = new Gson();
        String json = prefs.getString("task list", null);
        Type type = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        ArrayList<Integer> mExampleList = gson.fromJson(json, type);

        if (mExampleList == null) {
            mExampleList = new ArrayList<>();
            mExampleList.add(score);
        } else {

            if (mExampleList.contains(score)) {

            } else {
                mExampleList.add(score);
            }
        }

        String json2 = gson.toJson(mExampleList);

        prefsEditor.putString("task list", json2);
        prefsEditor.apply();
    }


    public ArrayList<Integer> getScore() {
        Gson gson = new Gson();
        String json = prefs.getString("task list", null);
        Type type = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        ArrayList<Integer> mExampleList = gson.fromJson(json, type);

        if (mExampleList == null) {
            mExampleList = new ArrayList<>();
        }
        return mExampleList;
    }
}
