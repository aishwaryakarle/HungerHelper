package com.example.hungerhelper;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;

public class Grid_food_adapter<Private> extends ArrayAdapter<String>
{
    ListView grid;
    final String[] name;
    final Integer[] icons;
    Context context;
    LayoutInflater layoutInflater;

    public Grid_food_adapter(Context context, Integer[] icons, List name) {
        super(context,R.layout.home_grid_info,name);
        this.context=context;
        this.name= (String[]) name.toArray(new String[0]);
        this.icons=icons;
    }
    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(R.layout.home_grid_info,null);
        ImageView img=convertView.findViewById(R.id.img_info);
        TextView text=convertView.findViewById(R.id.item_info);
        img.setImageResource(icons[position]);
        text.setText(name[position]);
        return convertView;
    }
}