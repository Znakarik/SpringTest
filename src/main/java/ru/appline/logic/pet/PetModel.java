package ru.appline.logic.pet;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PetModel implements Serializable {
    private static final PetModel instance = new PetModel();

    private final Map<Integer, Pet> model;

    private PetModel() {
        model = new HashMap<Integer, Pet>();
    }

    public static PetModel getInstance() {
        return instance;
    }

    public void add(Pet pet, int id) {
        model.put(id, pet);
    }

    public Pet findById(int id) {
        return model.get(id);
    }

    public Map<Integer, Pet> findAll() {
        return model;
    }

    public void deleteById(Integer id) {
        model.remove(id);
    }

    public void updateById(int id, String newName, String newType, int newAge) {
        model.remove(id);
        model.put(id, new Pet(newName, newType, newAge));
    }
}
