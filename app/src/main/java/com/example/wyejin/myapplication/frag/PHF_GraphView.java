package com.example.wyejin.myapplication.frag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.wyejin.myapplication.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

/**
 * Created by wye.jin on 21/7/2014.
 * http://android-graphview.org/#doc_howto
 */
public class PHF_GraphView extends Fragment {

    public static PHF_GraphView newInstance() {

        PHF_GraphView fragment = new PHF_GraphView();
        Bundle args = new Bundle();
        return fragment;
    }

    public PHF_GraphView(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_graphview, container, false);
        // init example series data
        GraphViewSeries exampleSeries = new GraphViewSeries(new GraphView.GraphViewData[] {
                new GraphView.GraphViewData(1, 2.0d)
                , new GraphView.GraphViewData(2, 1.5d)
                , new GraphView.GraphViewData(3, 2.5d)
                , new GraphView.GraphViewData(4, 1.0d)
        });

        GraphView graphView = new LineGraphView(rootView.getContext(), "GraphViewDemo");
        graphView.addSeries(exampleSeries); // data

        LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.lLayout_graphview);
        layout.addView(graphView);

        return rootView;
    }
}
