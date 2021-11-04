package com.queroquero.rest.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("produto")
public class ProdutoResource {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
    	return Response.ok(new String("Produtos está funcionando")).build();
    }
}
