package org.bismo.client.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.bismo.client.ApplicationController;

public class RestClient {
	
	public static final int HTTP_GET=1,HTTP_POST=2,HTTP_PUT=3,HTTP_DELETE=4;
	private ArrayList<NameValuePair> params;
	private ArrayList<NameValuePair> headers;

	private String url;

	private int responseCode;
	private String message;

	private String response;

	private int last_method=0;
	
	public String getResponse() {
		return response;
	}

	public String getErrorMessage() {
		return message;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public RestClient(String url) {
		this.url = url;
		params = new ArrayList<NameValuePair>();
		headers = new ArrayList<NameValuePair>();
//		AddHeader("Accept-Encoding", "gzip");
	}
	
	public RestClient(String url, String access_token) {
		this(url);
		AddHeader("Authorization", "Bearer "+access_token);
	}	

	public RestClient(ApplicationController ac, String url) throws Exception{
		this(url);
		if(!ConnectionChecker.checkConnection(ac)){
			throw new Exception();
		}
	}	
	
	public RestClient(ApplicationController ac, String url, String access_token) throws Exception{
		this(url,access_token);
		if(!ConnectionChecker.checkConnection(ac)){
			throw new Exception();
		}
	}	
	
	public void AddParam(String name, String value) {
		params.add(new BasicNameValuePair(name, value));
	}

	public void AddHeader(String name, String value) {
		headers.add(new BasicNameValuePair(name, value));
	}

	//TODO: throw all exceptions in the Execute method (or equivalent)
	public void Execute(int method) throws UnsupportedEncodingException  {
		last_method=method;
		switch (method) {
			case HTTP_GET: {
				HttpGet request = new HttpGet(url + getCombinedParams());
				// add headers
				for (NameValuePair h : headers) {
					request.addHeader(h.getName(), h.getValue());
				}
				executeRequest(request, url);
				break;
			}
			case HTTP_POST: {
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
			case HTTP_PUT: {
				HttpPut request = new HttpPut(url);
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
			case HTTP_DELETE: {
	
				HttpDelete request = new HttpDelete(url + getCombinedParams());
				// add headers
				for (NameValuePair h : headers) {
					request.addHeader(h.getName(), h.getValue());
				}
				executeRequest(request, url);
				break;
			}			
		}
	}
	public String createUser(String email, String password, String fullname, String filename){
		String result = "";
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost request = new HttpPost(url);

			for (NameValuePair h : headers) {
				request.addHeader(h.getName(), h.getValue());
			}
			
			MultipartEntity entity = new MultipartEntity();
			
			for (NameValuePair p : params) {
				entity.addPart(p.getName(),new StringBody( p.getValue()));
			}
			
			if(fullname!=null){
				entity.addPart("fullname", new StringBody(fullname));
			}
			if(email!=null){
				entity.addPart("username", new StringBody(email));
			}
			if(password!=null){
				entity.addPart("password", new StringBody(password));
			}
			if(filename!=null){
				File f = new File(filename);
				entity.addPart("photo", new FileBody(f));
			}
			
			request.setEntity(entity);
			// executeRequest(request, url);

			HttpResponse response;
			
			response = httpclient.execute(request);
			responseCode = response.getStatusLine().getStatusCode();
			message = response.getStatusLine().getReasonPhrase();
			
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
	
	public String editUser(String email, String password, String fullname, String filename, String nickname, String description,boolean emailNotifications){
		String result = "";
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost request = new HttpPost(url);

			for (NameValuePair h : headers) {
				request.addHeader(h.getName(), h.getValue());
			}
			MultipartEntity entity = new MultipartEntity();
			if(fullname!=null){
				entity.addPart("fullname", new StringBody(fullname));
			}
			if(nickname!=null){
				entity.addPart("nickname", new StringBody(nickname));
			}
			if(email!=null){
				entity.addPart("username", new StringBody(email));
			}			
			if(description!=null){
				entity.addPart("description", new StringBody(description));
			}
			if(password!=null){
				entity.addPart("password", new StringBody(password));
			}
			
			if (emailNotifications) {
				entity.addPart("emailNotifications", new StringBody("true"));
			}else{
				entity.addPart("emailNotifications", new StringBody("false"));
			}
			
			if(filename!=null){
				File f = new File(filename);
				entity.addPart("photo", new FileBody(f));
			}
			
			request.setEntity(entity);
			HttpResponse response;

			response = httpclient.execute(request);
			responseCode = response.getStatusLine().getStatusCode();
			message = response.getStatusLine().getReasonPhrase();
			
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
			responseCode = response.getStatusLine().getStatusCode();
			message = response.getStatusLine().getReasonPhrase();

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

	public String prepareUpload(String filename) throws Exception {
		String result = "";
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost request = new HttpPost(url);
			File f = new File(filename);
			MultipartEntity entity = new MultipartEntity();
			entity.addPart("photo[filename]", new FileBody(f));
			request.setEntity(entity);
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
	
	public String getURL() {
		return url;
	}
	
	public String getURLWithParams() {
		return url + getCombinedParams();
	}
	
	public String getStringWithURLandHeaders() {
		String res=getURLWithParams() + " (Method: " + getLastMethodSting() + " Header: ";
		for (NameValuePair nvp:headers)
			res+=nvp.getName()+"=>"+nvp.getValue() + " ";
		
		return res+")";
	}
	
	public String getCombinedParams() {
		String combinedParams = "";
		if (!params.isEmpty()) {
			combinedParams += "?";
			for (NameValuePair p : params) {
				String paramString = p.getName() + "=";	
				
				try {
					paramString+=URLEncoder.encode(p.getValue(), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					paramString+=URLEncoder.encode(p.getValue());
				}
				
				if (combinedParams.length() > 1) {
					combinedParams += "&" + paramString;
				} else {
					combinedParams += paramString;
				}
			}
		}
		return combinedParams;
	}
	
	public String getLastMethodSting() {
		switch( last_method ) {
		case 0:
			return "NONE";
		case 1:
			return "GET";
		case 2:
			return "POST";
		case 3:
			return "PUT";
		case 4:
			return "DELETE";
		default:
			return "#FAIL"; 
		}
	}
}