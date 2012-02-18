package org.bismo.client.http;

import org.bismo.client.ApplicationController;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;


public class ConnectionChecker {

	public static boolean checkConnection(Activity a) {
		// @see
		// http://developer.android.com/guide/topics/ui/notifiers/toasts.html to
		// announce no connection.
		// needs ref to context to publish toast.
		ConnectivityManager connec = (ConnectivityManager) a
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connec.getNetworkInfo(0).isConnected()
				|| connec.getNetworkInfo(1).isConnected()) {
			// internet connection available
			return true;
		} else {
			/*
			 * AlertDialog alertDialog = new AlertDialog.Builder(a).create();
			 * alertDialog.setTitle("Connection Trouble");
			 * alertDialog.setMessage(
			 * "Couldn't find an internet connection! Please check your phone settings."
			 * ); alertDialog.setButton("OK", new
			 * DialogInterface.OnClickListener() {
			 * 
			 * @Override public void onClick(DialogInterface dialog, int which)
			 * { //Stop the activity return; } }); alertDialog.show();
			 */
			// just return false and allow each activity/function to deal with
			// that as it wants.
			return false;
		}
	}

	public static boolean checkConnection(ApplicationController a) {
		// @see
		// http://developer.android.com/guide/topics/ui/notifiers/toasts.html to
		// announce no connection.
		// needs ref to context to publish toast.
		ConnectivityManager connec = (ConnectivityManager) a
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connec.getNetworkInfo(0).isConnected()
				|| connec.getNetworkInfo(1).isConnected()) {
			// internet connection available
			return true;
		} else {
			/*
			 * AlertDialog alertDialog = new AlertDialog.Builder(a).create();
			 * alertDialog.setTitle("Connection Trouble");
			 * alertDialog.setMessage(
			 * "Couldn't find an internet connection! Please check your phone settings."
			 * ); alertDialog.setButton("OK", new
			 * DialogInterface.OnClickListener() {
			 * 
			 * @Override public void onClick(DialogInterface dialog, int which)
			 * { //Stop the activity return; } }); alertDialog.show();
			 */
			// just return false and allow each activity/function to deal with
			// that as it wants.
			return false;
		}
	}
	
	public static boolean checkConnectionToast(Activity a) {
		// @see
		// http://developer.android.com/guide/topics/ui/notifiers/toasts.html to
		// announce no connection.
		// needs ref to context to publish toast.
		ConnectivityManager connec = (ConnectivityManager) a
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connec.getNetworkInfo(0).isConnected()
				|| connec.getNetworkInfo(1).isConnected()) {
			// internet connection available
			return true;
		} else {
			Toast
					.makeText(
							a,
							"Could not establish a connection, please check your wifi/3g",
							Toast.LENGTH_SHORT).show();
			return false;
		}
	}
}
