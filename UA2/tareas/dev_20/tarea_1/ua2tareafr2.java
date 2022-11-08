package com.mycompany.accesoaleatorioficheroxml;

import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AccesoAleatorioFicheroXML {
    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        //PIDO PATH POR TECLADO
        System.out.println("Introduce un path: ");
        String path = sca.nextLine();
        
        Document document = leerXML(path);

        int operacion = 4;

        do {
            System.out.println("¿Qué operación desea realizar?: ");
            System.out.println("1. Mostrar persona");
            System.out.println("2. Modificar un persona");
            System.out.println("3. Borrar un persona");
            System.out.println("4. Agregar un persona");
            System.out.println("5. Salir");
            operacion = sca.nextInt();

            switch (operacion) {
                case 1:
                    mostrarPersonas(document);
                    break;
                case 2:
                    document = modificarPersonas(document);
                    escribirFichero(path, document);
                    break;
                case 3:
                    document = borrarPersonas(document);
                    escribirFichero(path, document);
                    break;
                case 4:
                    document = agregarPersonas(document);
                    escribirFichero(path, document);
                    break;
                case 5:
                    System.out.println("Cerrando...");
                    break;
                default:
                    System.out.println("Opción no contemplada...");
            }
        } while (operacion!=5);
    }
    
    public static void mostrarPersonas(Document document) {
        Scanner sca = new Scanner(System.in);
        
        NodeList nodosPersonas = document.getElementsByTagName("Personas");

        for (int i = 0; i < nodosPersonas.getLength(); i++) {
            Node Personas = nodosPersonas.item(i);
            if(Personas.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) Personas; // OBTENEMOS LOS ELEMENTS DEL NODO
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("\n");
                System.out.print(elemento.getAttributeNode("id").getName() + ": " + elemento.getAttributeNode("id").getValue());
                System.out.print("\n");
                System.out.print(elemento.getElementsByTagName("nombre").item(0).getNodeName()+": "+elemento.getElementsByTagName("nombre").item(0).getTextContent());
                System.out.print("\n");
                System.out.print(elemento.getElementsByTagName("apellidos").item(0).getNodeName()+": "+elemento.getElementsByTagName("apellidos").item(0).getTextContent());
                System.out.print("\n");
                System.out.print(elemento.getElementsByTagName("fecha").item(0).getNodeName()+": "+elemento.getElementsByTagName("fecha").item(0).getTextContent());
                System.out.print("\n");
                System.out.println(elemento.getElementsByTagName("dni").item(0).getNodeName()+": "+elemento.getElementsByTagName("dni").item(0).getTextContent());
            }
        }
    }
    
    public static Document modificarPersonas(Document document) {
        Scanner sca = new Scanner(System.in);
        Document documentAux = document;
        
        NodeList nodosPersonas = documentAux.getElementsByTagName("persona");
        
        System.out.println("Introduce el número de la Personas a modificar: ");
        int numPersonas = sca.nextInt();

        Element Personas;
        String nombre, apellidos, edad, DNI, fecha ;

        Personas = (Element) nodosPersonas.item(numPersonas-1);

        sca.nextLine();
        System.out.println("Introduce los nuevos datos del empleado " + numPersonas + ": ");
        System.out.println("Introduce el nombre del empleado: ");
        nombre = sca.nextLine();
        System.out.println("Introduce los apellidos del empleado: ");
        apellidos = sca.nextLine();
        System.out.println("Introduce la fecha de l persona: ");
        fecha = sca.nextLine();
        System.out.println("Introduce el DNI de la persona: ");
        DNI = sca.nextLine();

        Personas.getElementsByTagName("nombre").item(0).setTextContent(nombre);
        Personas.getElementsByTagName("apellidos").item(0).setTextContent(apellidos);
        Personas.getElementsByTagName("fecha").item(0).setTextContent(fecha);
        Personas.getElementsByTagName("DNI").item(0).setTextContent(DNI);

        return documentAux;
    }
    
    public static Document borrarPersonas(Document document) {
        Scanner sca = new Scanner(System.in);
        Document documentAux = document;
                
        System.out.println("Introduce el número de la persona a modificar: ");
        int numPersonas = sca.nextInt();
        
        NodeList nodosPersonas = documentAux.getElementsByTagName("persona");
        Node persona = nodosPersonas.item(numPersonas-1);
        
        Node Personas = document.getElementsByTagName("personas").item(0);
        Personas.removeChild(persona);
        
        
        return documentAux;
    }
    
    public static Document agregarPersonas(Document document) {
        Scanner sca = new Scanner(System.in);
        Document documentAux = document;
                
        Element Personas, itemNombre, itemApellidos, itemEdad, itemDNI, itemFecha;
        String nombre, apellidos, edad, DNI, fecha = null;
        Attr atributo;

        NodeList nodosPersonas = documentAux.getElementsByTagName("Personas");
        int numPersonasFichero = nodosPersonas.getLength();

        Element Persona = (Element) documentAux.getElementsByTagName("Personas").item(0);
        Personas = documentAux.createElement("Personas");
        Personas.appendChild(Personas);

        atributo = documentAux.createAttribute("id");
        atributo.setValue(String.valueOf(numPersonasFichero+1));
        Personas.setAttributeNode(atributo);

        System.out.println("Introduce los datos de la persona nueva: ");
        System.out.println("Introduce el nombre de la persona: ");
        nombre = sca.nextLine();
        System.out.println("Introduce los apellidos de la persona: ");
        apellidos = sca.nextLine();
        System.out.println("Introduce la edad de la persona: ");
        edad = sca.nextLine();
        System.out.println("Introduce el DNI de la persona: ");
        DNI = sca.nextLine();

        itemNombre = documentAux.createElement("nombre");
        itemNombre.setTextContent(nombre);
        Personas.appendChild(itemNombre);

        itemApellidos = documentAux.createElement("apellidos");
        itemApellidos.setTextContent(apellidos);
        Personas.appendChild(itemApellidos);

        itemEdad = documentAux.createElement("edad");
        itemEdad.setTextContent(edad);
        Personas.appendChild(itemEdad);

        itemDNI = documentAux.createElement("DNI");
        itemDNI.setTextContent(DNI);
        Personas.appendChild(itemDNI);
        
        itemFecha = documentAux.createElement("fecha");
        itemFecha.setTextContent(fecha);
        Personas.appendChild(itemFecha);

        return documentAux;
    }
    
    public static Document leerXML(String path) {
        DocumentBuilder builder = null;
        DocumentBuilderFactory factory = null;
        Document document = null;
        File f = new File (path);

        try {
            factory = DocumentBuilderFactory.newInstance(); // INSTANCIAMOS UNA FABRICA DE CONSTRUCTORES DE DOCUMENTOS
            builder = factory.newDocumentBuilder(); // INSTANCIAMOS UN CONSTRUCTOR DE DOCUMENTOS
            document = builder.parse(f); // OBJETO QUE REPRESENTA TODO EL XML, PERO PARSEADO
            document.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
            document = null;
        }
        
        return document;
    }
    
    public static void escribirFichero(String path, Document document) {
        try {
            //Se escribe el contenido del XML en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(path));
            transformer.transform(source, result);

            System.out.println("Se terminó de escribir en el fichero");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//clase contador 

package bloquesnosincronizados;
/**
 *
 * @author Lucía Luna
 */
class Contador
{
    private int c = 0;  // Atributo Contador
    Contador (int c) { 
        this.c = c;
    }

    public void incrementa() {
        c++;
    }

    public void decrementa() {
        c--;
    }

    public int valor() {
        return c;
    }
    
    
} 

//clase HiloSumador
package bloquesnosincronizados;
/**
 *
 * @author Lucía Luna
 */
class HiloSumador implements Runnable 
   {
    private Contador contador;

    public HiloSumador (String nombre, Contador c){synchronized (this){
        
        setName(nombre);
        contador = c;
    }
    }

    public void run() {
        for(int j=0; j<1000;j++) 
        {
            contador.incrementa();
            
        }
        System.out.println(getName() + " - contador vale " + contador.valor());
    }

    private void setName(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private String getName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   } // Fin Class HiloSumador
