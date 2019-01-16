package gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private opins OP = new opins();

    Menu(){
        super("Menu");
        setSize(200, 100);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        add(OP);
        setVisible(true);
    }

    public opins getOP() {
        return OP;
    }
}
class opins extends JComponent{
    private Button newGame = new Button("New world");
    private TextField fieldAx = new TextField("Size of field");
    private Button exit = new Button("Exit");
    private int sizeField = 0;

    opins(){
        setLayout(new GridLayout(3, 1, 2, 2));
        newGame.addActionListener(new newButton(this));
        exit.addActionListener(new exButton(this));
        add(fieldAx);
        add(newGame);
        add(exit);

    }


    String getField(TextField field){
        return field.getText();
    }

    TextField getFieldAx() {
        return fieldAx;
    }

    public void setSizeField(int sizeField) {
        this.sizeField = sizeField;
    }

    public int getSizeField() {
        return sizeField;
    }
}

class newButton implements ActionListener{
    private opins OP;

    newButton(opins OP){
        this.OP = OP;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = OP.getField(OP.getFieldAx());
        s = s.split("\\s+")[0];
        OP.setSizeField(Integer.valueOf(s));
    }
}

class exButton implements ActionListener{
    private opins OP;

    exButton(opins OP){
        this.OP = OP;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = OP.getField(OP.getFieldAx());
        s = s.split("\\s+")[0];
        OP.setSizeField(-1);
    }
}