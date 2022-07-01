package jogadorPagamento.ProjectJogador.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

@Entity
@Table(name="pagamento")
public class pagamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private short  ano;

    @Column
    private byte mes;

    @Column
    private float valor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jogador_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private jogadorModel jogador;

    public pagamentoModel() {
    }

    public pagamentoModel(short ano, byte mes, float valor, jogadorModel jogador) {
        this.ano = ano;
        this.mes = mes;
        this.valor = valor;
        this.jogador = jogador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getAno() {
        return ano;
    }

    public void setAno(short ano) {
        this.ano = ano;
    }

    public byte getMes() {
        return mes;
    }

    public void setMes(byte mes) {
        this.mes = mes;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public jogadorModel getJogador() {
        return jogador;
    }

    public void setJogador(jogadorModel jogador) {
        this.jogador = jogador;
    }

    
}
