package br.csi.sistema_review.service;

import br.csi.sistema_review.model.tipo.Tipo;
import br.csi.sistema_review.model.tipo.TipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoService {

    private final TipoRepository repository;

    public TipoService(TipoRepository repository) {
        this.repository = repository;
    }

    public void salvar(Tipo tipo) {
        this.repository.save(tipo);
    }

    public List<Tipo> listar() {
        return this.repository.findAll();
    }

    public Tipo getTipo(Long id) {
        // Segue o seu padrão de usar .get()
        return this.repository.findById(id).get();
    }

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

    public void atualizar(Tipo tipo) {
        Tipo t = this.repository.getReferenceById(tipo.getId());
        t.setNome(tipo.getNome());
        this.repository.save(t);
    }

    // Método exposto do repositório
    public Tipo getTipoPeloNome(String nome) {
        return this.repository.findByNome(nome);
    }
}