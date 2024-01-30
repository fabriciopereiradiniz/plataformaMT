package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

//Classe MyButton: Responsável por criar um botão personalizado.
public class MyButton extends JButton {

 private static final long serialVersionUID = 1L;

 private boolean over; // Flag para indicar se o mouse está sobre o botão.
 private Color color; // Cor padrão do botão.
 private Color colorOver; // Cor quando o mouse está sobre o botão.
 private Color colorClick; // Cor quando o botão é clicado.
 private Color borderColor; // Cor da borda do botão.
 private int radius = 10; // Raio para cantos arredondados.

 // Construtor da classe MyButton.
 public MyButton() {
     // Inicializa as cores padrão.
     setColor(Color.WHITE);
     colorOver = new Color(217, 217, 217);
     colorClick = Color.decode("#3995f7");
     borderColor = new Color(30, 136, 56);
     setContentAreaFilled(false);
     setBorderPainted(false); // Garante que a borda padrão não seja exibida.

     // Adiciona ouvintes de eventos de mouse.
     addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent me) {
             setBackground(colorOver);
             over = true;
         }

         @Override
         public void mouseExited(MouseEvent me) {
             setBackground(color);
             over = false;
         }

         @Override
         public void mousePressed(MouseEvent me) {
             setBackground(colorClick);
         }

         @Override
         public void mouseReleased(MouseEvent me) {
             if (over) {
                 setBackground(colorOver);
             } else {
                 setBackground(color);
             }
         }
     });
 }

 // Getters e setters para os atributos da classe MyButton.

 public boolean isOver() {
     return over;
 }

 public void setOver(boolean over) {
     this.over = over;
 }

 public Color getColor() {
     return color;
 }

 public void setColor(Color color) {
     this.color = color;
     setBackground(color);
 }

 public Color getColorOver() {
     return colorOver;
 }

 public void setColorOver(Color colorOver) {
     this.colorOver = colorOver;
 }

 public Color getColorClick() {
     return colorClick;
 }

 public void setColorClick(Color colorClick) {
     this.colorClick = colorClick;
 }

 public Color getBorderColor() {
     return borderColor;
 }

 public void setBorderColor(Color borderColor) {
     this.borderColor = borderColor;
 }

 public int getRadius() {
     return radius;
 }

 public void setRadius(int radius) {
     this.radius = radius;
 }

 // Método para desenhar o componente do botão.
 @Override
 protected void paintComponent(Graphics grphcs) {
     Graphics2D g2 = (Graphics2D) grphcs;
     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

     // Desenha a borda do botão.
     g2.setColor(Color.decode("#ffffff"));
     g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
     g2.setColor(getBackground());

     // Desenha a borda interna do botão.
     g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);

     // Define a cor do texto para #3995f7.
     g2.setColor(Color.decode("#3995f7"));

     super.paintComponent(grphcs); // Chama o método paintComponent da superclasse.
 }
}
