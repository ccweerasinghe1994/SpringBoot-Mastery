package chamara.springframework.petclinic.services;

import chamara.springframework.petclinic.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findBiId(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}
