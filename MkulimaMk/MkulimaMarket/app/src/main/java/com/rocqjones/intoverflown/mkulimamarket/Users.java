package com.rocqjones.intoverflown.mkulimamarket;

public class Users {
    public String id,name;

    public Users() {
    }

    public Users(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
