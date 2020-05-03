package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;


public class LineaHorizonte {
	
	private ArrayList <Punto> LineaHorizonte ;
	
    /*
     * Constructor sin par�metros
     */
    public LineaHorizonte()
    {
        LineaHorizonte = new ArrayList <Punto>();
    }
            
    /*
     * m�todo que devuelve un objeto de la clase Punto
     */
    public Punto getPunto(int i) {
        return (Punto)this.LineaHorizonte.get(i);
    }
    
    // A�ado un punto a la l�nea del horizonte
    public void addPunto(Punto p)
    {
        LineaHorizonte.add(p);
    }    
    
    // m�todo que borra un punto de la l�nea del horizonte
    public void borrarPunto(int i)
    {
        LineaHorizonte.remove(i);
    }
    
    public int size()
    {
        return LineaHorizonte.size();
    }
    // m�todo que me dice si la l�nea del horizonte est� o no vac�a
    public boolean isEmpty()
    {
        return LineaHorizonte.isEmpty();
    }
   
    /*
      M�todo al que le pasamos una serie de par�metros para poder guardar 
      la linea del horizonte resultante despu�s de haber resuelto el ejercicio
      mediante la t�cnica de divide y vencer�s.
     */
    
    public void guardaLineaHorizonte (String fichero)
    {
        try
        {
            FileWriter fileWriter = new FileWriter(fichero);
            PrintWriter out = new PrintWriter (fileWriter);
         
            for(int i=0; i<this.size(); i++)
            {
            	getPunto(i).guardarPunto(out);
            }
            out.close();
        }
        catch(Exception e){}
    }
    
    
    public void imprimir (){
    	
    	for(int i=0; i< LineaHorizonte.size(); i++ ){
    		//System.out.println("X: " + LineaHorizonte.get(i).getX() + " Y: " + LineaHorizonte.get(i).getY());
    		System.out.println(cadena(i));
    	}
    }
    
    public String cadena (int i){
    	
    	return LineaHorizonte.get(i).toString();
    }
    
    public LineaHorizonte LineaHorizonteFussion(LineaHorizonte s2)
    {
    	// en estas variables guardaremos las alturas de los puntos anteriores, en s1y la del s1, en s2y la del s2 
    	// y en prev guardaremos la previa del segmento anterior introducido
        int s1y=-1, s2y=-1, prev=-1;    
        LineaHorizonte salida = new LineaHorizonte(); // LineaHorizonte de salida
        
           // punto donde guardaremos el primer punto del LineaHorizonte s2
        
        imprimirHorizontes(this, s2);
        
        //Mientras tengamos elementos en s1 y en s2
        while (noEstanVacios(this,s2)) 
        {
            Punto paux = new Punto();  // Inicializamos la variable paux
            Punto p1 = this.getPunto(0); // guardamos el primer elemento de s1
            Punto p2 = s2.getPunto(0); // guardamos el primer elemento de s2

            if (p1.tieneMenorX(p2)) // si X del s1 es menor que la X del s2
            {
            	// guardamos en paux esa X y hacemos que el maximo entre la Y del s1 y la altura previa del s2 sea la altura Y de paux
                paux.setXY(p1.getX(),Math.max(p1.getY(), s2y));                
                prev = paux.agregarPunto(prev, salida);
                s1y = p1.getY();   // actualizamos la altura s1y
                this.borrarPunto(0); // en cualquier caso eliminamos el punto de s1 (tanto si se añade como si no es valido)
            }
            else if (p1.tieneMayorX(p2)) // si X del s1 es mayor que la X del s2
            {
            	// guardamos en paux esa X y hacemos que el maximo entre la Y del s2 y la altura previa del s1 sea la altura Y de paux
                paux.setXY(p2.getX(), Math.max(p2.getY(), s1y));     
                prev = paux.agregarPunto(prev, salida);
                s2y = p2.getY();   // actualizamos la altura s2y
                s2.borrarPunto(0); // en cualquier caso eliminamos el punto de s2 (tanto si se añade como si no es valido)
            }
            else // si la X del s1 es igual a la X del s2
            {
                if (p1.compA(p2,prev)) // guardaremos aquel punto que tenga la altura mas alta
                {
                    salida.addPunto(p1);
                    prev = p1.getY();
                }
                if (p1.compB(p2,prev))
                {
                    salida.addPunto(p2);
                    prev = p2.getY();
                }
                s1y = p1.getY();   // actualizamos la s1y e s2y
                s2y = p2.getY();
                this.borrarPunto(0); // eliminamos el punto del s1 y del s2
                s2.borrarPunto(0);
            }
        }
        this.acabarLineaHorizonte(salida, prev);
        s2.acabarLineaHorizonte(salida, prev);

        return salida;
    }
    
    private void acabarLineaHorizonte(LineaHorizonte salida, int prev) {
    	while ((!this.isEmpty())) //si aun nos quedan elementos en el s
        {
    		prev = this.getPunto(0).agregarPunto(prev, salida);
            this.borrarPunto(0); // en cualquier caso eliminamos el punto de s (tanto si se añade como si no es valido)
        }
    }
    
    private void imprimirHorizontes(LineaHorizonte s1, LineaHorizonte s2) {
    	System.out.println("==== S1 ====");
        s1.imprimir();
        System.out.println("==== S2 ====");
        s2.imprimir();
        System.out.println("\n");
    }
    
    private boolean noEstanVacios(LineaHorizonte s1,LineaHorizonte s2) {
		 return (!s1.isEmpty()) && (!s2.isEmpty());
	}
    
    
}
