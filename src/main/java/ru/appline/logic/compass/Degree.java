package ru.appline.logic.compass;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Degree {
    @JsonProperty("Degree")
    int degree;

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
}
