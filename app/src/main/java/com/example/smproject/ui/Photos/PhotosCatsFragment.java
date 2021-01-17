package com.example.smproject.ui.Photos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smproject.MainActivity;
import com.example.smproject.R;

public class PhotosCatsFragment extends Fragment {

    private Button but1;
    private Button but2;
    private Button but3;
    private Button but4;
    private Button but5;
    private Button but6;
    private Button but7;
    private Button but8;
    private Button but9;
    private Button but10;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_photoscats, container, false);

        but1 = (Button)root.findViewById(R.id.buttonphotozdjecie3);
        but2 = (Button)root.findViewById(R.id.buttonphotolist3);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).photoszdjecia();
            }
        });
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).photoslist();
            }
        });




        but3 = (Button)root.findViewById(R.id.buttonphotobeng);
        but4 = (Button)root.findViewById(R.id.buttonphotoabys);
        but5 = (Button)root.findViewById(R.id.buttonphotobali);
        but6 = (Button)root.findViewById(R.id.buttonphotobirm);
        but7 = (Button)root.findViewById(R.id.buttonphotobomb);
        but8 = (Button)root.findViewById(R.id.buttonphotobure);
        but9 = (Button)root.findViewById(R.id.buttonphotohima);
        but10 = (Button)root.findViewById(R.id.buttonphotojava);


        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).photoscatsrnd("beng");
            }
        });

        but4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).photoscatsrnd("abys");
            }
        });
        but5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).photoscatsrnd("bali");
            }
        });
        but6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).photoscatsrnd("birm");
            }
        });
        but7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).photoscatsrnd("bomb");
            }
        });
        but8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).photoscatsrnd("bure");
            }
        });
        but9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).photoscatsrnd("hima");
            }
        });
        but10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).photoscatsrnd("java");
            }
        });



        return root;
    }
}
