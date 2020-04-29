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

    public static class Builder {
        private long id;
        private long idCapitao;
        private String nome;
        private LocalDate dataCriacao;
        private String corUniformePrincipal;
        private String corUniformeSecundario;
        public List<Jogador> jogadores;

        public Builder() {
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder idCapitao(long idCapitao) {
            this.idCapitao = idCapitao;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder dataCriacao(LocalDate dataCriacao) {
            this.dataCriacao = dataCriacao;
            return this;
        }

        public Builder corUniformePrincipal(String corUniformePrincipal) {
            this.corUniformePrincipal = corUniformePrincipal;
            return this;
        }

        public Builder corUniformeSecundario(String corUniformeSecundario) {
            this.corUniformeSecundario = corUniformeSecundario;
            return this;
        }


        public Time build() {
            return new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
        }
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
