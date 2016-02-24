package com.a2big.android.library.custom.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.a2big.android.library.R;

import java.util.ArrayList;

/**
 * Created by a2big on 16. 2. 24..
 */

 public class AppAdapter extends ArrayAdapter<ShareApp> {
    private Context mContext;
    private ArrayList<ShareApp> items;
    public AppAdapter(Context context, int textViewResourceId, ArrayList<ShareApp> items) {
        super(context, textViewResourceId, items);
        this.items = items;
        mContext = context;
    }


    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           // LayoutInflater vi = LayoutInflater.from(mContext).inflate(R.layout.item_view, parent, false);

            v = vi.inflate(R.layout.share_dialog_row, null);
        }

        ShareApp p = items.get(position);
        if( p != null){
            TextView label=(TextView)v.findViewById(R.id.label);

            label.setText(getItem(position).getName());
            Drawable drawable = mContext.getResources().getDrawable(getItem(position).getImage());

            ImageView icon=(ImageView)v.findViewById(R.id.icon);
            icon.setImageDrawable(drawable);
        }

        return v;
    }

}




