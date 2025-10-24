package br.csi.sistema_review.service;

import br.csi.sistema_review.model.obra.Obra;
import br.csi.sistema_review.model.obra.ObraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObraService {

    private final ObraRepository repository;

    public ObraService(ObraRepository repository) {
        this.repository = repository;
    }

    public void salvar(Obra obra) {
        this.repository.save(obra);
    }

    public List<Obra> listar() {
        return this.repository.findAll();
    }

    public Obra getObra(Long id) {
        return this.repository.findById(id).get();
    }

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

    public void atualizar(Obra obra) {
        Obra o = this.repository.getReferenceById(obra.getId());
        o.setNome(obra.getNome());
        o.setDirecao(obra.getDirecao());
        o.setUrl_imagem(obra.getUrl_imagem());
        o.setTipo(obra.getTipo()); // Atualiza a relação
        this.repository.save(o);
    }

    // Métodos expostos do repositório
    public List<Obra> getObrasPorNome(String nome) {
        return this.repository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Obra> getObrasPorTipo(Long idtipo) {
        return this.repository.findByTipoId(idtipo);
    }

    public List<Obra> getObrasPorDirecao(String direcao) {
        return this.repository.findByDirecaoContainingIgnoreCase(direcao);
    }
}