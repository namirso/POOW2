CREATE TABLE reviews (
                         id serial NOT NULL PRIMARY KEY,
                         titulo text NOT NULL,
                         descricao text NOT NULL,
                         nota integer NOT NULL,

                         idobra integer NOT NULL,
                         CONSTRAINT fk_obra
                             FOREIGN KEY(idobra)
                                 REFERENCES obras(id)
                                 ON DELETE CASCADE,

                         idusuario integer NOT NULL,
                         CONSTRAINT fk_usuario
                             FOREIGN KEY(idusuario)
                                 REFERENCES usuarios(id)
                                 ON DELETE CASCADE
);