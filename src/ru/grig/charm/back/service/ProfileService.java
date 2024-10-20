package ru.grig.charm.back.service;

import ru.grig.charm.back.dao.ProfileDao;
import ru.grig.charm.back.model.Profile;

import java.util.List;
import java.util.Optional;

public class ProfileService {

    private final ProfileDao dao;

    public ProfileService(ProfileDao dao) {
        this.dao = dao;
    }

    public Profile save(Profile profile){
        return dao.save(profile);
    }


    public Optional<Profile> findById(Long id){
        if(id == null)return Optional.empty();
        return dao.findById(id);
    }

    public List<Profile> findAll() {
        return dao.findAll();
    }

    public void update(Profile profile) {
        dao.update(profile);
    }

    public boolean delete(Long id) {
        if (id == null) return false;
        return dao.delete(id);
    }
}
