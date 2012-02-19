package org.bismo.client.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BismoHelper {
	
	public static final int QR_CODE_RESULT = 1;
	
	public static ArrayList<String> retrieveLinks(String text) {
        ArrayList<String> links = new ArrayList<String>();

        String regex = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        while(m.find()) {
        String urlStr = m.group();

        if (urlStr.startsWith("(") && urlStr.endsWith(")"))
        {

            char[] stringArray = urlStr.toCharArray(); 

            char[] newArray = new char[stringArray.length-2];
            System.arraycopy(stringArray, 1, newArray, 0, stringArray.length-2);
            urlStr = new String(newArray);
            System.out.println("Finally Url ="+newArray.toString());

        }
        System.out.println("...Url..."+urlStr);
        links.add(urlStr);
        }
        return links;
        }
}
