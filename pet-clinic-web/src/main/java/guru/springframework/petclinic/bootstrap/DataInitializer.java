package guru.springframework.petclinic.bootstrap;

import guru.springframework.petclinic.model.*;
import guru.springframework.petclinic.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    @Autowired
    public DataInitializer(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                           SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if(count == 0) {
            initData();
        }
    }

    private void initData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

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

        Visit burzaVisit = new Visit();
        burzaVisit.setPet(burza);
        burzaVisit.setDate(LocalDate.now());
        burzaVisit.setDescription("Sick cat");

        visitService.save(burzaVisit);

        Vet vet1 = new Vet();
        vet1.setFirstName("Artur");
        vet1.setLastName("Kossowski");
        vet1.getSpecialties().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Michał");
        vet2.setLastName("Nadolski");
        vet2.getSpecialties().add(savedSurgery);
        vetService.save(vet2);
    }
}
