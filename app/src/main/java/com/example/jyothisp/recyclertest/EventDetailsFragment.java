package com.example.jyothisp.recyclertest;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        CollapsingToolbarLayout toolbarLayout = view.findViewById(R.id.collapsing);
        TabLayout tabLayout = view.findViewById(R.id.tab);

        animateViews(view);
        setupActionBar(toolbar);
        toolbarLayout.setTitle(title);
        setupTabs(tabLayout, view);
//        populateViewsWithPlaceholders(event, view);
        populateViews(event, view);
        setupShowMoreFeature(view);



        return view;
    }

    private void populateViewsWithPlaceholders(Event event, View view) {
        String prize1 = "₹3000";
        String prize2 = "₹2000";
        String prize3 = "₹500";

        String rules, description, coordinatorNameOne, coordinatorNameTwo;
        final String coordinatorPhoneOne, coordinatorPhoneTwo;

        rules = getString(R.string.placeholder_long);
//        rules = "";
        description = getString(R.string.placeholder_xl);
        coordinatorNameOne = getString(R.string.placeholder_coordinator);
        coordinatorNameTwo = getString(R.string.placeholder_coordinator);
        coordinatorPhoneOne = "9497600590";
        coordinatorPhoneTwo = "8921969033";

        TextView descriptionText = view.findViewById(R.id.description_text_view);
        TextView rulesText = view.findViewById(R.id.rules_text_view);
        TextView prizeTextOne = view.findViewById(R.id.event_prize_one_text);
        TextView prizeTextTwo = view.findViewById(R.id.event_prize_two_text);
        TextView prizeTextThree = view.findViewById(R.id.event_prize_three_text);
        TextView coordinatorTextOne = view.findViewById(R.id.coordinator_name_text_one);
        TextView coordinatorTextTwo = view.findViewById(R.id.coordinator_name_text_two);
        TextView feesText = view.findViewById(R.id.reg_fee_text_view);
        TextView statusText = view.findViewById(R.id.reg_status_text_view);
        CardView registerButton = view.findViewById(R.id.register_btn);
        CardView prizeCardOne = view.findViewById(R.id.prize_one_card);
        CardView prizeCardTwo = view.findViewById(R.id.prize_two_card);
        CardView prizeCardThree = view.findViewById(R.id.prize_three_card);
        CardView coordinatorCardOne = view.findViewById(R.id.coordinator_card_one);
        CardView coordinatorCardTwo = view.findViewById(R.id.coordinator_card_two);
        CardView rulesCard = view.findViewById(R.id.rules_card);
        CardView rulesTitleCard = view.findViewById(R.id.rules_title_card);


        descriptionText.setText(description);
        rulesText.setText(rules);
        prizeTextOne.setText(prize1);
        prizeTextTwo.setText(prize2);
        prizeTextThree.setText(prize3);
        coordinatorTextOne.setText(coordinatorNameOne);
        coordinatorTextTwo.setText(coordinatorNameTwo);

        coordinatorCardOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "" + coordinatorPhoneOne, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String uri = "tel:" + coordinatorPhoneOne.trim();
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });
        coordinatorCardTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "" + coordinatorPhoneTwo, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String uri = "tel:" + coordinatorPhoneTwo.trim();
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });


        rulesCard.setVisibility((rules.equals("")) ? View.GONE : View.VISIBLE);
        rulesTitleCard.setVisibility((rules.equals("")) ? View.GONE : View.VISIBLE);
        prizeCardOne.setVisibility((prize1.equals("")) ? View.GONE : View.VISIBLE);
        prizeCardTwo.setVisibility((prize2.equals("")) ? View.GONE : View.VISIBLE);
        prizeCardThree.setVisibility((prize3.equals("")) ? View.GONE : View.VISIBLE);


    }

    private void populateViews(Event event, View view) {
        String prize1 = event.getmPrize1();
        String prize2 = event.getmPrize2();
        String prize3 = event.getmPrize3();

        String rules, description, coordinatorNameOne, coordinatorNameTwo;
        final String coordinatorPhoneOne, coordinatorPhoneTwo;

        rules = event.getmRules();
//        rules = "";
        description = event.getmDescription();
        coordinatorNameOne = getString(R.string.placeholder_coordinator);
        coordinatorNameTwo = getString(R.string.placeholder_coordinator);
        coordinatorPhoneOne = event.getCoordinator1().getNumber();
        coordinatorPhoneTwo = event.getmCoordinator2().getNumber();

        TextView descriptionText = view.findViewById(R.id.description_text_view);
        TextView rulesText = view.findViewById(R.id.rules_text_view);
        TextView prizeTextOne = view.findViewById(R.id.event_prize_one_text);
        TextView prizeTextTwo = view.findViewById(R.id.event_prize_two_text);
        TextView prizeTextThree = view.findViewById(R.id.event_prize_three_text);
        TextView coordinatorTextOne = view.findViewById(R.id.coordinator_name_text_one);
        TextView coordinatorTextTwo = view.findViewById(R.id.coordinator_name_text_two);
        TextView feesText = view.findViewById(R.id.reg_fee_text_view);
        TextView statusText = view.findViewById(R.id.reg_status_text_view);
        CardView registerButton = view.findViewById(R.id.register_btn);
        CardView prizeCardOne = view.findViewById(R.id.prize_one_card);
        CardView prizeCardTwo = view.findViewById(R.id.prize_two_card);
        CardView prizeCardThree = view.findViewById(R.id.prize_three_card);
        CardView coordinatorCardOne = view.findViewById(R.id.coordinator_card_one);
        CardView coordinatorCardTwo = view.findViewById(R.id.coordinator_card_two);
        CardView coordinatorContainer = view.findViewById(R.id.coordinator_container);
        CardView rulesCard = view.findViewById(R.id.rules_card);
        CardView rulesTitleCard = view.findViewById(R.id.rules_title_card);

        String fee = event.getmFee();
        if (fee.matches("[0-9]+"))
            fee = "₹" + fee;
        feesText.setText(fee);
        statusText.setText(event.getmStatus());
        descriptionText.setText(event.getmDescription());
        rulesText.setText(rules);
        prizeTextOne.setText(prize1);
        prizeTextTwo.setText(prize2);
        prizeTextThree.setText(prize3);
        int i = 0;
        if (event.getCoordinator1().getName().equals("")){
            coordinatorCardOne.setVisibility(View.GONE);
            i++;
        }
        else {
            coordinatorTextOne.setText(event.getCoordinator1().getName());
            coordinatorCardOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "" + coordinatorPhoneOne, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    String uri = "tel:" + coordinatorPhoneOne.trim();
                    intent.setData(Uri.parse(uri));
                    startActivity(intent);
                }
            });
        }

        if (event.getmCoordinator2().getName().equals("")){
            coordinatorCardTwo.setVisibility(View.GONE);
            i++;
        }
        else {
            coordinatorTextTwo.setText(event.getmCoordinator2().getName());

            coordinatorCardTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "" + coordinatorPhoneTwo, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    String uri = "tel:" + coordinatorPhoneTwo.trim();
                    intent.setData(Uri.parse(uri));
                    startActivity(intent);
                }
            });
        }

        if (i==2)
            coordinatorContainer.setVisibility(View.GONE);



        rulesCard.setVisibility((rules.equals("")) ? View.GONE : View.VISIBLE);
        rulesTitleCard.setVisibility((rules.equals("")) ? View.GONE : View.VISIBLE);
        prizeCardOne.setVisibility((prize1.equals("")) ? View.GONE : View.VISIBLE);
        prizeCardTwo.setVisibility((prize2.equals("")) ? View.GONE : View.VISIBLE);
        prizeCardThree.setVisibility((prize3.equals("")) ? View.GONE : View.VISIBLE);


    }

    private void setupShowMoreFeature(View view) {
        CardView descriptionCard = view.findViewById(R.id.description_card);
        CardView rulesCard = view.findViewById(R.id.rules_card);

        descriptionCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView descriptionTextView = view.findViewById(R.id.description_text_view);
                TextView showMoreTextView = view.findViewById(R.id.description_show_more);
                animateCardExpansion(descriptionTextView, showMoreTextView);
            }
        });
        rulesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView descriptionTextView = view.findViewById(R.id.rules_text_view);
                TextView showMoreTextView = view.findViewById(R.id.rules_show_more);
                animateCardExpansion(descriptionTextView, showMoreTextView);
            }
        });
    }

    private void setupTabs(TabLayout tabLayout, View view) {

        final TabLayout.Tab regTab = tabLayout.newTab().setIcon(R.drawable.reg_icon);
        final TabLayout.Tab detailsTab = tabLayout.newTab().setIcon(R.drawable.details_icon);
        final LinearLayout registration = view.findViewById(R.id.event_registration_layout);
        final LinearLayout description = view.findViewById(R.id.event_description_layout);
        tabLayout.addTab(regTab);
        tabLayout.addTab(detailsTab);

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

    }

    private void setupActionBar(Toolbar toolbar) {
        MainActivity activity = (MainActivity) getActivity();

        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setHomeButtonEnabled(true);
        activity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        setHasOptionsMenu(true);
    }

    private void animateCardExpansion(final TextView content, final TextView button) {
        int maxLines = (content.getMaxLines() == 4) ? 75 : 4;
        TextUtils.TruncateAt ellipseSize = (content.getMaxLines() == 4) ? null : TextUtils.TruncateAt.END; //3 -> END
        final int stringID = (content.getMaxLines() == 4) ? R.string.text_show_less : R.string.text_show_more;
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
