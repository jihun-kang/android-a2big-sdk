package com.a2big.android.library.custom.adapter;

public class ShareApp {

   private int image;
   private String name;

   public ShareApp(int image, String name){
       this.name = name;
       this.image = image;
   }

   public String getName() {
       return name;
   }

   public int getImage() {
       return image;
   }

}
