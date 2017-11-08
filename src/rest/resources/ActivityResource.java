package rest.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import rest.model.Activity;
import rest.model.ActivityType;

@Stateless
@LocalBean
@Path("/activity_types")
public class ActivityResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public ActivityType[] getActivityTypeList() {
		System.out.println("--> ActivityResource request...");
		System.out.println("--> URI = "+uriInfo);
		System.out.println("--> request = "+request);
		ActivityType[] activities = ActivityType.values();
		
		return activities;
	}

	
}