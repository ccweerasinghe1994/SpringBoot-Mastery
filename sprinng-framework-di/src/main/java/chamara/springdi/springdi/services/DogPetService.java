package chamara.springdi.springdi.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"dog", "default"})
@Service
public class DogPetService implements PetService {
    @Override
    public String GetPetType() {
        return "Dogs are the best!";
    }
}
