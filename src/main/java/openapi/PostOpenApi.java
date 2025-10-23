package openapi;
import com.example.apiredis.model.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Posts", description = "Endpoints para gerenciamento de posts")
@RequestMapping("/posts")
public interface PostOpenApi {

    @Operation(summary = "Salva um novo post")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Post criado com sucesso") })
    @PostMapping("/salvar") // <-- Path ATUALIZADO
    @ResponseStatus(HttpStatus.CREATED)
    Post savePost(@RequestBody Post post);

    // --- O MÉTODO getPostByIds FOI REMOVIDO DAQUI ---

    // --- O MÉTODO listAllPosts FOI REMOVIDO DAQUI ---

    @Operation(summary = "Verifica se um post existe")
    @GetMapping("/existe")
    ResponseEntity<Map<String, Boolean>> checkExistence(
            @Parameter(description = "ID do usuário", required = true) @RequestParam String userId,
            @Parameter(description = "ID do post", required = true) @RequestParam String postId);

    @Operation(summary = "Remove um post pela sua chave composta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Post removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Post não encontrado")
    })
    @DeleteMapping("/excluir") // <-- Path ATUALIZADO
    ResponseEntity<Void> deletePostByIds(
            @Parameter(description = "ID do usuário", required = true) @RequestParam String userId,
            @Parameter(description = "ID do post", required = true) @RequestParam String postId);

    @Operation(summary = "Apaga todos os posts salvos")
    @DeleteMapping("/todos")
    ResponseEntity<Map<String, String>> deleteAllPosts();
}