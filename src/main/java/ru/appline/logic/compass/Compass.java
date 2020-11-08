package ru.appline.logic.compass;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Compass {
    @JsonProperty("North")
    String north;
    @JsonProperty("North-east")
    String northEast;
    @JsonProperty("East")
    String east;
    @JsonProperty("East-south")
    String eastSouth;
    @JsonProperty("South")
    String south;
    @JsonProperty("South-west")
    String southWest;
    @JsonProperty("West")
    String west;
    @JsonProperty("West-north")
    String westNorth;

    Compass() {
    }

    public String getEast() {
        return east;
    }

    public String getSouth() {
        return south;
    }

    public String getWest() {
        return west;
    }

    public String getNorth() {
        return north;
    }

    public String getNorthEast() {
        return northEast;
    }

    public String getEastSouth() {
        return eastSouth;
    }

    public String getSouthWest() {
        return southWest;
    }

    public String getWestNorth() {
        return westNorth;
    }

    public void setNorthEast(String northEast) {
        this.northEast = northEast;
    }

    public void setEastSouth(String eastSouth) {
        this.eastSouth = eastSouth;
    }

    public void setSouthWest(String southWest) {
        this.southWest = southWest;
    }

    public void setWestNorth(String westNorth) {
        this.westNorth = westNorth;
    }

    public void setNorth(String north) {
        this.north = north;
    }

     public void setEast(String east) {
        this.east = east;
    }

     public void setSouth(String south) {
        this.south = south;
    }

     public void setWest(String west) {
        this.west = west;
    }
}
