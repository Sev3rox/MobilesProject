package com.example.smproject.ui.Photos;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smproject.MainActivity;
import com.example.smproject.R;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context context;
    List<Photo> photos;

    int pom2=0;
    CustomAdapter(Context context, List<Photo> photos){
        this.context=context;
        this.photos=photos;
    }
    @Override
    public int getCount(){
        return photos.size();
    }
    @Override
    public Object getItem(int position){return photos.get(position);}

    @Override
    public long getItemId(int position){return photos.indexOf(getItem(position));}

    private class ViewHolder{
        ImageView profile_pic;
        TextView url_name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder=null;
        LayoutInflater inf=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView==null||(pom2<=10)){
            convertView=inf.inflate(R.layout.list_item,null);
            holder=new ViewHolder();
            holder.url_name=(TextView) convertView.findViewById(R.id.url_name);
            holder.profile_pic=(ImageView) convertView.findViewById(R.id.profile_pic);
            Photo row_pos=photos.get(position);

            holder.url_name.setText(row_pos.geturl());
            String pom=row_pos.geturl();
            Log.d(">>>>>>>>>>>>>  ", ""+pom);
            Glide.with(MainActivity.context).load(pom).into(holder.profile_pic);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        return convertView;
    }

}
