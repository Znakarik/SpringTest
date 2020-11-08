package ru.appline.controller.pet;

import org.springframework.web.bind.annotation.*;
import ru.appline.logic.pet.Pet;
import ru.appline.logic.pet.PetModel;
import ru.appline.ResponseModel;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class PetController {

    private static final PetModel petModel = PetModel.getInstance();
    private static final AtomicInteger newId = new AtomicInteger(1);

//    @PostMapping(value = "/create", consumes = "application/json")
//    public ResponseModel createPet(@RequestBody Pet pet) {
//        int id = newId.getAndIncrement();
//        ResponseModel response;
//        petModel.add(pet, id);
//        Pet createdPet = petModel.findById(id);
//        if (createdPet != null) {
//            response = new ResponseModel(0, "Животное создано");
//        } else {
//            response = new ResponseModel(1, "Ошибка, животное не создано");
//        }
//        return response;
//    }

    @GetMapping(value = "/findAll", produces = "application/json")
    public Map<Integer, Pet> findAll() {
        return petModel.findAll();
    }

    @GetMapping(value = "findOne", consumes = "application/json", produces = "application/json")
    public Pet findOneById(@RequestBody Map<String, Integer> id) {
        return petModel.findById(id.get("id"));
    }

    @DeleteMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public ResponseModel deleteById(@RequestBody Map<String, Integer> id) {
        int petId = id.get("id");
        ResponseModel response;
        Pet pet = petModel.findById(petId);
        if (pet != null) {
            petModel.deleteById(petId);
            response = new ResponseModel(0, "Животное с id " + petId + " удалено");
        } else {
            response = new ResponseModel(1, "Животное с id " + petId + " не найдено");
        }
        return response;
    }

    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public ResponseModel updateById(@RequestBody Map<String, String> rq) {
        ResponseModel response;
        int id = Integer.parseInt(rq.get("id"));
        if (petModel.findById(id) != null) {
            String name = rq.get("name");
            String type = rq.get("type");
            int age = Integer.parseInt(rq.get("age"));
            petModel.updateById(id, name, type, age);
            response = new ResponseModel(0, "Животное с id " + id + " успешно создано");
        } else {
            response = new ResponseModel(1, "Животное с id " + id + " не найдено");
        }
        return response;
    }
}
