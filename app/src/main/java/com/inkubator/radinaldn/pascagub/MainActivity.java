package com.inkubator.radinaldn.pascagub;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView imageView;
    TextView tvname, tvkategori;
    View header;
    private ProgressDialog pDialog;
    WebView wvutama;
    String id_pascagub, nama, kategori, dapil, daerah, id_daerah;
    SharedPreferences sharedPreferences;

    public static final String TAG_ID = "id_pascagub";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_KATEGORI= "kategori";

    //get server connection
    Koneksi koneksi = new Koneksi();
    String service_api = koneksi.getUrl();
    String server_api = koneksi.getServer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get session
        sharedPreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        id_pascagub = getIntent().getStringExtra(TAG_ID);
        nama = getIntent().getStringExtra(TAG_NAMA);
        kategori = getIntent().getStringExtra(TAG_KATEGORI);

        wvutama = (WebView) findViewById(R.id.wvutama);
        WebSettings settings = wvutama.getSettings();
        settings.setJavaScriptEnabled(true);
        wvutama.setWebViewClient(new WebViewClient());
        settings.setPluginState(WebSettings.PluginState.ON);

        wvutama.loadUrl(server_api + "index.php/kategori/frontendPascagub/" + id_pascagub);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LaporKegiatanPascagubActivity.class);
                i.putExtra(TAG_ID, id_pascagub);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);


        imageView = header.findViewById(R.id.imageView);
        tvname = header.findViewById(R.id.tvname);
        tvkategori = header.findViewById(R.id.tvkategori);

        Picasso.with(getApplicationContext()).load(R.drawable.pascagub).resize(100, 100).into(imageView);
        tvname.setText(nama);
        tvkategori.setText(kategori);

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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // Handling for logout action
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(LoginActivity.session_status, false);
            editor.putString(TAG_ID, null);
            editor.putString(TAG_NAMA, null);
            editor.putString(TAG_KATEGORI, null);
            editor.commit();

            // redirect to login page
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            finish();
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.kegiatan) {
            // Handle the camera action
            Intent i = new Intent(MainActivity.this, DaftarKegiatanPascagubActivity.class);
            i.putExtra(TAG_ID, id_pascagub);
            i.putExtra(TAG_KATEGORI, kategori);
            startActivity(i);

        } else if (id == R.id.nav_about) {
            AlertDialog alertDialog = new AlertDialog.Builder(
                    MainActivity.this).create();

            // Setting Dialog Title
            alertDialog.setTitle(R.string.license_title);

            // Setting Dialog Message
            alertDialog.setMessage("PASCAGUB v1.0\nCopyright ©WAR ROOM DPW PKS RIAU");

            // Setting Icon to Dialog
            alertDialog.setIcon(R.drawable.ic_logo);

            // Setting OK Button
//            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    // Write your code here to execute after dialog closed
//                    Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
//                }
//            });

            // Showing Alert Message
            alertDialog.show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
