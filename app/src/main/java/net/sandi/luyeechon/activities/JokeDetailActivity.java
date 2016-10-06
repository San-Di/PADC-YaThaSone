package net.sandi.luyeechon.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.sandi.luyeechon.LuYeeChonApp;
import net.sandi.luyeechon.R;
import net.sandi.luyeechon.data.vos.JokeVO;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class JokeDetailActivity extends AppCompatActivity {

    private static final String IE_JOKE_TOPIC_NAME = "IE_JOKE_TOPIC_NAME";

    @BindView(R.id.toolbar_joke)
    Toolbar toolbarJoke;

    @BindView(R.id.fab_joke_detail)
    FloatingActionButton fabJoke;

    @BindView(R.id.tv_joke_desc)
    TextView tvJokeDes;

    @BindView(R.id.iv_joke_photo)
    ImageView ivJoke;

//    @BindView(R.id.btn_share_joke)
//    Button btnShareJoke;

    @BindString(R.string.joke_list_detail_name) String JOKE_IMAGE_TRANSITION_NAME;

    @BindView(R.id.collapsing_toolbar_joke)
    CollapsingToolbarLayout collapsingToolbarJoke;

    private String mJokeTitle;
    private static JokeVO mJoke;


    public static Intent newIntent(JokeVO joke) {
        Intent intent = new Intent(LuYeeChonApp.getContext(), JokeDetailActivity.class);
        intent.putExtra(IE_JOKE_TOPIC_NAME,joke.getJokeTitle());
        mJoke=joke;
        return intent;
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.menu_joke_detail_screen, menu);
//
//        MenuItem shareItem = menu.findItem(R.id.action_share_joke);
//        shareItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(JokeDetailActivity.this)
//                .setType("text/plain")
//                .setText(mJoke.getJokeTitle() + " \n\n\n " + mJoke.getJokeDes())
//                .getIntent(), getString(R.string.action_share)));
//                return true;
//            }
//        });
//        return true;
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_detail);

        ButterKnife.bind(this, this);

        boolean saved = false;
        ViewCompat.setTransitionName(ivJoke, JOKE_IMAGE_TRANSITION_NAME);

        setSupportActionBar(toolbarJoke);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if(mJoke.getFav().equals("1")) {
//            fabJoke.setImageResource(R.drawable.ic_favorite_full_black_24dp);
            fabJoke.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
        }
//        else if (mJoke.getFav().equals("0")) {
////            fabJoke.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
//            fabJoke.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
//        }


        fabJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(mJoke.getFav().equals("1")) {
//                    JokeVO.removeFavouriteJoke(mJoke);
//                    fabJoke.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
//                    Toast.makeText(LuYeeChonApp.getContext(),mJoke.getJokeTitle()+" is removed from Favourite",Toast.LENGTH_LONG).show();
//
//                }
//                else if(mJoke.getFav().equals("0")) {
//                    mJoke.setFav("1");
//                    fabJoke.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
//                    JokeVO.saveFavouriteJoke(mJoke,"1");
//                    Toast.makeText(LuYeeChonApp.getContext(),mJoke.getJokeTitle()+" is added from Favourite",Toast.LENGTH_LONG).show();
//                }

                        startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(JokeDetailActivity.this)
                                .setType("text/plain")
                                .setText(mJoke.getJokeTitle() + " \n\n\n " + mJoke.getJokeDes())
                                .getIntent(), getString(R.string.action_share)));


            }
        });
        mJokeTitle = getIntent().getStringExtra(IE_JOKE_TOPIC_NAME);
        tvJokeDes.setText(mJoke.getJokeDes());

        Glide.with(ivJoke.getContext())
                .load(mJoke.getImageJoke())
                .centerCrop()
                .placeholder(R.drawable.joke_placeholder)
                .into(ivJoke);
        collapsingToolbarJoke.setTitle(mJokeTitle);

        final Typeface tf = Typeface.createFromAsset(LuYeeChonApp.getContext().getAssets(), "fonts/Zawgyi.ttf");
        collapsingToolbarJoke.setCollapsedTitleTypeface(tf);
        collapsingToolbarJoke.setExpandedTitleTypeface(tf);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
    }
    private void bindData(JokeVO jokeVO) {
        tvJokeDes.setText(jokeVO.getJokeDes());

        /*
        String imageUrl = MyanmarAttractionsConstants.IMAGE_ROOT_DIR + attraction.getImages()[0];
        Glide.with(ivAttraction.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivAttraction);
                */

        collapsingToolbarJoke.setTitle(mJokeTitle);
    }



}

