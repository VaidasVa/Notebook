package pro.vaidas.authserver.service;

import pro.vaidas.authserver.model.User;

import java.util.Optional;

public interface UserDetailsRetrievalService {

    User getUserByEmail(String email);

}
