package br.com.aracajucontroledepragas.auditor2;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.PreferenceManager;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MapsActivity extends FragmentActivity implements  GoogleMap.OnInfoWindowLongClickListener, GoogleMap.OnInfoWindowClickListener,  OnMapReadyCallback, SharedPreferences.OnSharedPreferenceChangeListener {

    private GoogleMap mMap;
    FloatingActionButton fabSettings, fabPonto;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fabSettings = (FloatingActionButton) findViewById(R.id.fabSettings);
        fabPonto = (FloatingActionButton) findViewById(R.id.fabPonto);

        setupSharedPreferences();

    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setIndoorEnabled(false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-26.3594415, -49.2029469)));
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-26.3594415, -49.2029469), 8));
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnInfoWindowLongClickListener(this);
        mMap.getUiSettings().setCompassEnabled(true);
    }

    public void abreSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {


        switch (key){
            case "move_mapa" : Toast.makeText(this, "Move mapa", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Outra chave", Toast.LENGTH_SHORT).show();;
        }




    }

    private void setupSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        /*
        Ponto pontoclicado = bancodedados.pontoDao().getPontoByID(Integer.valueOf(marker.getTag().toString()));
        if (pontoclicado.getTipoponto().equals("Coleta")) {
            novaColeta((Integer) marker.getTag());
        }
        if (pontoclicado.getTipoponto().equals("Vazão")) {
            novaVazao((Integer) marker.getTag());
        }
        if (pontoclicado.getTipoponto().equals("Aplicação")) {

            String pontos_aplicacao_manual = bancodedados.ConfiguracaoDao().getConfiguracaoByNome("pontos_aplicacao_manual").getDado();
            String[] pontos_aplicacao_manual_array = pontos_aplicacao_manual.split(";");
            for (int i=0;  i < pontos_aplicacao_manual_array.length; i++) {
                if ( pontos_aplicacao_manual_array[i].equals( String.valueOf(pontoclicado.getId() ) ) ) {
                    Log.d(TAG, "Clicado no ponto com marcacao maual ID: " +  String.valueOf(pontoclicado.getId() ) );
                    novaAplicacao( pontoclicado.getId() );
                }
            }
        }  */
    }

    @Override
    public void onInfoWindowLongClick(Marker marker) {
        /*
        if (marker.getTitle().contains("TEMP")) {
            editaPontotemp( (Integer) marker.getTag() ) ;
            Log.d(TAG,"contem TEMP ID: " + marker.getTag());

        }else{

            editaPonto( (Integer) marker.getTag() );
            Log.d(TAG,"nao contem TEMP ID: " + marker.getTag());
        } */
    }
}
