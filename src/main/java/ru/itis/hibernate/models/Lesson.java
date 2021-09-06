package ru.itis.hibernate.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// чтобы не печатать course внутри lesson, иначе stackoverflow
@ToString(exclude = "course")
public class Lesson {

    private Long id;
    private String name;

    // внешний ключ для курса
    private Course course;
}
