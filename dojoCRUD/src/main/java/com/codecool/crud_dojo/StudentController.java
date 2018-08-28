package com.codecool.crud_dojo;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

public class StudentController implements HttpHandler {

    private List<Student> students;

    public StudentController() {
        this.students = new ArrayList<Student>();
        this.students.add(new Student("aa", "bb", 35));
        this.students.add(new Student("bbb", "ccc", 25));
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        System.out.println(httpExchange.getRequestMethod());
        System.out.println(httpExchange.getRequestURI());

        String response = "";
        String method = httpExchange.getRequestMethod();
        String URI = httpExchange.getRequestURI().toString();

        if (parseUri(URI).equals("students") && method.equals("GET")) {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("index.twig");
            JtwigModel model = JtwigModel.newModel();

            model.with("students", this.students);

            response = template.render(model);
        }

        if (parseUri(URI).equals("add") && method.equals("GET")) {
            response = getFileWithUtil("add.html");
        }

        if (parseUri(URI).equals("add") && method.equals("POST")) {
            
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            System.out.println(formData);
            Map inputs = parseFormData(formData);
            add();
            response = getFileWithUtil("add.html");
        }

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }

    private void index() {}

    private void add(Student student) {
        this.students.add(student);
    }

    private Student edit(int ID) {
        return this.students.get(ID);
    }

    private void delete(int ID) {
        this.students.remove(ID);
    }

    private String getFileWithUtil(String fileName) {

        String result = "";

        ClassLoader classLoader = getClass().getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private String parseUri(String URI) {
        String[] parts = URI.split("/");

        for (int i=0; i< parts.length; i++) {
            System.out.println(i + " " + parts[i]);
        }

        List<String> list = Arrays.asList("add", "edit", "delete");

        if (parts.length == 2 && parts[1].equals("students")) {
            return parts[1];
        }

        if(parts[2].equals("add")) {
            return parts[2];
        }

        if(parts[2].equals("edit")) {
            return parts[2] + " ";
        }

        if(parts[2].equals("delete")) {
            return parts[2] + " " + parts[3];
        }
        return "";
    }
}
