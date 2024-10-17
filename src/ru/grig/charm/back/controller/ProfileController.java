package ru.grig.charm.back.controller;

import ru.grig.charm.back.model.Profile;
import ru.grig.charm.back.service.ProfileService;

import java.util.Optional;

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

        if (params.length < 4) return "bad req: need 4 params to save";


        Profile profile = new Profile();
        profile.setEmail(params[0]);
        profile.setName(params[1]);
        profile.setSurname(params[2]);
        profile.setAbout(params[3]);


        return service.save(profile).toString();
    }

    public String findById(String request){
        String[] strings = request.split(",");
        if (strings.length !=1) return "Bad req: need one number param";

        long id;
        try {
            id = Long.parseLong(strings[0]);
        }catch (NumberFormatException e){
            return "Bad req: cant parse str[" + strings[0] + "] to Long";
        }

        Optional<Profile> maybeProfile = service.findById(id);

        if (maybeProfile.isEmpty()) return "Not found on server";

        return maybeProfile.get().toString();
    }

    public String findAll(){return service.findAll().toString();}

    public String update(String request){
        String[] strings = request.split(",");
        if (strings.length != 5) return "Bad req: need 5 params to upd";

        long id;
        try{
            id = Long.parseLong(strings[0]);
        } catch (NumberFormatException e) {
            return "Bad req: cant parse Str [" + strings[0] + "to Long";
        }
        Profile profile = new Profile();
        profile.setId(id);
        profile.setEmail(strings[1]);
        profile.setName(strings[2]);
        profile.setSurname(strings[3]);
        profile.setAbout(strings[4]);

        service.update(profile);
        return "updated";
    }

    public String delete (String request) {
        String[] strings = request.split(",");
        if (strings.length != 1) return "Bad req: need one nu,ber param";

        long id;
        try {
            id = Long.parseLong(strings[0]);
        } catch (NumberFormatException e) {
            return "Bad req: cant parse str[" + strings[0] + "] to long";
        }

        boolean result = service.delete(id);
        if (!result) return "Not found";
        return "Deleted";
    }



}
