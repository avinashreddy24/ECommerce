package com.aug22.avinashchintareddy.ecommerce.intro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


/*
*
* CreateAccount for registering the new customer
* */
public class CreateAccount extends AppCompatActivity {


    Button btn_signup;
    EditText et_name,et_email,et_phone,et_pwd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        btn_signup = (Button) findViewById(R.id.btn_signup);
        et_name = (EditText) findViewById(R.id.et_username);
        et_email = (EditText) findViewById(R.id.et_email);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_pwd = (EditText) findViewById(R.id.et_pwd);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                String email = et_email.getText().toString();
                final String phone = et_phone.getText().toString();
                String pwd = et_pwd.getText().toString();

                String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/shop_reg.php?name="+name+"&email="+email+"&mobile="+phone+"&password="+pwd;

                if(validPasword(pwd)){
                checkEntries(url);
                }
                else{
                    et_pwd.setError("Enter a minimum of 6 digits");
                }

            }
        });

    }
    public void checkEntries(String url){
        StringRequest strReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.contains("registered")) {
                    Toast.makeText(CreateAccount.this, "Registration Succesful", Toast.LENGTH_SHORT).show();
                    CreateAccount.this.finish();

                } else {
                    Toast.makeText(CreateAccount.this, "Mobile number already exists", Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(CreateAccount.this);
        requestQueue.add(strReq);

    }

    public boolean validPasword(String s){
        if(s.length()<6){
            return false;
        }
        return true;

    }

    }
