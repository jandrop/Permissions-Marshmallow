package es.atrapandocucarachas.permissionsm;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import es.atrapandocucarachas.permissionsm.helpers.PermissionHelper;

public class MainActivity extends AppCompatActivity implements PermissionHelper.PermissionCallback {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		PermissionHelper permissionHelper = new PermissionHelper(this, this);
		if (!permissionHelper.isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
			String[] permissions = new String[3];
			permissions[0] = Manifest.permission.ACCESS_FINE_LOCATION;
			permissions[1] = Manifest.permission.CAMERA;
			permissions[2] = Manifest.permission.READ_CONTACTS;
			permissionHelper.requestPermissions(permissions);
		}
	}


	@Override
	public void onPermissionGranted(String permission) {
		Toast.makeText(MainActivity.this, permission, Toast.LENGTH_SHORT).show();
		Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onPermissionDenied(String permission) {
		Toast.makeText(MainActivity.this, permission, Toast.LENGTH_SHORT).show();
		Toast.makeText(MainActivity.this, "You have denied the permission so if you want to access the location, " +
				"you will activate the permission manually", Toast.LENGTH_SHORT).show();

	}
}
