package com.aug22.avinashchintareddy.ecommerce.intro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aug22.avinashchintareddy.ecommerce.Displayer.ShopList;
import com.aug22.avinashchintareddy.ecommerce.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
* The activity for intial login activity
*
* */
public class SecondActivity extends AppCompatActivity {
    EditText pnumber,password;
    TextView forgot,register;
    Button login;
    SharedPreferences contactshared;
    SharedPreferences.Editor editor;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        pnumber= (EditText) findViewById(R.id.et_mnumber);
        password= (EditText) findViewById(R.id.et_pswd);
        login= (Button) findViewById(R.id.btn_login);
        forgot= (TextView) findViewById(R.id.txt_fpswd);
        register= (TextView) findViewById(R.id.txt_register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String pno=pnumber.getText().toString();
                Log.i("TestMain",""+pno);
                String pswd=password.getText().toString();
                Log.i("TestMain",""+pswd);
                    if((pno.length()==10 || pno.length()==12)&& password.length()>6){
                    String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/shop_login.php?mobile=" + pno + "&password=" + pswd;
                    //"http://rjtmobile.com/ansari/shopingcart/androidapp/shop_login.php?mobile=9887139847&password=android";
                    Log.i("TestMain", "" + url);
                    checkEntries(url);
            }
            else{
                        if(pno.length()<10){
                            pnumber.setError("Number must be atleast of 10 digits");
                        }
                        if(pno.length()>10 && pno.length()<12){
                            pnumber.setError("Number must be atleast of 10 or 12 digits ");
                        }

                        if(pswd.length()<6){
                            password.setError("Password must be atleadt of 6 digits");

                        }


                    }



            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inforgot= new Intent(SecondActivity.this,ForgotActivity.class);
                String pno=pnumber.getText().toString();
                if(!pno.isEmpty()){
                    inforgot.putExtra("number",pno);
                }
                startActivity(inforgot);

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(SecondActivity.this,CreateAccount.class);
                startActivity(intent);

            }
        });


    }


    public void checkEntries(String url){
        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                Log.i("TestMain","Entered volley"+response);

                try {
                   // Object jsob= new JSONTokener(response).nextValue();
                    if(response.startsWith("{")) {

                        Log.i("TestMain","is Object"+response);
                        JSONObject jsonObject= new JSONObject(response);
                        //JSONArray msgobj=jsonObject.getJSONArray("msg");
                        //String msg=msgobj.getString(0);
                        String msg= jsonObject.getString("msg");
                        Toast.makeText(SecondActivity.this, msg, Toast.LENGTH_SHORT).show();

                    }
                    else {

                       // String jstring = response.toString();
                       JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);

                        if (jsonObject.getString("msg").equals("success")) {
                            Log.i("TestMain", "Sucesslogin" + jsonObject.getString("msg"));
                            Intent i = new Intent(SecondActivity.this, ShopList.class);
                            startActivity(i);

                            contactshared= getApplicationContext().getSharedPreferences("MyContact", 2);
                            editor = contactshared.edit();
                            editor.putString("M_Number", pnumber.getText().toString());
                            editor.commit();



                        } else {
                            Log.i("TestMain", "Incorrect password " + jsonObject.getString("msg"));
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(sr);

    }



}
