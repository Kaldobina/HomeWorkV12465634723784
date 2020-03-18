package com.example.homeworkv12465634723784;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataSource {

    private static int COUNT_OF_ITEMS = 100, UPPER_RANGE = 100;

    private static List<MyData> myData;

    private static DataSource sInstance;

    public DataSource() {
        myData = new ArrayList<>();

        for (int i = 0; i < COUNT_OF_ITEMS; i++){
            myData.add(new MyData(new Random().nextInt(UPPER_RANGE), i % 2 == 0 ? R.color.colorEven : R.color.colorOdd));
        }
    }

    public static void addItem() {
        int position = COUNT_OF_ITEMS++;
        myData.add(position, new MyData(new Random().nextInt(UPPER_RANGE), R.color.colorEven));
        MainActivity.adapter.notifyItemInserted(position);
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
        int color;
        int value;

        public MyData(int value, int color) {
            this.color = color;
            this.value = value;
        }
    }
}
