package com.Core.Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.Core.graphics.shader.Shaderclass;

public class FileBuilder {
	 public static String gettext(String name)
	    {
	        StringBuilder source = new StringBuilder();
	        try
	        {
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                                                                Shaderclass.class
	                                                                .getClassLoader()
	                                                                .getResourceAsStream(name)));
	            
	            String line;
	            while ((line = reader.readLine()) != null)
	            {
	                source.append(line).append("\n");
	            }
	            
	            reader.close();
	        }
	        catch (Exception e)
	        {
	            System.err.println("Error loading source code: " + name);
	            e.printStackTrace();
	          
	        }
	        
	        return source.toString();
	    }
}
