package guru.springframework.petclinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visist extends BaseEntity{

    private LocalDate date;
    private String description;
    private Pet pet;
}
