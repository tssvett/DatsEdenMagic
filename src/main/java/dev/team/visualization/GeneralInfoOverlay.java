package dev.team.visualization;

import dev.team.dto.MoveResponse;

import javax.swing.*;
import java.awt.*;

public class GeneralInfoOverlay extends JPanel {
    private String infoText = "Your Information Here"; // Default text
    private static final int RECTANGLE_HEIGHT = 40; // Fixed height for each rectangle
    private static final int RECTANGLE_PADDING = 10; // Padding around the text

    public GeneralInfoOverlay(GameRenderer gameRenderer) {
        setOpaque(false); // Make it transparent
    }

    public void updateGameInfoLabel(MoveResponse moveResponse, GameRenderer gameRenderer) {
        StringBuilder infoBuilder = new StringBuilder();
        // Append existing information
        infoBuilder.append(String.format("""
            Team: %s
            Total Money: %s
            Map size: %sx%s
            Camera Position: (%s, %s)
            """,
                moveResponse.name(),
                moveResponse.points(),
                moveResponse.mapSize().x().intValue(),
                moveResponse.mapSize().y().intValue(),
                gameRenderer.getCameraX(),
                gameRenderer.getCameraY()

        ));

        // Update infoText with the complete information
        infoText = infoBuilder.toString();

        repaint(); // Repaint to show updated information
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing for smoother text
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set font and color for the text
        g2d.setFont(new Font("Arial", Font.BOLD, 16)); // Font style and size
        g2d.setColor(Color.BLACK); // Text color

        // Split the infoText into lines
        String[] lines = infoText.split("\n");

        // Calculate maximum width needed for the rectangles
        int maxWidth = 0;
        for (String line : lines) {
            maxWidth = Math.max(maxWidth, g2d.getFontMetrics().stringWidth(line));
        }

        // Add padding to maxWidth
        maxWidth += 2 * RECTANGLE_PADDING;

        // Calculate starting position for drawing text
        int yPosition = 40; // Starting vertical position from top

        for (String line : lines) {
            yPosition = drawLeftAlignedText(g2d, line, yPosition, maxWidth);
        }
    }

    private int drawLeftAlignedText(Graphics2D g2d, String text, int yPosition, int maxWidth) {
        // Draw a fixed-size background rectangle for each line aligned to the right
        g2d.setColor(new Color(240, 128, 128, 150)); // Light coral color with transparency
        g2d.fillRoundRect(5, yPosition - RECTANGLE_PADDING / 2,
                maxWidth, RECTANGLE_HEIGHT, 15, 15); // Rounded corners

        // Draw the text aligned to the left with padding within the rectangle
        g2d.setColor(Color.BLACK); // Text color
        g2d.drawString(text, 10 + RECTANGLE_PADDING / 2, yPosition + RECTANGLE_HEIGHT / 2 + 5); // Centered vertically

        return yPosition + RECTANGLE_HEIGHT + RECTANGLE_PADDING; // Return updated yPosition based on fixed rectangle height
    }
}