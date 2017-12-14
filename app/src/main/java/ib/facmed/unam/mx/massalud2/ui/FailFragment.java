package ib.facmed.unam.mx.massalud2.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ib.facmed.unam.mx.massalud2.R;

/**
 * Created by samo92 on 05/12/2017.
 */

public class FailFragment extends Fragment {

    public void FailFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fail,container,false);

        return view;
    }

}
