package ranglerz.com.pk.estate;

import android.app.DatePickerDialog;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;



public class FindYourDreamProperties extends AppCompatActivity {

    Spinner sp_choseCity, sp_propertyType, sp_propertyStatu, sp_bedrooms, sp_bathrooms;
    Spinner sp_minPrice, sp_maxPrice;
    EditText et_availableFrom;
    Button bt_search;

    String selectedCity, selectedPropertyType, selectedPropertyStatus, selectedBedrooms, selectedBathrooms, selectedMinPrice, selectedMaxPrice;

    private DatePickerDialog datePictkerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_your_dream_properties);

        init();
        datePictureDilaogOnETAvail();
        searchButtonClickHandler();


    }//end of onCreate

    public void init(){

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(FindYourDreamProperties.this ,R.color.colorSkyBlue)));
        getSupportActionBar().setTitle(R.string.tv_pk_estate);
        sp_choseCity = (Spinner) findViewById(R.id.sp_chose_city);
        sp_propertyType = (Spinner) findViewById(R.id.sp_property_type);
        sp_propertyStatu = (Spinner) findViewById(R.id.sp_property_status);
        sp_bedrooms = (Spinner) findViewById(R.id.sp_bedrooms);
        sp_bathrooms = (Spinner) findViewById(R.id.sp_bathrooms);
        sp_minPrice = (Spinner) findViewById(R.id.sp_min_price);
        sp_maxPrice = (Spinner) findViewById(R.id.sp_max_price);
        et_availableFrom = (EditText) findViewById(R.id.ed_available_from);
        bt_search = (Button) findViewById(R.id.bt_search);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    }//end of init

    public void datePictureDilaogOnETAvail(){
        et_availableFrom.setFocusable(false);
        et_availableFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                datePictkerDialog = new DatePickerDialog(FindYourDreamProperties.this, new DatePickerDialog.OnDateSetListener() {


                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        et_availableFrom.setText(dateFormatter.format(newDate.getTime()));
                    }

                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


                datePictkerDialog.show();
                datePictkerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

            }
        });
    }//end of datePickerDialog

    public void searchButtonClickHandler(){

        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean spChecker =  validatingSpinner();
                if (spChecker){
                    Toast.makeText(FindYourDreamProperties.this, "Sending data....", Toast.LENGTH_SHORT).show();
                }else {
                    //Toast.makeText(this, "Sending data....", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    public boolean validatingSpinner(){
        boolean isSpinnerEmpty;
        int spPositionCity = sp_choseCity.getSelectedItemPosition();
        int spPositionPropertyType = sp_propertyType.getSelectedItemPosition();
        int spPositionPropertyStatus = sp_propertyStatu.getSelectedItemPosition();
        int spPositionBedrooms = sp_bedrooms.getSelectedItemPosition();
        int spPositionBathrooms = sp_bathrooms.getSelectedItemPosition();
        int spPositionMinPrice = sp_minPrice.getSelectedItemPosition();
        int spPositionMaxPrice = sp_maxPrice.getSelectedItemPosition();

        if (spPositionCity==0){

            Toast.makeText(this, "Please Chose City", Toast.LENGTH_SHORT).show();
            isSpinnerEmpty = false;

        }else if(spPositionPropertyType==0){

            Toast.makeText(this, "Please Chose Property Type", Toast.LENGTH_SHORT).show();
            isSpinnerEmpty = false;

        }else if(spPositionPropertyStatus==0){

            Toast.makeText(this, "Please Chose Property Status", Toast.LENGTH_SHORT).show();
            isSpinnerEmpty = false;

        }else if(spPositionBedrooms==0){

            Toast.makeText(this, "Please Chose Bedrooms", Toast.LENGTH_SHORT).show();
            isSpinnerEmpty = false;

        }else if(spPositionBathrooms==0){

            Toast.makeText(this, "Please Chose Bathrooms", Toast.LENGTH_SHORT).show();
            isSpinnerEmpty = false;

        }else if (spPositionMinPrice==0){

            Toast.makeText(this, "Please Chose Min Price", Toast.LENGTH_SHORT).show();
            isSpinnerEmpty = false;

        }else if(spPositionMaxPrice==0){

            Toast.makeText(this, "Please Chose Max Price", Toast.LENGTH_SHORT).show();
            isSpinnerEmpty = false;

        }else if(et_availableFrom.getText().length()==0){
            Toast.makeText(this, "Please Set Date", Toast.LENGTH_SHORT).show();
            isSpinnerEmpty = false;
        }
        else {

            selectedCity = sp_choseCity.getSelectedItem().toString();
            selectedPropertyType = sp_propertyType.getSelectedItem().toString();
            selectedPropertyStatus = sp_propertyStatu.getSelectedItem().toString();
            selectedBedrooms = sp_bedrooms.getSelectedItem().toString();
            selectedBathrooms = sp_bathrooms.getSelectedItem().toString();
            selectedMinPrice = sp_minPrice.getSelectedItem().toString();
            selectedMaxPrice = sp_maxPrice.getSelectedItem().toString();

            Log.e("TAGE", "TEST VALUES: " + selectedCity);
            Log.e("TAGE", "TEST VALUES: " + selectedPropertyType);
            Log.e("TAGE", "TEST VALUES: " + selectedPropertyStatus);
            Log.e("TAGE", "TEST VALUES: " + selectedBedrooms);
            Log.e("TAGE", "TEST VALUES: " + selectedBathrooms);
            Log.e("TAGE", "TEST VALUES: " + selectedMinPrice);
            Log.e("TAGE", "TEST VALUES: " + selectedMaxPrice);

            isSpinnerEmpty = true;

        }

        return isSpinnerEmpty;

    }


}
