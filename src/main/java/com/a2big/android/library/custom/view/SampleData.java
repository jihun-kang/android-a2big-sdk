package com.a2big.android.library.custom.view;

import java.util.ArrayList;

public class SampleData {

  //  public static final int SAMPLE_DATA_ITEM_COUNT = 24;
    public static final int SAMPLE_DATA_ITEM_COUNT = 12;

    private static String[] imageURLArray = new String[]{
            "http://outer.a2big.com/sample/1.jpg",
            "http://outer.a2big.com/sample/2.jpg",
            "http://outer.a2big.com/sample/3.jpg",
            "http://outer.a2big.com/sample/4.jpg",
            "http://outer.a2big.com/sample/5.jpg",
            "http://outer.a2big.com/sample/6.jpg",
            "http://outer.a2big.com/sample/7.jpg",
            "http://outer.a2big.com/sample/8.jpg",
            "http://outer.a2big.com/sample/9.jpg",
            "http://outer.a2big.com/sample/10.jpg",
            "http://outer.a2big.com/sample/11.jpg",
            "http://outer.a2big.com/sample/12.jpg"};


    /*
    private static String[] imageURLArray = new String[]{
            "http://outer.a2big.com/sample/1.jpg",
            "http://outer.a2big.com/sample/2.jpg",
            "http://outer.a2big.com/sample/3.jpg",
            "http://outer.a2big.com/sample/4.jpg",
            "http://outer.a2big.com/sample/5.jpg",
            "http://outer.a2big.com/sample/6.jpg",
            "http://outer.a2big.com/sample/7.jpg",
            "http://outer.a2big.com/sample/8.jpg",
            "http://outer.a2big.com/sample/9.jpg",
            "http://outer.a2big.com/sample/10.jpg",
            "http://outer.a2big.com/sample/11.jpg",
            "http://outer.a2big.com/sample/12.jpg",
            "http://outer.a2big.com/sample/13.jpg",
            "http://outer.a2big.com/sample/14.jpg",
            "http://outer.a2big.com/sample/15.jpg",
            "http://outer.a2big.com/sample/16.jpg",
            "http://outer.a2big.com/sample/17.jpg",
            "http://outer.a2big.com/sample/18.jpg",
            "http://outer.a2big.com/sample/19.jpg",
            "http://outer.a2big.com/sample/20.jpg",
            "http://outer.a2big.com/sample/21.jpg",
            "http://outer.a2big.com/sample/22.jpg",
            "http://outer.a2big.com/sample/23.jpg",
            "http://outer.a2big.com/sample/24.jpg"};

*/

    public static ArrayList<String> generateSampleData() {
        final ArrayList<String> data = new ArrayList<String>(SAMPLE_DATA_ITEM_COUNT);

        for (int i = 0; i < SAMPLE_DATA_ITEM_COUNT; i++) {
            //data.add("SAMPLE #");
            data.add(imageURLArray[i]);
        }

        return data;
    }
/*

    public static ArrayList<String> generateSampleData2() {
        final ArrayList<String> data = new ArrayList<String>(SAMPLE_DATA_ITEM_COUNT);

        for (int i = 0; i < SAMPLE_DATA_ITEM_COUNT; i++) {
            //data.add("SAMPLE #");
            data.add(imageURLArray2[i]);
        }

        return data;
    }
    public static ArrayList<String> generateSampleData3() {
        final ArrayList<String> data = new ArrayList<String>(SAMPLE_DATA_ITEM_COUNT);

        for (int i = 0; i < SAMPLE_DATA_ITEM_COUNT; i++) {
            //data.add("SAMPLE #");
            data.add(imageURLArray3[i]);
        }

        return data;
    }
    public static ArrayList<String> generateSampleData4() {
        final ArrayList<String> data = new ArrayList<String>(SAMPLE_DATA_ITEM_COUNT);

        for (int i = 0; i < SAMPLE_DATA_ITEM_COUNT; i++) {
            //data.add("SAMPLE #");
            data.add(imageURLArray4[i]);
        }

        return data;
    }
*/
}