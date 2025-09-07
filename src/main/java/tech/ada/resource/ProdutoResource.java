package tech.ada.resource;

import jakarta.inject.Inject;
import tech.ada.repository.ProdutoRepository;
import tech.ada.dto.ProdutoDTO;
import tech.ada.model.Produto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ProdutoRepository repository;

    // 1. Criar Produto
    @POST
    @Transactional
    public Response create(@Valid ProdutoDTO produtoDTO) {
        Produto produto = new Produto(produtoDTO);
        produto.persist();
        return Response.status(Response.Status.CREATED).entity(new ProdutoDTO(produto)).build();
    }

    // 2. Listar Produtos
    @GET
    public List<ProdutoDTO> listAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<Produto> produtos = repository.findAll().page(page, size).list();
        return ProdutoDTO.from(produtos);
    }

    // 3. Buscar Produto por ID
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return repository.findByIdOptional(id)
                .map(produto -> Response.ok(new ProdutoDTO(produto)).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    // 4. Atualizar Produto
    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, @Valid ProdutoDTO dadosAtualizados) {
        return repository.findByIdOptional(id)
                .map(produto -> {
                    produto.atualiza(dadosAtualizados);
                    return Response.ok(new ProdutoDTO(produto)).build();
                })
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    // 5. Excluir Produto
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        return repository.findByIdOptional(id)
                .map(produto -> {
                    produto.delete();
                    return Response.noContent().build();
                })
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}
