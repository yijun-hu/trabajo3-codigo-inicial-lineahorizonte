package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


/*
 Clase fundamental.
 Sirve para hacer la lectura del fichero de entrada que contiene los datos de como
 están situados los edificios en el fichero de entrada. xi, xd, h, siendo. Siendo
 xi la coordenada en X origen del edificio iésimo, xd la coordenada final en X, y h la altura del edificio.
 
 */
public class Ciudad {
	
    private ArrayList <Edificio> ciudad;

    public Ciudad()
    {
    	
    	/*
    	 * Generamos una ciudad de manera aleatoria para hacer 
    	 * pruebas.
    	 */
ciudad = new ArrayList <Edificio>();

  this.metodoRandom(5); //Llamamos al método en vez de repetir el mismo código.
        
ciudad = new ArrayList <Edificio>();
}
    
        
    public Edificio getEdificio(int i) {
        return (Edificio)this.ciudad.get(i);
    }
    
       
    public void addEdificio (Edificio e)
    {
        ciudad.add(e);
    }
    public void removeEdificio(int i)
    {
        ciudad.remove(i);
    }
    
    public int size()
    {
        return ciudad.size();
    }
    
    public LineaHorizonte getLineaHorizonte()
    {
    	// pi y pd, representan los edificios de la izquierda y de la derecha.
        int pi = 0;                       
        int pd = ciudad.size()-1;      
        return crearLineaHorizonte(pi, pd);  
    }
    
	public LineaHorizonte crearLineaHorizonte(int pi, int pd)
	{
		LineaHorizonte linea = new LineaHorizonte(); // LineaHorizonte de salida
		        
		// Caso base, la ciudad solo tiene un edificio, el perfil es el de ese edificio. 
		if(pi==pd) 
		{
				horizonteRecursivoCasoBase(this.getEdificio(pi), linea);
		}
		else
		{
				// Edificio mitad
				linea = horizonteRecursivo(new Punto(pi, pd), linea);
		}
		return linea;
	}
    
private void horizonteRecursivoCasoBase(Edificio edificio, LineaHorizonte linea) {
	 // Obtenemos el Ãºnico edificio y lo guardo en b
	// En cada punto guardamos la coordenada X y la altura.
	Punto p1 = new Punto(edificio.getXi(), edificio.getY());
	// como el edificio se compone de 3 variables, en la Y de p2 le aÃ±adiremos un 0
	Punto p2 = new Punto(edificio.getXd(), 0);
	// AÃ±ado los puntos a la lÃ­nea del horizonte
	linea.addPunto(p1);    
	linea.addPunto(p2);
}

private LineaHorizonte horizonteRecursivo(Punto p, LineaHorizonte linea) {
	int medio= p.mitad();
	LineaHorizonte s1 = this.crearLineaHorizonte(p.getX(),medio);  
	LineaHorizonte s2 = this.crearLineaHorizonte(medio+1,p.getY());
	return s1.LineaHorizonteFussion(s2);
}



    /**
     * Función encargada de fusionar los dos LineaHorizonte obtenidos por la técnica divide y
     * vencerás. Es una función muy compleja ya que es la encargada de decidir si un
     * edificio solapa a otro, si hay edificios contiguos, etc. y solucionar dichos
     * problemas para que el LineaHorizonte calculado sea el correcto.
     */
    
    /*
     Método que carga los edificios que me pasan en el
     archivo cuyo nombre se encuentra en "fichero".
     El formato del fichero nos lo ha dado el profesor en la clase del 9/3/2020,
     pocos días antes del estado de alarma.
     */

    public void cargarEdificios (String fichero)
    {
//    	int n = 6;
//    	int i=0;
//        int xi,y,xd;
//        for(i=0;i<n;i++)
//        {
//            xi=(int)(Math.random()*100);
//            y=(int)(Math.random()*100);
//            xd=(int)(xi+(Math.random()*100));
//            this.addEdificio(new Edificio(xi,y,xd));
//        }
    	
        try
        {
            int xi, y, xd;
            Scanner sr = new Scanner(new File(fichero));

            while(sr.hasNext())
            {
            	xi = xd = y = sr.nextInt(); // Hace lo mismo que usar tres líneas, ocupa menos.
                
                Edificio Salida = new Edificio(new Punto(xi, y), xd);
                this.addEdificio(Salida);
            }
        }
        catch(Exception e){} 
           
    }

    
    public void metodoRandom(int n)
    {
        int i=0;
        int xi,y,xd;
        for(i=0;i<n;i++)
        {
            xi=(int)(Math.random()*100);
            y=(int)(Math.random()*100);
            xd=(int)(xi+(Math.random()*100));
            this.addEdificio(new Edificio(new Punto(xi, y), xd));
        }
    }
}
