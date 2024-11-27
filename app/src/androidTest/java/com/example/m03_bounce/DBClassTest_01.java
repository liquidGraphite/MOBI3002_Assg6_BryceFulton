package com.example.m03_bounce;

import android.content.Context;
import android.graphics.Color;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DBClassTest_01 {

    private DBClass dbClass;

    @Before
    public void setUp() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        dbClass = new DBClass(appContext);
        dbClass.clearAll();
    }

    @Test
    public void testSaveAndFindAll() {
        // Create DataModel instances with the updated constructor
        DataModel a = new DataModel(0, "TestBall1", 20.0f, 20.0f, -4.0f, 4.0f, Color.RED);
        dbClass.save(a);

        a = new DataModel(0, "TestBall2", 30f, 30f, 3f, -3f, Color.BLUE);
        dbClass.save(a);

        // Retrieve all entries from the database
        List<DataModel> dataList = dbClass.findAll();

        // Check if two entries are retrieved
        assertTrue("Expected 2 entries, but found " + dataList.size(), dataList.size() == 2);
    }

    @Test
    public void testSaveAndRetrieveDataModel() {
        DataModel newBall = new DataModel(0, "TestBall", 5.0f, 5.0f, 5.0f, 5.0f, Color.GREEN);
        dbClass.save(newBall);

        List<DataModel> dataList = dbClass.findAll();

        assertTrue("No entries found in the database.", dataList.size() > 0);

        DataModel one = dataList.get(0);

        // Verify the data
        boolean dataMatches = (one.getX() == 5.0f) &&
                (one.getY() == 5.0f) &&
                (one.getDx() == 5.0f) &&
                (one.getDy() == 5.0f);

        assertTrue("DataModel data does not match expected values.", dataMatches);
    }
}