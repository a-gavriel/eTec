/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Algoritmos.Search.Binary_Search;
import Algoritmos.Search.InterpolationSearch;
import Algoritmos.Sorts.BubbleSort;
import Algoritmos.Sorts.InsertionSort;
import Algoritmos.Sorts.MergeSort;
import basicsOBJs.Item;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Gabriel
 */
public class ItemsDoubleLinkedList extends DoubleLinkedList<Item> {
    private final static String Direccion = "C:\\Users\\Gabriel\\Documents\\serverdata\\";
    private String searchstack = "";
    
    public String busquedaNombres(String id){
        searchstack = "";
        Node<Item> current = this.getHead();
        while (current!=null){
            if(current.getData().getNombre().contains(id)){
                searchstack+=current.getData().getNombre()+";"+current.getData().getCode()+">";
                current = current.getNext();
            }else{
                current = current.getNext();
            } 
        }
        return searchstack;
    }
    public String busquedaCodigo(int id){
        String x= String.valueOf(id);
        searchstack = "";
        Node<Item> current = this.getHead();
        while (current!=null){
            if(String.valueOf(current.getData().getCode()).contains(x)){
                searchstack+=current.getData().getNombre()+";"+current.getData().getCode()+">";
                current = current.getNext();
            }else{
                current = current.getNext();
            } 
        }
        return searchstack;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void saveItem()
    {
        Node n1 =this.getHead();
        saveItem(n1, "Items");
    }
    private void saveItem(Node<Item> n1,String FileName)
    {
         try {

            DocumentBuilderFactory dfc = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dfc.newDocumentBuilder();

            Document doc = db.newDocument();
            Element Mainelement = doc.createElement("Items");
            doc.appendChild(Mainelement);
                
            int munId = 1;
            while (n1 != null) {
                Element element = doc.createElement("Item");
                Mainelement.appendChild(element);
                
                
                Attr attr = doc.createAttribute("id");
                attr.setValue(String.valueOf(munId));
                element.setAttributeNode(attr);
                munId++;    
                
                Element nombre = doc.createElement("nombre");
                nombre.appendChild(doc.createTextNode(n1.getData().getNombre()));
                element.appendChild(nombre);

                Element imagen = doc.createElement("imagen");
                imagen.appendChild(doc.createTextNode("1"));
                element.appendChild(imagen);

                Element precio = doc.createElement("precio");
                precio.appendChild(doc.createTextNode(String.valueOf(n1.getData().getPrecio())));
                element.appendChild(precio);

                Element stock = doc.createElement("stock");
                stock.appendChild(doc.createTextNode("1"));
                element.appendChild(stock);

                n1 = n1.getNext();
            }

            TransformerFactory tfac = TransformerFactory.newInstance();
            Transformer tra = tfac.newTransformer();

            DOMSource source = new DOMSource(doc);
            StreamResult str = new StreamResult(new File(Direccion+FileName+".xml"));

            tra.transform(source, str);

        } catch (ParserConfigurationException | TransformerException ex) {
            Logger.getLogger(ItemsDoubleLinkedList.class.getName()).log(Level.SEVERE, null, ex);
        
        }}
        
    
    public static ItemsDoubleLinkedList loadItems(){

        
              try {
            File xmlFile= new File( Direccion+"items.xml");
            DocumentBuilderFactory dfc = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dfc.newDocumentBuilder();
            Document doc = db.parse(xmlFile);
            
            doc.getDocumentElement().normalize();
            
            NodeList list = doc.getElementsByTagName("Item");
            
            ItemsDoubleLinkedList lispro =new ItemsDoubleLinkedList();
            
             for (int temp = 0; temp < list.getLength();temp++){
                 org.w3c.dom.Node noNode = list.item(temp);
                 
                 if(noNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE){
                 Element eElement = (Element) noNode;
                    String nombre = eElement.getElementsByTagName("nombre").item(0).getTextContent();
                    String imagen = eElement.getElementsByTagName("imagen").item(0).getTextContent();
                    String precio = eElement.getElementsByTagName("precio").item(0).getTextContent();
                    String stock = eElement.getElementsByTagName("stock").item(0).getTextContent();
                    double value;
                    int cantidad;
                    System.out.println(stock);
                     if (precio.equals("0.0") || precio.equals("") || precio.equals("0")){
                        value = 0;
                    }
                    else{
                        value = Double.parseDouble(precio);
                    }
                     if (stock.equals("") || stock.equals("0")){
                        cantidad = 0;
                    }
                    else{
                        cantidad = Integer.parseInt(stock);
                    }
                    Item iTemp =new Item(nombre,(int)value,cantidad);
                    lispro.addLast(iTemp);
                 }
             }
             
            
            
            
            
            
            
            return lispro;
            
            
            
            
            
            
            
            
            
            
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(ItemsDoubleLinkedList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
         
        
    }
    
    
    public static int Search(String searchAlg,String porBuscar,ItemsDoubleLinkedList l1 ){
        if(searchAlg.compareTo("binaria")>=0){
            
            int buscado = Binary_Search.busquedaBinariaDoubleLinkedList(l1, porBuscar);
            
        return buscado;
            
        } 
        return -1;
    }
    
    public static ItemsDoubleLinkedList Sort(String AlgOrden,ItemsDoubleLinkedList l1){
        
        if(AlgOrden.compareTo("Bubble")>=0){
        BubbleSort.bubbleDoubleLinkedList(l1);
        }
        else if(AlgOrden.compareTo("Insertion")>=0){
            InsertionSort.insertionDoubleLinkedList(l1);
        }
        else if(AlgOrden.compareTo("Merge")>=0){
        MergeSort.MergeSortDoubleLinkedList(l1);
        }
        else if(AlgOrden.compareTo("Quick")>=0){}
        else if(AlgOrden.compareTo("Radix")>=0){}
        else if(AlgOrden.compareTo("Selection")>=0){}
        else if(AlgOrden.compareTo("Shell")>=0){}
        
        return l1;
    }
    
}
