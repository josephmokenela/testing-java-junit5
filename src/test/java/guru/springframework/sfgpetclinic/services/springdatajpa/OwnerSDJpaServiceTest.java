package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerSDJpaServiceTest {

    private OwnerSDJpaService ownerSDJpaService;

    @BeforeEach
    public void setup() {
        ownerSDJpaService = new OwnerSDJpaService(null, null, null);
    }

    @Test
    void findByLastName() {
        //Owner owner = ownerSDJpaService.findByLastName("Mokenela");

        //assertThat()
    }

    @Test
    void findAllByLastNameLike() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }
}