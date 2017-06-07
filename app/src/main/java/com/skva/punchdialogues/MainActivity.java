package com.skva.punchdialogues;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ShareActionProvider mShareActionProvider;
    private AnimationDrawable animationDrawable;
    private SwipeRefreshLayout swipeContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().hide();

        swipeContainer = (SwipeRefreshLayout)findViewById(R.id.swipeContainer);
        animationDrawable = (AnimationDrawable) swipeContainer.getBackground();
        animationDrawable.setEnterFadeDuration(3000);
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
            animationDrawable.start();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
    }


    private void getNewContent(){
        TextView mTextView = ((TextView)findViewById(R.id.dialogue));
        String mDialogue = mTextView.getText().toString()+"a";
        mTextView.setText(mDialogue);
    }

   /*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareIntent(createShareIntent());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        if (item.getItemId()== R.id.action_share) {
            Log.d("onOptionsItemSelected","inside condition");
            setShareIntent(createShareIntent());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
    private Intent createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String temp = ((TextView)findViewById(R.id.dialogue)).getText().toString();
        Log.d("createShareIntent",temp);
        shareIntent.putExtra(Intent.EXTRA_TEXT,temp);
        return shareIntent;
    } */


}
