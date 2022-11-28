package com.example.jokesapp;

public class Joke {
    private float id;
    private String type;
    private String setup;
    private String punchline;


    // Getter Methods

    public float getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSetup() {
        return setup;
    }

    public String getPunchline() {
        return punchline;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public void setPunchline(String punchline) {
        this.punchline = punchline;
    }
}