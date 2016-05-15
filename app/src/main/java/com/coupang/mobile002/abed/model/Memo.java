package com.coupang.mobile002.abed.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mindvalley on 11/02/2016.
 */
@Table(database = Coupang_Database.class)
public class Memo extends BaseModel implements Parcelable {

    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    String body;

    @Column
    String date;


    Memo() {
    }

    public Memo(String body, String date) {

        this.body = body;
        this.date = date;
    }


    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(body);
        dest.writeSerializable(date);
    }

    public Memo(Parcel in) {
        body = in.readString();
        date = in.readString();

    }

    public static final Creator<Memo> CREATOR = new Creator<Memo>() {
        @Override
        public Memo createFromParcel(Parcel in) {
            return new Memo(in);
        }

        @Override
        public Memo[] newArray(int size) {
            return new Memo[size];
        }
    };

    public static HashMap<String, List<Memo>> getAllMemos() {
        HashMap<String, List<Memo>> memoHashMap = new HashMap<>();
        List<Memo> memos = new Select().from(Memo.class).queryList();
        for (Memo memo : memos) {
            Log.d("memo::",memo.body+"_"+memo.date);
            if(memoHashMap.get(memo.getDate())==null)
                 memoHashMap.put(memo.getDate(), new LinkedList<Memo>());
            memoHashMap.get(memo.getDate()).add(memo);
        }
        return memoHashMap;
    }


    public static List<Memo> getMemosInDate(String date){
        return new Select().from(Memo.class).where(Memo_Table.date.eq(date)).queryList();
    }

}
