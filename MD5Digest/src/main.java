import appayudas.EncriptCadena;
import java.awt.*;
import java.awt.event.*;
 
 
/**
 * This class can take a variable number of parameters on the command
 * line. Program execution begins with the main() method. The class
 * constructor is not invoked unless an object of type 'Class1'
 * created in the main() method.
 */
public class main extends Frame implements ActionListener, ItemListener
{
                boolean accion=true;
                TextField clave;
                TextArea texto1;
                TextArea texto2;
                Checkbox op1,op2;
                Button ejecutar;
                String textoboton="";
                Panel panel1,panel2,panel3;
               
                public main(String nombre)
                {
                               super(nombre);
                               panel1 = new Panel();
                                               panel1.add(new Label("Escoje  la acción"));
                                               CheckboxGroup acciones = new CheckboxGroup();
                                               op1 = new Checkbox("Encriptar",acciones,true);
                                               op2 = new Checkbox("Desencriptar",acciones,false);
                                               op1.addItemListener(this);
                                               op2.addItemListener(this);
                                               panel1.add(op1);
                                               panel1.add(op2);
                              
                                                              
                               panel2 = new Panel();
                                               panel2.add(new Label("Texto a encriptar"));
                                               texto1 = new TextArea(10,10);
                                               panel2.add(texto1);
                                               panel2.add(new Label("Texto a desencriptar"));
                                               texto2 = new TextArea(10,10);
                                               panel2.add(texto2);
                               panel3 = new Panel();
                                               panel3.add(new Label("Introduce la Clave"));
                                               clave = new TextField(20);
                                               panel3.add(clave);
                                               textoboton="Encriptar";
                                               ejecutar= new Button(textoboton);
                                               ejecutar.addActionListener(this);                                                
                                               panel3.add(ejecutar);      
                               setSize(450,250);
                               BorderLayout rejilla = new BorderLayout();
                               setLayout(rejilla);
                                               add("North",panel1);
                                               add("West",panel2);
                                               add("South",panel3);
                                               addWindowListener(new Cerrar());
                                               setVisible(true);
                              
                }
               
                public void actionPerformed(ActionEvent ae)
                {
                               if (clave.getText().equals(""))
                               {
                                               clave.setText("Introduzca una clave de encriptado");
                                               return;
                               }
                               if (accion)
                               {
                                               if (texto1.getText().equals(""))
                                               {
                                                               texto1.setText("Introduzca texto a encriptar");
                                                               return;
                                               }
                                              
                                              
                                               texto2.setText(new EncriptCadena(clave.getText(), texto1.getText(),accion).retorno);
                               }
                               else
                               {
                                               if (texto2.getText().equals(""))
                                               {
                                                               texto2.setText("Introduzca texto a desencriptar");
                                                               return;
                                               }
                              
                                               texto1.setText(new EncriptCadena(clave.getText(), texto2.getText(),accion).retorno);
 
                               }             
 
                }
                public void itemStateChanged(ItemEvent ie)
                {
                               accion=op1.getState();
               
                              
                               repaint();
                }
 
                class Cerrar extends WindowAdapter
                {
                               public void windowClosing(WindowEvent  we)
                               {
                                               System.exit(0);
                               }
                }
               
                public void paint(Graphics g)
                {
                              
               
                }
                public static void main (String[] args)
                {
                               main c = new main("Encriptar-Desencriptar");
                }
}
 
//y añadimos la clase EncriptCadena.java del programa Encriptar-Desencriptar de Windows.