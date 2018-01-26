package com.lignanislore.uselessApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import javafx.scene.layout.Pane;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;



public class Controller {

    @FXML
    private Pane pane;

    @FXML
    private RadioButton btn;

    @FXML
    private Label label;





private Robot robot;

    {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void action(ActionEvent event)  {

       if (btn.isSelected()){

           btn.setText("ON");
           Thread thread = new Thread(){
               public void run(){
                   try {
                       Thread.sleep(2000);
                       hareket();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           };
           thread.start();

       }else {

           btn.setText("OFF");
           try {

               AudioInputStream ınputStream= AudioSystem.getAudioInputStream(new File("Youcanttouchthis.wav"));
               Clip clip=AudioSystem.getClip();
               clip.open(ınputStream);
               clip.start();
           } catch (UnsupportedAudioFileException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           } catch (LineUnavailableException e) {
               e.printStackTrace();
           }
       }





    }

    private void hareket() {

        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        int x = (int) b.getX();
        int y = (int) b.getY();

        int xFark=x-675;//butonun x koordinatı=675
        int yFark=y-372;//butonun y koordinatı=372


        while (xFark!=0 || yFark!=0){

            if (xFark>0){
                xFark-=1;
                x-=1;
            }else if (xFark<0){
                x+=1;
                xFark+=1;
            }

            if (yFark>0){
                y-=1;
                yFark-=1;
            }else if (yFark<0){
                y+=1;
                yFark+=1;
            }

            robot.mouseMove(x,y);

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        robot.mousePress(java.awt.event.InputEvent.BUTTON1_MASK);
        robot.mouseRelease(java.awt.event.InputEvent.BUTTON1_MASK);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int i=1;
        while(i<200){
            robot.mouseMove(x+i,y+i);

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            i++;
        }




    }


}
