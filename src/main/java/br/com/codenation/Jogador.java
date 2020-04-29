package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;


public class Jogador {
    private Long id;
    private Long idTime;
    private String nome;
    private LocalDate dataNascimento;
    private Integer nivelHabilidade;
    private BigDecimal salario;
    private Boolean capitao;

    public Jogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
                   BigDecimal salario) {
        this.id = id;
        this.idTime = idTime;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nivelHabilidade = nivelHabilidade;
        this.salario = salario;
        this.capitao = false;
    }

    public static class Builder {
        private Long id;
        private Long idTime;
        private String nome;
        private LocalDate dataNascimento;
        private Integer nivelHabilidade;
        private BigDecimal salario;
        private Boolean capitao;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder idTime(Long idTime) {
            this.idTime = idTime;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder dataNascimento(LocalDate dataNascimento) {
            this.dataNascimento = dataNascimento;
            return this;
        }

        public Builder nivelHabilidade(Integer nivelHabilidade) {
            this.nivelHabilidade = nivelHabilidade;
            return this;
        }

        public Builder salario(BigDecimal salario) {
            this.salario = salario;
            return this;
        }

        public Jogador build() {
            return new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
        }
    }

    public long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public Integer getIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public Integer getNivelHabilidade() {
        return this.nivelHabilidade;
    }

    public BigDecimal getSalario() {
        return this.salario;
    }

    public Boolean getCapitao() {
        return this.capitao;
    }

    public void setCapitao(Boolean capitao) {
        this.capitao = capitao;
    }
}

