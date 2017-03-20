package ranglerz.com.pk.estate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class Home extends BaseActvitvityForDrawer {

    Button btFindYourDreamHome, btSubmitYourProperty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_home, null, false);
        mDrawerLayout.addView(contentView, 0);

        init();
        buttosClick();
    }

    public void init(){
        /*getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(Home.this ,R.color.colorSkyBlue)));
        getSupportActionBar().setTitle(R.string.tv_pk_estate);*/
        btFindYourDreamHome = (Button) findViewById(R.id.bt_find_your_dream_home);
        btSubmitYourProperty = (Button) findViewById(R.id.bt_submit_your_prooperty);
    }

    public void buttosClick(){

        btSubmitYourProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent submitProperty = new Intent(Home.this, SubmitProperty.class);
                startActivity(submitProperty);
            }
        });//end of submit property button


        btFindYourDreamHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findDreamHome = new Intent(Home.this, FindYourDreamProperties.class);
                startActivity(findDreamHome);
            }
        });//end of find property button
    }

}
