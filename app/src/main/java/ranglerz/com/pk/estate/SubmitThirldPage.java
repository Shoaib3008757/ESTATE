package ranglerz.com.pk.estate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by User-10 on 16-Mar-17.
 */
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class SubmitThirldPage extends Fragment {

    public static EditText m_ed_location, m_ed_city, m_ed_phoneNumber, m_ed_Name, m_ed_email;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.submit_third_page, container, false);


        m_ed_location = (EditText) view.findViewById(R.id.m_ed_location);
        m_ed_city = (EditText) view.findViewById(R.id.m_ed_city);
        m_ed_phoneNumber = (EditText) view.findViewById(R.id.m_ed_phone_number);
        m_ed_Name = (EditText) view.findViewById(R.id.m_ed_name);
        m_ed_email = (EditText) view.findViewById(R.id.m_ed_email);





        return view;
    }


}