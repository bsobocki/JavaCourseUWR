import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ToolBar extends JToolBar {
    private JButton colorButton;
    private JButton pencilButton;
    private JButton saveButton;
    private JButton openButton;
    private JButton zoomPButton;
    private JButton zoomMButton;
    private JButton moveRightButton;
    private JButton moveLeftButton;
    private JButton moveUpButton;
    private JButton moveDownButton;
    private String format;

    public ToolBar(App app)throws IOException{
        Image img;
        format = "png";

        // PENCIL

        //CREATE THE pencilButton
        //get image pencil.png as icon
        img = ImageIO.read(getClass().getResource("pencil.png"));
        pencilButton = new JButton(new ImageIcon(img));
        pencilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createNewWindow(app,4,'p');
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // COLOR

        //CREATE THE colorButton
        //get image colors.png as icon
        img = ImageIO.read(getClass().getResource("color.png"));
        colorButton = new JButton(new ImageIcon(img));
        //add an ActionListener to button
        colorButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e))
                    app.setLeftColor(JColorChooser.showDialog(app, "Select a color",Color.red));
                else
                    app.setRightColor(JColorChooser.showDialog(app, "Select a color",Color.red));
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        // OPEN / SAVE

        //CREATE THE saveButton
        //get image save.png as icon
        img = ImageIO.read(getClass().getResource("save.png"));
        saveButton = new JButton(new ImageIcon(img));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createNewWindow(app,2,'s');
                    ImageIO.write(app.getPanel().getImg(),format,new File(app.getNameOfFile()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //CREATE THE openButton
        //get image open.png as icon
        img = ImageIO.read(getClass().getResource("open.png"));
        openButton = new JButton(new ImageIcon(img));
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //create file chooser
                JFileChooser chooser = new JFileChooser();
                //set filter of file names
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "JPG & PNG Images", "jpg", "png");
                chooser.setFileFilter(filter);
                //open dialog
                int returnVal = chooser.showOpenDialog(new JFrame());
                //user chosen
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    //FILE SOURCE
                    String name = chooser.getSelectedFile().getPath();
                    app.setNameOfFile(name);
                    try {
                        app.getPanel().setImg(ImageIO.read(new File(name)));
                        app.repaint();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        // ZOOM

        //CREATE THE zoomPButton
        //get image lupaPlus.png as icon
        img = ImageIO.read(getClass().getResource("zoomPlus.png"));
        zoomPButton = new JButton(new ImageIcon(img));
        zoomPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int zoom = app.getPanel().getZoom();
                if(zoom<16) {
                    zoom++;
                app.getPanel().setZoom(zoom);
                app.repaint();
                }
            }
        });

        //CREATE THE zoomMButton
        //get image lupaMinus.png as icon
        img = ImageIO.read(getClass().getResource("zoomMinus.png"));
        zoomMButton = new JButton(new ImageIcon(img));
        zoomMButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int zoom = app.getPanel().getZoom();
                if(zoom>0) {
                    zoom--;
                    app.getPanel().setZoom(zoom);
                    app.repaint();
                }
            }
        });

        // MOVING

        //CREATE THE moveRightButton
        moveRightButton = createArrow("rightArrow.png",app,-10,'x');

        //CREATE THE moveLeftButton
        moveLeftButton = createArrow("leftArrow.png",app,10,'x');

        //CREATE THE moveDownButton
        moveDownButton = createArrow("downArrow.png",app,-10,'y');

        //CREATE THE moveUpButton
        moveUpButton = createArrow("upArrow.png",app,10,'y');

        //TOOLBAR

        setBounds(0,0,750,60);
        add(pencilButton);
        add(colorButton);
        add(openButton);
        add(saveButton);
        add(zoomMButton);
        add(zoomPButton);
        add(moveUpButton);
        add(moveDownButton);
        add(moveLeftButton);
        add(moveRightButton);
    }

    // CREATING

    //howMuch - how much check boxes will be in toolbar
    //what - what will be in toolboxes - somethings for choosing pencil or format to save
    private void createNewWindow(App app, int howMuch, char what) throws IOException {
        //create components
        JFrame fr = new JFrame("choose Pencil!");
        JToolBar toolBar = createToolBar(fr, app,howMuch, what);
        //set bounds
        fr.setBounds(20,20,300,100);
        fr.add(toolBar);
        fr.setVisible(true);
    }
    private JToolBar createToolBar(JFrame fr, App app,int howMuch, char what) throws IOException {
        //create components
        JToolBar toolBar =  new JToolBar();
        JLabel label = new JLabel("Pencil tchickness:");
        JCheckBox[] checkBoxs = new JCheckBox[howMuch];
        //create check boxes
        for(int i=1; i<=howMuch; i++)
            checkBoxs[i-1] = createJCB(i, fr, app,what);
        //set Bounds
        label.setBounds(0,0,200,50);
        toolBar.setBounds(0,100,100,10);
        //add components
        toolBar.add(label);
        for(int i=0; i<howMuch; i++)
            toolBar.add(checkBoxs[i]);

        return toolBar;
    }
    private JCheckBox createJCB (int name, JFrame fr, App app, char what) throws IOException {
        JCheckBox checkBox;
        if(what=='p'){
            checkBox = new JCheckBox(""+name);
            if(name==1)
                checkBox.setIcon(new ImageIcon(ImageIO.read(new File("arrow.jpg"))));
            //add ActionListener
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    app.setDrawingTool(name);
                    fr.dispose();
                }
            });
        }
        else {
            if(name==1)
                checkBox = new JCheckBox("png");
            else
                checkBox = new JCheckBox("jpg");

            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(name==1)
                        format = "png";
                    else
                        format = "jpg";
                    fr.dispose();
                }
            });
        }
        return checkBox;
    }
    private JButton createArrow(String src, App app, int addToMove, char coordinate)
            throws IOException{
        JButton but ;
        Image img;
        //CREATE THE moveUpButton
        img = ImageIO.read(getClass().getResource(src));
        but = new JButton(new ImageIcon(img));
        //add listener
        but.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                action(app,addToMove,coordinate);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                action(app,addToMove,coordinate);
                app.repaint();
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
        });
        return but;
    }
    public void action(App app, int addToMove, char coordinate) {
        int move;
        if (coordinate == 'y') {
            move = app.getPanel().getMoveY();
            if((move+addToMove<=0 && addToMove>0)||
                    (move+addToMove+app.getPanel().getImg().getHeight() >= app.getHeight()-120 && addToMove<0))
                app.getPanel().setMoveY(move + addToMove);
        }
        else if(coordinate == 'x'){
            move = app.getPanel().getMoveX();
            if((move+addToMove<=0 && addToMove>0)||
                    (move+addToMove+app.getPanel().getImg().getWidth()>=app.getWidth() && addToMove<0))
                app.getPanel().setMoveX(move + addToMove);
        }
        app.repaint();
    }
}
