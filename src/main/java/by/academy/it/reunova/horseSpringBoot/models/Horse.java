package by.academy.it.reunova.horseSpringBoot.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;


@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "horse")
@Entity
public class Horse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "age")
    private Integer age;

    @Column(name = "price")
    private Integer price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horse horse = (Horse) o;
        return Objects.equals(id, horse.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
