package com.ryzhov_andrei.spring_security_rest_api_app.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class Event extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private File file;
}
