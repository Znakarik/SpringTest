package ru.appline.logic.compass;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Side {
    @JsonProperty("Side")
    String side;

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    enum Sides {
        NORTH("North"),
        NORTH_EAST("North-east"),
        EAST("East"),
        EAST_SOUTH("East-south"),
        SOUTH("South"),
        SOUTH_WEST("South-west"),
        WEST("West"),
        WEST_NORTH("West-north");

        private String side;

        Sides(String side) {
            this.side = side;
        }

        public String getSide() {
            return side;
        }
    }
}
