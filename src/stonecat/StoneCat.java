/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonecat;

import Controller.ControllerRequisicao;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Giancarlo
 */
public class StoneCat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(80);
        server.setReuseAddress(true);
        ControllerRequisicao controller = new ControllerRequisicao();
        
        
        while (true) {
            System.out.println("Aguardando conexao...");
            try (Socket conn = server.accept();) {
                System.out.println("Conectado com: " + conn.getInetAddress().getHostAddress());
                conn.setReuseAddress(true);
                
                controller.setSocket(conn);
                if (!conn.isInputShutdown()) {
                    try {
                        controller.processaRequisicao();
                    }
                    catch(Exception e) {
                       
                    }
                }
                conn.close();   
            }
        }
    }

}
