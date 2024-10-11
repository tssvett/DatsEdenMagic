package dev.team.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Anomaly {
    private Double effectiveRadius; // Радиус действия аномалии
    private String id;               // Уникальный идентификатор аномалии
    private Double radius;           // Радиус аномалии
    private Double strength;         // Сила аномалии (может быть отрицательной)
    private Vector2D velocity;       // Вектор скорости аномалии
    private Integer x;               // Координата X (обязательный параметр)
    private Integer y;               // Координата Y (обязательный параметр)
}