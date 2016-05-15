package com.abed.coupangtest;

import com.abed.coupangtest.model.Memo;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MemoUnitTest {
    @Test
    public void insertionCorrect() throws Exception {
        Memo memo =new Memo("body","1/2/1991");
        memo.save();
        assertEquals(new Select().from(Memo.class).queryList().size(),1);
    }
}