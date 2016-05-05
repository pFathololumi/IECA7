/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.internetengineering.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author Hamed Ara
 */
public class JsonBuilder {
    public static void writeToJSON(Map<String,Object>map, HttpServletResponse response) throws IOException{
            JSONObject json = new JSONObject(map);
            response.getWriter().print(json);
            response.setContentType("application/json");
    }
    
}
