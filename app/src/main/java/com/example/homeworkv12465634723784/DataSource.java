package com.example.homeworkv12465634723784;

import java.util.ArrayList;
import java.util.List;

class DataSource {

    private static final int SPAN_COUNT_PORTRAIT = 3, SPAN_COUNT_LANDSCAPE = 4;

    private static final int COUNT_OF_ITEMS = 100;

    private List<MyData> myData;

    private static DataSource sInstance;

    private DataSource() {
        myData = new ArrayList<>();

        for (int i = 0; i < COUNT_OF_ITEMS; i++){
            myData.add(new MyData(i+1));
        }
    }

    static int getSpanCountPortrait() {
        return SPAN_COUNT_PORTRAIT;
    }

    static int getSpanCountLandscape() {
        return SPAN_COUNT_LANDSCAPE;
    }

    static void addItem() {
        final int newPosition = DataSource.getInstance().getData().size() + 1;
        DataSource.getInstance().getData().add(new MyData(newPosition));
    }

    void updateSize(int size) {
        for (int i = myData.size(); i < size; i++){
            myData.add(new MyData(i+1));
        }
    }

    List<MyData> getData() {
        return myData;
    }

    synchronized static  DataSource getInstance() {
        if (sInstance == null){
            sInstance = new DataSource();
        }
        return sInstance;
    }

    static class MyData {
        int value;

        MyData(int value) {
            this.value = value;
        }
    }
}
