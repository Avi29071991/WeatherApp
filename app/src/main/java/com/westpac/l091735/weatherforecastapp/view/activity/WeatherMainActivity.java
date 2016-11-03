package com.westpac.l091735.weatherforecastapp.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.westpac.l091735.weatherforecastapp.R;
import com.westpac.l091735.weatherforecastapp.application.MyWeatherApplication;
import com.westpac.l091735.weatherforecastapp.databinding.ActivityWeatherMainBinding;
import com.westpac.l091735.weatherforecastapp.model.beans.DailyData;
import com.westpac.l091735.weatherforecastapp.model.interfaces.IWeather;
import com.westpac.l091735.weatherforecastapp.presenter.WeatherImpl;
import com.westpac.l091735.weatherforecastapp.utils.Codes;
import com.westpac.l091735.weatherforecastapp.utils.Utilities;
import com.westpac.l091735.weatherforecastapp.view.adapter.DailyWeatherAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.List;

import javax.inject.Inject;

public class WeatherMainActivity extends AppCompatActivity implements IWeather, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    @Inject
    Context context;

    @Inject
    GoogleApiClient.Builder builder; // Building GoogleApiClient object

    @Inject
    LocationRequest locationRequest; // For initializing the request depending upon the type and time interval

    private GoogleApiClient googleApiClient; // Requesting location updates to begin

    private RecyclerView recyclerView;
    private TextView msgText;
    private ProgressBar loadingBar;

    private ActivityWeatherMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = WeatherMainActivity.this;

        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather_main);

        ((MyWeatherApplication) getApplication()).getAppComponent().injectWeatherActivity(this);

        setupUI();


        /**
         * This method needs to be used for actual implementation.
         **/
        if (Utilities.checkPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)) {
            startCall();
        } else {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        Codes.LOCATION_PERMISSION);
            }
        }
    }

    /**
     * Initializes the GoogleAPIClient
     * object which can be used for fetching location updates.
     **/
    private void startCall() {
        if (Utilities.isAlive(context)) {

            if (builder != null) {
                builder.addConnectionCallbacks(this);
                builder.addOnConnectionFailedListener(this);
                googleApiClient = builder.build();
                googleApiClient.connect();
            }

        }
    }

    public void setupUI() {
        if (Utilities.isAlive(context) && binding != null) {
            binding.setToolBarEmptyTitle(context.getResources().getString(R.string.empty));
            binding.setToolBarTitle(context.getResources().getString(R.string.demo_name));
            if (binding.getRoot() != null) {

                setSupportActionBar((Toolbar) binding.getRoot().findViewById(R.id.toolbar));
                View scrollingView = binding.getRoot().findViewById(R.id.scrolling_view);

                if (scrollingView != null) {

                    recyclerView = (RecyclerView) scrollingView.findViewById(R.id.recycler_view);
                    msgText = (TextView) scrollingView.findViewById(R.id.msg_text);
                    loadingBar = (ProgressBar) scrollingView.findViewById(R.id.loadingbar);

                    if (recyclerView != null) {
                        recyclerView.setVisibility(View.GONE);
                    }

                    if (loadingBar != null) {
                        loadingBar.setVisibility(View.VISIBLE);
                    }

                    if (msgText != null) {
                        msgText.setVisibility(View.GONE);
                    }

                }
            }
        }
    }

    @Override
    protected void onStart() {
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
        super.onStart();
    }

    @Override
    protected void onStop() {
        if (googleApiClient != null) {
            googleApiClient.disconnect();
        }
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Displays the list view with weather details for 8 days.
     *
     * @param dataList List of Daily Data which needs to be displayed.
     **/
    @Override
    public void onWeatherDataResponseSuccessful(List<DailyData> dataList) {
        if (dataList != null && dataList.size() > 0) {
            if (recyclerView != null) {
                DailyWeatherAdapter adapter = new DailyWeatherAdapter(dataList, context);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setVisibility(View.VISIBLE);
            }

            if (loadingBar != null) {
                loadingBar.setVisibility(View.GONE);
            }

            if (msgText != null) {
                msgText.setVisibility(View.GONE);
            }

        }
    }

    /**
     * Displays the screen with error
     * message if some error occurs while fetching data.
     *
     * @param message String which needs to be displayed
     **/
    @Override
    public void onWeatherDataResponseFailure(String message) {
        if (Utilities.isNotEmpty(message)) {
            if (recyclerView != null) {
                recyclerView.setVisibility(View.GONE);
            }

            if (loadingBar != null) {
                loadingBar.setVisibility(View.GONE);
            }

            if (msgText != null) {
                msgText.setText(message);
                msgText.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED && requestCode == Codes.LOCATION_PERMISSION) {

            if (googleApiClient != null) {
                fetchLocation();
            }

        } else {

            onWeatherDataResponseFailure(context.getResources().getString(R.string.permission_denied));

        }
    }


    private void fetchLocation() {
        try {
            if (googleApiClient != null && locationRequest != null) {

                LocationServices.FusedLocationApi.requestLocationUpdates(
                        googleApiClient, locationRequest, this);

            }
        } catch (SecurityException se) {
            se.printStackTrace();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        fetchLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    /**
     * Called when the location of device is updated.
     *
     * @param location the updated location of the device which
     *                 can be used for fetching weather data for updated location.
     **/
    @Override
    public void onLocationChanged(Location location) {
        if (Utilities.isAlive(context)) {
            if (location != null) {

                new WeatherImpl(context).fetchWeatherDataForDisplay(WeatherMainActivity.this, location.getLatitude(), location.getLongitude());

                if (googleApiClient != null) {
                    locationRequest = null;
                    LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
                }

            } else {
                onWeatherDataResponseFailure(context.getResources().getString(R.string.null_location));
            }
        }


    }
}