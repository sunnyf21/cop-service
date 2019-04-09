/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2014 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package cop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import org.json.simple.JSONObject;

public class MessageServlet extends HttpServlet {

    public static final String MESSAGE = "HelloWorld";

    private String message="HelloWorld";

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        message = config.getInitParameter("message");
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        PrintWriter writer = resp.getWriter();
        JSONObject propertiesConfig = new JSONObject();
        propertiesConfig.put("TimeStamp",dateFormat.format(date));
        propertiesConfig.put("Namespace",System.getenv("MY_POD_NAMESPACE"));
        propertiesConfig.put("Pod",System.getenv("MY_POD"));
        propertiesConfig.put("DatabaseName",System.getenv("databasename"));
        propertiesConfig.put("DatabaseHost",System.getenv("databasehost"));
        propertiesConfig.put("DatabasePwd",System.getenv("dbpassword"));

//        writer.write("<p>Time Now is <em>" + dateFormat.format(date) + "</em></p>");
  //      writer.write("<p>NameSpace is <em>" +System.getenv("MY_POD_NAMESPACE") + "</em></p>");
    //    writer.write("<p>POD NAME IS <em>" + System.getenv("MY_POD") + "</em></p>");
      //  writer.write("<p>config properties are <em>" + System.getenv("CONFIG_PROPERTIES") + "</em></p>");

          writer.write("<p>" +  propertiesConfig.toJSONString() + "</p>");

        //Properties pros = System.getProperties();
       // writer.write("<p>NameSpace is <em>" + pros + "</em></p>");
        //writer.write(message);
        writer.close();

    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
