package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CarritoServlet")
public class CarritoServlet extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        
        
        
        //Creamos o recuperamos el objeto HttpSession
        HttpSession sesion = request.getSession();
        
        //recuperamos la lista de articulos agregados si es que existen
        //lista de objetos de tipo String
        //se le solicita a la sesion nos retorne la lista de articulos
        //se hace un casteo por que nos retorna un objeto inicialmente y no una lista
        List<String> articulos = (List<String>) sesion.getAttribute("articulos");
        
        
        //verificamos si de verdad la lista de articulos existe
        if(articulos == null){ //significa que todavia no se ha inicializado la lista de articulos
            //inicializamos la lista de articulos
            articulos = new ArrayList<>();
            
            //una vez creada la lista, la agregamos a nuestra sesion
            sesion.setAttribute("articulos", articulos);
        }
        
        
        
        //Procesamos el nuevo articulo
        String articuloNuevo = request.getParameter("articulo");
        
        
        //revisamos y agregamos el articulo nuevo
        //si es diferente de nulo y ademas no es cadena vacia y quitamos espacios en blanco
        if(articuloNuevo != null && !articuloNuevo.trim().equals("")){
            //entonces agregamos este articulo a la lista de articulos
            articulos.add(articuloNuevo);
        }
        
        //imprimos la lista de articulos
        PrintWriter salida = response.getWriter();
        salida.print("<h1>Lista de articulos</h1>");
        salida.print("<br>");
        //usamos for each para traer de vuelta todos los articulos
        for(String articulo:articulos){
            salida.print("<li>"+articulo+"</li>");
        }
        
        //link para retornar a la pagina anterior
        //Nota: si se abren mas sesiones el carrito aparecera vacio nuevamente, los elementos
        //permaneceran en el servidor de aplicaciones por 30 mins como maximo a no ser que estemos
        //interactuando todo el rato con este carrito
        salida.print("<br>");
        salida.print("<a href='/EjemploCarritoCompras'>Regresar al Inicio</a>");
        salida.close();
    }
}