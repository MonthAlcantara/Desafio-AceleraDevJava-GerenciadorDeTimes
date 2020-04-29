package br.com.codenation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Time {
    private long id;
    private long idCapitao;
    private String nome;
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String corUniformeSecundario;
    public List<Jogador> jogadores;

    public Time(long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.corUniformeSecundario = corUniformeSecundario;
        this.jogadores = new ArrayList<Jogador>();
        this.idCapitao = 0;
    }

    public Time() {

    }

    public long getId() {
        return this.id;
    }

    public void setIdCapitao(long id) {
        this.idCapitao = id;
    }

    public long getIdCapitao() {
        return this.idCapitao;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCorUniformePrincipal() {
        return this.corUniformePrincipal;
    }

    public String getCorUniformeSecundario() {
        return this.corUniformeSecundario;
    }

    public Boolean existeJogador(long id) {
        for (Jogador jogador : this.jogadores) {
            if (jogador.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
