/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstanimation;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;
import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCondition;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOnElapsedTime;
import javax.vecmath.Vector3f;

/**
 *
 * @author Albert
 */
public class SimpleBehavior extends Behavior                      //aktualizuje scenerie co okreslony czas
{
    
     private TransformGroup Tank;
     private TransformGroup targetTG;
     private TransformGroup cannonL;
     private TransformGroup cannonR;
     private TransformGroup Bullet = new TransformGroup();
     private TransformGroup Bullet2 = new TransformGroup();
     private TransformGroup Ground = new TransformGroup();
     private Vector3f TankXYZpos = new Vector3f(0.0f,0.0f,0.0f);
     private Vector3f BulletXYZpos = new Vector3f(0.0f,-1f,0.0f);
     private Vector3f BulletXYZpos2 = new Vector3f(0.0f,-1f,0.0f);
     private Vector3f BulletXYZnewpos = new Vector3f();
      
         private Transform3D setTurretPos = new Transform3D();
         private Transform3D setTurretRot = new Transform3D();
         private Transform3D FinalTurret = new Transform3D();
         private Transform3D CLrot = new Transform3D();
         private Transform3D CRrot = new Transform3D();
         private Transform3D CLposF = new Transform3D();
         private Transform3D CRposF = new Transform3D();
         Transform3D finalT3DL= new Transform3D();
         Transform3D finalT3DR = new Transform3D();
         
         public boolean[] isButtonPressed = new boolean[9]; // lewo prawo gora dol a d w s spacja czy dane przyciski sa wcisniete;
         public float angleY = 0.0f;        //kat do obrotu w osi Y 
         public float angleTur = 0.0f;        //kat do obrotu w osi X
         public float angleYT = 0.0f;
         private WakeupCondition wc = new WakeupOnElapsedTime(70);          // aktualizejymy scene co x milisekund
         
         SimpleBehavior(TransformGroup targetTG,TransformGroup cannonL,TransformGroup cannonR,TransformGroup Tank,TransformGroup Bullet,TransformGroup Bullet2,TransformGroup Ground)
         {
             this.targetTG = targetTG;
             this.cannonL = cannonL;
             this.cannonR = cannonR;
             this.Tank = Tank;
             this.Bullet = Bullet;
             this.Ground = Ground;
             this.Bullet2 = Bullet2;
             
         }
         
         public void initialize()
         {
         //this.wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
         this.wakeupOn(wc);    
        }
         public void processStimulus(Enumeration criteria)
         {
            
             update();
             setGround();
             setCannonPos();
             setTankPos();
             setBullet();
               this.wakeupOn(wc);
         
         
         }
         
         private void update()
         {
             if(isButtonPressed[0]) angleY+=0.2f;
             if(isButtonPressed[1]) angleY-=0.2f;
             if(isButtonPressed[2]) angleTur+=0.1f;
             if(isButtonPressed[3]) angleTur-=0.1f;
             if(isButtonPressed[4]) angleYT+=0.1f;
             if(isButtonPressed[5]) angleYT-=0.1f;
             if(isButtonPressed[6]) {TankXYZpos.z-=cos(angleYT)/8; TankXYZpos.x-=sin(angleYT)/8;}
             if(isButtonPressed[7]) {TankXYZpos.z+=cos(angleYT)/9; TankXYZpos.x+=sin(angleYT)/9;}
             
             if(isButtonPressed[8]) 
             {
                finalT3DL.get(BulletXYZpos2);
                finalT3DR.get(BulletXYZpos);
                BulletXYZnewpos.x = (float) -(sin(angleY+angleYT)*cos(angleTur))/5;
                BulletXYZnewpos.z = (float) -(cos(angleY+angleYT)*cos(angleTur))/5;
                BulletXYZnewpos.y = (float) sin(angleTur)/5;
                isButtonPressed[8] = false;
            }
           
         }
         private void setTankPos()
         {
             Transform3D tankPos = new Transform3D();
             Transform3D tankRot = new Transform3D();
             tankPos.setTranslation(new Vector3f(0.0f+TankXYZpos.x,-1.07f,0.0f+TankXYZpos.z));
             tankRot.rotY(angleYT);
             tankPos.mul(tankPos,tankRot);
             Tank.setTransform(tankPos);
             
         }
         private void setCannonPos()
         {
             setTurretPos.setTranslation(new Vector3f(0.0f+TankXYZpos.x,-0.8f+TankXYZpos.y,-0.15f+TankXYZpos.z));
             setTurretRot.rotY(angleY+angleYT);
             FinalTurret.mul(setTurretPos,setTurretRot);                //obroty podstawy wiezyczki
             
             targetTG.setTransform(FinalTurret);
             
             //ustawianie dzialek
             
              finalT3DL= new Transform3D();
              finalT3DR = new Transform3D();
             
             CLposF.setTranslation(new Vector3f(0.0f,0.2f,0.0f));
             CRposF.setTranslation(new Vector3f(0.0f,0.2f,0.0f));
             
             CLrot.rotX(toRadians(90)+angleTur);
             CRrot.rotX(toRadians(90)+angleTur);
             
             finalT3DL.mul(CLposF,CLrot);
             finalT3DR.mul(CLposF,CLrot);
             
             CLposF.setTranslation(new Vector3f(0.0f,-0.2f,0.0f));
             CRposF.setTranslation(new Vector3f(0.0f,-0.2f,0.0f));
           
             finalT3DL.mul(finalT3DL,CLposF);
             finalT3DR.mul(finalT3DR,CRposF);

             CLposF.setTranslation(new Vector3f(-0.1f,-0.9f,-0.25f));
             CRposF.setTranslation(new Vector3f(0.1f,-0.9f,-0.25f));

             finalT3DL.mul(CLposF,finalT3DL);
             finalT3DR.mul(CRposF,finalT3DR);
             
             CLposF.setTranslation(new Vector3f(0.0f,0.0f,0.15f));
             CRposF.setTranslation(new Vector3f(0.0f,0.0f,0.15f));
             
            finalT3DL.mul(CLposF,finalT3DL);
            finalT3DR.mul(CRposF,finalT3DR);
            
             CLrot.rotY(angleY+angleYT);
             CRrot.rotY(angleY+angleYT);
             
            finalT3DL.mul(CLrot,finalT3DL);
            finalT3DR.mul(CRrot,finalT3DR);
            
            CLposF.setTranslation(new Vector3f(0.0f+TankXYZpos.x,0.0f,-0.15f+TankXYZpos.z));
            CRposF.setTranslation(new Vector3f(0.0f+TankXYZpos.x,0.0f,-0.15f+TankXYZpos.z));
             
            finalT3DL.mul(CLposF,finalT3DL);
            finalT3DR.mul(CRposF,finalT3DR);
            
            
             cannonL.setTransform(finalT3DL);
             cannonR.setTransform(finalT3DR);
             
               
              
         }
         private void setGround()
         {
             Transform3D setGround = new Transform3D();
             setGround.setTranslation(new Vector3f(0.0f,-1.17f,0.0f));
             Ground.setTransform(setGround);
             
             
         }
         
         private void setBullet()
         {
             Transform3D bulletpos = new Transform3D();
             Transform3D bulletpos2 = new Transform3D();
             
//             Transform3D bulletrot = new Transform3D();
//             bulletpos.setTranslation(new Vector3f(-0.1f+TankXYZpos.x,-0.5f,-0.25f+TankXYZpos.z));
//             bulletrot.rotY(angleY+angleYT);
//             bulletpos.mul(bulletpos,bulletrot);
                BulletXYZpos = new Vector3f(BulletXYZpos.x+BulletXYZnewpos.x,BulletXYZpos.y+BulletXYZnewpos.y,BulletXYZpos.z+BulletXYZnewpos.z);
                BulletXYZpos2 = new Vector3f(BulletXYZpos2.x+BulletXYZnewpos.x,BulletXYZpos2.y+BulletXYZnewpos.y,BulletXYZpos2.z+BulletXYZnewpos.z);
                bulletpos.setTranslation(new Vector3f(BulletXYZpos.x,BulletXYZpos.y,BulletXYZpos.z));
                 Bullet.setTransform(bulletpos);
                 bulletpos2.setTranslation(new Vector3f(BulletXYZpos2.x,BulletXYZpos2.y,BulletXYZpos2.z));
                 Bullet2.setTransform(bulletpos2);
                 
                 
         }
    
 }
