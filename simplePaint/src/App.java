import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class App extends JFrame {
    private ToolBar toolBar;
    private JLabel label;
    private Panel panel;
    private Listener l;
    private Color rightColor;
    private Color leftColor;
    private int drawingTool;
    private String nameOfFile;

    // CONSTRUCTOR

    public App() throws IOException {
        // Initialize
        initEverything();
        // Add components
        add(panel);
        add(toolBar);
        add(label);
        getContentPane().add(panel.getScrollPane());
		//repaint
        repaint();
    }

    // INITIALIZE

    private void initEverything() throws IOException{
        l = new Listener();
        initFrame();
        initLabel();
        initPanel();
        initToolbar();
        rightColor = Color.white;
        leftColor = Color.black;
        drawingTool = 2;
        nameOfFile = "test.jpg";
    }
    private void initFrame(){
        //size, title and layout
        setSize(750, 700);
        setTitle("Simple Paint! - Photo Editor");
        setLayout(null);
        //user cannot resize the frame
        setResizable(false);
        //close the window by click the red button in the upper right corner
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set the location to centre of the screen
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void initPanel() throws IOException{
        //create a new panel
        panel = new Panel(getWidth(),getHeight());
        //add listeners
        panel.addMouseListener(l);
        panel.addMouseMotionListener(l);
    }
    private void initLabel(){
        //create a new label to shows a mouse position
        label = new JLabel("X: " + 0 + "  Y: " + 0);
        label.setBounds(10, 600, 100, 100);
        label.setBackground(Color.red);
        label.setVisible(true);
    }
    private void initToolbar() throws IOException{
        toolBar = new ToolBar(this);
    }

    // GETTERS

    public Panel getPanel(){
        return panel;
    }
    public String getNameOfFile(){
        return nameOfFile;
    }

    // SETTERS

    public void setRightColor(Color c){
        rightColor = c;
    }
    public void setLeftColor(Color c){
        leftColor = c;
    }
    public void setDrawingTool(int dt){
        drawingTool = dt;
    }
    public void setNameOfFile(String name){
        nameOfFile = name;
    }


    // LISTENERS

   private class Listener implements MouseListener, MouseMotionListener {

        private boolean draw(MouseEvent e, BufferedImage img){
            //default the mouse button is left, but if was clicked the right mouse button
            //program will choose rightColor
            Color color = leftColor;
            if(SwingUtilities.isRightMouseButton(e))
                color = rightColor;
            //counting mouse position
            int moveX = panel.getMoveX();
            int moveY = panel.getMoveY();
            int x = (int)((double)(e.getX() - moveX)*8/panel.getZoom());
            int y = (int)((double)(e.getY() - moveY)*8/panel.getZoom());
            //border of image must be shifted to the left because
            //it protrudes beyond the image
            int borderX = panel.getImg().getWidth();
            int borderY = panel.getImg().getHeight();
            //check if position is not beyond the image's border
            if(x < borderX && y < borderY) {
                switch (drawingTool) {
                    case 1:
                        for (int i = 0; i < 4; i++) {
                            //check if the position in the iteration is not beyond the image's border
                            if(x+i < borderX) {
                                img.setRGB(x + i, y, color.getRGB());
                                //check if the position in the iteration is not beyond the image's border
                                if(y+i < borderY)
                                    img.setRGB(x + i, y + i, color.getRGB());
                            }
                            //check if the position in the iteration is not beyond the image's border
                            if (y+i < borderY)
                                img.setRGB(x, y + i, color.getRGB());
                        }
                        break;
                    default:
                        for (int i = 0; i < drawingTool; i++) {
                            for (int j = 0; j < drawingTool; j++) {
                                if(x+i < borderX && y+j < borderY)
                                    img.setRGB(x + i, y + j, color.getRGB());
                            }
                        }
                }
                return true;
            }
            return false;
        }
        private void action(MouseEvent e){
            //draw on the img
            //change panel's img
            if(draw(e, panel.getImg()))
                repaint();
        }

       @Override
       public void mouseClicked(MouseEvent e) {
           action(e);
       }

       @Override
       public void mousePressed(MouseEvent e) {
           action(e);
       }

       @Override
       public void mouseReleased(MouseEvent e) {
       }

       @Override
       public void mouseEntered(MouseEvent e) {
       }

       @Override
       public void mouseExited(MouseEvent e) {
       }

       @Override
       public void mouseDragged(MouseEvent e) {
           action(e);
       }

       @Override
       public void mouseMoved(MouseEvent e) {
           label.setText("X: " + e.getX() + "  Y: " + e.getY());
       }
   }
}
