package com.happening.aeven;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.happening.aeven.type.IconsType;
import com.mapbox.android.gestures.AndroidGesturesManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;

import static com.happening.aeven.type.IconsType.MAKI_ICON_HARBOR;


public class MapsContent extends Fragment implements
        OnMapReadyCallback {

    private MapView mapView;
    private MapboxMap mapboxMap;
    private SymbolManager symbolManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(getActivity(), "pk.eyJ1IjoiYWRpbmFzOTIiLCJhIjoiY2p4b3praGFpMGJjcjNubW96YWp2dXF3MSJ9.5AHqWfXgJK-hPPK_1f60zw");
        View view = inflater.inflate(R.layout.activity_maps, container, false);
        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;

        mapboxMap.setStyle(Style.DARK, style -> {

            // Set up a SymbolManager instance
            symbolManager = new SymbolManager(mapView, mapboxMap, style);
            symbolManager.setIconAllowOverlap(true);
            symbolManager.setTextAllowOverlap(true);

            // Add symbol at specified lat/lon
            moveToNextPoint(52.232537, 21.009421, MAKI_ICON_HARBOR);
        });
    }

    public void moveToNextPoint(double lan, double lon, IconsType icon) {
        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(buildCameraPosition(lan, lon)), 2000);
        addSymbol(lan, lon, icon.toString());
    }

    private CameraPosition buildCameraPosition(double v, double v2) {
        return new CameraPosition.Builder()
                .target(new LatLng(v, v2))
                .zoom(15)
                .tilt(20)
                .build();
    }

    private Symbol addSymbol(double v, double v2, String makiIconHarbor) {
        return symbolManager.create(new SymbolOptions()
                .withLatLng(new LatLng(v, v2))
                .withIconImage(makiIconHarbor)
                .withIconSize(2.0f)
                .withDraggable(true));
    }


    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}