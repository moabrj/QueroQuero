package com.queroquero.rest.resources;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.queroquero.rest.model.Produto;
import com.queroquero.rest.model.Venda;
import com.queroquero.rest.model.Vendedor;
import com.queroquero.rest.repository.VendaRepository;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("venda")
public class VendaResource {

	private static VendaRepository repo = new VendaRepository();
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
    	return Response.ok(new String("Venda está funcionando")).build();
    }
	
	@POST
	public Response add(String jsonText) {
		//treating the text json
		JsonParser jsonParser = new JsonParser();
		JsonObject mapping = (JsonObject)jsonParser.parse(jsonText);
		//getting the list of products of the sale
		JsonElement jsonProdutos = mapping.get("produtos");
		Type listType = new TypeToken<List<Integer>>() {}.getType();
		List<Integer> produtos = new Gson().fromJson(jsonProdutos, listType);
		//getting matricula of the salesman
		JsonElement jsonMatricula = mapping.get("matricula");
		int matricula = new Gson().fromJson(jsonMatricula, Integer.class);
		
		//Setting data from json in java objects
		Vendedor vendedor = new Vendedor();
		vendedor.setMatricula(matricula);
		Venda venda = new Venda();
		venda.setVendedor(vendedor);
		List<Produto> listaProdutos = new ArrayList<>();
		for (Integer p : produtos) {
			Produto prod = new Produto();
			prod.setId(p);
			listaProdutos.add(prod);
		}
		venda.setProdutos(listaProdutos);
		(new VendaRepository()).add(venda);
		
		return Response.ok().build();
	}
	
	
}
