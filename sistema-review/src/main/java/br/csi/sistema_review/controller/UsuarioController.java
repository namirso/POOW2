@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService service;
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }
    /* http://localhost:8080/sistema-reviews/usuario/listar */
    @GetMapping("/listar")
    public List<Usuario> listar(){
        return this.service.listar();
    }

    @GetMapping("/uuid/{uuid}")
    public Usuario usuario(@PathVariable Strign uuid){
        return this.service.getUsuarioUUID(uuid);
    }

    @GetMapping("/{id}")
    public Usuario usuario(@PathVariable Long id){
        return this.service.getUsuario(id);
    }

    @PostMapping("/print-json")
    public void printJson(@RequestBody String json){
        System.out.println(json);
    }

    @PostMapping()
    @Transacional
    public void salvar(@RequestBody @Valid Usuario usuario){
        this.service.salvar(usuario);
        URI uri = uriBuilder.path("/usuario/{uuid}").buildAndExpand(usuario.getUuid()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @PutMapping
    public void atualizarUUID(@RequestBody Usuario usuario){
        this.service.atualizarUUID(usuario);
    }

    @PutMapping
    @Transacional
    public ResponseEntity atualizar(@RequestBody Usuario usuario){
        this.service.atualizar(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }


}