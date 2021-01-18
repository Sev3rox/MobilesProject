package com.example.smproject;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import com.example.smproject.sqlite.DatabaseHandler;
import com.example.smproject.sqlite.setings;
import com.example.smproject.ui.Facts.FactsFragment;
import com.example.smproject.ui.Facts.FactsListFragment;
import com.example.smproject.ui.Photos.PhotosCatsFragment;
import com.example.smproject.ui.Photos.PhotosCatsRndFragment;
import com.example.smproject.ui.Photos.PhotosFragment;
import com.example.smproject.ui.Photos.PhotosListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    FragmentManager fragmentManager;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        if (gyroscopeSensor == null) {

            Toast.makeText(this,"The device doest have gyroscope", Toast.LENGTH_SHORT).show();
            finish();
        }

context=getApplicationContext();


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_photos, R.id.navigation_facts, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        DatabaseHandler db = new DatabaseHandler(this);

        // Inserting Contacts
      /*  Log.d("Insert: ", "Inserting ..");
        db.addSetings(new setings("Ravi", 999));
        db.addSetings(new setings("Srinivas", 900));
        db.addSetings(new setings("Tommy", 990));
        db.addSetings(new setings("Karthik", 9999));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");*/
        List<setings> setings = db.getAllSetings();

        for (setings se : setings) {
            String log = "Id: " + se.getID() + " ,Name: " + se.getName() + " ,Mode: " +
                    se.getMode();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }


    }

public void photoslist(){
    PhotosListFragment fragment2 = new PhotosListFragment();
    FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
    ft.replace(R.id.nav_host_fragment, fragment2);
    ft.commit();
//FragmentManager fm=getSupportFragmentManager();
//fm.beginTransaction().add(R.id.nav_host_fragment, fragment2).commit();


}

    public void photoszdjecia(){
        PhotosFragment fragment2 = new PhotosFragment();
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment, fragment2);
        ft.commit();



    }
    public void facts(){
        FactsFragment fragment2 = new FactsFragment();
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment, fragment2);
        ft.commit();
    }
    public void factslist(){
        FactsListFragment fragment2 = new FactsListFragment();
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment, fragment2);
        ft.commit();
    }

    public void photoscats(){
        PhotosCatsFragment fragment2 = new PhotosCatsFragment();
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment, fragment2);
        ft.commit();



    }

    public void photoscatsrnd(String pom){
        PhotosCatsRndFragment fragment2 = new PhotosCatsRndFragment(pom);
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment, fragment2);
        ft.commit();
//FragmentManager fm=getSupportFragmentManager();
//fm.beginTransaction().add(R.id.nav_host_fragment, fragment2).commit();


    }

}

