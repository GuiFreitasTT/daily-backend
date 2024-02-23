package com.daily.model;

import com.daily.dto.TaskRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "tasks")
@Entity(name = "tasks")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
@ToString
public class Task {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tsk_id")
    private Long id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "usr_id")
    private User user;

    public Task(TaskRequestDTO data){
        this.description = data.description();
    }

}
