import org.w3c.dom.css.Rect;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.DirectoryIteratorException;
import java.util.ArrayList;

public class PaintField extends JPanel implements ActionListener{
    private int delay = 200 ;
    Timer timer = new Timer(delay,this);

    private boolean startOfGame = true;
    private static int X_APPLE , Y_APPLE ;
    private static int X_SNACK = 10  , Y_SNACK = 10;
    private static int score = 1;
    private boolean stoped = false ;
    private boolean losed = false ;
    private ArrayList<SnakeCel> snakeCels = new ArrayList<>() ;
    public PaintField(){
        timer.start();
        snakeCels.add(0 , new SnakeCel());
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        this.setShapes(g);
        if(!losed) {
            for (int i = 0; i < snakeCels.size(); i++) {
                g.setColor(Color.white);
                g.fillOval(snakeCels.get(i).X_SNACK, snakeCels.get(i).Y_SNACK, snakeCels.get(i).width, snakeCels.get(i).height);
            }
            g.setColor(Color.cyan);
            generateApple(g, startOfGame);
        }else {
            g.drawString("game over ! " , getWidth() / 2 - 20 , getHeight()/2 - 10);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(snakeCels.get(0).X_SNACK == X_APPLE && snakeCels.get(0).Y_SNACK == Y_APPLE){
            PaintApp.setScore();
            generateApple(getGraphics(),true);
            score++;
            switch (snakeCels.get(snakeCels.size() - 1 ).DIRECTION){
                case "left":   snakeCels.add(new SnakeCel(snakeCels.get( snakeCels.size() - 1 ).X_SNACK + 10 , snakeCels.get( snakeCels.size() - 1 ).Y_SNACK , "left")); ;
                    break;
                case "right": snakeCels.add(new SnakeCel(snakeCels.get( snakeCels.size() - 1 ).X_SNACK - 10 , snakeCels.get( snakeCels.size() - 1 ).Y_SNACK , "right"))  ;
                    break;
                case "up":snakeCels.add(new SnakeCel(snakeCels.get( snakeCels.size() - 1 ).X_SNACK , snakeCels.get( snakeCels.size() - 1 ).Y_SNACK + 10, "up"))      ;
                    break;
                case "down":snakeCels.add(new SnakeCel(snakeCels.get( snakeCels.size() - 1 ).X_SNACK  , snakeCels.get( snakeCels.size() - 1 ).Y_SNACK - 10, "down"))    ;
                    break;
            }
            delay += 2 ;
        }
        for (int i = snakeCels.size() - 1  ; i >= 0 ; i-- ) {
            if(i > 0) snakeCels.get(i).setDIRECTION(snakeCels.get(i - 1).DIRECTION);
        }
        for(int i=0 ; i < snakeCels.size()  ;i++ ){
            switch (snakeCels.get(i).DIRECTION){
                case "left":
                    if( i == 0 && snakeCels.size() > 1) {
                        snakeCels.get(i).X_SNACK = snakeCels.get(i+1).X_SNACK - 10;
                        snakeCels.get(i).Y_SNACK = snakeCels.get(i+1).Y_SNACK ;
                    }
                    snakeCels.get(i).moveLeft(getWidth());
                    break;
                case "right":
                    if( i == 0 && snakeCels.size() > 1) {
                        snakeCels.get(i).X_SNACK = snakeCels.get(i+1).X_SNACK + 10;
                        snakeCels.get(i).Y_SNACK = snakeCels.get(i+1).Y_SNACK ;
                    }
                    snakeCels.get(i).moveRight(getWidth()); ;
                    break;
                case "up":
                    if( i == 0 && snakeCels.size() > 1) {
                        snakeCels.get(i).Y_SNACK = snakeCels.get(i+1).Y_SNACK - 10;
                        snakeCels.get(i).X_SNACK = snakeCels.get(i+1).X_SNACK;
                    }
                    snakeCels.get(i).moveUp(getHeight());
                    break;
                case "down":
                    if( i == 0 && snakeCels.size() > 1) {
                        snakeCels.get(i).Y_SNACK = snakeCels.get(i+1).Y_SNACK + 10;
                        snakeCels.get(i).X_SNACK = snakeCels.get(i+1).X_SNACK;
                    }
                    snakeCels.get(i).moveDown(getHeight());
                    break;
            }
        }
        if(isGameOver()) {
            losed = true ;
            timer.stop();
        }
        repaint();
    }
    private void generateApple(Graphics g ,boolean newApple){
        if(newApple){
            do {
                X_APPLE = (int) (Math.random() * (getWidth() - 10));
                Y_APPLE = (int) (Math.random() * (getHeight() - 10));
            }while (X_APPLE%10!=0 || Y_APPLE%10!=0 || X_APPLE == 0 || X_APPLE == getWidth() - 10 || Y_APPLE == 0 || Y_APPLE == getHeight() - 10 );
        }
        startOfGame = false;
        g.fillOval(X_APPLE,Y_APPLE,10,10);
    }
    public void stopGame(){
        timer.stop();
        stoped = true ;
    }
    public void resume(){
        timer.restart();
        stoped = false ;
    }
    public void newGame(){
        timer.restart();
        snakeCels = new ArrayList<>();
        snakeCels.add(0 , new SnakeCel());
        startOfGame = true;
        losed = false ;
        repaint();
    }
    public boolean isStoped(){
        return stoped;
    }
    public ArrayList<SnakeCel> getSnakeCels() {
        return snakeCels;
    }
    private boolean isGameOver() {
        if( snakeCels.size() > 2 ) {
            for (int i = 1 ; i < snakeCels.size() ; i++) {
                if((snakeCels.get(i).X_SNACK == snakeCels.get(0).X_SNACK && snakeCels.get(i).Y_SNACK == snakeCels.get(0).Y_SNACK)
                ){
                    return true ;
                }
            }
        }
        if( ( snakeCels.get(0).X_SNACK == 0 && snakeCels.get(0).DIRECTION == "left" )
                || (snakeCels.get(0).X_SNACK == getWidth() - 10 && snakeCels.get(0).DIRECTION == "right" )
                || (snakeCels.get(0).Y_SNACK == 0 && snakeCels.get(0).DIRECTION == "up" )
                || ( snakeCels.get(0).Y_SNACK == getHeight() - 10 && snakeCels.get(0).DIRECTION == "down" )) {
            return true ;
        }
        return false ;
    }
    public void setShapes(Graphics g) {
        for(int i = 0 ; i < getHeight() ; i = i + 10) {
            g.setColor(Color.red);
            g.fillRect(0,i,10,10);
            g.fillRect(getWidth() - 10 , i ,10,10);
        }
        for(int i = 0 ; i < getWidth(); i = i + 10) {
            g.setColor(Color.red);
            g.fillRect(i,0,10,10);
            g.fillRect(i , getHeight() - 10 ,10,10);
        }
    }
}
