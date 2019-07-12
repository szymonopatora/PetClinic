package guru.springframework.petclinic.bootstrap;

import guru.springframework.petclinic.model.Owner;
import guru.springframework.petclinic.model.Pet;
import guru.springframework.petclinic.model.PetType;
import guru.springframework.petclinic.model.Vet;
import guru.springframework.petclinic.service.OwnerService;
import guru.springframework.petclinic.service.PetTypeService;
import guru.springframework.petclinic.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Autowired
    public DataInitializer(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Marcjanna");
        owner1.setLastName("Ras");
        owner1.setAddress("Maszynowa 21");
        owner1.setCity("Poznań");
        owner1.setTelephone("+48600111222");

        Pet burek = new Pet();
        burek.setPetType(savedDogPetType);
        burek.setOwner(owner1);
        burek.setBirthDay(LocalDate.now());
        burek.setName("Burek");
        owner1.getPets().add(burek);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Szymon");
        owner2.setLastName("Patora");
        owner2.setAddress("Nad Stawem 6");
        owner2.setCity("Batorowo");
        owner2.setTelephone("+48600333444");

        Pet burza = new Pet();
        burza.setPetType(savedCatPetType);
        burza.setOwner(owner2);
        burza.setBirthDay(LocalDate.now());
        burza.setName("Burza");
        owner2.getPets().add(burza);

        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setFirstName("Artur");
        vet1.setLastName("Kossowski");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Michał");
        vet2.setLastName("Nadolski");

        vetService.save(vet2);
    }
}
