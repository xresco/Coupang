package com.coupang.mobile002.abed.view.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.coupang.mobile002.abed.R;
import com.coupang.mobile002.abed.model.Memo;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mindvalley on 15/05/2016.
 */

public class CalendarAdapter extends ArrayAdapter<Date> {
    // for view inflation
    private LayoutInflater inflater;

    private HashMap<String, List<Memo>> memos;

    public CalendarAdapter(Context context, ArrayList<Date> days) {
        super(context, R.layout.control_calendar_day, days);
        inflater = LayoutInflater.from(context);
        memos = Memo.getAllMemos();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        // day in question
        Calendar calendar = dateToCalendar(getItem(position));
        // today
        Date today = new Date();
        Calendar today_calendar = dateToCalendar(today);
        // inflate item if it does not exist yet
        if (view == null)
            view = inflater.inflate(R.layout.control_calendar_day, parent, false);

        // if this day has an event, specify event image
        view.setBackgroundResource(0);
        // clear styling
        ((TextView) view).setTypeface(null, Typeface.NORMAL);
        ((TextView) view).setTextColor(Color.BLACK);

        if (calendar.get(Calendar.DATE) == today_calendar.get(Calendar.DATE)
                && calendar.get(Calendar.MONTH) == today_calendar.get(Calendar.MONTH)
                && calendar.get(Calendar.YEAR) == today_calendar.get(Calendar.YEAR)) {
            // if it is today, set it to blue/bold
            ((TextView) view).setTypeface(null, Typeface.BOLD);
            ((TextView) view).setTextColor(ContextCompat.getColor(view.getContext(), R.color.today));
            YoYo.with(Techniques.Shake)
                    .duration(2000)
                    .playOn(view);

        } else {
            // if this day is outside current month, grey it out
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
                ((TextView) view).setTextColor(ContextCompat.getColor(view.getContext(), R.color.colorSunday));
            else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
                ((TextView) view).setTextColor(ContextCompat.getColor(view.getContext(), R.color.colorSaturday));
            else
                ((TextView) view).setTextColor(ContextCompat.getColor(view.getContext(), R.color.greyed_out));
        }
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        if(memos.get(formatter.format(getItem(position)))!=null)
            view.setBackgroundColor(Color.GREEN);

        // set text
        ((TextView) view).setText(String.valueOf(calendar.get(Calendar.DATE)));

        return view;
    }

    private Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
}