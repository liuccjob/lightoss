package controllers;

import play.libs.Json;
import play.mvc.*;
import play.mvc.Http.RequestBody;

import com.fasterxml.jackson.databind.JsonNode; 
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pbn.oss.adaptor.IAdaptor;
import com.pbn.oss.adaptor.IResourceAdaptor;
import com.pbn.oss.adaptor.eoc.gcable.DiscoveryEocImpl;

import views.html.*;

import models.*;

public class Application extends Controller {
  
    /**
     * Display the home page.
     */
    public static Result index() {
        return ok(index.render());
    }
    
    /**
     * Display the update page.
     */
    public static Result update() {
        return ok(update.render());
    }
    
    /**
     * Start MIB Test.
     */
    public static Result mainJS() {
        return ok(views.js.main.render());
    }
    
    @BodyParser.Of(BodyParser.Json.class)
    public static Result startTest(){  	
    	ObjectNode result = Json.newObject();
    	result.put("masterIP",request().getQueryString("masterIP"));
    	result.put("retry", request().getQueryString("retry"));
    	return ok(result);
    }
    
    /**
     * Display the home page.
     */
    public static Result findEoC(final String adaptor) {
    	if(adaptor.equals("eoc_db_gcable"))
    	{
    		IAdaptor ira = new DiscoveryEocImpl();
    		IResourceAdaptor irs = new DiscoveryEocImpl(); 
    		ira.getSystemsByNetworkRange("192.168.16.101", "192.168.16.101", 1, "public");
    		//irs.discovery(hostIp, xmlpath, snmpVersion, readComm, writeComm);
    	}
    	return ok(result.render());
    }
  
}
