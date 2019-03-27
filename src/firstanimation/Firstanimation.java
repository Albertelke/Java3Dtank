/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstanimation;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Enumeration;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.Behavior;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.LineArray;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;


public class Firstanimation extends Applet implements KeyListener
{
    float angle;
    SimpleBehavior myRotationBehavior;
    float posZ=0.0f;
    
    
    
    
    public void keyPressed(KeyEvent e) {

   //Invoked when a key has been pressed.

   if (e.getKeyCode()==KeyEvent.VK_RIGHT) {myRotationBehavior.isButtonPressed[1] = true;}

  if (e.getKeyCode()==KeyEvent.VK_LEFT) {myRotationBehavior.isButtonPressed[0] = true;}
  
   if (e.getKeyCode()==KeyEvent.VK_UP) {myRotationBehavior.isButtonPressed[2] = true; }

  if (e.getKeyCode()==KeyEvent.VK_DOWN) {myRotationBehavior.isButtonPressed[3] = true;}
  
  if (e.getKeyChar()=='a') {myRotationBehavior.isButtonPressed[4] = true;}

  if (e.getKeyChar()=='d') {myRotationBehavior.isButtonPressed[5] = true;}
  
   if (e.getKeyChar()=='w') {myRotationBehavior.isButtonPressed[6] = true; }

  if (e.getKeyChar()=='s') {myRotationBehavior.isButtonPressed[7] = true;}

   
        
   }
   
  public void keyReleased(KeyEvent e)
{

    if (e.getKeyCode()==KeyEvent.VK_RIGHT) {myRotationBehavior.isButtonPressed[1] = false;}

  if (e.getKeyCode()==KeyEvent.VK_LEFT) {myRotationBehavior.isButtonPressed[0] = false;}
  
   if (e.getKeyCode()==KeyEvent.VK_UP) {myRotationBehavior.isButtonPressed[2] = false; }

  if (e.getKeyCode()==KeyEvent.VK_DOWN) {myRotationBehavior.isButtonPressed[3] = false;}
  
  if (e.getKeyChar()=='a') {myRotationBehavior.isButtonPressed[4] = false;}

  if (e.getKeyChar()=='d') {myRotationBehavior.isButtonPressed[5] = false;}
  
   if (e.getKeyChar()=='w') {myRotationBehavior.isButtonPressed[6] = false; }

  if (e.getKeyChar()=='s') {myRotationBehavior.isButtonPressed[7] = false;}
  
  {if (e.getKeyCode()==KeyEvent.VK_SPACE) {myRotationBehavior.isButtonPressed[8] = true;}}
  

}

public void keyTyped(KeyEvent e){

   //Invoked when a key has been typed.

}


    Color3f ultramaryna = new Color3f(0.227f, 0.909f, 0.949f); //
    Color3f green = new Color3f(0.211f, 0.788f, 0.227f);
    Color3f blue = new Color3f(0.211f, 0.278f, 0.788f);
    Color3f red = new Color3f(0.7f, .15f, .15f);
    Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
    Color3f black = new Color3f(0f,0f,0f);
     Appearance ap = new Appearance();
     Appearance gr = new Appearance();
     Appearance bullet = new Appearance();
      public BranchGroup createSceneGraph()
      {
         BranchGroup objRoot = new BranchGroup();
         TransformGroup objRotate = new TransformGroup();
         TransformGroup CannonL = new TransformGroup();
         TransformGroup CannonR = new TransformGroup();
         TransformGroup Tank = new TransformGroup();
         TransformGroup Bullet = new TransformGroup();
         TransformGroup Ground = new TransformGroup();
         TransformGroup Bullet2 = new TransformGroup();
         TransformGroup lines = drawLines();
         
         
         
         TextureLoader loader = new TextureLoader("grass.jpg",null);
         ImageComponent2D mImage = loader.getImage( );     
         Texture2D  tx2 = new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, mImage.getWidth(), mImage.getHeight());
         tx2.setImage(0, mImage);
         //tx2.setBoundaryModeS(Texture.WRAP);
         //tx2.setBoundaryModeT(Texture.WRAP);
         
         objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         CannonL.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         CannonR.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         Tank.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         Bullet.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         Ground.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         Bullet2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
         objRoot.addChild(Tank);
         objRoot.addChild(objRotate);
         objRoot.addChild(CannonL);
         objRoot.addChild(CannonR);
         objRoot.addChild(Ground);
         objRoot.addChild(Bullet);
         objRoot.addChild(Bullet2);
         ap.setMaterial(new Material());
         gr.setTexture(tx2);
         gr.setMaterial(new Material(red,black,red,black,1));
         bullet.setMaterial(new Material(red,black,red,black,120));
         
         objRotate.addChild(new Box(0.15f,0.15f,0.15f,ap));
         CannonL.addChild(new Cylinder(0.035f,0.4f) );
         CannonR.addChild(new Cylinder(0.035f,0.4f) );
         Tank.addChild(new Box(0.3f,0.22f,0.55f,ap));
         Ground.addChild(new Box(20.f,0.01f,20.f,Box.GENERATE_TEXTURE_COORDS,gr));
         Bullet.addChild(new Sphere(0.07f,bullet));
         Bullet2.addChild(new Sphere(0.07f,bullet));
         
            
         
         objRoot.addChild(lines);
        
        //tworzenie swiatla
          Color3f lightCol = new Color3f(0.941f, 0.941f, 0.937f);
          BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 1000.0f);
          Vector3f lightDir = new Vector3f(4.0f, -7.0f, -12.0f);
          DirectionalLight light = new DirectionalLight (lightCol,lightDir);
          light.setInfluencingBounds(bounds);
          AmbientLight alldirlight = new AmbientLight(new Color3f(0.941f, 0.941f, 0.937f));
          alldirlight.setInfluencingBounds(bounds);
          objRoot.addChild(alldirlight);        // dodamy sobie wygodne swiatlo do  ogladania obiektow z kazdej strony
          objRoot.addChild(light);
          Background bg = new Background(blue);
          bg.setApplicationBounds(bounds);
          TextureLoader myLoader = new  TextureLoader("sky.jpg",null);
          ImageComponent2D myImage = myLoader.getImage( );
          bg.setImage(myImage);
          bg.setApplicationBounds(bounds);
          myRotationBehavior = new SimpleBehavior(objRotate,CannonL,CannonR,Tank,Bullet,Bullet2,Ground);
          myRotationBehavior.setSchedulingBounds(bounds);
          objRoot.addChild(bg);
          objRoot.addChild(myRotationBehavior);
          
          
         
          objRoot.compile();
          return objRoot;


      }
    public TransformGroup drawLines()
    {
        TransformGroup lines=new TransformGroup();
         Appearance app = new Appearance();
        ColoringAttributes ca = new ColoringAttributes(new Color3f(Color.RED), ColoringAttributes.NICEST);
        app.setColoringAttributes(ca);

        Point3f[] plaPts = new Point3f[2];
        plaPts[0] = new Point3f(0.0f, 0.0f, 0.0f);
        plaPts[1] = new Point3f(3.0f, 0.0f, 0.0f);
        LineArray pla = new LineArray(2, LineArray.COORDINATES);
        pla.setCoordinates(0, plaPts);
        Shape3D plShape = new Shape3D(pla, app);
        lines.addChild(plShape);

        Appearance app2 = new Appearance();
        ColoringAttributes ca2 = new ColoringAttributes(new Color3f(Color.BLUE), ColoringAttributes.NICEST);
        app2.setColoringAttributes(ca2);

        Point3f[] plaPts2 = new Point3f[2];
        plaPts2[0] = new Point3f(0.0f, 0.0f, 0.0f);
        plaPts2[1] = new Point3f(0.0f, -3.0f, 0.0f);
        LineArray pla2 = new LineArray(2, LineArray.COORDINATES);
        pla2.setCoordinates(0, plaPts2);
        Shape3D plShape2 = new Shape3D(pla2, app2);
        lines.addChild(plShape2);

        Appearance app3 = new Appearance();
        ColoringAttributes ca3 = new ColoringAttributes(new Color3f(Color.GREEN), ColoringAttributes.NICEST);
        app3.setColoringAttributes(ca3);

        Point3f[] plaPts3 = new Point3f[2];
        plaPts2[0] = new Point3f(0.0f, 0.0f, 0.0f);
        plaPts2[1] = new Point3f(0.0f, 0.0f, -3.0f);
        LineArray pla3 = new LineArray(2, LineArray.COORDINATES);
        pla3.setCoordinates(0, plaPts2);
        Shape3D plShape3 = new Shape3D(pla3, app3);
        lines.addChild(plShape3);
        return lines;
    }
    
    public Firstanimation()
    {
        
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration(); 
        setLayout(new BorderLayout()); 
        Canvas3D canvas3d = new Canvas3D(config);
        add("Center", canvas3d);
        canvas3d.addKeyListener(this);
        BranchGroup scena = createSceneGraph();
       
       // System.out.println("plotka");
        //scena.compile()
        
       
        SimpleUniverse simpleU = new SimpleUniverse(canvas3d);
       // simpleU.getViewingPlatform().setNominalViewingTransform();
       OrbitBehavior orbit = new OrbitBehavior(canvas3d, OrbitBehavior.REVERSE_ROTATE); //tworzymy cos takiego żeby moc obracać kamere myszka       
        orbit.setSchedulingBounds(new BoundingSphere());    
        simpleU.getViewingPlatform().setViewPlatformBehavior(orbit);                        
        //simpleU.getViewingPlatform().getViewPlatformTransform();  
        simpleU.getViewingPlatform().setNominalViewingTransform();                      //ustawiamy tak zebysmy mieli ladny widok od uruchomienia programu
        simpleU.addBranchGraph(scena);
         
    }
    
    public static void main(String[] args)
    {
        System.setProperty("sun.awt.erasebackground", "true");

   Firstanimation test = new Firstanimation();
  // test.addKeyListener(test);


   new MainFrame(test, 1000, 800);
        
    }
}
