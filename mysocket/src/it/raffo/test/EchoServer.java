package it.raffo.test;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo") 
public class EchoServer {
    /**
     * @OnOpen questo metodo ci permette di intercettare la creazione di una nuova sessione.
     * La classe session permette di inviare messaggi ai client connessi.
     * Nel metodo onOpen, faremo sapere all'utente che le operazioni di handskake 
     * sono state completate con successo ed � quindi possibile iniziare le comunicazioni.
     */
    @OnOpen
    public void onOpen(Session session){
        System.out.println(session.getId() + " ha aperto una connessione"); 
        try {
            session.getBasicRemote().sendText("Connessione Stabilita!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Quando un client invia un messaggio al server questo metodo intercetter� tale messaggio
     * e compier� le azioni di conseguenza. In questo caso l'azione � rimandare una eco del messaggi indietro.
     */
    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println("Ricevuto messaggio da: " + session.getId() + ": " + message);
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
   /**
     * Metodo che intercetta la chiusura di una connessine da parte di un client
     * 
     * Nota: non si possono inviare messaggi al client da questo metodo
     */
    @OnClose
    public void onClose(Session session){
        System.out.println("Session " +session.getId()+" terminata");
    }
}