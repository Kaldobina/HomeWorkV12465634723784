package com.example.homeworkv12465634723784;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private static final int SPAN_COUNT_PORTRAIT = 3, SPAN_COUNT_LANDSCAPE = 4;

    public static int COUNT_OF_ITEMS = 100;

    private static int NEW_COUNT;

    private static List<MyData> myData;

    private static DataSource sInstance;

    private DataSource() {
        myData = new ArrayList<>();

        for (int i = 0; i < COUNT_OF_ITEMS; i++){
            myData.add(new MyData(i+1));
        }
    }

    public static int getSpanCountPortrait() {
        return SPAN_COUNT_PORTRAIT;
    }

    public static int getSpanCountLandscape() {
        return SPAN_COUNT_LANDSCAPE;
    }

    public static void addItem() {
        int position = COUNT_OF_ITEMS++;
        myData.add(position, new MyData(position + 1));
    }

    public List<MyData> getData() {
        return myData;
    }

    public synchronized static  DataSource getInstance() {
        if (sInstance == null){
            sInstance = new DataSource();
        }
        return sInstance;
    }

    public static class MyData {
        int value;

        public MyData(int value) {
            this.value = value;
        }
    }
}
