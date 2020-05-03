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
    	return (this.x+this.y)/2;
    }
    
    public boolean tieneMenorX(Punto p2) {
    	return this.x < p2.x;
    }
    
    public boolean tieneMayorX(Punto p2) {
    	return this.x > p2.x;
    }
    
    public double distancia (Punto b){
    	double cateto1 = x - b.x;
    	double cateto2 = y - b.y;
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
		out.print(this.x);
        out.print(" ");
        out.print(this.y);
        out.println();
	}
	
	public int agregarPunto(int prev, LineaHorizonte salida) {
        if (this.y!=prev) // si paux no tiene la misma altura del segmento previo
        {
            salida.addPunto(this); // lo añadimos al LineaHorizonte de salida
            return this.y;    // y actualizamos prev
        }
        return prev;
	}

    
}
