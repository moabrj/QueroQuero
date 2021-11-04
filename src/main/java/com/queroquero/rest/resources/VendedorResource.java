package com.queroquero.rest.resources;

import com.google.gson.Gson;
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
import jakarta.ws.rs.core.Response.Status;

@Path("vendedor")
public class VendedorResource {
	
	private static VendedorRepository repo = new VendedorRepository();
	
    @GET
    @Path("todos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
    	Gson gson = new Gson();
    	String json = gson.toJson(repo.getAll(0));
    	return Response.ok(json).build();
    }
    
    @GET
    @Path("maiorqnt")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopQuantity() {
    	Gson gson = new Gson();
    	String json = gson.toJson(repo.getAll(1));
    	return Response.ok(json).build();
    }
    
    @GET
    @Path("maiorvalor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopValue() {
    	Gson gson = new Gson();
    	String json = gson.toJson(repo.getAll(2));
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
    public Response delete(String jsonText) {
    	Gson gson = new Gson();
    	Vendedor v = gson.fromJson(jsonText, Vendedor.class);
    	repo.delete(v.getMatricula());
    	return Response.ok().build();
    }
    
    @PUT
    public Response replace(String jsonText) {
    	Gson gson = new Gson();
    	Vendedor v = gson.fromJson(jsonText, Vendedor.class);
    	boolean ok = repo.update(v);
    	if(ok)
    		return Response.accepted(gson.toJson(v)).build();
    	return Response.status(Status.BAD_REQUEST).build();
    }
}
