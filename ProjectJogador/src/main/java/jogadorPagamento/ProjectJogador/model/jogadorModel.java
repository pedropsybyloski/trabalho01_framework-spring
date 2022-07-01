package jogadorPagamento.ProjectJogador.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jogador")
public class jogadorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String dataNascimento;

    @OneToMany(mappedBy = "jogador", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<pagamentoModel> pm = new HashSet<>();

    public jogadorModel() {
    }

    public jogadorModel(String nome, String email, String dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Set<pagamentoModel> getPm() {
        return pm;
    }

    public void setPm(Set<pagamentoModel> pm) {
        this.pm = pm;
    }

}