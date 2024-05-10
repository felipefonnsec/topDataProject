package com.desafio.topdata.services;

import com.desafio.topdata.models.User;
import com.desafio.topdata.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //listando os usuarios
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    //criacao de usuario
    public User createUser(User user){
        if (userRepository.findByEmail(user.getEmail())){
            throw new RuntimeException("Email do usuario: " + user.getEmail());
        }
        return userRepository.save(user);
    }

    //buscando usuario por id
    public User getUserById(Integer id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado/cadastrado"));
    }

    //alterando o usuario
    public User updateUser(Integer id, User userDetails){
        User user =  userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado/cadastrado"));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        return userRepository.save(user);
    }

    //deletando o usuario por Id (apenas para teste)
    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

}
