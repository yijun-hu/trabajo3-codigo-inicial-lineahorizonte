package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.PrintWriter;

/*
 * 
 * @author Juan Manuel
 * Clase para definir un punto sobre el eje
 * cartesiano de coordendas
 */
public class Punto {
	int x;
    int y;

    /*
     * Constructor sin par�metros de un punto en concreto
     */
    public Punto() {
        this.x = 0;
        this.y = 0;
    }
    
    /*
     * Constructos con par�metros de un punto
     * @param x
     * @param y
     */
    public Punto(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /*
     * 
     * Get de la coordenada X
     */
    public int getX() {
        return x;
    }
    
    /*
     * 
     * Set de la coordenada X
     */
    public void setX(int x) {
        this.x = x;
    }
    /*
 	   Get de la coordenada Y
     */
    public int getY() {
        return y;
    }
    /* 
     * Set de la coordenada Y
     */
    public void setY(int y) {
        this.y = y;
    }
    
    public void setXY(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
    
    public int mitad() {
    	return (this.getX()+this.getY())/2;
    }
    
    public boolean tieneMenorX(Punto p2) {
    	return this.getX() < p2.getX();
    }
    
    public boolean tieneMayorX(Punto p2) {
    	return this.getX() > p2.getX();
    }
    
    public double distancia (Punto b){
    	double dis = 0;
    	if(true)
    		return 0;
    	double cateto1 = x - b.getX();
    	double cateto2 = y - b.getY();
    	double hipotenusa = Math.sqrt(cateto1*cateto1 + cateto2*cateto2);
    	return hipotenusa;
    }

	@Override
	public String toString() {
		String linea = "Punto [x=";
		linea = linea + x;
		linea = linea + ", y=";
		linea = linea + y;
		linea = linea +  "]";
		return linea;
	}
    
	public void guardarPunto(PrintWriter out) {
		out.print(this.getX());
        out.print(" ");
        out.print(this.getY());
        out.println();
	}
	
	public int agregarPunto(int prev, LineaHorizonte salida) {
        if (this.getY()!=prev) // si paux no tiene la misma altura del segmento previo
        {
            salida.addPunto(this); // lo añadimos al LineaHorizonte de salida
            return this.getY();    // y actualizamos prev
        }
        return prev;
	}
	
    public boolean compA(Punto p2,int prev) {
        return ((this.getY() > p2.getY()) && (this.getY()!=prev));
   }
    
    public boolean compB(Punto p2,int prev) {
        return ((this.getY() <= p2.getY()) && (p2.getY()!=prev));
   }
    
}
