package com.queroquero.rest.resources;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.queroquero.rest.model.Vendedor;
import com.queroquero.rest.repository.VendedorRepository;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("vendedor")
public class VendedorResource {
	
	private static VendedorRepository repo = new VendedorRepository();
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
    	Gson gson = new Gson();
    	//gson.fromJson(json, new TypeToken<List<Vendedor>>(){}.getType());
    	String json = gson.toJson(repo.getAll());//, new TypeToken<List<Vendedor>>(){}.getType());
    	return Response.ok(json).build();
    }
    
    @GET
    @Path("maiorqnt")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopQuantity() {
    	Gson gson = new Gson();
    	//gson.fromJson(json, new TypeToken<List<Vendedor>>(){}.getType());
    	String json = gson.toJson(repo.getTopQuantity());//, new TypeToken<List<Vendedor>>(){}.getType());
    	return Response.ok(json).build();
    }
    
    @GET
    @Path("maiorvalor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopValue() {
    	Gson gson = new Gson();
    	//gson.fromJson(json, new TypeToken<List<Vendedor>>(){}.getType());
    	String json = gson.toJson(repo.getTopValue());//, new TypeToken<List<Vendedor>>(){}.getType());
    	return Response.ok(json).build();
    }
    
    @GET
    @Path("{matricula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("matricula") int matricula) {
    	Gson gson = new Gson();
    	String json = gson.toJson(repo.get(matricula));
    	return Response.ok(json).build();
    }
    
    @POST
    public Response add(String jsonText) {
    	Gson gson = new Gson();
    	Vendedor v = gson.fromJson(jsonText, Vendedor.class);
    	repo.add(v);
    	return Response.ok(gson.toJson(v)).build();
    }
    
    @DELETE
    @Path("{matricula}")
    public Response delete(@PathParam("matricula") int matricula) {
    	repo.delete(matricula);
    	return Response.ok().build();
    }
    
    @PUT
    public Response replace(String jsonText) {
    	Gson gson = new Gson();
    	Vendedor v = gson.fromJson(jsonText, Vendedor.class);
    	boolean ok = repo.update(v);
    	if(ok)
    		return Response.accepted(gson.toJson(v)).build();
    	return Response.notModified().build();
    }
}
