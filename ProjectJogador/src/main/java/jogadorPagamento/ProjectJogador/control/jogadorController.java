package jogadorPagamento.ProjectJogador.control;

import java.net.URI;
import jogadorPagamento.ProjectJogador.repository.jogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jogadorPagamento.ProjectJogador.model.jogadorModel;

import java.util.List;
import java.util.Optional;
import jogadorPagamento.ProjectJogador.model.pagamentoModel;
import jogadorPagamento.ProjectJogador.repository.pagamentoRepository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class jogadorController {

    @Autowired
    jogadorRepository jr;

    @Autowired
    pagamentoRepository pr;

    @GetMapping("/jogador/{id}")
    public ResponseEntity<jogadorModel> getById(@PathVariable Integer id) {
        Optional<jogadorModel> opj = jr.findById(id);
        if (!opj.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(opj.get());
    }

    @GetMapping("/jogadores")
    public List<jogadorModel> getAll() {
        return jr.findAll();
    }

    @PostMapping("/criarJogador")
    public ResponseEntity<jogadorModel> create(@RequestBody jogadorModel jm) {
        jogadorModel saveJogador = jr.save(jm);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(saveJogador.getId()).toUri();

        return ResponseEntity.created(location).body(saveJogador);
    }
    
    @PutMapping("/jogador/{id}")
    public ResponseEntity<jogadorModel> update(@PathVariable Integer id, @RequestBody jogadorModel jm){
        Optional<jogadorModel> opj = jr.findById(id);
        if (!opj.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        jm.setId(opj.get().getId());
        jr.save(jm);
        
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/jogador/{id}")
    public ResponseEntity<jogadorModel> deleteJogador(@PathVariable Integer id)
    {
        try {
            jr.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}