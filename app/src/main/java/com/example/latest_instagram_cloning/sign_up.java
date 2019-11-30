package com.example.latest_instagram_cloning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class sign_up extends AppCompatActivity {

    private Button saveData,getAllData,switchBtn;
    private EditText name,punchSpeed,punchPower;
    private TextView getData;
    private String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        saveData=findViewById(R.id.saveData);
        name=findViewById(R.id.edit1);
        punchSpeed=findViewById(R.id.edit2);
        punchPower=findViewById(R.id.edit3);
        getData=findViewById(R.id.getData);
        getAllData=findViewById(R.id.getAllData);
        data="";
        switchBtn=findViewById(R.id.switchBtn);

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    final ParseObject parseObject = new ParseObject("Kickboxer");
                    parseObject.put("name", name.getText().toString());
                    parseObject.put("PunchSpeed", Integer.parseInt(punchSpeed.getText().toString()));

                    parseObject.put("PunchPower", Integer.parseInt(punchPower.getText().toString()));
                    parseObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(sign_up.this, parseObject.get("name") + " is saved", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                            } else {
                                FancyToast.makeText(sign_up.this, e.getMessage() + "", FancyToast.LENGTH_LONG, FancyToast.WARNING, true).show();
                            }
                        }
                    });
                }
                catch(Exception e)
                {
                    FancyToast.makeText(sign_up.this,e.getMessage()+"",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                }

            }
        });



        //listner for text view
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> getServerData=ParseQuery.getQuery("Kickboxer");
                getServerData.getInBackground("yntOFWDNMS", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(e==null)
                            getData.setText(object.get("name")+" is punch speed: "+object.get("PunchSpeed"));
                    }
                });


            }
        });



        //listner for get all data from server
        getAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> getAllServerData=ParseQuery.getQuery("Kickboxer");
                //getAllServerData.whereGreaterThan("PunchPower",100);
                getAllServerData.whereGreaterThanOrEqualTo("PunchPower", 100);
                getAllServerData.setLimit(2);


                getAllServerData.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if(e==null)
                        {
                            if(objects.size()>0)
                            {
                                for(ParseObject object:objects)
                                {
                                    data=data+object.get("name")+" "+object.get("PunchSpeed")+" "+object.get("PunchPower")+"\n";
                                }
                                FancyToast.makeText(sign_up.this, data, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();


                            }
                        }
                        else
                        {
                            FancyToast.makeText(sign_up.this, e.getMessage() + "", FancyToast.LENGTH_LONG, FancyToast.WARNING, true).show();
                        }
                    }
                });
            }
        });


        //switch button




    }
}
