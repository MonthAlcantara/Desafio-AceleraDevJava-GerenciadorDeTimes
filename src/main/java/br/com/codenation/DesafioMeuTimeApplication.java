package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;


public class DesafioMeuTimeApplication implements MeuTimeInterface {
    List<Time> listaTimes = new ArrayList<Time>();

    @Desafio("incluirTime")
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) throws IdentificadorUtilizadoException {
        if (existeTime(id)) {
            throw new IdentificadorUtilizadoException();
        }
        listaTimes.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
    }

    @Desafio("incluirJogador")
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario)
    throws IllegalArgumentException, TimeNaoEncontradoException, IdentificadorUtilizadoException{
        if (id < 0 || idTime < 0 || nivelHabilidade < 0 || nivelHabilidade > 100) {
            throw new IllegalArgumentException();
        }
        if (!existeTime(idTime)) {
            throw new TimeNaoEncontradoException();
        }
        if (existeJogador(id)) {
            throw new IdentificadorUtilizadoException();
        }

        for (Time time : listaTimes) {
            if (time.getId() == idTime) {
                time.jogadores.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
            }
        }
    }

    @Desafio("definirCapitao")
    public void definirCapitao(Long idJogador) throws JogadorNaoEncontradoException{
        if (!existeJogador(idJogador)) {
            throw new JogadorNaoEncontradoException();
        }
        for (Time time : listaTimes) {
            for (Jogador jogador : time.jogadores) {
                if (jogador.getId() == idJogador) {
                    jogador.setCapitao(true);
                    time.setIdCapitao(jogador.getId());
                } else {
                    jogador.setCapitao(false);
                }
            }
        }
    }

    @Desafio("buscarCapitaoDoTime")
    public Long buscarCapitaoDoTime(Long idTime) throws TimeNaoEncontradoException, CapitaoNaoInformadoException{
        if (!existeTime(idTime)) {
            throw new TimeNaoEncontradoException();
        }
        Long idCapitao = 0L;

        for (Time time : listaTimes) {
            if (time.getId() == idTime) {
                if (time.getIdCapitao() != 0) {
                    idCapitao = time.getIdCapitao();
                } else {
                    throw new CapitaoNaoInformadoException();
                }
            }
        }
        return idCapitao;
    }

    @Desafio("buscarNomeJogador")
    public String buscarNomeJogador(Long idJogador) throws JogadorNaoEncontradoException{
        if (!existeJogador(idJogador)) {
            throw new JogadorNaoEncontradoException();
        }
        String nomeJogador = "";

        for (Time time : listaTimes) {
            for (Jogador jogador : time.jogadores) {
                if (jogador.getId() == idJogador) {
                    nomeJogador = jogador.getNome();
                }
            }
        }
        return nomeJogador;
    }

    @Desafio("buscarNomeTime")
    public String buscarNomeTime(Long idTime) throws TimeNaoEncontradoException{
        if (!existeTime(idTime)) {
            throw new TimeNaoEncontradoException();
        }

        String nomeTime = "";

        for (Time time : listaTimes) {
            if (time.getId() == idTime) {
                nomeTime = time.getNome();
            }
        }
        return nomeTime;
    }

    @Desafio("buscarJogadoresDoTime")
    public List<Long> buscarJogadoresDoTime(Long idTime) throws TimeNaoEncontradoException{
        List<Long> listaIdJogadores = new ArrayList<>();
        if (!existeTime(idTime)) {
            throw new TimeNaoEncontradoException();
        }

        for (Time time : listaTimes) {
            if (time.getId() == idTime) {
                for (Jogador jogador : time.jogadores) {
                    listaIdJogadores.add(jogador.getId());
                }
            }
        }
        listaIdJogadores.sort(Comparator.naturalOrder());
        return listaIdJogadores;
    }

    @Desafio("buscarMelhorJogadorDoTime")
    public Long buscarMelhorJogadorDoTime(Long idTime) throws TimeNaoEncontradoException{
        if (!existeTime(idTime)) {
            throw new TimeNaoEncontradoException();
        }
        Long idMelhorJogador = 0L;

        for (Time time : listaTimes) {
            if (time.getId() == idTime) {
                time.jogadores.sort((Jogador j1, Jogador j2) -> j2.getNivelHabilidade().compareTo(j1.getNivelHabilidade()));
                idMelhorJogador = time.jogadores.get(0).getId();
            }
        }
        return idMelhorJogador;
    }

    @Desafio("buscarJogadorMaisVelho")
    public Long buscarJogadorMaisVelho(Long idTime) throws TimeNaoEncontradoException{
        if (!existeTime(idTime)) {
            throw new TimeNaoEncontradoException();
        }
        Long idJogadorMaisVelho = 0L;

        for (Time time : listaTimes) {
            if (time.getId() == idTime) {
                time.jogadores.sort((Jogador j1, Jogador j2) -> j2.getIdade().compareTo(j1.getIdade()));
                idJogadorMaisVelho = time.jogadores.get(0).getId();
            }
        }
        return idJogadorMaisVelho;
    }

    @Desafio("buscarTimes")
    public List<Long> buscarTimes() {
        List<Long> idTimes = new ArrayList<>();
        for (Time time : listaTimes) {
            idTimes.add(time.getId());
        }
        idTimes.sort(Comparator.naturalOrder());
        return idTimes;
    }

    @Desafio("buscarJogadorMaiorSalario")
    public Long buscarJogadorMaiorSalario(Long idTime) throws TimeNaoEncontradoException{
        if (!existeTime(idTime)) {
            throw new TimeNaoEncontradoException();
        }
        Long idJogadorMaiorSalario = 0L;

        for (Time time : listaTimes) {
            if (time.getId() == idTime) {
                time.jogadores.sort((Jogador j1, Jogador j2) -> j2.getSalario().compareTo(j1.getSalario()));
                idJogadorMaiorSalario = time.jogadores.get(0).getId();
            }
        }
        return idJogadorMaiorSalario;
    }

    @Desafio("buscarSalarioDoJogador")
    public BigDecimal buscarSalarioDoJogador(Long idJogador) throws JogadorNaoEncontradoException{
        if (!existeJogador(idJogador)) {
            throw new JogadorNaoEncontradoException();
        }
        BigDecimal salarioJogador = new BigDecimal("0");

        for (Time time : listaTimes) {
            for (Jogador jogador : time.jogadores) {
                if (jogador.getId() == idJogador) {
                    salarioJogador = jogador.getSalario();
                }
            }
        }
        return salarioJogador;
    }

    @Desafio("buscarTopJogadores")
    public List<Long> buscarTopJogadores(Integer top) throws IllegalArgumentException{
        List<Jogador> listaJogadores = new ArrayList<>();
        List<Long> idJogadores = new ArrayList<>();

        if (!listaTimes.isEmpty()) {
            for (Time time : listaTimes) {
                for (Jogador jogador : time.jogadores) {
                    if (!time.jogadores.isEmpty()) {
                        listaJogadores.add(jogador);
                    }
                }
            }
            if (listaJogadores.size() < top) {
                throw new IllegalArgumentException();
            }
            listaJogadores.sort((j1, j2) -> {
                if (j1.getNivelHabilidade().equals(j2.getNivelHabilidade())) {
                    return (int) (j1.getId() - j2.getId());
                } else {
                    return j2.getNivelHabilidade().compareTo(j1.getNivelHabilidade());
                }
            });
            for (int i = 0; i < top; i++) {
                idJogadores.add(listaJogadores.get(i).getId());
            }
        }
        if (listaJogadores.isEmpty()) {
            return new ArrayList<Long>();
        } else {
            return idJogadores;
        }
    }

    @Desafio("buscarCorCamisaTimeDeFora")
    public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) throws TimeNaoEncontradoException{
        if (!existeTime(timeDaCasa) || !existeTime(timeDeFora)) {
            throw new TimeNaoEncontradoException();
        }

        Time timeCasa = new Time();
        Time timeFora = new Time();

        for (Time time : listaTimes) {
            if (time.getId() == timeDaCasa) {
                timeCasa = time;
            } else if (time.getId() == timeDeFora) {
                timeFora = time;
            }
        }
        if (timeCasa.getCorUniformePrincipal() != timeFora.getCorUniformePrincipal()) {
            return timeFora.getCorUniformePrincipal();
        } else {
            return timeFora.getCorUniformeSecundario();
        }
    }

    private Boolean existeTime(Long id) {
        for (Time time : this.listaTimes) {
            if (time.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private Boolean existeJogador(Long id) {
        for (Time time : listaTimes) {
            if (time.existeJogador(id)) {
                return true;
            }
        }
        return false;
    }
}
