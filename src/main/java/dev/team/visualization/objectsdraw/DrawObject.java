package dev.team.visualization.objectsdraw;

import lombok.AllArgsConstructor;

import java.awt.*;

@AllArgsConstructor
public class DrawObject {
    private int x;
    private int y;
    private int innerRadius;
    private Color innerColor;
    private int outerRadius;
    private Color outerColor;
}
