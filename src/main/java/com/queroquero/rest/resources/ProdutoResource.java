package com.queroquero.rest.resources;

import com.google.gson.Gson;
import com.queroquero.rest.model.Produto;
import com.queroquero.rest.repository.ProdutoRepository;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("produto")
public class ProdutoResource {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
    	return Response.ok(new String("Produtos está funcionando")).build();
    }
	
	@POST
	public Response add(String jsonText) {
		Produto produto;
		Gson gson = new Gson();
		produto = gson.fromJson(jsonText, Produto.class);
		
		ProdutoRepository pr = new ProdutoRepository();
		boolean ok = pr.add(produto);
		
		if(ok)
			return Response.accepted().build();
		return Response.status(Status.BAD_REQUEST).build();
		
		
	}
	
	@PUT
	public Response replace(String jsonText) {
		Produto produto;
		Gson gson = new Gson();
		produto = gson.fromJson(jsonText, Produto.class);
		
		ProdutoRepository pr = new ProdutoRepository();
		boolean ok = pr.update(produto);
		
		if(ok)
			return Response.accepted().build();
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	@DELETE
	public Response delete(String jsonText) {
		Produto produto;
		Gson gson = new Gson();
		produto = gson.fromJson(jsonText, Produto.class);
		
		ProdutoRepository pr = new ProdutoRepository();
		boolean ok = pr.delete(produto.getId());
		
		if(ok)
			return Response.accepted().build();
		return Response.status(Status.BAD_REQUEST).build();
	}
}
