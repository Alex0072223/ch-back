package ru.grig.charm.back.model;

public enum Commands {

    SAVE("save "),

    FIND_BY_ID("findBYId "),

    FIND_ALL("findAll "),

    UPDATE("update "),

    DELETE("delete ");


    private final String prefix;

    Commands(String prefix){
        this.prefix = prefix;
    }

    public String getPrefix(){return prefix;}

}
