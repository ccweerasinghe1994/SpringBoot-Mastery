package chamara.springframework.petclinic.services;

import chamara.springframework.petclinic.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findBiId(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
