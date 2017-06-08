package com.example.jesse.ciphertools;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Menu savedMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.share_main, menu);
        savedMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // makes the texts invisible unless its necessary.
        if (id != R.id.caesarian_shift){

            EditText output = (EditText) findViewById(R.id.output);
            output.setVisibility(View.VISIBLE);

            TextView caesar_5 = (TextView) findViewById(R.id.caesarText_5);
            TextView caesar_4 = (TextView) findViewById(R.id.caesarText_4);
            TextView caesar_3 = (TextView) findViewById(R.id.caesarText_3);
            TextView caesar_2 = (TextView) findViewById(R.id.caesarText_2);
            TextView caesar_1 = (TextView) findViewById(R.id.caesarText_1);
            TextView caesar0 = (TextView) findViewById(R.id.caesarText0);
            TextView caesar1 = (TextView) findViewById(R.id.caesarText1);
            TextView caesar2 = (TextView) findViewById(R.id.caesarText2);
            TextView caesar3 = (TextView) findViewById(R.id.caesarText3);
            TextView caesar4 = (TextView) findViewById(R.id.caesarText4);
            TextView caesar5 = (TextView) findViewById(R.id.caesarText5);

            caesar_5.setText("");
            caesar_4.setText("");
            caesar_3.setText("");
            caesar_2.setText("");
            caesar_1.setText("");
            caesar0.setText("");
            caesar1.setText("");
            caesar2.setText("");
            caesar3.setText("");
            caesar4.setText("");
            caesar5.setText("");
        }

        if (id == R.id.atbash) {

            EditText input = (EditText) findViewById(R.id.input);
            EditText output = (EditText) findViewById(R.id.output);

            // this is where the text will be translated.
            input.addTextChangedListener(new TextWatcher() {

                EditText input = (EditText) findViewById(R.id.input);
                EditText output = (EditText) findViewById(R.id.output);

                @Override
                public void afterTextChanged(Editable s) {}

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {}

                // this is where the text will be translated.
                @Override
                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                    // get the input string and chop it up to smaller strings.
                    String inputString = input.getText().toString();

                    output.setText(BasicCiphers.translateAtbash(inputString));

                    updateShare();
                }

            });


        } else if (id == R.id.caesarian_shift) {


            EditText input = (EditText) findViewById(R.id.input);
            EditText output = (EditText) findViewById(R.id.output);
            output.setVisibility(View.INVISIBLE);


            // this is where the text will be translated.
            input.addTextChangedListener(new TextWatcher() {

                EditText input = (EditText) findViewById(R.id.input);

                @Override
                public void afterTextChanged(Editable s) {}

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {}

                // this is where the text will be translated.
                @Override
                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                    // get the input string and chop it up to smaller strings.
                    String inputString = input.getText().toString();

                    TextView caesar_5 = (TextView) findViewById(R.id.caesarText_5);
                    TextView caesar_4 = (TextView) findViewById(R.id.caesarText_4);
                    TextView caesar_3 = (TextView) findViewById(R.id.caesarText_3);
                    TextView caesar_2 = (TextView) findViewById(R.id.caesarText_2);
                    TextView caesar_1 = (TextView) findViewById(R.id.caesarText_1);
                    TextView caesar0 = (TextView) findViewById(R.id.caesarText0);
                    TextView caesar1 = (TextView) findViewById(R.id.caesarText1);
                    TextView caesar2 = (TextView) findViewById(R.id.caesarText2);
                    TextView caesar3 = (TextView) findViewById(R.id.caesarText3);
                    TextView caesar4 = (TextView) findViewById(R.id.caesarText4);
                    TextView caesar5 = (TextView) findViewById(R.id.caesarText5);

                    caesar_5.setText("Shifted Left 5 Times: " + BasicCiphers.caesarianShift(-5, inputString ));
                    caesar_4.setText("Shifted Left 4 Times: " + BasicCiphers.caesarianShift(-4, inputString ));
                    caesar_3.setText("Shifted Left 3 Times: " + BasicCiphers.caesarianShift(-3, inputString ));
                    caesar_2.setText("Shifted Left 2 Times: " + BasicCiphers.caesarianShift(-2, inputString ));
                    caesar_1.setText("Shifted Left 1 Time: " + BasicCiphers.caesarianShift(-1, inputString ));
                    caesar0.setText("Original Message: " + BasicCiphers.caesarianShift(0, inputString ));
                    caesar1.setText("Shifted Right 1 Time: " + BasicCiphers.caesarianShift(1, inputString ));
                    caesar2.setText("Shifted Right 2 Times: " + BasicCiphers.caesarianShift(2, inputString ));
                    caesar3.setText("Shifted Right 3 Times: " + BasicCiphers.caesarianShift(3, inputString ));
                    caesar4.setText("Shifted Right 4 Times: " + BasicCiphers.caesarianShift(4, inputString ));
                    caesar5.setText("Shifted Right 5 Times: " + BasicCiphers.caesarianShift(5, inputString ));


                }

            });

        } else if (id == R.id.morse_code) {

        } else if (id == R.id.skip) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void updateShare(){
        MenuItem item = savedMenu.findItem(R.id.shareButton);
        EditText output = (EditText) findViewById(R.id.output);
        ShareActionProvider mShareActionProvider = (ShareActionProvider)MenuItemCompat.getActionProvider(item);
        //create the sharing intent
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = output.getText().toString();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Translate This!");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);

        //then set the sharingIntent
        mShareActionProvider.setShareIntent(sharingIntent);
    }


}
