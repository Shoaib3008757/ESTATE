package ranglerz.com.pk.estate;

/**
 * Created by User-10 on 28-Mar-17.
 */


import android.app.Activity;
import android.content.Context;
import android.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;


public class SingleImageLazyLoad extends BaseAdapter {

    private Activity activity;
    private String[] data;
    ArrayList<HashMap<String, String>> contactList;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;

    public SingleImageLazyLoad(Activity a,ArrayList<HashMap<String, String>> contactList1) {
        activity = a;
        //data=d;
        contactList = contactList1;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return contactList.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.dilaog_single_property_images, null);

        ImageView image=(ImageView)vi.findViewById(R.id.single_image);


        imageLoader.DisplayImage(contactList.get(position).get("imageurl"), image);
        //imageLoader.DisplayImage(data[position], image);
        return vi;
    }


}