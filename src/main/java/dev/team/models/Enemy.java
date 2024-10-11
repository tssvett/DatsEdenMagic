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
public class Enemy {
    @JsonProperty("health")
    private Integer health;                // Очки здоровья врага

    @JsonProperty("killBounty")
    private Integer killBounty;            // Награда за уничтожение врага

    @JsonProperty("shieldLeftMs")
    private Integer shieldLeftMs;          // Время до окончания действия щита в миллисекундах

    @JsonProperty("status")
    private String status;                 // Текущее состояние (alive или dead)

    @JsonProperty("velocity")
    private Vector2D velocity;             // Текущая скорость по X и Y


    @JsonProperty("x")
    private Integer x;                     // Координата X (обязательный параметр)

    @JsonProperty("y")
    private Integer y;                     // Координата Y (обязательный параметр)

}