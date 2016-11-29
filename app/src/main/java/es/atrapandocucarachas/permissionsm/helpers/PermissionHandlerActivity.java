package es.atrapandocucarachas.permissionsm.helpers;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;

public abstract class PermissionHandlerActivity extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback {

	private static final int PERMISSION_REQ_CODE = 324;

	/**
	 * Checks if the given permission has been granted by the user to this application before.
	 *
	 * @param permission to check availability for
	 * @return true if the permission is currently granted, false otherwise.
	 */
	protected boolean isPermissionGranted(@NonNull String permission) {
		int result = ContextCompat.checkSelfPermission(this, permission);
		return result == PermissionChecker.PERMISSION_GRANTED;
	}

	/**
	 * Starts the async request of the given permission.
	 *
	 * @param permission to request to the user
	 */
	protected void requestPermission(@NonNull String permission) {
		ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSION_REQ_CODE);
	}

	/**
	 * Starts the async request of the given permissions
	 *
	 * @param permissions to request to the user
	 */
	public void requestPermissions(@NonNull String[] permissions) {
		ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQ_CODE);

	}

	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		if (requestCode != PERMISSION_REQ_CODE || (permissions.length == 0 && grantResults.length == 0)) {
			return;
		}
		for (int i = 0; i < permissions.length; i++) {
			if (grantResults[i] == PermissionChecker.PERMISSION_GRANTED) {
				onPermissionGranted(permissions[i]);
			} else {
				onPermissionDenied(permissions[i]);
			}
		}
	}

	/**
	 * Called when a permission request is accepted by the user.
	 *
	 * @param permission the user just accepted.
	 */
	protected abstract void onPermissionGranted(String permission);

	/**
	 * Called when a permission request is declined by the user.
	 *
	 * @param permission the user just declined.
	 */
	protected abstract void onPermissionDenied(String permission);

}
