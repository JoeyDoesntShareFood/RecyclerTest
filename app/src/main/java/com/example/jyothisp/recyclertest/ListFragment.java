package com.example.jyothisp.recyclertest;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Adapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    RecyclerView mDepartmentsRecyclerView, mFlagshipRecyclerView;
    DepartmentsAdapter mVerticalAdapter;
    FlagshipAdapter mFlagshipAdapter;
    ArrayList<Department> departments;
    ArrayList<Event> flagshipEvents;

    String LOG_TAG = "ListFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mDepartmentsRecyclerView = view.findViewById(R.id.recycler_view);
        mFlagshipRecyclerView = view.findViewById(R.id.flagship_recycler_view);

        flagshipEvents = new ArrayList<>();
        departments = new ArrayList<>();

        mFlagshipAdapter = new FlagshipAdapter(flagshipEvents);
        mVerticalAdapter = new DepartmentsAdapter(departments);

        mFlagshipRecyclerView.setLayoutManager(new CustomLinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mFlagshipRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFlagshipRecyclerView.setAdapter(mFlagshipAdapter);
        mFlagshipRecyclerView.setNestedScrollingEnabled(false);
        prepareFlagshipEvents();
//        setupAutoScrollForFlagshipEvents();
        runAnimation(mFlagshipRecyclerView, mFlagshipAdapter);

        mFlagshipRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

            }
        });


        mDepartmentsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mDepartmentsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mDepartmentsRecyclerView.setNestedScrollingEnabled(false);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mDepartmentsRecyclerView.setAdapter(mVerticalAdapter);


        prepareDepartments();

        return view;
    }


    private void setupAutoScrollForFlagshipEvents() {

        //TODO: stop the process when the user scrolls through the cards.
        final int speedScroll = 3200;
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int count = 0;
            boolean flag = true;

            @Override
            public void run() {
                if (count < mFlagshipAdapter.getItemCount()) {

                    if(count==mFlagshipAdapter.getItemCount()-1){
                        flag = false;
                    }else if(count == 0){
                        flag = true;
                    }
                    if(flag) count++;
                    else count--;

                    mFlagshipRecyclerView.smoothScrollToPosition(count);
                    handler.postDelayed(this, speedScroll);

                }
            }
        };

        handler.postDelayed(runnable, speedScroll);
    }

    private void prepareFlagshipEvents() {
        flagshipEvents.addAll(placeHolderEvents());
        mFlagshipAdapter.notifyDataSetChanged();
    }

    private void prepareDepartments() {
        departments.add(new Department("CS", placeHolderEvents()));
        departments.add(new Department("EC", placeHolderEvents()));
        departments.add(new Department("EEE", placeHolderEvents()));
        departments.add(new Department("MEC", placeHolderEvents()));
        departments.add(new Department("IT", placeHolderEvents()));
        departments.add(new Department("CE", placeHolderEvents()));
        mVerticalAdapter.notifyDataSetChanged();
    }


    private ArrayList<Event> placeHolderEvents() {
        ArrayList<Event> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String x = "Caption_" + i;
            list.add(new Event("Event Name", x));
        }
        return list;
    }

    private void runAnimation(RecyclerView recyclerView, RecyclerView.Adapter adapter){
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_anim);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutAnimation(controller);
        adapter.notifyDataSetChanged();


    }

}
