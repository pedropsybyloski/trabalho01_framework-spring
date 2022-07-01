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

//    /*
//     * GET /api/jogadores : listar todos os jogadores
//     */
//    @GetMapping("/jogador")
//    public ResponseEntity<List<jogadorModel>> getAllJogadores(@RequestParam(required = false) String nome){
//        try {
//            List<jogadorModel> jm = new ArrayList<jogadorModel>();
//
//            if (nome == null){
//                jr.findAll().forEach(jm::add);
//            }else{
//                jr.findByNome(nome).forEach(jm::add);
//            }
//
//            if (jm.isEmpty()){
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//    return new ResponseEntity<>(jm, HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    /*
//     * POST /api/criarJogador : criar artigo
//     */
//    @PostMapping("/criarJogador")
//    public ResponseEntity<jogadorModel> createJogador(@RequestBody jogadorModel jM)
//    {
//        try {
//            jogadorModel j = jr.save(new jogadorModel(jM.getNome(), jM.getEmail(), jM.getDataNascimento(), jM.getPm()));
//
//            return new ResponseEntity<>(j, HttpStatus.CREATED);
//
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
//
//    /*
//     * GET /api/artigos/:id : listar artigo dado um id
//     */
//    @GetMapping("/artigos/{id}")
//    public ResponseEntity<jogadorModel> getJogadorById(@PathVariable("id") long id)
//    {
//        Optional<jogadorModel> jm = jr.findById(id);
//
//        if (jm.isPresent())
//            return new ResponseEntity<>(jm.get(), HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    /*
//     * PUT /api/artigos/:id : atualizar artigo dado um id
//     */
//    @PutMapping("/jogador/{id}")
//    public ResponseEntity<jogadorModel> updateJogador(@PathVariable("id") long id, @RequestBody jogadorModel j)
//    {
//        Optional<jogadorModel> jm = jr.findById(id);
//
//        if (jm.isPresent())
//        {
//            jogadorModel jM = jm.get();
//            jM.setNome(j.getNome());
//            jM.setEmail(j.getEmail());
//            jM.setDataNascimento(j.getDataNascimento());
//
//            return new ResponseEntity<>(jr.save(jM), HttpStatus.OK);
//        }
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//    }
//
//    /*
//     * DEL /api/artigos/:id : remover artigo dado um id
//     */
//    @DeleteMapping("/jogador/{id}")
//    public ResponseEntity<HttpStatus> deleteJogador(@PathVariable("id") long id)
//    {
//        try {
//            jr.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
//
//    /*
//     * DEL /api/jogadores : remover todos os jogadores
//     */
//    @DeleteMapping("/jogadores")
//    public ResponseEntity<HttpStatus> deleteAllJogadores()
//    {
//        try {
//            jr.deleteAll();
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
