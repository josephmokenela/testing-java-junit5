package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    private OwnerMapService ownerMapService;
    private PetTypeService petTypeService;
    private PetService petService;

    @BeforeEach
    public void setup() {
        petTypeService = new PetTypeMapService();
        petService = new PetMapService();
        ownerMapService = new OwnerMapService(petTypeService, petService);
    }

    @DisplayName("Verify Zero Owners")
    @Test
    public void test_owner_are_zero() {
     int ownerCount = ownerMapService.findAll().size();

     assertThat(ownerCount).isZero();
    }

    @DisplayName("Pet Types - ")
    @Nested
    class TestCreatePetTypes {

        @BeforeEach
        public void setup() {
            PetType dog = new PetType(1L, "`dog");
            PetType cat = new PetType(2L, "Cat");
            petTypeService.save(dog);
            petTypeService.save(cat);
        }

        @Test
        public void test_pest_count() {
            int petCount = petTypeService.findAll().size();
            assertThat(petCount).isNotZero().isEqualTo(2);
        }

        @DisplayName("Save Owners Tests - ")
        @Nested
        class SaveOwnersTest{

            @BeforeEach
            public void setup() {
                System.out.println("Inside the nested owner class");
            }
        }
    }

    @DisplayName("Verify Still Zero owners")
    @Test
    public void test_owners_are_still_zero() {
        int ownersCount = ownerMapService.findAll().size();

        assertThat(ownersCount).isZero();
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

    @Test
    void findByLastName() {
    }

    @Test
    void findAllByLastNameLike() {
    }
}