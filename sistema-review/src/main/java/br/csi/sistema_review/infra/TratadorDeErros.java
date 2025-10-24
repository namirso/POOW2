@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErroDadosInvalidos(MethodArgumentNotValidException ex){
        List<FieldError> errors = ex.getFieldErrors();
        List<DadosErroValidacao> dados = new ArrayList<>();

        for(FieldError fe : errors){
            dados.add(new DadosErroValidacao(fe.getField, fe.getDefaultMessage()));
        }
        return ResponseEntity.badRequest().body(dados);
    }

    private record DadosErroValidacao(String campo, String mensagem){
        
    }
}