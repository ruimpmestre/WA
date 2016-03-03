package com.warwickanalytics.process;

public class DataFormatter {

	
	public static String formatTabularData(String data) {
		
		String output = "<html><table border=\"3\"><tr><td>" + 
						data.replace(",","</td><td>").replace("\n","</td></tr><tr><td>") +  
						"</td></tr></table><html>";

		return output;
	}
}
