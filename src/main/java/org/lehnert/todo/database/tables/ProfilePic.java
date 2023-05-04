package org.lehnert.todo.database.tables;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Profile pics by users
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProfilePic {
    @Id
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String type;

    @Lob
    @Setter
    @Getter
    @Column(name = "imagedata", length = 1000)
    private byte[] imageData;


    @Setter
    @Getter
    @OneToOne
    @JoinColumn(name = "users_id", unique = true)
    private Users users;
}
