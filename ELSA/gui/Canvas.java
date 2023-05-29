package gui;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.awt.BasicStroke;

public class Canvas extends JPanel {
    public Dimension getPreferredSize() {
        return new Dimension(500,300); 
    } 
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics); 

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(0, 0, Color.BLUE, 250, 250, Color.CYAN);
        g2d.setPaint(gradient);
        GeneralPath path = new GeneralPath();
        path.moveTo(100, 100);
        path.curveTo(125, 125, 225, 75, 200, 200);
        path.curveTo(175, 225, 75, 175, 100, 100);
        g2d.fill(path);
        g2d.setColor(Color.BLACK);
        
        Font font = new Font("Arial", Font.BOLD, 70);
        g2d.setFont(font);
        g2d.drawString("ELSA", 225, 175);
        g2d.setColor(Color.BLACK);
        
        ///////////////////////
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        Color startColor = new Color(0, 255, 128, 128);
        Color endColor = new Color(0, 255, 255, 100);
        GradientPaint gradientt = new GradientPaint(0, 0, startColor, getWidth(), getHeight(), endColor);
        g2d.setPaint(gradientt);
        int x = 65;
        int y = 80;
        int width = 385;
        int height = 150;
        int arcWidth = 120;
        int arcHeight = 120;
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(new RoundRectangle2D.Double(x, y, width, height, arcWidth, arcHeight));
        g2d.fill(new RoundRectangle2D.Double(x, y, width, height, arcWidth, arcHeight));

    }
    
    private GeneralPath createPath() {
        GeneralPath path = new GeneralPath();
        int[] xPoints = {50, 100, 150, 100};
        int[] yPoints = {50, 150, 50, 100};
        path.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < xPoints.length; i++) {
        path.lineTo(xPoints[i], yPoints[i]);
        }
        path.closePath();
        return path;
  }
}
