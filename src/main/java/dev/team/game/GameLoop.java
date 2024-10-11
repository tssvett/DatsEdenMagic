package dev.team.game;

import dev.team.dto.MoveResponse;
import dev.team.integration.GameAPI;
import dev.team.visualization.GameRenderer;

import javax.swing.*;

public class GameLoop {
    private final GameRenderer renderer;
    private final GameAPI gameAPI;

    public GameLoop(GameRenderer renderer, GameAPI gameAPI) {
        this.renderer = renderer;
        this.gameAPI = gameAPI;


        // Таймер для отправки запросов каждые 0.3 секунды
        Timer timer = new Timer(300, e -> runLoop());
        timer.start();
    }
    

    private void runLoop() {
        MoveResponse moveResponse = gameAPI.sendMoveRequest();
        //какие обекты рисовать рисовать
        //logic

        renderer.draw(); // Перерисовка панели после обновления данных
    }
    
}
