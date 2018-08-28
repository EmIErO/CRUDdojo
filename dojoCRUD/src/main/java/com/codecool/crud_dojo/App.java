package com.codecool.crud_dojo;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;


public class App {

    public StudentController getStudentControler() {
        return studentControler;
    }

    private StudentController studentControler = new StudentController();

    public static void main(String[] args) throws Exception {

        App app = new App();
        // create a server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // set routes
        server.createContext("/students", app.getStudentControler());
        server.setExecutor(null); // creates a default executor

        // start listening
        server.start();
    }

}
