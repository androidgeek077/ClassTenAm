package app.techsol.classtenamdemoapp.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.techsol.classtenamdemoapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentsFragments extends Fragment {


    public StudentsFragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_students_fragments, container, false);
    }

}
