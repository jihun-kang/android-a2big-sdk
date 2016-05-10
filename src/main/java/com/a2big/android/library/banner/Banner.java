package com.a2big.android.library.banner;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.a2big.android.library.R;
import com.a2big.android.library.init.A2BigApp;
import com.a2big.android.library.utils.DevLog;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by a2big on 16. 4. 26..
 */
public class Banner extends RelativeLayout implements ViewPager.OnPageChangeListener{

    private LinearLayout point_group;
    private static final int MSG_GETMESSAGE = 1;
    private Handler handler;
    private Context context;

    private int preEnablePositon = 0;

    //private ViewPager viewPager;
    private BannerViewPager viewPager;
    private boolean isStop = false;
    private List<ImageView> imagelist = new ArrayList<ImageView>();
    //private List<NetworkImageView> imagelist = new ArrayList<NetworkImageView>();

    private ImageView singleImageView;
   //private NetworkImageView singleImageView;

    private View view;

    private LinearLayout.LayoutParams params;
    private OnItemListenner listenner;
    private int imageposition = 0;


//    List<Bitmap> ;
    ArrayList<BannerObject.BannerItem> mImageItem;
    public interface OnItemListenner{
        public void OnChecked(View v, int position);
    }

    public void setItemListenner(OnItemListenner listenner){
        this.listenner = listenner;
    }


    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHandler();
        init(context);
    }

    public Banner(Context context) {
        super(context);
        initHandler();
        init(context);


    }

    public Banner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initHandler();
        init(context);


    }

    boolean isForceMove = false;
    private void initHandler(){
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case MSG_GETMESSAGE:

                        if(isForceMove==false) {
                            int newindex = viewPager.getCurrentItem() + 1;
                            viewPager.setCurrentItem(newindex);
                        }
                        else{
                            isForceMove = false;
                        }
                        break;
                }
            }
        };
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.class_banner, this, true);
        viewPager = (BannerViewPager)findViewById(R.id.viewpager);
        viewPager.setPagingEnabled(false);
        point_group = (LinearLayout)findViewById(R.id.ll_point_group);
        point_group.setVisibility(View.INVISIBLE);
        this.context = context;
    }

    public void initImage(List<Integer> imageId) {

        int num = imageId.size();
        for (int i = 0; i < num; i++) {
            singleImageView = new NetworkImageView(context);
            singleImageView.setBackgroundResource(imageId.get(i));
            imagelist.add(singleImageView);

            view = new View(context);
            view.setBackgroundResource(R.drawable.point_background);
            params = new LinearLayout.LayoutParams(12, 12);
            params.leftMargin = 5;
            view.setEnabled(false);
            view.setLayoutParams(params);
            point_group.addView(view);

            singleImageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    listenner.OnChecked(singleImageView,imageposition);
                }
            });
        }

        viewPager.setAdapter(new MyAdapter());
        viewPager.setOnPageChangeListener(this);
        point_group.getChildAt(0).setEnabled(true);
        int index = (Integer.MAX_VALUE / 2)
                - ((Integer.MAX_VALUE / 2) % imagelist.size());
        viewPager.setCurrentItem(index);

        Thread myThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (!isStop) {
                    SystemClock.sleep(3000);
                    handler.obtainMessage(MSG_GETMESSAGE).sendToTarget();
                }
            }
        });
        myThread.start();
    }
    private ImageLoader mImageLoader;

    public void initBannerImage(ArrayList<BannerObject.BannerItem> imageId) {

        A2BigApp app = A2BigApp.getApplication();
        mImageLoader = app.getImageLoader();

        mImageItem = imageId;

        int num = imageId.size();
        for (int i = 0; i < num; i++) {
            singleImageView = new ImageView(context);
            int targetW = singleImageView.getWidth();
            int targetH = singleImageView.getHeight();
            DevLog.defaultLogging("{initBannerImage}." + targetW + " " + targetH);

            ///singleImageView.setBackgroundResource(imageId.get(i));
           /// singleImageView.setImageUrl(imageId.get(i).getPhotoUrl(), mImageLoader);    //start load
            singleImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.with(context).load(imageId.get(i).getPhotoUrl()).into(singleImageView);
            //singleImageView.setImageUrl(imageId.get(i).getPhotoUrl(), mImageLoader);
            mImageLoader.get(imageId.get(i).getPhotoUrl(), new ImageLoader.ImageListener() {

                @Override
                public void onErrorResponse(VolleyError arg0) {

                }

                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                    Bitmap bitmap = response.getBitmap();
                    if (bitmap != null) {
                        singleImageView.setImageBitmap(bitmap);

                    } else {
                        //lookView.setImageResource(R.drawable.ic_background_v3);
                    }
                }

            });




            /// singleImageView.setImageBitmap(imageId.get(i));
            imagelist.add(singleImageView);

            view = new View(context);
            view.setBackgroundResource(R.drawable.point_background);
            params = new LinearLayout.LayoutParams(12, 12);
            params.leftMargin = 5;
            view.setEnabled(false);
            view.setLayoutParams(params);
            point_group.addView(view);

            singleImageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    listenner.OnChecked(singleImageView,imageposition);
                }
            });
        }

        viewPager.setAdapter(new MyAdapter());
        viewPager.setOnPageChangeListener(this);
        point_group.getChildAt(0).setEnabled(true);
        int index = (Integer.MAX_VALUE / 2)
                - ((Integer.MAX_VALUE / 2) % imagelist.size());
        viewPager.setCurrentItem(index);

        Thread myThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (!isStop) {
                    SystemClock.sleep(3000);
                    handler.obtainMessage(MSG_GETMESSAGE).sendToTarget();
                }
            }
        });
        myThread.start();
    }

    public void MoveNext(){
        isForceMove = true;
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }

    public void MovePrevious(){
        isForceMove = true;
        viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
    }


    class MyAdapter extends PagerAdapter {
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imagelist.get(position % imagelist.size()));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imagelist.get(position % imagelist.size()));
            ImageView imageView =  imagelist.get(position % imagelist.size());
            return imageView;
          //  return imagelist.get(position % imagelist.size());
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int position) {
        int newPositon = position % (imagelist.size());
        point_group.getChildAt(preEnablePositon).setEnabled(false);
        point_group.getChildAt(newPositon).setEnabled(true);
        preEnablePositon = newPositon;
        imageposition = newPositon;

    }
}

