import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Panel extends JPanel {
    private JScrollPane scrollPane;
    private BufferedImage img;
    private int zoom;
    private int moveX;
    private int moveY;

    // CONSTRUCTOR

    Panel(int appWidth, int appHeight) throws IOException {
        zoom = 8;
        moveX = moveY = 0;
        initPanel(appWidth,appHeight);
        initImage();
        initScrollPane();
    }

    // INITIALIZE

    private void initScrollPane(){
        scrollPane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }
    private void initImage() throws IOException{
        img = ImageIO.read(new File("test.jpg"));
    }
    private void initPanel(int appWidth, int appHeight){
        //set dimensions and background
        setBounds(0, 60 ,appWidth, appHeight-120);
        setBackground(Color.gray);
        setVisible(true);
    }

    // GETTERS

    JScrollPane getScrollPane(){
        return scrollPane;
    }
    BufferedImage getImg(){
        return img;
    }
    int getZoom() {return zoom;}
    int getMoveX(){return moveX;}
    int getMoveY(){return moveY;}

    // SETTERS

    void setImg(BufferedImage img){
        this.img = img;
    }
    void setZoom(int z){
        zoom = z;
    }
    void setMoveX(int m){
        moveX = m;
    }
    void setMoveY(int m){
        moveY = m;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, moveX, moveY,(img.getWidth()/8)*zoom,(img.getHeight()/8)*zoom,this);
    }
}