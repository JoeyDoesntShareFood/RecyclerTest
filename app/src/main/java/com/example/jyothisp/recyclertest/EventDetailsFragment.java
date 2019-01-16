package com.example.jyothisp.recyclertest;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class EventDetailsFragment extends android.support.v4.app.Fragment {

    public EventDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.shared));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.event_details_layout, container, false);


        Bundle bundle = getArguments();
        Event event = (Event) bundle.getSerializable("event");
        String title = event.getmTitle();
        animateViews(view);


        MainActivity activity = (MainActivity) getActivity();

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setHomeButtonEnabled(true);
        activity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        setHasOptionsMenu(true);
//        activity.getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout toolbarLayout = view.findViewById(R.id.collapsing);
        toolbarLayout.setTitle(title);
//        toolbarLayout.setCollapsedTitleTextColor(getColor(R.color.titleTextColor));


        final LinearLayout registration = view.findViewById(R.id.event_registration_layout);
        final LinearLayout description = view.findViewById(R.id.event_description_layout);

        TabLayout tabLayout = view.findViewById(R.id.tab);
        final TabLayout.Tab regTab = tabLayout.newTab().setIcon(R.drawable.reg_icon);
        final TabLayout.Tab detailsTab = tabLayout.newTab().setIcon(R.drawable.details_icon);
        tabLayout.addTab(regTab);
        tabLayout.addTab(detailsTab);

        CardView descriptionCard = view.findViewById(R.id.description_card);

        descriptionCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView descriptionTextView = view.findViewById(R.id.description_text_view);
                TextView showMoreTextView = view.findViewById(R.id.show_more);
                animateCardExpansion(descriptionTextView, showMoreTextView);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.equals(regTab)) {
                    registration.setVisibility(View.VISIBLE);
                    description.setVisibility(View.GONE);
                } else if (tab.equals(detailsTab)) {
                    registration.setVisibility(View.GONE);
                    description.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;
    }

    private void animateCardExpansion(final TextView content, final TextView button) {
        int maxLines = (content.getMaxLines() == 2) ? 50 : 2;
        TextUtils.TruncateAt ellipseSize = (content.getMaxLines() == 2) ? null : TextUtils.TruncateAt.END; //3 -> END
        final int stringID = (content.getMaxLines() == 2) ? R.string.text_show_less : R.string.text_show_more;
        content.setEllipsize(ellipseSize);
        ObjectAnimator animator = ObjectAnimator.ofInt(content, "maxLines", maxLines);
        animator.setDuration(1000);
        animator.start();

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                button.setText(stringID);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    private void animateViews(View view) {

        final View content = view.findViewById(R.id.scroll);
        View tab = view.findViewById(R.id.tab);


        content.animate()
                .alpha(1)
                .setDuration(300)
                .setStartDelay(300)
                .setListener(null)
                .start();
        tab.animate()
                .alpha(1)
                .setDuration(300)
                .setStartDelay(300)
                .setListener(null)
                .start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(getContext(), "Pressed", Toast.LENGTH_SHORT).show();
            getFragmentManager().popBackStack();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
