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


/*

    
    //GET /api/jogadores : listar todos os jogadores
     
    @GetMapping("/{jogador_id}")
    public ResponseEntity<List<pagamentoModel>> getAllPagamentosByJogadorId(@PathVariable(value = "jogador_id") Long jId){
        if (!jr.existsById(jId)) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<pagamentoModel> pagamentoM = pr.findByJogadorId(jId);
        return new ResponseEntity<>(pagamentoM, HttpStatus.OK);
        /*try {
            List<pagamentoModel> pm = new ArrayList<pagamentoModel>();

            if (nome == null){
                pr.findAll().forEach(pm::add);
            }

            if (pm.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pm, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    //POST /api/criarJogador : criar artigo
     
    @PostMapping("/criarPagamento/{jogador_id}")
    public ResponseEntity<pagamentoModel> createPagamento(@PathVariable(value = "jogador_id") Long jId, @RequestBody pagamentoModel pM)
    {
        Optional<pagamentoModel> pm = jr.findById(jId).map(jogador -> {
            pM.setJogador(jogador);
            return pr.save(pM);
        });
        /*try {
            pagamentoModel p = pr.save(new pagamentoModel(pM.getAno(), pM.getMes(), pM.getValor()));

            return new ResponseEntity<>(p, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(pm.get(), HttpStatus.OK);
    }

    
    //GET /api/artigos/:id : listar artigo dado um id
     
    @GetMapping("/pagamento/{id}")
    public ResponseEntity<pagamentoModel> getJogadorById(@PathVariable("id") long id)
    {
        Optional<pagamentoModel> pm = pr.findById(id);

        if (pm.isPresent())
            return new ResponseEntity<>(pm.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
    //PUT /api/artigos/:id : atualizar artigo dado um id
     
    @PutMapping("/jogador/{id}")
    public ResponseEntity<pagamentoModel> updatePagamento(@PathVariable("id") long id, @RequestBody pagamentoModel p)
    {
        Optional<pagamentoModel> pm = pr.findById(id);

        if (pm.isPresent())
        {
            pagamentoModel pM = pm.get();
            pM.setAno(p.getAno());
            pM.setMes(p.getMes());
            pM.setValor(pM.getValor());

            return new ResponseEntity<>(pr.save(pM), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    
    //DEL /api/artigos/:id : remover artigo dado um id
     
    @DeleteMapping("/pagamento/{id}")
    public ResponseEntity<HttpStatus> deletePagamento(@PathVariable("id") long id)
    {
        try {
            pr.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    
    //DEL /api/jogadores : remover todos os jogadores
    @DeleteMapping("/pagamentos")
    public ResponseEntity<HttpStatus> deleteAllPagamentos()
    {
        try {
            pr.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
 */