package net.learnwithanaya.organisationservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "organisation")
public class Organisation {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(nullable = false)
    private String organisationName;
    private String organisationDescription;
    @Column(nullable = false, unique = true)
    private String organisationCode;
    //Hibernate provided annotation. Hibernate will automatically add the value in the Database, we don't have to manually add the value
    @CreationTimestamp
    private LocalDateTime createdDate;
}
