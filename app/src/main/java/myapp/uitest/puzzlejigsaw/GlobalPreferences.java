package myapp.uitest.puzzlejigsaw;

import android.content.Context;
import android.content.SharedPreferences;


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
}
