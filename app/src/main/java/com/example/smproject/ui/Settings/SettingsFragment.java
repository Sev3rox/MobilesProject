package com.example.smproject.ui.Settings;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.smproject.MainActivity;
import com.example.smproject.R;
import com.example.smproject.sqlite.DatabaseHandler;
import com.example.smproject.sqlite.setings;
import com.example.smproject.ui.Photos.Photo;
import com.example.smproject.ui.Photos.PhotosFragment;
import com.example.smproject.ui.Settings.SettingsViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SettingsFragment extends Fragment {

    setings pom2;
    Button but2;
    Button but1;
    DatabaseHandler db;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {View root = inflater.inflate(R.layout.fragment_settings, container, false);
        db = new DatabaseHandler(MainActivity.context);
        List<setings> setings = db.getAllSetings();
        pom2=setings.get(0);
        /*but1 = (Button)root.findViewById(R.id.switch1);
        but2 = (Button)root.findViewById(R.id.switch2);
        if(pom2.getMode()==1){but1.setText("ON");}
        if(pom2.getMode()==2){but1.setText("OFF");}
        if(pom2.getMode2()==1){but2.setText("ON");}
        if(pom2.getMode2()==2){but2.setText("OFF");}
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler db1 = new DatabaseHandler(MainActivity.context);
                List<setings> setings1 = db1.getAllSetings();
                setings pom21=setings1.get(0);
                if(pom21.getMode()==2){pom21.setMode(1);but1.setText("ON");}
                else if(pom21.getMode()==1){pom21.setMode(2);but1.setText("OFF");}
                db1.updateSetings(pom21);
            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler db1 = new DatabaseHandler(MainActivity.context);
                List<setings> setings1 = db1.getAllSetings();
                setings pom21=setings1.get(0);
                if(pom21.getMode2()==2){pom21.setMode2(1);but2.setText("ON");}
                else if(pom21.getMode2()==1){pom21.setMode2(2);but2.setText("OFF");}
                db1.updateSetings(pom21);
            }
        });*/
        Switch switch1 = (Switch) root.findViewById(R.id.switch1);
        Switch switch2 = (Switch) root.findViewById(R.id.switch2);
        if(pom2.getMode()==2)switch1.setChecked(false);
        if(pom2.getMode()==1)switch1.setChecked(true);
        if(pom2.getMode2()==2)switch2.setChecked(false);
        if(pom2.getMode2()==1)switch2.setChecked(true);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)pom2.setMode(1);
                if(isChecked==false)pom2.setMode(2);
                db.updateSetings(pom2);
            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)pom2.setMode2(1);
                if(isChecked==false)pom2.setMode2(2);
                db.updateSetings(pom2);

            }
        });
        String url=null;


        int temp1=pom2.getMode();
        int temp2=pom2.getMode2();
        url=pom2.getName();
        if(url!=null){
        ImageView imgview= root.findViewById(R.id.image_photosset);
        Glide.with(this).load(url).into(imgview);}
        return root;
    }


}
