package dev.team.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransportResponse {
    private Vector2D anomalyAcceleration; // Ускорение от аномалий


    private Integer attackCooldownMs;      // Время восстановления атаки в миллисекундах


    private Integer deathCount;            // Количество раз, когда ковёр был уничтожен


    private Integer health;                // Очки здоровья ковра

    private String id;                     // Уникальный идентификатор ковра

    private Vector2D selfAcceleration;     // Ускорение, заданное игроком

    private Integer shieldCooldownMs;      // Время до восстановления щита в миллисекундах


    private Integer shieldLeftMs;          // Время до окончания действия щита в миллисекундах

    private String status;                 // Текущее состояние (alive или dead)

    private Vector2D velocity;             // Текущая скорость по X и Y

    private Integer x;                     // Координата X (обязательный параметр)

    private Integer y;                     // Координата Y (обязательный параметр)

}