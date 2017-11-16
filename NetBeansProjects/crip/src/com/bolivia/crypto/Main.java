package com.bolivia.crypto;
import com.bolivia.sqlite.SQLite;

public class Main {

    public static void main(String[] args) {

        SQLite sqlite = new SQLite();
        sqlite.addKey("123456");
        //Nuevos registros
        sqlite.insert("nombres", " Nombre, Apellido ", " '"+sqlite.encrypt("Benito")+"','"+sqlite.encrypt("Camela")+"' ");
        sqlite.insert("nombres", " Nombre, Apellido ", " '"+sqlite.encrypt("Lola")+"','"+sqlite.encrypt("Mento")+"' ");
        sqlite.insert("nombres", " Nombre, Apellido ", " '"+sqlite.encrypt("Débora")+"','"+sqlite.encrypt("Dora")+"' ");
        sqlite.insert("nombres", " Nombre, Apellido ", " '"+sqlite.encrypt("Encarna")+"','"+sqlite.encrypt("Vales")+"' ");
        sqlite.insert("nombres", " Nombre, Apellido ", " '"+sqlite.encrypt("Francisco")+"','"+sqlite.encrypt("Jones")+"' ");
        sqlite.insert("nombres", " Nombre, Apellido ", " '"+sqlite.encrypt("Andrés")+"','"+sqlite.encrypt("Trozado")+"' ");

        //Se imprimen la tabla de nombres
        System.out.println( sqlite.printTable() );        
    }
}