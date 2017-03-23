package ranglerz.com.pk.estate;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class showProperties extends AppCompatActivity {


    ListView listViewShowProperties;
    private ProgressBar progressBar;


    final String TAG = "ShowLiisActvity";


    ListView list;
    LazyAdapter adapter;
    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_properties);


        init();

        showListofProperties();
        listviewOnItemClickHander();

    }

    public void init(){
        listViewShowProperties = (ListView) findViewById(R.id.listView_show_properties);
        contactList = new ArrayList<>();

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(showProperties.this ,R.color.colorSkyBlue)));

        list=(ListView)findViewById(R.id.listView_show_properties);

        progressBar = (ProgressBar) this.findViewById(R.id.progressBar);
    }//endo of init

    public void showListofProperties(){

        new GetContacts().execute();

    }//end of showListOfProperties


    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String url = "http://www.pk.estate/app_webservices/get_properties.php";
            String jsonStr = sh.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    Log.e("TAG", "RESULT 1" + jsonObj);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("property_data");

                    Log.e("TAG", "RESULT 2" + contacts);

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String property_title = c.getString("property_title");
                        String price = c.getString("price");
                        String landArea = c.getString("land_area");
                        String city = c.getString("city");
                        String propertyType = c.getString("property_type");
                        String propertystatus = c.getString("status");
                        String description = c.getString("property_description");
                        String Imageurl = c.getString("images");
                        String phone = "03350388888";

                        final String staticURL = "http://www.pk.estate/frontend/propertyimages/";

                        String imageURL = staticURL+Imageurl;

                        Log.e("TAG", "URL 123 " + imageURL);

                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        contact.put("imageurl", imageURL);
                        contact.put("property_title", property_title);
                        contact.put("price", price);
                        contact.put("landArea", landArea);
                        contact.put("city", city);
                        contact.put("phone", phone);
                        contact.put("property_type", propertyType);
                        contact.put("status", propertystatus);
                        contact.put("property_description", description);

                        contactList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            adapter=new LazyAdapter(showProperties.this, contactList);
            list.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);


        }
    }

    @Override
    public void onDestroy()
    {
        list.setAdapter(null);
        super.onDestroy();
    }


    public void listviewOnItemClickHander(){
        listViewShowProperties.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tv_price = (TextView)view.findViewById(R.id.tv_price);
                TextView tv_propertyTitle = (TextView)view.findViewById(R.id.tv_property_title);
                TextView tv_propertyPropertyLandArea = (TextView)view.findViewById(R.id.tv_land_area);
                TextView tv_propertyCity = (TextView)view.findViewById(R.id.tv_location);
                TextView tv_propertyPhone = (TextView)view.findViewById(R.id.tv_contact);
                TextView tv_protperty_type = (TextView)view.findViewById(R.id.tv_protperty_type);
                TextView tv_protperty_status = (TextView)view.findViewById(R.id.tv_protperty_status);
                TextView tv_protperty_description = (TextView)view.findViewById(R.id.tv_protperty_description);
                ImageView image=(ImageView)view.findViewById(R.id.image);

                final BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();
                final Bitmap imageBitmap = bitmapDrawable.getBitmap();


                String propertyTitle = tv_propertyTitle.getText().toString();
                String propertyPrice = tv_price.getText().toString();
                String propertyLandArea = tv_propertyPropertyLandArea.getText().toString();
                String propertyCity = tv_propertyCity.getText().toString();
                String propertyContact = tv_propertyPhone.getText().toString();
                String propertyType = tv_protperty_type.getText().toString();
                String propertyStatus = tv_protperty_status.getText().toString();
                String propertyDescription = tv_protperty_description.getText().toString();



                Log.e("TAG", "Property TILE: " + propertyTitle);
                Log.e("TAG", "Property Price: " + propertyPrice);
                Log.e("TAG", "Property Area: " + propertyLandArea);
                Log.e("TAG", "Property City: " + propertyCity);
                Log.e("TAG", "Property Contact: " + propertyContact);
                Log.e("TAG", "Property Type: " + propertyType);
                Log.e("TAG", "Property Status: " + propertyStatus);
                Log.e("TAG", "Property Description: " + propertyDescription);


                Dialog singleListViewItemDialog = new Dialog(showProperties.this);
                singleListViewItemDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                singleListViewItemDialog.setContentView(R.layout.single_list_item_view_dialog);

                TextView dialog_tv_propertyTitle = (TextView)singleListViewItemDialog.findViewById(R.id.dialog_tv_title);
                TextView dialog_tv_price = (TextView)singleListViewItemDialog.findViewById(R.id.tv_text_price);
                TextView dialog_tv_propertyPropertyLandArea = (TextView)singleListViewItemDialog.findViewById(R.id.tv_text_area);
                TextView dialog_tv_propertyCity = (TextView)singleListViewItemDialog.findViewById(R.id.tv_text_location);
                TextView dialog_tv_propertyPhone = (TextView)singleListViewItemDialog.findViewById(R.id.tv_text_contact);
                TextView dialog_tv_protperty_type = (TextView)singleListViewItemDialog.findViewById(R.id.tv_text_type);
                TextView dialog_tv_protperty_status = (TextView)singleListViewItemDialog.findViewById(R.id.tv_text_status);
                TextView dialog_tv_protperty_description = (TextView)singleListViewItemDialog.findViewById(R.id.tv_text_description);
                ImageView dialog_image = (ImageView)singleListViewItemDialog.findViewById(R.id.dialog_property_image);

                dialog_tv_propertyTitle.setText(propertyTitle);
                dialog_tv_price.setText(propertyPrice);
                dialog_tv_propertyPropertyLandArea.setText(propertyLandArea);
                dialog_tv_propertyCity.setText(propertyCity);
                dialog_tv_propertyPhone.setText(propertyContact);
                dialog_tv_protperty_type.setText(propertyType);
                dialog_tv_protperty_status.setText(propertyStatus);
                dialog_tv_protperty_description.setText(propertyDescription);

                dialog_image.setImageBitmap(imageBitmap);






                singleListViewItemDialog.show();


            }
        });
    }
}