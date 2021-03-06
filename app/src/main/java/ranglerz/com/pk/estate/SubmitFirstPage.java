package ranglerz.com.pk.estate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

/**
 * Created by User-10 on 16-Mar-17.
 */
public class SubmitFirstPage extends Fragment {


    public static EditText ed_protpertyTitle, ed_propertyPrice, ed_noOfRooms, ed_ofBathrooms, ed_floors, ed_description, ed_structureOfProperty, ed_landAread;
    public static EditText ed_flooringStructer, ed_wallsStructure, ed_doorsStructure, ed_windowsStructure, ed_electricalStructure;

    public static Spinner sp_propertyType, sp_propertyFor, sp_landArea, sp_propertyAvail;

    public static LinearLayout ll_propertyInerNumber, ll_property_flooring, ll_walls_door, ll_windwos_electrical;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.submit_first_page, container, false);

        ed_protpertyTitle = (EditText) view.findViewById(R.id.et_property_title);
        ed_propertyPrice = (EditText) view.findViewById(R.id.ed_price);
        ed_noOfRooms = (EditText) view.findViewById(R.id.ed_rooms);
        ed_ofBathrooms = (EditText) view.findViewById(R.id.ed_no_of_bathrooms);
        ed_floors = (EditText) view.findViewById(R.id.ed_no_of_floors);
        ed_description = (EditText) view.findViewById(R.id.et_description);
        /*ed_structureOfProperty = (EditText) view.findViewById(R.id.ed_structure_of_property);
        ed_flooringStructer = (EditText) view.findViewById(R.id.ed_flooring_structure);
        ed_wallsStructure = (EditText) view.findViewById(R.id.ed_walls_structure);
        ed_doorsStructure = (EditText) view.findViewById(R.id.ed_doors_structure);
        ed_windowsStructure = (EditText) view.findViewById(R.id.ed_windows_structure);
        ed_electricalStructure = (EditText) view.findViewById(R.id.ed_electrical_structure);
        */
        ed_landAread = (EditText) view.findViewById(R.id.ed_land_area);


        sp_propertyType = (Spinner) view.findViewById(R.id.sp_property_type);
        sp_propertyFor = (Spinner) view.findViewById(R.id.sp_property_for);
        sp_landArea = (Spinner) view.findViewById(R.id.sp_land_area);
        sp_propertyAvail = (Spinner) view.findViewById(R.id.sp_property_avail);

        ll_propertyInerNumber = (LinearLayout) view.findViewById(R.id.ll_propertyInerNumber);

        /*ll_property_flooring = (LinearLayout)  view.findViewById(R.id.property_flooring);
        ll_walls_door = (LinearLayout)  view.findViewById(R.id.walls_doors);
        ll_windwos_electrical = (LinearLayout)  view.findViewById(R.id.ll_window_electrical);

*/
        checkingPropertytype();



        ed_protpertyTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ed_propertyPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ed_noOfRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ed_ofBathrooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ed_floors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ed_description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /*
        ed_structureOfProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ed_flooringStructer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ed_wallsStructure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ed_doorsStructure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ed_windowsStructure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ed_electricalStructure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        ll_propertyInerNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ll_property_flooring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ll_walls_door.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ll_windwos_electrical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
*/

        sp_propertyFor.getSelectedItem().toString();








        return view;
    }


    public void checkingPropertytype(){



        sp_propertyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (sp_propertyType.getSelectedItem().equals("Land") || sp_propertyType.getSelectedItem().equals("Plot")){

                    ll_propertyInerNumber.setVisibility(View.GONE);

                  /*  ll_property_flooring.setVisibility(View.GONE);
                    ll_walls_door.setVisibility(View.GONE);
                    ll_windwos_electrical.setVisibility(View.GONE);*/

                }

                if (sp_propertyType.getSelectedItem().equals("House")
                        || sp_propertyType.getSelectedItem().equals("Flat")
                        || sp_propertyType.getSelectedItem().equals("Commercial")
                        || sp_propertyType.getSelectedItem().equals("Shop")){

                    ll_propertyInerNumber.setVisibility(View.VISIBLE);


                    /*ll_property_flooring.setVisibility(View.VISIBLE);
                    ll_walls_door.setVisibility(View.VISIBLE);
                    ll_windwos_electrical.setVisibility(View.VISIBLE);*/
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


    }



}
