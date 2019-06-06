package kz.qazapp.myatek.fragments.homeworkpack;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

@SuppressLint("ValidFragment")
public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener{
    int year, month, day, hour, min;
    int yearF, monthF, dayF, hourF, minF;
    EditText textDate;

    @SuppressLint("ValidFragment")
    public DateDialog(View v) {
        textDate =(EditText)v;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getContext(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearF = year;
        monthF = month+1;
        dayF = dayOfMonth;
        String date = dayF+"/"+monthF+"/"+year;
        textDate.setText(date);

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        min = c.get(Calendar.MINUTE);

    }
}
