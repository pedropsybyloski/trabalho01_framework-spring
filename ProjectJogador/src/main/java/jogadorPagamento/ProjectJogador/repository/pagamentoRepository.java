package jogadorPagamento.ProjectJogador.repository;

import jogadorPagamento.ProjectJogador.model.jogadorModel;
import jogadorPagamento.ProjectJogador.model.pagamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface pagamentoRepository extends JpaRepository<pagamentoModel, Integer> {

    //List<jogadorModel> findByNome(String nome);
    //List<pagamentoModel> findByJogadorId(Long jId);
}
