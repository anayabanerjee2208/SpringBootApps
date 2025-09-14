package net.learnwithfun.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
/*@RequestMapping is missing*/
 public class TestController {


    @GetMapping("/bookmark")
/*Good approach would be to use @PathVariable instead of @RequestParam*/
    /*Use List<Map<String, Object>> instead of List<Map<String, ?>>*/
    
public List<Map<String, ?>> get(@RequestParam(value = "id", defaultValue = "0") int id) {

        try {

            /*Password should not be hardcoded inside the code. Instead it should be added as config in the
            application.properties file. Add url, userid and password in application.properties file and create
            separate config files for different environments*/
            String myurl = "jdbc:mysql://localhost:3306/db";

            /*The DB connection is not closed. Create a Finally block and close the connection*/
            Connection conn = DriverManager.getConnection(myurl,"root","******");

            /*Statement interface is suitable for DDL queries like CREATE, ALTER, and DROP and is vulnerable to SQL injection.
            Use PreparedStatement as it is used to execute parameterized SQL queries. Moreover if we are using springboot,
            then best approach would be to use spring data jpa as it will handle all the underlying sql commands and connectivity
            and it will reduce code complexity size of the code.*/
             Statement stmt = conn.createStatement();


             ResultSet rs;



             rs = stmt.executeQuery("SELECT * from bookmarks where id = " + id);

             /*Use List<Map<String, Object>> instead of List<Map<String, ?>>*/

             List<Map<String, ?>> results = new ArrayList<>();

             while (rs.next()) {



                 ResultSetMetaData rsmd = rs.getMetaData();

                 Map<String, Object> row = new HashMap<>();

                 for (int i = 1; i <= rsmd.getColumnCount(); i++) {

                     row.put(rsmd.getColumnLabel(i), rs.getObject(i));

                     }


                 results.add(row);

                 }

             return results;

            /*Better approach is to handle the SQLException, IOException and other exceptions separately*/

        } catch (Exception e) {

             System.err.println(e.getMessage());

             }

/*Return null if there is an exception else return the list*/

         return null;

         }


/*For the update operation, use @PutMapping*/
         @PostMapping("/bookmark")
         /*Use multiple parameters without defining their names or count by just using a Map, @RequestParam Map<String,String> allParams.
         Best would be to use @RequestBody instead of @RequestPara */

        public void update(@RequestParam(value = "id", defaultValue = "0") int id, @RequestParam(value = "url", defaultValue = "") String url) {

         try {
              /*Password should not be hardcoded inside the code. Instead it should be added as config in the
            application.properties file. Add url, userid and password in application.properties file and create
            separate config files for different environments*/

             String myurl = "jdbc:mysql://localhost:3306/db";


             /*The DB connection is not closed. Create a Finally block and close the connection*/
             Connection conn = DriverManager.getConnection(myurl,"root","******");


            /*Statement interface is suitable for DDL queries like CREATE, ALTER, and DROP and is vulnerable to SQL injection.
            Use PreparedStatement as it is used to execute parameterized SQL queries. Moreover if we are using springboot,
            then best approach would be to use spring data jpa as it will handle all the underlying sql commands and connectivity*/


             Statement stmt = conn.createStatement();

             ResultSet rs;



            if (id == 0) {


                /*SimpleDateFormat is not thread safe, best way to use SimpleDateFormat is to use it with ThreadLocal*/
                 SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");

                 Date date = new Date(System.currentTimeMillis());

                /*Use PreparedStatement as it is used to execute parameterized SQL queries.*/
                 stmt.executeUpdate("INSERT INTO bookmarks (url, date) VALUES ('" + url + "', '" + formatter.format(date) + "')");

                 } else {

                 stmt.executeUpdate("UPDATE bookmarks set url = '" + url + "' where id = " + id);

                 }


            /*Better approach is to handle the SQLException, IOException and other exceptions separately*/
             } catch (Exception e) {

             System.err.println(e.getMessage());

             }

         }

 }