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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * saves the menu for the sake of the share function.
     */
    private Menu savedMenu;

    /**
     * the encrypt radio button
     */
    private RadioButton encrypt;

    /**
     * the decrypt radio button
     */
    private RadioButton decrypt;

    /**
     * the input text box.
     */
    private EditText input;

    /**
     * the output text box
     */
    private EditText output;

    /**
     * the selected menu.
     */
    private int selectedMenu;

    /**
     * this is the spinner controlling the caesarian shift.
     */
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize variables.
        output = (EditText) findViewById( R.id.output );
        input = (EditText) findViewById( R.id.input );

        selectedMenu = R.id.atbash;

        encrypt = (RadioButton) findViewById(R.id.encryptButton);
        decrypt = (RadioButton) findViewById(R.id.decryptButton);
        encrypt.setVisibility(View.INVISIBLE);
        decrypt.setVisibility(View.INVISIBLE);

        spinner = (Spinner) findViewById( R.id.caesarSpinner );
        spinner.setVisibility(View.INVISIBLE);


        // end of manual initialization.

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //sets default to atbash and checks the menu item.
        navigationView.getMenu().getItem(0).setChecked(true);
        setUpAtBash();

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.caesarValues, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                updateCaesarText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

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
        selectedMenu = item.getItemId();

        /*
         * sets up the layouts.
         */
        setUp();

        // updates the output
        updateOutput();

        /*
         * deals with the drawers.
         */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * handles the radio buttons.
     * @param view the view
     */
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.encryptButton:
                if (checked) {
                    decrypt.setChecked(false);
                    encrypt.setChecked(true);
                    setUp();
                    updateOutput();
                }
                break;
            case R.id.decryptButton:
                if (checked) {
                    encrypt.setChecked(false);
                    decrypt.setChecked(true);
                    setUp();
                    updateOutput();
                }
                break;
        }
    }

    /**
     * updates the share functionality.
     */
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


    /**
     * sets up the atbash layout.
     */
    private void setUpAtBash(){
        // this is where the text will be translated.
        input.addTextChangedListener(new TextWatcher() {

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
    }

    /**
     * sets up the caesarian shift operation
     */
    private void setUpCaesarianShift(){
        // this is where the text will be translated.
        input.addTextChangedListener(new TextWatcher() {

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
                updateCaesarText();
                updateShare();
            }

        });
    }

    /**
     * updates the caesar text.
     */
    private void updateCaesarText(){
        if (decrypt.isChecked()) {
            output.setText(BasicCiphers.caesarianShift(Integer.valueOf(spinner.getSelectedItem().toString()).intValue() * -1, input.getText().toString()));
        } else {
            output.setText(BasicCiphers.caesarianShift(Integer.valueOf(spinner.getSelectedItem().toString()).intValue(), input.getText().toString()));
        }
    }

    /**
     * sets up the morse encoder.
     */
    private void setUpMorse(){
        // this is where the text will be translated.
        input.addTextChangedListener(new TextWatcher() {

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
                String inputString = input.getText().toString().toLowerCase();

                if (decrypt.isChecked()) {
                    output.setText(BasicCiphers.morseCode(true, inputString));
                } else {
                    output.setText(BasicCiphers.morseCode(false, inputString));
                }
                updateShare();
            }

        });
    }

    /**
     * updates the output text.
     */
    private void updateOutput(){
        if ( selectedMenu == R.id.atbash ){
            output.setText(BasicCiphers.translateAtbash(input.getText().toString()));
        } else if ( selectedMenu == R.id.caesarian_shift ){
            updateCaesarText();
        } else if ( selectedMenu == R.id.morse_code ){
            if ( decrypt.isChecked() ){
                output.setText(BasicCiphers.morseCode(true, input.getText().toString()));
            } else {
                output.setText(BasicCiphers.morseCode(false, input.getText().toString()));
            }
        } else if ( selectedMenu == R.id.skip ){

        }
        updateShare();
    }

    /**
     * figures out which menu to set up.
     */
    private void setUp(){

        if (selectedMenu == R.id.atbash) {
            setUpAtBash();
        } else if (selectedMenu == R.id.caesarian_shift) {
            setUpCaesarianShift();
            spinner.setVisibility(View.VISIBLE);
            decrypt.setVisibility(View.VISIBLE);
            encrypt.setVisibility(View.VISIBLE);
        } else if (selectedMenu == R.id.morse_code) {
            spinner.setVisibility(View.INVISIBLE);
            decrypt.setVisibility(View.VISIBLE);
            encrypt.setVisibility(View.VISIBLE);
            setUpMorse();
        } else if (selectedMenu == R.id.skip) {
            spinner.setVisibility(View.INVISIBLE);
            decrypt.setVisibility(View.INVISIBLE);
            encrypt.setVisibility(View.INVISIBLE);

        }
    }


}
