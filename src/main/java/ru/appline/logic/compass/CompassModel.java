package ru.appline.logic.compass;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Field;
import java.util.Arrays;

public class CompassModel {
    private Compass compass;
    private static CompassModel instance;

    public static CompassModel getInstance() {
        if (instance == null) {
            instance = new CompassModel();
        }
        return instance;
    }

    CompassModel() {
        compass = new Compass();
    }

    public Compass getCompass() {
        return compass;
    }

    /**
     * Через рефлексию получаем значение полей типа String
     *
     * @param field поле класса {@link Compass}
     * @return значение поля типа String
     */
    public String getPartValueFromField(Field field) {
        String fieldValString = null;
        try {
            Object fieldValObj = (field.getType().getName().equals("java.lang.String")) ? field.get(this.compass) : null;
            if (fieldValObj != null) {
                fieldValString = fieldValObj.toString();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fieldValString;
    }

    /**
     * Опрделяем сторону по входящему градусу через reflection
     *
     * @param degree - входящий параметр из JSON запроса
     * @return - обьект {@link Side} с определенной стороной
     * @throws IllegalAccessException
     */
    public Side getSideByDegree(Integer degree) throws IllegalAccessException {
        Side side = null;
        // список всех полей
        for (Field field : this.getCompass().getClass().getDeclaredFields()) {
            // только значения полей типа String
            if (this.getPartValueFromField(field) != null) {
                // разбиваем значение поля на массив из двух int
                String[] fieldRange = getPartValueFromField(field).split("-");
                int start = Integer.parseInt(fieldRange[0]);
                int finish = Integer.parseInt(fieldRange[1]);
                // получаем весь диапазон в виде массива
                Integer[] diapason = getArrFromStartToFinish(start, finish);
                // содержит ли диапазон наш градус
                if (Arrays.asList(diapason).contains(degree)) {
                    side = new Side();
                    // проверяем правильные ли указаны в запросе стороны
                    // TODO: 11/6/20 по хорошему эту проверку сделать сразу при получении запроса
                    JsonProperty property = field.getAnnotation(JsonProperty.class);
                    if (property != null) {
                        Arrays.stream(Side.Sides.values()).anyMatch(sideName -> sideName.equals(property.value()));
                        side.setSide(property.value());
                    }
                    break;
                }
            }
        }
        return side;
    }

    /**
     * Получаем массив чисел от начала диапазона до конца
     *
     * @param start  - начало диапазона (число из JSON перед дефисом)
     * @param finish - конец диапазона (число из JSON после дефиса)
     * @return массив чисел диапазона
     * @example {"North": "316-45"}
     */
    // TODO: 11/6/20 переделать под 8 сегментов
    public Integer[] getArrFromStartToFinish(int start, int finish) {
        Integer[] range = new Integer[45];
        for (int i = 0; i < range.length - 1; i++) {
            range[i] = start + i;
            if (range[i] >= 361) {
                range[i] = --finish;
            }
            if (range[i] < 0) {
                break;
            }
        }
        return range;
    }

    /**
     * Чистим стороны
     */
    public void clearCompass() {
        compass.setNorth(null);
        compass.setWest(null);
        compass.setSouth(null);
        compass.setEast(null);
    }

}
