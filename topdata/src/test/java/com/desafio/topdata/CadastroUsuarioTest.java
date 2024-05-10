package com.desafio.topdata;

import com.desafio.topdata.models.User;
import com.desafio.topdata.repositories.UserRepository;
import com.desafio.topdata.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class CadastroUsuarioTest {
    private UserService userService;
    private UserRepository userRepository;

    @Test
    public void testeCadastroUsuario(){
        User user = new User(17,"Teste",0, "test@gmail.com", "123456");
        userRepository.save(user);

        // Tenta cadastrar um novo usuário com o mesmo e-mail
        User novoUsuario = new User(17, "Novo Teste",0, "teste@teste.com", "outrasenha");
        try {
            userService.createUser(novoUsuario);
        } catch (RuntimeException e) {
            // Verifica se a exceção é lançada corretamente se o e-mail já estiver em uso
            assertTrue(e.getMessage().contains("Email do usuario: teste@teste.com"));
        }

        // Tenta cadastrar um novo usuário com um e-mail diferente
        User outroUsuario = new User(18,"Outro Teste",0, "outro@teste.com", "outrasenha");
        User createdUser = userService.createUser(outroUsuario);
    }

}
