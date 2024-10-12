package ru.grig.charm.back.controller;

import ru.grig.charm.back.model.Profile;
import ru.grig.charm.back.service.ProfileService;

public class ProfileController {

    private final ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    public void work(String request){ // убрать void и return
        return ;
    }

    public String save(String save) {
        String[] params = save.split(",");

        if (params.length < 4) return "bad req";


        Profile profile = new Profile();
        profile.setEmail(params[0]);
        profile.setName(params[1]);
        profile.setSurname(params[2]);
        profile.setAbout(params[3]);


        return service.save(profile).toString();
    }




}
