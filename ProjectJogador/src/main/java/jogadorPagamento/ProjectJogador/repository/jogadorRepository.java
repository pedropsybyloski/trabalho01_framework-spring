package jogadorPagamento.ProjectJogador.repository;

import jogadorPagamento.ProjectJogador.model.jogadorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface jogadorRepository extends JpaRepository<jogadorModel, Integer> {
    //List<jogadorModel> findByNome(String nome);

    /*List<jogadorModel> findByEmailContaining(String email);*/
}
