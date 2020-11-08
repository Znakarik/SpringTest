package ru.appline.controller.compass;

import org.springframework.web.bind.annotation.*;
import ru.appline.logic.compass.Compass;
import ru.appline.logic.compass.CompassModel;
import ru.appline.logic.compass.Side;
import ru.appline.ResponseModel;

import java.util.Map;

@RestController
public class CompassController {

    /**
     * Устанавливаем стороны компаса
     *
     * @param compass
     */
    @PostMapping(value = "/create", consumes = "application/json")
    protected ResponseModel createCompass(@RequestBody Compass compass) {
        Compass compass1 = CompassModel.getInstance().getCompass();
        // TODO: 11/6/20 ЧОТ МНОГО
        compass1.setNorth(compass.getNorth());
        compass1.setEast(compass.getEast());
        compass1.setSouth(compass.getSouth());
        compass1.setWest(compass.getWest());
        compass1.setNorthEast(compass.getNorthEast());
        compass1.setEastSouth(compass.getEastSouth());
        compass1.setSouthWest(compass.getSouthWest());
        compass1.setWestNorth(compass.getWestNorth());
        return new ResponseModel(0, "Данные сторон успешно выставлены");
    }

    @GetMapping(value = "/get", consumes = "application/json", produces = "application/json")
    public Side getPrt(@RequestBody Map<String, String> part) throws IllegalAccessException {
        int degree = Integer.parseInt(part.get("Degree"));
        return CompassModel.getInstance().getSideByDegree(degree);
    }
}
