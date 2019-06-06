package kz.qazapp.myatek.fragments.homeworkpack;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

@SuppressLint("ValidFragment")
public class HourDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    int hour, min;
    EditText textDate;
    public HourDialog(View v) {
        textDate =(EditText)v;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int min = c.get(Calendar.MINUTE);
        return new TimePickerDialog(getContext(), this, hour, min, DateFormat.is24HourFormat(getContext()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        int hourF = hourOfDay;
        int minF = minute;

        String date = hourF+":"+minF;
        textDate.setText(date);
    }
}
