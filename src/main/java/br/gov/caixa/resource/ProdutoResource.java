package br.gov.caixa.resource;

import br.gov.caixa.dto.ProdutoDTO;
import br.gov.caixa.mapper.ProdutoMapper;
import br.gov.caixa.model.Produto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import jakarta.annotation.security.RolesAllowed;
import io.quarkus.cache.CacheResult;
import io.quarkus.cache.CacheInvalidate;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @POST
    @Transactional
    @RolesAllowed("admin")
    public Response create(@Valid ProdutoDTO dto) {
        Produto produto = ProdutoMapper.toEntity(dto);
        produto.persist();
        ProdutoDTO responseDTO = ProdutoMapper.toDTO(produto);
        return Response.status(Response.Status.CREATED).entity(responseDTO).build();
    }

    @GET
    @RolesAllowed({"user","admin"})
    public List<ProdutoDTO> listAll() {
        List<Produto> produtos = Produto.listAll();
        return ProdutoMapper.toDTOList(produtos);
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"user","admin"})
    @CacheResult(cacheName = "produtos")
    public Response getById(@PathParam("id") Long id) {
        Produto produto = Produto.findById(id);
        if (produto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        ProdutoDTO dto = ProdutoMapper.toDTO(produto);
        return Response.ok(dto).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    @Transactional
    @CacheInvalidate(cacheName = "produtos") // Invalida o cache do produto alterado
    public Response update(@PathParam("id") Long id, @Valid ProdutoDTO dto) {
        Produto produto = Produto.findById(id);
        if (produto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        produto.nome = dto.nome;
        produto.descricao = dto.descricao;
        produto.preco = dto.preco;

        ProdutoDTO responseDTO = ProdutoMapper.toDTO(produto);
        return Response.ok(responseDTO).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    @Transactional
    @CacheInvalidate(cacheName = "produtos") // Invalida o cache do produto deletado
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = Produto.deleteById(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
