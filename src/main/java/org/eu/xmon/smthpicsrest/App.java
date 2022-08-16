package org.eu.xmon.smthpicsrest;

import io.jooby.Jooby;
import org.eu.xmon.smthpicsrest.controler.UserControler;
import org.eu.xmon.smthpicsrest.database.DbConnect;

public class App extends Jooby {

  {
    //mvc(new UserControler());
    before(ctx -> {
      ctx.setResponseHeader("Server", "Created by Xmon - Smth.pics");
    });
    mount(new UserControler());
  }

  public static void main(final String[] args) {
    final DbConnect dbConnect = new DbConnect();
    dbConnect.connect();

    runApp(args, App::new);
  }

}
