package hu.balogh.restaurantbe.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

}
