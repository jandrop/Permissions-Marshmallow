package es.atrapandocucarachas.permissionsm;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import es.atrapandocucarachas.permissionsm.helpers.PermissionHandlerActivity;

public class MainActivity extends PermissionHandlerActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)){
            requestPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        }

    }

    @Override
    protected void onPermissionGranted(String permission) {
        Toast.makeText(MainActivity.this, permission, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPermissionDenied(String permission) {
        Toast.makeText(MainActivity.this, permission, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "You have denied the permission so if you want to access the location, you will activate the permission manually", Toast.LENGTH_SHORT).show();
    }
}