package jogadorPagamento.ProjectJogador.control;

import java.net.URI;
import jogadorPagamento.ProjectJogador.model.pagamentoModel;
import jogadorPagamento.ProjectJogador.repository.jogadorRepository;
import jogadorPagamento.ProjectJogador.repository.pagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import jogadorPagamento.ProjectJogador.model.jogadorModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/pag")
public class pagamentoController {

    @Autowired
    pagamentoRepository pr;
    @Autowired
    jogadorRepository jr;
    
    @GetMapping("/pagamentos")
    public ResponseEntity<Page<pagamentoModel>> getAll(Pageable pageable) {
        return ResponseEntity.ok(pr.findAll(pageable));
    }
    
    @GetMapping("/pagamento/{id}")
    public ResponseEntity<jogadorModel> getById(@PathVariable Integer id) {
        Optional<jogadorModel> opj = jr.findById(id);
        if (!opj.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(opj.get());
    }
    
    @PostMapping("/{id}")
    public ResponseEntity<pagamentoModel> create(@PathVariable Integer id, @RequestBody pagamentoModel pm) {
        Optional<jogadorModel> opj = jr.findById(pm.getJogador().getId());
        if (!opj.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        pm.setJogador(opj.get());

        pagamentoModel savedPagamento = pr.save(pm);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedPagamento.getId()).toUri();

        return ResponseEntity.created(location).body(savedPagamento);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<pagamentoModel> update(@RequestBody pagamentoModel pm, @PathVariable Integer id) {
        Optional<jogadorModel> optionalLibrary = jr.findById(pm.getJogador().getId());
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<pagamentoModel> opp = pr.findById(id);
        System.out.println(opp.get());
        if (!opp.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        pm.setJogador(optionalLibrary.get());
        pm.setId(opp.get().getId());
        pr.save(pm);

        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<pagamentoModel> delete(@PathVariable Integer id) {
        Optional<pagamentoModel> opp = pr.findById(id);
        if (!opp.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        pr.delete(opp.get());

        return ResponseEntity.noContent().build();
    }
    
}