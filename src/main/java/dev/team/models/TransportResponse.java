package dev.team.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransportResponse {
    @JsonProperty("anomalyAcceleration")
    private Vector2D anomalyAcceleration; // Ускорение от аномалий

    @JsonProperty("attackCooldownMs")
    private Integer attackCooldownMs;      // Время восстановления атаки в миллисекундах

    @JsonProperty("deathCount")
    private Integer deathCount;            // Количество раз, когда ковёр был уничтожен

    @JsonProperty("health")
    private Integer health;                // Очки здоровья ковра

    @JsonProperty("id")
    private String id;                     // Уникальный идентификатор ковра

    @JsonProperty("selfAcceleration")
    private Vector2D selfAcceleration;     // Ускорение, заданное игроком

    @JsonProperty("shieldCooldownMs")
    private Integer shieldCooldownMs;      // Время до восстановления щита в миллисекундах

    @JsonProperty("shieldLeftMs")
    private Integer shieldLeftMs;          // Время до окончания действия щита в миллисекундах

    @JsonProperty("status")
    private String status;                 // Текущее состояние (alive или dead)

    @JsonProperty("velocity")
    private Vector2D velocity;             // Текущая скорость по X и Y

    @JsonProperty("x")
    private Integer x;                     // Координата X (обязательный параметр)

    @JsonProperty("y")
    private Integer y;

}