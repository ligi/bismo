package org.bismo.tv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class RESTHelper {

	public final static int METHOD_POST=0;
	public final static int METHOD_GET=1;
	
	public static String doREST(String url_str,int method) {
		HttpClient client = new DefaultHttpClient();
		String resp="";
		
		HttpRequestBase request=null;
		
		switch(method) {
			case METHOD_POST:
				request=new HttpPost(url_str);
				break;
			case METHOD_GET:
				request=new HttpGet(url_str);
				break;
		}
		
		try {
			HttpResponse response = client.execute(request);
			
			HttpEntity entity = response.getEntity();

			if (entity != null) {

				InputStream instream = entity.getContent();
				Header contentEncoding = response
						.getFirstHeader("Content-Encoding");
				if (contentEncoding != null
						&& contentEncoding.getValue().equalsIgnoreCase("gzip")) {
					instream = new GZIPInputStream(instream);
				}
				resp = convertStreamToString(instream);

				instream.close();
				Log.i("BismoREST"," resp" + resp);
				// Closing the input stream will trigger connection release
				
			}		
			
			
		} catch (ClientProtocolException e) {
		} catch (IOException e) {

		}
		return resp;
	}

    private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
