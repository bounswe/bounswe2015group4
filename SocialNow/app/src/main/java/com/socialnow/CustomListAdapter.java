package com.socialnow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

/**
 * Created by mugekurtipek on 29/10/15.
 */
public class CustomListAdapter extends BaseAdapter {
    List<String> title;
    List<Date> date;
    List <Bitmap> photo;

    Context context;

    private static LayoutInflater inflater=null;

    public CustomListAdapter(Activity mainActivity, List titleA, List dateA, List photoA) {
        // TODO Auto-generated constructor stub
        title=titleA;
        date=dateA;
        photo=photoA;
        context=mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return title.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView txtTitle;
        TextView txtDate;
        ImageView imgPhoto;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.list_layout_detail, null);
        holder.txtTitle=(TextView) rowView.findViewById(R.id.txtTitle);
        holder.txtDate=(TextView) rowView.findViewById(R.id.txtDate);
        holder.imgPhoto=(ImageView) rowView.findViewById(R.id.imgEvent);
        holder.txtTitle.setText(title.get(position));
        holder.txtDate.setText(date.get(position).toString());
        holder.imgPhoto.setImageBitmap(photo.get(position));
        return rowView;
    }
}
