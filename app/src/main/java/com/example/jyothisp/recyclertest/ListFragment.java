package com.example.jyothisp.recyclertest;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Display;
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

    RecyclerView mFlagshipRecyclerView;
    EventsAdapter[] mEventAdapters;
    RecyclerView[] mEventRecyclers;
    ArrayList<Event>[] mEventsLists;
    TypedArray mRecyclerIDs;
    FlagshipAdapter mFlagshipAdapter;
    ArrayList<Event> flagshipEvents;

    int no_of_dept = 6;
    String LOG_TAG = "ListFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mFlagshipRecyclerView = view.findViewById(R.id.flagship_recycler_view);

        flagshipEvents = new ArrayList<>();

        mFlagshipAdapter = new FlagshipAdapter(flagshipEvents);

        mFlagshipRecyclerView.setLayoutManager(new CustomLinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mFlagshipRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFlagshipRecyclerView.setAdapter(mFlagshipAdapter);
        mFlagshipRecyclerView.setNestedScrollingEnabled(false);
        prepareFlagshipEvents();
//        setupAutoScrollForFlagshipEvents();

        mFlagshipRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mFlagshipRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                View transView = view.findViewById(R.id.layout_with_bg);
                animateToDetails(transView);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        mEventRecyclers = new RecyclerView[6];
        mEventsLists = new ArrayList[6];
        mEventAdapters = new EventsAdapter[6];
        mRecyclerIDs = getResources().obtainTypedArray(R.array.departments_recycler_views);
        for (int i = 0; i < no_of_dept; i++) {
            mEventsLists[i] = new ArrayList<>();
            int id = mRecyclerIDs.getResourceId(i, 0);
            mEventRecyclers[i] = (RecyclerView) view.findViewById(id);
            mEventAdapters[i] = new EventsAdapter(mEventsLists[i]);
            mEventRecyclers[i].setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            mEventRecyclers[i].setItemAnimator(new DefaultItemAnimator());
            mEventRecyclers[i].setNestedScrollingEnabled(true);
            mEventRecyclers[i].setAdapter(mEventAdapters[i]);

        }


        prepareDepartments();

        return view;
    }

    private void animateToDetails(View view){
//        TODO: Build version check.

        setSharedElementReturnTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.shared));
        setExitTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.no_transition));

        EventDetailsFragment nextPage = new EventDetailsFragment();

        nextPage.setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.default_trans));
        nextPage.setEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.no_transition));

        openDetails(nextPage, view);

    }

    private void openDetails(EventDetailsFragment nextPage, View view) {
        String transName = getString(R.string.transition_string);
        getFragmentManager().beginTransaction()
                .addSharedElement(view, transName)
                .replace(R.id.container, nextPage)
                .addToBackStack("tab")
                .commit();
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

                    if (count == mFlagshipAdapter.getItemCount() - 1) {
                        flag = false;
                    } else if (count == 0) {
                        flag = true;
                    }
                    if (flag) count++;
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
        for (int i = 0; i < no_of_dept; i++) {
            mEventsLists[i].addAll(placeHolderEvents());
            mEventAdapters[i].notifyDataSetChanged();
        }

    }


    private ArrayList<Event> placeHolderEvents() {
        ArrayList<Event> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String x = "Caption_" + i;
            list.add(new Event("Event Name", x));
        }
        return list;
    }

    private void runAnimation(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_anim);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutAnimation(controller);
        adapter.notifyDataSetChanged();


    }

}
