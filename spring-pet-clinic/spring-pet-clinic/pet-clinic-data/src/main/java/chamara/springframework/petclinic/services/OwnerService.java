package chamara.springframework.petclinic.services;

import chamara.springframework.petclinic.model.Owner;

import java.util.Set;

public interface OwnerService {

    Owner findByLastName(String lastName);

    Owner findBiId(Long id);

    Owner save(Owner owner);

    Set<Owner> findAll();
}
