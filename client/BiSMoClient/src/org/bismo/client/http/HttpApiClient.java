package org.bismo.client.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;


import android.util.Log;

public class HttpApiClient {
	
	private ArrayList<NameValuePair> params;
	private ArrayList<NameValuePair> headers;

	private String url;

	private int responseCode;
	private String message;

	private String response;

	public String getResponse() {
		if (response != null) {
			Log.d("RestClient", "response : " + response);
			Log.d("RestClient", "response empty");
		}
		return response;
	}

	public String getErrorMessage() {
		return message;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public HttpApiClient(String url) {
		this.url = url;
		params = new ArrayList<NameValuePair>();
		headers = new ArrayList<NameValuePair>();
		AddHeader("Accept-Encoding", "gzip");
	}

	public void AddParam(String name, String value) {
		params.add(new BasicNameValuePair(name, value));
	}

	public void AddHeader(String name, String value) {
		headers.add(new BasicNameValuePair(name, value));
	}

	// replaced enum with int for method. 1=get, 2=post.
	public void Execute(int method) throws Exception {
		switch (method) {
		case 1: {
			// add parameters
			String combinedParams = "";
			if (!params.isEmpty()) {
				combinedParams += "?";
				for (NameValuePair p : params) {
					String paramString = p.getName() + "="
							+ URLEncoder.encode(p.getValue(), "UTF-8");
					if (combinedParams.length() > 1) {
						combinedParams += "&" + paramString;
					} else {
						combinedParams += paramString;
					}
				}
			}

			HttpGet request = new HttpGet(url + combinedParams);

			// add headers
			for (NameValuePair h : headers) {
				request.addHeader(h.getName(), h.getValue());
			}
			executeRequest(request, url);
			break;
		}
		case 2: {
			HttpPost request = new HttpPost(url);
			// add headers
			for (NameValuePair h : headers) {
				request.addHeader(h.getName(), h.getValue());
			}

			if (!params.isEmpty()) {
				request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			}

			executeRequest(request, url);
			break;
		}
		}
	}

	public String uploadFile(String filename, String title, String description,
			boolean flickr, boolean facebook, boolean twitter) throws Exception {
		String result = "";
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost request = new HttpPost(url);
			File f = new File(filename);

			MultipartEntity entity = new MultipartEntity();
			entity.addPart("photo[title]", new StringBody(title));
			entity.addPart("photo[description]", new StringBody(description));
			if (flickr) {
				entity
						.addPart("photo[share][flickr]", new StringBody(
								"flickr"));
			}
			if (facebook) {
				entity.addPart("photo[share][facebook]", new StringBody(
						"facebook"));
			}
			if (twitter) {
				entity.addPart("photo[share][twitter]", new StringBody(
						"twitter"));
			}
			entity.addPart("photo[filename]", new FileBody(f));
			request.setEntity(entity);
			// executeRequest(request, url);

			HttpResponse response;

			response = httpclient.execute(request);

			if (entity != null) {
				entity.consumeContent();
				HttpEntity resEntity = response.getEntity();
				InputStream instream = resEntity.getContent();
				Header contentEncoding = response
						.getFirstHeader("Content-Encoding");
				if (contentEncoding != null
						&& contentEncoding.getValue().equalsIgnoreCase("gzip")) {
					instream = new GZIPInputStream(instream);
				}
				result = convertStreamToString(instream);
				instream.close();
			}
		} catch (Exception ex) {
			Log.d("FormReviewer", "Upload failed: " + ex.getMessage()
					+ " Stacktrace: " + ex.getStackTrace());
			return null;
		}
		return result;

	}

	public String prepareUpload(String filename) throws Exception {
		String result = "";
		try {
			Log.d("UploadForm", "upload speed: prepare  restclient - creating http client");
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost request = new HttpPost(url);
			File f = new File(filename);
			MultipartEntity entity = new MultipartEntity();
			Log.d("UploadForm", "upload speed: prepare  restclient - adding file");
			entity.addPart("photo[filename]", new FileBody(f));
			request.setEntity(entity);
			HttpResponse response;
			Log.d("UploadForm", "upload speed: prepare  restclient - executing call");
			response = httpclient.execute(request);
			Log.d("UploadForm", "upload speed: prepare  restclient - executed call");
			if (entity != null) {
				entity.consumeContent();
				HttpEntity resEntity = response.getEntity();
				InputStream instream = resEntity.getContent();
				Header contentEncoding = response
						.getFirstHeader("Content-Encoding");
				if (contentEncoding != null
						&& contentEncoding.getValue().equalsIgnoreCase("gzip")) {
					instream = new GZIPInputStream(instream);
				}
				result = convertStreamToString(instream);
				instream.close();
			}
		} catch (Exception ex) {
			return null;			
		}
		return result;
	}

	private void executeRequest(HttpUriRequest request, String url) {
		HttpClient client = new DefaultHttpClient();

		HttpResponse httpResponse;

		try {
			httpResponse = client.execute(request);
			responseCode = httpResponse.getStatusLine().getStatusCode();
			message = httpResponse.getStatusLine().getReasonPhrase();

			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {

				InputStream instream = entity.getContent();
				Header contentEncoding = httpResponse
						.getFirstHeader("Content-Encoding");
				if (contentEncoding != null
						&& contentEncoding.getValue().equalsIgnoreCase("gzip")) {
					instream = new GZIPInputStream(instream);
				}
				response = convertStreamToString(instream);

				// Closing the input stream will trigger connection release
				instream.close();
			}

		} catch (ClientProtocolException e) {
			client.getConnectionManager().shutdown();
			e.printStackTrace();
		} catch (IOException e) {
			client.getConnectionManager().shutdown();
		}
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