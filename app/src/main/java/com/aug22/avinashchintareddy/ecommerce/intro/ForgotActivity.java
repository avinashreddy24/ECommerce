package com.aug22.avinashchintareddy.ecommerce.intro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aug22.avinashchintareddy.ecommerce.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/*
* Forgot password class
* */
public class ForgotActivity extends AppCompatActivity {
    EditText num,newPassword,oldPassword;
    Button reset;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        num = (EditText) findViewById(R.id.et_nownum);
        newPassword= (EditText) findViewById(R.id.et_newpswd);
        oldPassword = (EditText) findViewById(R.id.et_oldpswd);
        reset= (Button) findViewById(R.id.btn_chngpswd);
        Intent intent=getIntent();
        if(intent.getStringExtra("number")!=null) {
            String pname = intent.getStringExtra("number");
            Log.i("Forgot", "pname" + pname);

            if (!pname.isEmpty()) {
                num.setText(pname);
            }
        }
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String setnumber=num.getText().toString();
                String setnewpassword=newPassword.getText().toString();
                String setoldpassword= oldPassword.getText().toString();
                if(!setnewpassword.isEmpty() ||!setnewpassword.isEmpty()|| !setnumber.isEmpty()) {
                    String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/shop_reset_pass.php?&mobile=" + setnumber + "&password=" + setoldpassword + "&newpassword="+ setnewpassword;
                    //"http://rjtmobile.com/ansari/shopingcart/androidapp/shop_login.php?mobile=9887139847&password=android";
                    Log.i("TestMain", "" + url);
                    checkEntries(url);
                }


            }
        });


    }

    public void checkEntries(String url){
        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                Log.i("TestMain","Entered volley"+response);

                try {
                    //Object jsob= new JSONTokener(response).nextValue();


                        // String jstring = response.toString();
                    JSONObject jsobj= new JSONObject(response);
                        JSONArray jsonArray = jsobj.getJSONArray("msg");
                        String found = jsonArray.getString(0);

                        if (found.contains("successfully")) {
                            Log.i("TestMain", found );

                            ForgotActivity.this.finish();

                        } else {

                            Toast.makeText(ForgotActivity.this, found, Toast.LENGTH_SHORT).show();
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
