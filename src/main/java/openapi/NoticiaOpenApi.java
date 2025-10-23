package openapi;
import com.example.apiredis.model.Noticia;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Notícias", description = "Endpoints para gerenciamento de notícias")
@RequestMapping("/noticias") // A anotação de mapping também pode ficar aqui
public interface NoticiaOpenApi {

    @Operation(summary = "Salva uma nova notícia", description = "Cria e armazena uma nova notícia no Redis.")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Notícia criada com sucesso") })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Noticia saveNoticia(@RequestBody Noticia noticia);

    @Operation(summary = "Busca uma notícia por link", description = "Retorna uma notícia específica com base no seu link único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notícia encontrada"),
            @ApiResponse(responseCode = "404", description = "Notícia não encontrada para o link fornecido")
    })
    @GetMapping
    ResponseEntity<Noticia> getNoticiaByLink(
            @Parameter(description = "Link da notícia a ser buscada", required = true, example = "http://g1.globo.com/minha-noticia")
            @RequestParam String link);

    @Operation(summary = "Lista todas as notícias salvas", description = "Retorna uma lista de todas as notícias armazenadas no Redis.")
    @GetMapping("/listar")
    List<Noticia> listAllNoticias();

    @Operation(summary = "Verifica se uma notícia existe", description = "Checa se uma notícia com o link especificado já está salva.")
    @GetMapping("/existe")
    ResponseEntity<Map<String, Boolean>> checkExistence(
            @Parameter(description = "Link da notícia a ser verificada", required = true)
            @RequestParam String link);

    @Operation(summary = "Remove uma notícia pelo link", description = "Apaga uma notícia específica do Redis com base no seu link.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Notícia removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Notícia não encontrada para o link fornecido")
    })
    @DeleteMapping
    ResponseEntity<Void> deleteNoticiaByLink(
            @Parameter(description = "Link da notícia a ser removida", required = true)
            @RequestParam String link);

    @Operation(summary = "Apaga todas as notícias salvas", description = "Remove todas as notícias do Redis. Use com cuidado.")
    @DeleteMapping("/todas")
    ResponseEntity<Map<String, String>> deleteAllNoticias();
}
