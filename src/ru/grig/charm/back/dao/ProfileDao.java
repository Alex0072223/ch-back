package ru.grig.charm.back.dao;

import ru.grig.charm.back.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProfileDao {

    private final ConcurrentHashMap<Long, Profile> storage;
    private  final AtomicLong idStorage;

    public ProfileDao() {
        this.storage = new ConcurrentHashMap<>();
        this.idStorage = new AtomicLong(1L);
    }

    public Profile save(Profile profile){
        profile.setId(idStorage.getAndIncrement());
        storage.put(profile.getId(), profile);
        System.out.println(storage.values());
        return profile;
    }

    public Optional<Profile> findById(Long id){ //optional потому что в методах где можно вернуть null не возвращать null а возвращать optional, и там уже проверять существует ли объект или нет, ибо nullpointerexeption известная проблема в джаве и лучше кк избегать
        return Optional.ofNullable(storage.get(id));
    }

    public List<Profile> findAll(){return new ArrayList<>(storage.values());
    }

    public void update(Profile profile){
        Long id = profile.getId();
        if (id==null) return;
        storage.put(id, profile);
    }

    public boolean delete(Long id){
        return storage.remove(id) != null;
    }
}
