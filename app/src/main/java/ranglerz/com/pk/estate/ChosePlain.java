package ranglerz.com.pk.estate;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ranglerz.com.pk.estate.util.IabBroadcastReceiver;
import ranglerz.com.pk.estate.util.IabHelper;
import ranglerz.com.pk.estate.util.IabResult;
import ranglerz.com.pk.estate.util.Inventory;
import ranglerz.com.pk.estate.util.Purchase;

public class ChosePlain extends AppCompatActivity implements IabBroadcastReceiver.IabBroadcastListener{


    RadioGroup radioGroup;
    RadioButton radioButton;
    Button prcceedButton;

    private ProgressBar bar;

    int idx = -1;

    private final String serverUrlLogin = "http://pk.estate/app_webservices/login.php";


    static final String TAG = "CHOSE PLAIN";
    //in app billing

    static final String SKU_PLANE_1 = "plan1";
    static final String SKU_PLANE_2 = "plan2";
    static final String SKU_PLANE_3 = "plan3";
    static final String SKU_PLANE_4 = "plan4";
    static final String SKU_PLANE_5 = "plan5";
    static final String SKU_PLANE_6 = "plan6";
    static final String SKU_PLANE_7 = "plan7";
    static final String SKU_PLANE_8 = "plan8";
    static final String SKU_PLANE_9 = "plan9";
    static final String SKU_PLANE_10 = "plan10";
    static final String SKU_PLANE_11 = "plan11";
    static final String SKU_PLANE_12 = "plan12";
    static final String SKU_PLANE_13 = "plan13";
    static final String SKU_PLANE_14 = "plan14";
    static final String SKU_PLANE_15 = "plan15";
    static final String SKU_PLANE_16 = "plan16";


    IabHelper mHelper;
    String devPayLoad = "";
    private static final int IAPCODE = 10001;
    static final int RC_REQUEST = 10001;
    boolean mIsPremium = false;
    boolean mSubscribedToInfiniteGas = false;
    // Provides purchase notification while this app is running
    IabBroadcastReceiver mBroadcastReceiver;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_plain);

        init();
        proccedButtonClickHandler();

        keyInitialization();
        startUpLab();


    }

    public void init(){


        radioGroup = (RadioGroup) findViewById(R.id.radio_group_chose_plain);
        prcceedButton = (Button) findViewById(R.id.bt_proceed);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(ChosePlain.this ,R.color.colorSkyBlue)));

        bar = (ProgressBar) this.findViewById(R.id.progressBar);
    }

    private void proccedButtonClickHandler(){



        prcceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View radioButton = radioGroup.findViewById(radioButtonID);
                idx = radioGroup.indexOfChild(radioButton);

                Log.e("TAGE", "SELECTED RADIO ID: " + idx);

                if (idx==-1){
                    Toast.makeText(ChosePlain.this, "Please Choase A Plain First ", Toast.LENGTH_SHORT).show();
                }else {


                final Dialog procceedDialog = new Dialog(ChosePlain.this);
                procceedDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                procceedDialog.setContentView(R.layout.procceed_dialog);

                    Button dialogBtPayLater = (Button) procceedDialog.findViewById(R.id.dialog_bt_pay_later);
                    final Button dialogBtPayNow = (Button) procceedDialog.findViewById(R.id.dialog_bt_pay_now);

                    //setting button for paylater
                    dialogBtPayLater.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            procceedDialog.dismiss();
                        }
                    });//end of pay later dialog


                    dialogBtPayNow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //do purchase code here


                            if (idx==0) {

                                buyPlane1();
                            }

                            if (idx==1) {

                                buyPlane2();
                            }

                            if (idx==2) {

                                buyPlane3();
                            }

                            if (idx==3) {

                                buyPlane4();
                            }

                            if (idx==4) {

                                buyPlane5();
                            }

                            if (idx==5) {

                                buyPlane6();
                            }

                            if (idx==6) {

                                buyPlane7();
                            }

                            if (idx==7) {

                                buyPlane8();
                            }

                            if (idx==8) {

                                buyPlane9();
                            }

                            if (idx==9) {

                                buyPlane10();
                            }

                            if (idx==10) {

                                buyPlane11();
                            }

                            if (idx==11) {

                                buyPlane12();
                            }

                            if (idx==12) {

                                buyPlane13();
                            }

                            if (idx==13) {

                                buyPlane14();
                            }

                            if (idx==14) {

                                buyPlane15();
                            }

                            if (idx==15) {

                                buyPlane16();
                            }



                            procceedDialog.dismiss();

                        }
                    });

                procceedDialog.show();
            }
            }
        });
    }



    public void keyInitialization(){
        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs5qpIjQqe0WbofquVegfsxxxbUQ/lcuUaFG85gg3G8K4CyFtWfWkF0mugce7OKE55L0gsP2NO0u9aSC9oB66F/TvR0FJWDebR4ou0dHkFBUyk9DQVHxy8cjQtBG9v62boIYDr9IAM28kakew6/Qb7g4MC7jFDoUyRwOCGyXjMRwKb32hNi/YI4uBik3xuJykFsPPOH7XAh1OgRZ90lo7Lnvuiiu9AOkLOecGoqgxDvoSkpWYyk4q+gzw2YWjsVtPm3T+BNv1aOdV1RN+EqbD+/ym6BAJkqeUDSoLrW2GWWpIeVAeI+fyMAelO+4ULXCmbv6+cqk/KDEVRDcFy5iPNwIDAQAB";

        // Some sanity checks to see if the developer (that's you!) really followed the
        // instructions to run this sample (don't put these checks on your app!)
        if (base64EncodedPublicKey.contains("CONSTRUCT_YOUR")) {
            throw new RuntimeException("Please put your app's public key in MainActivity.java. See README.");
        }
        if (getPackageName().startsWith("com.example")) {
            throw new RuntimeException("Please change the sample's package name! See README.");
        }

        // Create the helper, passing it our context and the public key to verify signatures with
        android.util.Log.d(TAG, "Creating IAB helper.");
        mHelper = new IabHelper(this, base64EncodedPublicKey);

        // enable debug logging (for a production application, you should set this to false).
        mHelper.enableDebugLogging(true);



    }//end of keyInitialization


    public void startUpLab(){
        // Start setup. This is asynchronous and the specified listener
        // will be called once setup completes.
        android.util.Log.d(TAG, "Starting setup.");

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                android.util.Log.d(TAG, "Setup finished.");

                if (!result.isSuccess()) {
                    // Oh noes, there was a problem.
                    complain("Problem setting up in-app billing: " + result);
                    return;
                }

                // Have we been disposed of in the meantime? If so, quit.
                if (mHelper == null) return;

                mBroadcastReceiver = new IabBroadcastReceiver(ChosePlain.this);
                IntentFilter broadcastFilter = new IntentFilter(IabBroadcastReceiver.ACTION);
                registerReceiver(mBroadcastReceiver, broadcastFilter);

                // IAB is fully set up. Now, let's get an inventory of stuff we own.
                android.util.Log.d(TAG, "Setup successful. Querying inventory.");
                try {
                    mHelper.queryInventoryAsync(mGotInventoryListener);
                } catch (IabHelper.IabAsyncInProgressException e) {
                    complain("Error querying inventory. Another async operation in progress.");
                }
            }
        });


    }//end of startup lab



    void complain(String message) {
        android.util.Log.e(TAG, "**** TrivialDrive Error: " + message);
        alert("Error: " + message);
    }


    void alert(String message) {
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        bld.setMessage(message);
        bld.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        android.util.Log.d(TAG, "Showing alert dialog: " + message);
        bld.setCancelable(false);
        bld.create().show();
    }
    void setWaitScreen(boolean set) {

        // Toast.makeText(getApplicationContext(), "Please Wait...", Toast.LENGTH_SHORT).show();

		/*findViewById(R.id.screen_main).setVisibility(set ? View.GONE : View.VISIBLE);
		findViewById(R.id.screen_wait).setVisibility(set ? View.VISIBLE : View.GONE);*/
    }

    /** Verifies the developer payload of a purchase. */
    boolean verifyDeveloperPayload(Purchase p) {
        String payload = p.getDeveloperPayload();

        return true;
    }


    @Override
    public void receivedBroadcast() {
// Received a broadcast notification that the inventory of items has changed
        android.util.Log.d(TAG, "Received broadcast notification. Querying inventory.");
        try {
            mHelper.queryInventoryAsync(mGotInventoryListener);
        } catch (IabHelper.IabAsyncInProgressException e) {
            android.util.Log.e(TAG, "Error querying inventory. Another async operation in progress.");
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == IAPCODE )
        {
            android.util.Log.d(TAG, "onActivityResult(" + requestCode + "," + resultCode + "," + data);
            if (mHelper == null) return;

            // Pass on the activity result to the helper for handling
            if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {




                super.onActivityResult(requestCode, resultCode, data);
            }
            else {
                android.util.Log.d(TAG, "onActivityResult handled by IABUtil.");
            }

        }

    }



    public class RandomString {

       /*
        * static { for (int idx = 0; idx < 10; ++idx) symbols[idx] = (char)
        * ('0' + idx); for (int idx = 10; idx < 36; ++idx) symbols[idx] =
        * (char) ('a' + idx - 10); }
        */


        private final Random random = new Random();

        private final char[] buf;

        public RandomString(int length) {
            if (length < 1)
                throw new IllegalArgumentException("length < 1: " + length);
            buf = new char[length];
        }

        public String nextString() {
            for (int idx = 0; idx < buf.length; ++idx)
                buf[idx] = payloadSymbols[random.nextInt(payloadSymbols.length)];
            return new String(buf);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        inAppBilling_onDestroy();

    }

    //starting in app billing code
    private static final char[] payloadSymbols = new char[36];

    static {
        for (int idx = 0; idx < 10; ++idx)
            payloadSymbols[idx] = (char) ('0' + idx);
        for (int idx = 10; idx < 36; ++idx)
            payloadSymbols[idx] = (char) ('a' + idx - 10);
    }


    public void inAppBilling_onDestroy()
    {

        // very important:
        if (mBroadcastReceiver != null) {
            unregisterReceiver(mBroadcastReceiver);
        }
        if (mHelper != null) try {
            mHelper.dispose();
            mHelper.disposeWhenFinished();
        } catch (IabHelper.IabAsyncInProgressException e) {
            e.printStackTrace();
        }
        mHelper = null;
    }



    // Callback for when a purchase is finished
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            android.util.Log.d(TAG, "Purchase finished: " + result + ", purchase: " + purchase);

            // if we were disposed of in the meantime, quit.
            if (mHelper == null) return;

            if (result.isFailure()) {
                complain("Error purchasing: " + result);
                setWaitScreen(false);
                return;
            }
            if (!verifyDeveloperPayload(purchase)) {
                complain("Error purchasing. Authenticity verification failed.");
                setWaitScreen(false);
                return;
            }

            android.util.Log.d(TAG, "Purchase successful.");

            //*********************************** Method for mobils *******************
            //***************property plane 1*****************
            if (idx == 0) {
                if (purchase.getSku().equals(SKU_PLANE_1)) {
                    // bought 1/4 tank of gas. So consume it.
                    android.util.Log.d(TAG, "Purchase is Star Goggle. Starting Goggle consumption.");
                    try {
                        mHelper.consumeAsync(purchase, mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming Goggle. Another async operation in progress.");
                        setWaitScreen(false);
                        return;
                    }
                }
            }//property plane 1


            //***************property plane 2*****************
            if (idx == 1) {
                if (purchase.getSku().equals(SKU_PLANE_2)) {
                    // bought 1/4 tank of gas. So consume it.
                    android.util.Log.d(TAG, "Purchase is Star Goggle. Starting Goggle consumption.");
                    try {
                        mHelper.consumeAsync(purchase, mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming Goggle. Another async operation in progress.");
                        setWaitScreen(false);
                        return;
                    }
                }
            }//property plane 2


            //***************property plane 3*****************
            if (idx == 2) {
                if (purchase.getSku().equals(SKU_PLANE_3)) {
                    // bought 1/4 tank of gas. So consume it.
                    android.util.Log.d(TAG, "Purchase is Star Goggle. Starting Goggle consumption.");
                    try {
                        mHelper.consumeAsync(purchase, mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming Goggle. Another async operation in progress.");
                        setWaitScreen(false);
                        return;
                    }
                }
            }//property plane 3


            //***************property plane 4*****************
            if (idx == 3) {
                if (purchase.getSku().equals(SKU_PLANE_4)) {
                    // bought 1/4 tank of gas. So consume it.
                    android.util.Log.d(TAG, "Purchase is Star Goggle. Starting Goggle consumption.");
                    try {
                        mHelper.consumeAsync(purchase, mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming Goggle. Another async operation in progress.");
                        setWaitScreen(false);
                        return;
                    }
                }
            }//property plane 4


            //***************property plane 5*****************
            if (idx == 4) {
                if (purchase.getSku().equals(SKU_PLANE_5)) {
                    // bought 1/4 tank of gas. So consume it.
                    android.util.Log.d(TAG, "Purchase is Star Goggle. Starting Goggle consumption.");
                    try {
                        mHelper.consumeAsync(purchase, mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming Goggle. Another async operation in progress.");
                        setWaitScreen(false);
                        return;
                    }
                }
            }//property plane 5


            //***************property plane 6*****************
            if (idx == 5) {
                if (purchase.getSku().equals(SKU_PLANE_6)) {
                    // bought 1/4 tank of gas. So consume it.
                    android.util.Log.d(TAG, "Purchase is Star Goggle. Starting Goggle consumption.");
                    try {
                        mHelper.consumeAsync(purchase, mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming Goggle. Another async operation in progress.");
                        setWaitScreen(false);
                        return;
                    }
                }
            }//property plane 6


            //***************property plane 7*****************
            if (idx == 6) {
                if (purchase.getSku().equals(SKU_PLANE_7)) {
                    // bought 1/4 tank of gas. So consume it.
                    android.util.Log.d(TAG, "Purchase is Star Goggle. Starting Goggle consumption.");
                    try {
                        mHelper.consumeAsync(purchase, mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming Goggle. Another async operation in progress.");
                        setWaitScreen(false);
                        return;
                    }
                }
            }//property plane 7


            //***************property plane 8*****************
            if (idx == 7) {
                if (purchase.getSku().equals(SKU_PLANE_8)) {
                    // bought 1/4 tank of gas. So consume it.
                    android.util.Log.d(TAG, "Purchase is Star Goggle. Starting Goggle consumption.");
                    try {
                        mHelper.consumeAsync(purchase, mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming Goggle. Another async operation in progress.");
                        setWaitScreen(false);
                        return;
                    }
                }
            }//property plane 8


            //***************property plane 9*****************
            if (idx == 8) {
                if (purchase.getSku().equals(SKU_PLANE_9)) {
                    // bought 1/4 tank of gas. So consume it.
                    android.util.Log.d(TAG, "Purchase is Star Goggle. Starting Goggle consumption.");
                    try {
                        mHelper.consumeAsync(purchase, mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming Goggle. Another async operation in progress.");
                        setWaitScreen(false);
                        return;
                    }
                }
            }//property plane 9


            //***************property plane 10*****************
            if (idx == 9) {
                if (purchase.getSku().equals(SKU_PLANE_10)) {
                    // bought 1/4 tank of gas. So consume it.
                    android.util.Log.d(TAG, "Purchase is Star Goggle. Starting Goggle consumption.");
                    try {
                        mHelper.consumeAsync(purchase, mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming Goggle. Another async operation in progress.");
                        setWaitScreen(false);
                        return;
                    }
                }
            }//property plane 10


            //***************property plane 11*****************
            if (idx == 10) {
                if (purchase.getSku().equals(SKU_PLANE_11)) {
                    // bought 1/4 tank of gas. So consume it.
                    android.util.Log.d(TAG, "Purchase is Star Goggle. Starting Goggle consumption.");
                    try {
                        mHelper.consumeAsync(purchase, mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming Goggle. Another async operation in progress.");
                        setWaitScreen(false);
                        return;
                    }
                }
            }//property plane 11


            //***************property plane 12*****************
            if (idx == 11) {
                if (purchase.getSku().equals(SKU_PLANE_12)) {
                    // bought 1/4 tank of gas. So consume it.
                    android.util.Log.d(TAG, "Purchase is Star Goggle. Starting Goggle consumption.");
                    try {
                        mHelper.consumeAsync(purchase, mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming Goggle. Another async operation in progress.");
                        setWaitScreen(false);
                        return;
                    }
                }
            }//property plane 12


            //***************property plane 13****************
            if (idx == 12) {
                if (purchase.getSku().equals(SKU_PLANE_13)) {
                    // bought 1/4 tank of gas. So consume it.
                    android.util.Log.d(TAG, "Purchase is Star Goggle. Starting Goggle consumption.");
                    try {
                        mHelper.consumeAsync(purchase, mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming Goggle. Another async operation in progress.");
                        setWaitScreen(false);
                        return;
                    }
                }
            }//property plane 13


            //***************property plane 14*****************
            if (idx == 13) {
                if (purchase.getSku().equals(SKU_PLANE_14)) {
                    // bought 1/4 tank of gas. So consume it.
                    android.util.Log.d(TAG, "Purchase is Star Goggle. Starting Goggle consumption.");
                    try {
                        mHelper.consumeAsync(purchase, mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming Goggle. Another async operation in progress.");
                        setWaitScreen(false);
                        return;
                    }
                }
            }//property plane 14


            //***************property plane 15*****************
            if (idx == 14) {
                if (purchase.getSku().equals(SKU_PLANE_15)) {
                    // bought 1/4 tank of gas. So consume it.
                    android.util.Log.d(TAG, "Purchase is Star Goggle. Starting Goggle consumption.");
                    try {
                        mHelper.consumeAsync(purchase, mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming Goggle. Another async operation in progress.");
                        setWaitScreen(false);
                        return;
                    }
                }
            }//property plane 15


            //***************property plane 16*****************
            if (idx == 15) {
                if (purchase.getSku().equals(SKU_PLANE_16)) {
                    // bought 1/4 tank of gas. So consume it.
                    android.util.Log.d(TAG, "Purchase is Star Goggle. Starting Goggle consumption.");
                    try {
                        mHelper.consumeAsync(purchase, mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming Goggle. Another async operation in progress.");
                        setWaitScreen(false);
                        return;
                    }
                }
            }//property plane 16



            //********************************************* Shoaib Anwar **********************************

        }
    };


    // Listener that's called when we finish querying the items and subscriptions we own
    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            android.util.Log.d(TAG, "Query inventory finished.");

            // Have we been disposed of in the meantime? If so, quit.
            if (mHelper == null) return;

            // Is it a failure?
            if (result.isFailure()) {
                complain("Failed to query inventory: " + result);
                return;
            }

            android.util.Log.d(TAG, "Query inventory was successful.");

            // Check for gas delivery -- if we own gas, we should fill up the tank immediately

            //************************************** for Laptops Perchase ********************************

            //************for property plane 1 perchase*********************
            if (idx==0){
                Purchase gasPurchase = inventory.getPurchase(SKU_PLANE_1);
                android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

                if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
                    android.util.Log.d(TAG, "We have gas. Consuming it.");
                    try {
                        mHelper.consumeAsync(inventory.getPurchase(SKU_PLANE_1), mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming gas. Another async operation in progress.");
                    }
                    return;
                }
            }//end for property plane 1 perchase


            //************for property plane 2 perchase*********************
            if (idx==1){
                Purchase gasPurchase = inventory.getPurchase(SKU_PLANE_2);
                android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

                if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
                    android.util.Log.d(TAG, "We have gas. Consuming it.");
                    try {
                        mHelper.consumeAsync(inventory.getPurchase(SKU_PLANE_2), mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming gas. Another async operation in progress.");
                    }
                    return;
                }
            }//end for property plane 2 perchase


            //************for property plane 3 perchase*********************
            if (idx==2){
                Purchase gasPurchase = inventory.getPurchase(SKU_PLANE_3);
                android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

                if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
                    android.util.Log.d(TAG, "We have gas. Consuming it.");
                    try {
                        mHelper.consumeAsync(inventory.getPurchase(SKU_PLANE_3), mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming gas. Another async operation in progress.");
                    }
                    return;
                }
            }//end for property plane 3 perchase


            //************for property plane 4 perchase*********************
            if (idx==3){
                Purchase gasPurchase = inventory.getPurchase(SKU_PLANE_4);
                android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

                if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
                    android.util.Log.d(TAG, "We have gas. Consuming it.");
                    try {
                        mHelper.consumeAsync(inventory.getPurchase(SKU_PLANE_4), mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming gas. Another async operation in progress.");
                    }
                    return;
                }
            }//end for property plane 4 perchase



            //************for property plane 5 perchase*********************
            if (idx==4){
                Purchase gasPurchase = inventory.getPurchase(SKU_PLANE_5);
                android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

                if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
                    android.util.Log.d(TAG, "We have gas. Consuming it.");
                    try {
                        mHelper.consumeAsync(inventory.getPurchase(SKU_PLANE_5), mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming gas. Another async operation in progress.");
                    }
                    return;
                }
            }//end for property plane 5 perchase




            //************for property plane 6 perchase*********************
            if (idx==5){
                Purchase gasPurchase = inventory.getPurchase(SKU_PLANE_6);
                android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

                if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
                    android.util.Log.d(TAG, "We have gas. Consuming it.");
                    try {
                        mHelper.consumeAsync(inventory.getPurchase(SKU_PLANE_6), mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming gas. Another async operation in progress.");
                    }
                    return;
                }
            }//end for property plane 6 perchase



            //************for property plane 7 perchase*********************
            if (idx==6){
                Purchase gasPurchase = inventory.getPurchase(SKU_PLANE_7);
                android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

                if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
                    android.util.Log.d(TAG, "We have gas. Consuming it.");
                    try {
                        mHelper.consumeAsync(inventory.getPurchase(SKU_PLANE_7), mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming gas. Another async operation in progress.");
                    }
                    return;
                }
            }//end for property plane 7 perchase


            //************for property plane 8 perchase*********************
            if (idx==7){
                Purchase gasPurchase = inventory.getPurchase(SKU_PLANE_8);
                android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

                if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
                    android.util.Log.d(TAG, "We have gas. Consuming it.");
                    try {
                        mHelper.consumeAsync(inventory.getPurchase(SKU_PLANE_8), mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming gas. Another async operation in progress.");
                    }
                    return;
                }
            }//end for property plane 8 perchase


            //************for property plane 9 perchase*********************
            if (idx==8){
                Purchase gasPurchase = inventory.getPurchase(SKU_PLANE_9);
                android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

                if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
                    android.util.Log.d(TAG, "We have gas. Consuming it.");
                    try {
                        mHelper.consumeAsync(inventory.getPurchase(SKU_PLANE_9), mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming gas. Another async operation in progress.");
                    }
                    return;
                }
            }//end for property plane 9 perchase



            //************for property plane 10 perchase*********************
            if (idx==9){
                Purchase gasPurchase = inventory.getPurchase(SKU_PLANE_10);
                android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

                if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
                    android.util.Log.d(TAG, "We have gas. Consuming it.");
                    try {
                        mHelper.consumeAsync(inventory.getPurchase(SKU_PLANE_10), mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming gas. Another async operation in progress.");
                    }
                    return;
                }
            }//end for property plane 10 perchase


            //************for property plane 11 perchase*********************
            if (idx==10){
                Purchase gasPurchase = inventory.getPurchase(SKU_PLANE_11);
                android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

                if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
                    android.util.Log.d(TAG, "We have gas. Consuming it.");
                    try {
                        mHelper.consumeAsync(inventory.getPurchase(SKU_PLANE_11), mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming gas. Another async operation in progress.");
                    }
                    return;
                }
            }//end for property plane 11 perchase



            //************for property plane 12 perchase*********************
            if (idx==11){
                Purchase gasPurchase = inventory.getPurchase(SKU_PLANE_12);
                android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

                if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
                    android.util.Log.d(TAG, "We have gas. Consuming it.");
                    try {
                        mHelper.consumeAsync(inventory.getPurchase(SKU_PLANE_12), mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming gas. Another async operation in progress.");
                    }
                    return;
                }
            }//end for property plane 12 perchase


            //************for property plane 12 perchase*********************
            if (idx==12){
                Purchase gasPurchase = inventory.getPurchase(SKU_PLANE_13);
                android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

                if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
                    android.util.Log.d(TAG, "We have gas. Consuming it.");
                    try {
                        mHelper.consumeAsync(inventory.getPurchase(SKU_PLANE_13), mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming gas. Another async operation in progress.");
                    }
                    return;
                }
            }//end for property plane 13 perchase


            //************for property plane 14 perchase*********************
            if (idx==13){
                Purchase gasPurchase = inventory.getPurchase(SKU_PLANE_14);
                android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

                if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
                    android.util.Log.d(TAG, "We have gas. Consuming it.");
                    try {
                        mHelper.consumeAsync(inventory.getPurchase(SKU_PLANE_14), mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming gas. Another async operation in progress.");
                    }
                    return;
                }
            }//end for property plane 14 perchase


            //************for property plane 15 perchase*********************
            if (idx==14){
                Purchase gasPurchase = inventory.getPurchase(SKU_PLANE_15);
                android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

                if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
                    android.util.Log.d(TAG, "We have gas. Consuming it.");
                    try {
                        mHelper.consumeAsync(inventory.getPurchase(SKU_PLANE_15), mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming gas. Another async operation in progress.");
                    }
                    return;
                }
            }//end for property plane 15 perchase


            //************for property plane 16 perchase*********************
            if (idx==15){
                Purchase gasPurchase = inventory.getPurchase(SKU_PLANE_16);
                android.util.Log.d(TAG, "Inventory Purchase " + gasPurchase);

                if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
                    android.util.Log.d(TAG, "We have gas. Consuming it.");
                    try {
                        mHelper.consumeAsync(inventory.getPurchase(SKU_PLANE_16), mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error consuming gas. Another async operation in progress.");
                    }
                    return;
                }
            }//end for property plane 16 perchase




            //************************************** end for Perchase ********************************

            setWaitScreen(false);
            android.util.Log.d(TAG, "Initial inventory query finished; enabling main UI.");
        }
    };

    // Called when consumption is complete
    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
        public void onConsumeFinished(Purchase purchase, IabResult result) {
            android.util.Log.d(TAG, "Consumption finished. Purchase: " + purchase + ", result: " + result);

            // if we were disposed of in the meantime, quit.
            if (mHelper == null) return;

            // We know this is the "gas" sku because it's the only one we consume,
            // so we don't check which sku was consumed. If you have more than one
            // sku, you probably should check...
            if (result.isSuccess()) {
                // successfully consumed, so we apply the effects of the item in our
                // game world's logic, which in our case means filling the gas tank a bit
                android.util.Log.d(TAG, "Consumption successful. Provisioning.");
                //mTank = mTank == TANK_MAX ? TANK_MAX : mTank + 1;
                //saveData();
                alert("You have successfully bought Laptop");

               // onSuccessLaptopBought();
               // DialogForCustomerInfo();





                //alert("You filled 1/4 tank. Your tank is now " + String.valueOf(mTank) + "/4 full!");
            }
            else {
                complain("Error while consuming: " + result);
            }
            //updateUi();

            setWaitScreen(false);
            android.util.Log.d(TAG, "End consumption flow.");
        }
    };



    //buy property plane 1
    public void buyPlane1(){
        if (mSubscribedToInfiniteGas) {
            complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
            return;
        }


        // launch the gas purchase UI flow.
        // We will be notified of completion via mPurchaseFinishedListener
        setWaitScreen(true);
        android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
        String payload = "";
        android.util.Log.e("testTag", "Buy Call");
        RandomString randomString = new RandomString(36);
        devPayLoad = randomString.nextString();
        payload = devPayLoad;

        try {
            mHelper.launchPurchaseFlow(this, SKU_PLANE_1, RC_REQUEST,
                    mPurchaseFinishedListener, payload);
        } catch (IabHelper.IabAsyncInProgressException e) {
            complain("Error launching purchase flow. Another async operation in progress.");
            setWaitScreen(false);
        }
    }//end buy property plane 1


    //buy property plane 2
    public void buyPlane2(){
        if (mSubscribedToInfiniteGas) {
            complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
            return;
        }


        // launch the gas purchase UI flow.
        // We will be notified of completion via mPurchaseFinishedListener
        setWaitScreen(true);
        android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
        String payload = "";
        android.util.Log.e("testTag", "Buy Call");
        RandomString randomString = new RandomString(36);
        devPayLoad = randomString.nextString();
        payload = devPayLoad;

        try {
            mHelper.launchPurchaseFlow(this, SKU_PLANE_2, RC_REQUEST,
                    mPurchaseFinishedListener, payload);
        } catch (IabHelper.IabAsyncInProgressException e) {
            complain("Error launching purchase flow. Another async operation in progress.");
            setWaitScreen(false);
        }
    }//end buy property plane 2




    //buy property plane 3
    public void buyPlane3(){
        if (mSubscribedToInfiniteGas) {
            complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
            return;
        }


        // launch the gas purchase UI flow.
        // We will be notified of completion via mPurchaseFinishedListener
        setWaitScreen(true);
        android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
        String payload = "";
        android.util.Log.e("testTag", "Buy Call");
        RandomString randomString = new RandomString(36);
        devPayLoad = randomString.nextString();
        payload = devPayLoad;

        try {
            mHelper.launchPurchaseFlow(this, SKU_PLANE_3, RC_REQUEST,
                    mPurchaseFinishedListener, payload);
        } catch (IabHelper.IabAsyncInProgressException e) {
            complain("Error launching purchase flow. Another async operation in progress.");
            setWaitScreen(false);
        }
    }//end buy property plane 3



    //buy property plane 4
    public void buyPlane4(){
        if (mSubscribedToInfiniteGas) {
            complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
            return;
        }


        // launch the gas purchase UI flow.
        // We will be notified of completion via mPurchaseFinishedListener
        setWaitScreen(true);
        android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
        String payload = "";
        android.util.Log.e("testTag", "Buy Call");
        RandomString randomString = new RandomString(36);
        devPayLoad = randomString.nextString();
        payload = devPayLoad;

        try {
            mHelper.launchPurchaseFlow(this, SKU_PLANE_4, RC_REQUEST,
                    mPurchaseFinishedListener, payload);
        } catch (IabHelper.IabAsyncInProgressException e) {
            complain("Error launching purchase flow. Another async operation in progress.");
            setWaitScreen(false);
        }
    }//end buy property plane 4



    //buy property plane 5
    public void buyPlane5(){
        if (mSubscribedToInfiniteGas) {
            complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
            return;
        }


        // launch the gas purchase UI flow.
        // We will be notified of completion via mPurchaseFinishedListener
        setWaitScreen(true);
        android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
        String payload = "";
        android.util.Log.e("testTag", "Buy Call");
        RandomString randomString = new RandomString(36);
        devPayLoad = randomString.nextString();
        payload = devPayLoad;

        try {
            mHelper.launchPurchaseFlow(this, SKU_PLANE_5, RC_REQUEST,
                    mPurchaseFinishedListener, payload);
        } catch (IabHelper.IabAsyncInProgressException e) {
            complain("Error launching purchase flow. Another async operation in progress.");
            setWaitScreen(false);
        }
    }//end buy property plane 5



    //buy property plane 6
    public void buyPlane6(){
        if (mSubscribedToInfiniteGas) {
            complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
            return;
        }


        // launch the gas purchase UI flow.
        // We will be notified of completion via mPurchaseFinishedListener
        setWaitScreen(true);
        android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
        String payload = "";
        android.util.Log.e("testTag", "Buy Call");
        RandomString randomString = new RandomString(36);
        devPayLoad = randomString.nextString();
        payload = devPayLoad;

        try {
            mHelper.launchPurchaseFlow(this, SKU_PLANE_6, RC_REQUEST,
                    mPurchaseFinishedListener, payload);
        } catch (IabHelper.IabAsyncInProgressException e) {
            complain("Error launching purchase flow. Another async operation in progress.");
            setWaitScreen(false);
        }
    }//end buy property plane 6



    //buy property plane 7
    public void buyPlane7(){
        if (mSubscribedToInfiniteGas) {
            complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
            return;
        }


        // launch the gas purchase UI flow.
        // We will be notified of completion via mPurchaseFinishedListener
        setWaitScreen(true);
        android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
        String payload = "";
        android.util.Log.e("testTag", "Buy Call");
        RandomString randomString = new RandomString(36);
        devPayLoad = randomString.nextString();
        payload = devPayLoad;

        try {
            mHelper.launchPurchaseFlow(this, SKU_PLANE_7, RC_REQUEST,
                    mPurchaseFinishedListener, payload);
        } catch (IabHelper.IabAsyncInProgressException e) {
            complain("Error launching purchase flow. Another async operation in progress.");
            setWaitScreen(false);
        }
    }//end buy property plane 7




    //buy property plane 8
    public void buyPlane8(){
        if (mSubscribedToInfiniteGas) {
            complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
            return;
        }


        // launch the gas purchase UI flow.
        // We will be notified of completion via mPurchaseFinishedListener
        setWaitScreen(true);
        android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
        String payload = "";
        android.util.Log.e("testTag", "Buy Call");
        RandomString randomString = new RandomString(36);
        devPayLoad = randomString.nextString();
        payload = devPayLoad;

        try {
            mHelper.launchPurchaseFlow(this, SKU_PLANE_8, RC_REQUEST,
                    mPurchaseFinishedListener, payload);
        } catch (IabHelper.IabAsyncInProgressException e) {
            complain("Error launching purchase flow. Another async operation in progress.");
            setWaitScreen(false);
        }
    }//end buy property plane 8



    //buy property plane 9
    public void buyPlane9(){
        if (mSubscribedToInfiniteGas) {
            complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
            return;
        }


        // launch the gas purchase UI flow.
        // We will be notified of completion via mPurchaseFinishedListener
        setWaitScreen(true);
        android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
        String payload = "";
        android.util.Log.e("testTag", "Buy Call");
        RandomString randomString = new RandomString(36);
        devPayLoad = randomString.nextString();
        payload = devPayLoad;

        try {
            mHelper.launchPurchaseFlow(this, SKU_PLANE_9, RC_REQUEST,
                    mPurchaseFinishedListener, payload);
        } catch (IabHelper.IabAsyncInProgressException e) {
            complain("Error launching purchase flow. Another async operation in progress.");
            setWaitScreen(false);
        }
    }//end buy property plane 9



    //buy property plane 10
    public void buyPlane10(){
        if (mSubscribedToInfiniteGas) {
            complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
            return;
        }


        // launch the gas purchase UI flow.
        // We will be notified of completion via mPurchaseFinishedListener
        setWaitScreen(true);
        android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
        String payload = "";
        android.util.Log.e("testTag", "Buy Call");
        RandomString randomString = new RandomString(36);
        devPayLoad = randomString.nextString();
        payload = devPayLoad;

        try {
            mHelper.launchPurchaseFlow(this, SKU_PLANE_10, RC_REQUEST,
                    mPurchaseFinishedListener, payload);
        } catch (IabHelper.IabAsyncInProgressException e) {
            complain("Error launching purchase flow. Another async operation in progress.");
            setWaitScreen(false);
        }
    }//end buy property plane 10



    //buy property plane 11
    public void buyPlane11(){
        if (mSubscribedToInfiniteGas) {
            complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
            return;
        }


        // launch the gas purchase UI flow.
        // We will be notified of completion via mPurchaseFinishedListener
        setWaitScreen(true);
        android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
        String payload = "";
        android.util.Log.e("testTag", "Buy Call");
        RandomString randomString = new RandomString(36);
        devPayLoad = randomString.nextString();
        payload = devPayLoad;

        try {
            mHelper.launchPurchaseFlow(this, SKU_PLANE_11, RC_REQUEST,
                    mPurchaseFinishedListener, payload);
        } catch (IabHelper.IabAsyncInProgressException e) {
            complain("Error launching purchase flow. Another async operation in progress.");
            setWaitScreen(false);
        }
    }//end buy property plane 11



    //buy property plane 12
    public void buyPlane12(){
        if (mSubscribedToInfiniteGas) {
            complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
            return;
        }


        // launch the gas purchase UI flow.
        // We will be notified of completion via mPurchaseFinishedListener
        setWaitScreen(true);
        android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
        String payload = "";
        android.util.Log.e("testTag", "Buy Call");
        RandomString randomString = new RandomString(36);
        devPayLoad = randomString.nextString();
        payload = devPayLoad;

        try {
            mHelper.launchPurchaseFlow(this, SKU_PLANE_12, RC_REQUEST,
                    mPurchaseFinishedListener, payload);
        } catch (IabHelper.IabAsyncInProgressException e) {
            complain("Error launching purchase flow. Another async operation in progress.");
            setWaitScreen(false);
        }
    }//end buy property plane 12



    //buy property plane 13
    public void buyPlane13(){
        if (mSubscribedToInfiniteGas) {
            complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
            return;
        }


        // launch the gas purchase UI flow.
        // We will be notified of completion via mPurchaseFinishedListener
        setWaitScreen(true);
        android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
        String payload = "";
        android.util.Log.e("testTag", "Buy Call");
        RandomString randomString = new RandomString(36);
        devPayLoad = randomString.nextString();
        payload = devPayLoad;

        try {
            mHelper.launchPurchaseFlow(this, SKU_PLANE_13, RC_REQUEST,
                    mPurchaseFinishedListener, payload);
        } catch (IabHelper.IabAsyncInProgressException e) {
            complain("Error launching purchase flow. Another async operation in progress.");
            setWaitScreen(false);
        }
    }//end buy property plane 13



    //buy property plane 14
    public void buyPlane14(){
        if (mSubscribedToInfiniteGas) {
            complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
            return;
        }


        // launch the gas purchase UI flow.
        // We will be notified of completion via mPurchaseFinishedListener
        setWaitScreen(true);
        android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
        String payload = "";
        android.util.Log.e("testTag", "Buy Call");
        RandomString randomString = new RandomString(36);
        devPayLoad = randomString.nextString();
        payload = devPayLoad;

        try {
            mHelper.launchPurchaseFlow(this, SKU_PLANE_14, RC_REQUEST,
                    mPurchaseFinishedListener, payload);
        } catch (IabHelper.IabAsyncInProgressException e) {
            complain("Error launching purchase flow. Another async operation in progress.");
            setWaitScreen(false);
        }
    }//end buy property plane 14



    //buy property plane 15
    public void buyPlane15(){
        if (mSubscribedToInfiniteGas) {
            complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
            return;
        }


        // launch the gas purchase UI flow.
        // We will be notified of completion via mPurchaseFinishedListener
        setWaitScreen(true);
        android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
        String payload = "";
        android.util.Log.e("testTag", "Buy Call");
        RandomString randomString = new RandomString(36);
        devPayLoad = randomString.nextString();
        payload = devPayLoad;

        try {
            mHelper.launchPurchaseFlow(this, SKU_PLANE_15, RC_REQUEST,
                    mPurchaseFinishedListener, payload);
        } catch (IabHelper.IabAsyncInProgressException e) {
            complain("Error launching purchase flow. Another async operation in progress.");
            setWaitScreen(false);
        }
    }//end buy property plane 15



    //buy property plane 16
    public void buyPlane16(){
        if (mSubscribedToInfiniteGas) {
            complain("No need! You're subscribed to infinite gas. Isn't that awesome?");
            return;
        }


        // launch the gas purchase UI flow.
        // We will be notified of completion via mPurchaseFinishedListener
        setWaitScreen(true);
        android.util.Log.d(TAG, "Launching purchase flow for gas.");

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
        String payload = "";
        android.util.Log.e("testTag", "Buy Call");
        RandomString randomString = new RandomString(36);
        devPayLoad = randomString.nextString();
        payload = devPayLoad;

        try {
            mHelper.launchPurchaseFlow(this, SKU_PLANE_16, RC_REQUEST,
                    mPurchaseFinishedListener, payload);
        } catch (IabHelper.IabAsyncInProgressException e) {
            complain("Error launching purchase flow. Another async operation in progress.");
            setWaitScreen(false);
        }
    }//end buy property plane 16


   //class for server API
    private class AsyncDataClass extends AsyncTask<String, Void, String> {

        @Override

        protected String doInBackground(String... params) {

            HttpParams httpParameters = new BasicHttpParams();

            HttpConnectionParams.setConnectionTimeout(httpParameters, 8000);

            HttpConnectionParams.setSoTimeout(httpParameters, 8000);

            HttpClient httpClient = new DefaultHttpClient(httpParameters);

            HttpPost httpPost = new HttpPost(params[0]);

            String jsonResult = "";

            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);



                    nameValuePairs.add(new BasicNameValuePair("email", params[1]));

                    nameValuePairs.add(new BasicNameValuePair("payment", params[2]));




                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpClient.execute(httpPost);

                jsonResult = inputStreamToString(response.getEntity().getContent()).toString();

                Log.e("TAG", "Resulted Returned Json object " + jsonResult.toString());

            } catch (ClientProtocolException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }

            return jsonResult;

        }

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

            bar.setVisibility(View.VISIBLE);


        }

        @Override

        protected void onPostExecute(String result) {

            super.onPostExecute(result);




            Log.e("TAG", "Resulted Value: " + result);

            if(result.equals("") || result == null){

                Toast.makeText(ChosePlain.this, "Server connection failed", Toast.LENGTH_LONG).show();

                bar.setVisibility(View.GONE);



                return;

            }

            String jsonResult = returnParsedJsonObject(result);

            Log.e("TAG", "RESULT " + result);





                if (result!=null) {

                    if (jsonResult.equals("already registered")) {

                        Toast.makeText(ChosePlain.this, "User Already Registered", Toast.LENGTH_LONG).show();
                        bar.setVisibility(View.GONE);


                        return;

                    }

                    if (jsonResult.equals("registered successfully")) {

                        Toast.makeText(ChosePlain.this, "Register Successfully", Toast.LENGTH_LONG).show();
                        bar.setVisibility(View.GONE);



                    }



            }



        }

        private StringBuilder inputStreamToString(InputStream is) {

            String rLine = "";

            StringBuilder answer = new StringBuilder();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            try {

                while ((rLine = br.readLine()) != null) {

                    answer.append(rLine);

                }

            } catch (IOException e) {

// TODO Auto-generated catch block

                e.printStackTrace();

            }

            return answer;

        }

    }//end of async class

    private String returnParsedJsonObject(String result){

        JSONObject resultObject = null;

        String returnedResult = null;

        try {

            resultObject = new JSONObject(result);



                resultObject = new JSONObject(result);





        } catch (JSONException e) {

            e.printStackTrace();

        }

        return returnedResult;

    }//end of method returnParserJsonObject



    public void onSuccessfullBoughtPropertyPlane(){

        SharedPreferences sharedPreferences = getSharedPreferences("user", 0);

        if (sharedPreferences!=null) {

            String email = sharedPreferences.getString("email", null);

            String payment;

            if (email != null) {
                Log.e("TAG", "SharePreference 11 " + email);

                if (idx==0){

                    payment = "25";


                    AsyncDataClass asyncRequestObject = new AsyncDataClass();

                    asyncRequestObject.execute(serverUrlLogin, email, payment);

                }

                if (idx==1){

                    payment = "49";


                    AsyncDataClass asyncRequestObject = new AsyncDataClass();

                    asyncRequestObject.execute(serverUrlLogin, email, payment);

                }

                if (idx==2){

                    payment = "75";


                    AsyncDataClass asyncRequestObject = new AsyncDataClass();

                    asyncRequestObject.execute(serverUrlLogin, email, payment);

                }
                if (idx==3){

                    payment = "105";


                    AsyncDataClass asyncRequestObject = new AsyncDataClass();

                    asyncRequestObject.execute(serverUrlLogin, email, payment);

                }
                if (idx==4){

                    payment = "145";


                    AsyncDataClass asyncRequestObject = new AsyncDataClass();

                    asyncRequestObject.execute(serverUrlLogin, email, payment);

                }if (idx==5){

                    payment = "189";


                    AsyncDataClass asyncRequestObject = new AsyncDataClass();

                    asyncRequestObject.execute(serverUrlLogin, email, payment);

                }
                if (idx==6){

                    payment = "195";


                    AsyncDataClass asyncRequestObject = new AsyncDataClass();

                    asyncRequestObject.execute(serverUrlLogin, email, payment);

                }
                if (idx==7){

                    payment = "209";


                    AsyncDataClass asyncRequestObject = new AsyncDataClass();

                    asyncRequestObject.execute(serverUrlLogin, email, payment);

                }if (idx==8){

                    payment = "225";


                    AsyncDataClass asyncRequestObject = new AsyncDataClass();

                    asyncRequestObject.execute(serverUrlLogin, email, payment);

                }
                if (idx==9){

                    payment = "249";


                    AsyncDataClass asyncRequestObject = new AsyncDataClass();

                    asyncRequestObject.execute(serverUrlLogin, email, payment);

                }
                if (idx==10){

                    payment = "299";


                    AsyncDataClass asyncRequestObject = new AsyncDataClass();

                    asyncRequestObject.execute(serverUrlLogin, email, payment);

                }
                if (idx==11){

                    payment = "315";


                    AsyncDataClass asyncRequestObject = new AsyncDataClass();

                    asyncRequestObject.execute(serverUrlLogin, email, payment);

                }
                if (idx==12){

                    payment = "325";


                    AsyncDataClass asyncRequestObject = new AsyncDataClass();

                    asyncRequestObject.execute(serverUrlLogin, email, payment);

                }
                if (idx==13){

                    payment = "349";


                    AsyncDataClass asyncRequestObject = new AsyncDataClass();

                    asyncRequestObject.execute(serverUrlLogin, email, payment);

                }
                if (idx==14){

                    payment = "385";


                    AsyncDataClass asyncRequestObject = new AsyncDataClass();

                    asyncRequestObject.execute(serverUrlLogin, email, payment);

                }
                if (idx==15){

                    payment = "399";


                    AsyncDataClass asyncRequestObject = new AsyncDataClass();

                    asyncRequestObject.execute(serverUrlLogin, email, payment);

                }


            }


        }

    }

    public void currentBalaceDialog(String balanec){


        final Dialog currentBalanceDilaog = new Dialog(ChosePlain.this);
        currentBalanceDilaog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        TextView tv_currentBalance = (TextView)currentBalanceDilaog.findViewById(R.id.tv_dilaog_current_balance);
        Button bt_ok = (Button) currentBalanceDilaog.findViewById(R.id.dialog_bt_ok);

        tv_currentBalance.setText("Your Available Balance is " + balanec + "$");

        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBalanceDilaog.dismiss();
            }
        });


    }//end of currentBalance Dialog


}
