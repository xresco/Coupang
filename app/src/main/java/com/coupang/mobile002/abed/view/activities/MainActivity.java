package com.coupang.mobile002.abed.view.activities;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;


import com.coupang.mobile002.abed.R;
import com.coupang.mobile002.abed.model.Memo;
import com.coupang.mobile002.abed.view.customeViews.CalendarView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView = (CalendarView) findViewById(R.id.calendar_view);

        calendarView.setOnDaySelectListener(new CalendarView.OnDaySelectListener() {
            @Override
            public void onDayPress(int position, final Date date) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Enter your memo");

                // Set up the input
                final EditText input = new EditText(MainActivity.this);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String m_Text = input.getText().toString();
                        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        Memo memo = new Memo(m_Text, formatter.format(date));
                        memo.save();

                        calendarView.updateCalendar();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }

            @Override
            public void onDayLongPress(Date date) {
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(MainActivity.this);
                builderSingle.setTitle("All memos");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        MainActivity.this,
                        android.R.layout.simple_expandable_list_item_1);

                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                for(Memo memo:Memo.getMemosInDate(formatter.format(date))) {
                    arrayAdapter.add(memo.getBody());
                }
                builderSingle.setAdapter(
                        arrayAdapter,null);
                builderSingle.show();
            }
        });
    }

}
