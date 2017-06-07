package com.skva.punchdialogues;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AnimationDrawable animationDrawable;
    private SwipeRefreshLayout swipeContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        // init constraintLayout

        swipeContainer = (SwipeRefreshLayout)findViewById(R.id.swipeContainer);
        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) swipeContainer.getBackground();

        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(3000);

        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(3000);
        swipeContainer = (SwipeRefreshLayout)findViewById(R.id.swipeContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getNewContent();
                swipeContainer.setRefreshing(false);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            // start the animation
            animationDrawable.start();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            // stop the animation
            animationDrawable.stop();
        }
    }

    private void getNewContent(){
        TextView mTextView = ((TextView)findViewById(R.id.dialogue));
        String mDialogue = mTextView.getText().toString()+"a";
        mTextView.setText(mDialogue);
    }
}
