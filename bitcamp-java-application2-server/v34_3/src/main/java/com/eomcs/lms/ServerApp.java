// v34_3 : Runnable 인터페이스를 사용하여 간접적으로 스레드를 실행하기.
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import com.eomcs.lms.observer.ServletContextListener;
import com.eomcs.lms.servlet.Servlet;

public class ServerApp {

  public static boolean isStoping = false;

  ArrayList<ServletContextListener> listeners = new ArrayList<>();

  // 서버가 실행되는 동안 공유할 객체를 보관하는 저장소를 준비한다.
  HashMap<String, Object> servletContext = new HashMap<>();

  int port;

  public ServerApp(int port) {
    this.port = port;
  }

  public void execute() {
    System.out.println("[수업관리시스템 서버 애플리케이션]");

    try (ServerSocket serverSocket = new ServerSocket(this.port)) {
      System.out.println("서버 시작!");

      // 서버가 시작되면 보고를 받을 관찰자(observer)에게 보고한다.
      for (ServletContextListener listener : listeners) {
        listener.contextInitialized(servletContext);
      }

      while (true) {

        System.out.println("클라이언트 요청을 기다리는 중...");
        // 클라이언트 요청이 들어오면 클라이언트와 통실할 때 사용할 소켓을 생성한다.
        Socket socket = serverSocket.accept();

        new Thread(new RequestHandler(socket)).start();

        // Thread t = new RequestHandler(socket) {
        // @Override
        // public void run() {
        //
        // }
        // };

        // t.start();

        // new RequestHandler(socket).start();
        if (isStoping)
          break;
      } // while
      // 서버가 종료될 때 관찰자(observer)에게 보고한다.
      for (ServletContextListener listener : listeners) {
        listener.contextDestroyed(servletContext);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("서버 종료!");
  }


  // 서버가 시작하거나 종료할 때 보고를 받을 객체를 등록하는 메서드
  // => 즉 서블릿을 실행하는데 필요한 환경을 준비시키는 객체를 등록한다.
  //
  public void addServletContextListener(ServletContextListener listener) {
    listeners.add(listener);
  }

  private Servlet findServlet(String command) {
    // keySet() : return a set view of the keys contained in this map
    Set<String> keys = servletContext.keySet();

    // 명령어에 포함된 키를 찾아서, 해당 키로 저장된 서블릿을 찾는다.
    // => 명령(/board/list) : 키(/board/)
    for (String key : keys) {
      if (command.startsWith(key)) {
        return (Servlet) servletContext.get(key);
      }
    }
    // => 명령(/files/list) : key (?)
    return null;
  }

  // Thread를 상속 받아 직접 스레드 역할ㅇㄹ 하는 대신에
  // Thread 통해 실행할 코드를 실행한다.
  private class RequestHandler implements Runnable {

    protected Socket socket;

    // 클라이언트가 접속을 하면 그 소켓 정보를 가지고 이 클래스의 인스턴스를 생성한다.
    // => 그런 후 스레드를 분리하여 실행시키면, run()이 실행된다.
    public RequestHandler(Socket socket) {
      this.socket = socket;
    }

    // 별도의 실행 흐름[실; 스레드]은 run() 메서드에 작성한다.
    @Override
    public void run() {
      // 여기에서 클라이언트의 요청을 처리한다.
      // main thread와는 분리된 실행 흐름(thread)이기 때문에
      // 여기서 실행이 지체되더라도 main thread의 흐름에는 영향을 끼치지 않는다.

      // 지금 Socket을 왜 임시변수로 했냐면 try블록을 나갈때 closer가 자동 호출되도록. this.socket한거임
      // 임시변수(this.socket)담을 필요가 없는데 그럼에도 불구하고 하는이유 -> 트라이 블록을 나갈때 socket에 대해서는
      // close를 자동으로 하지않음. 그래서 인스턴스값을 변수값에 저장시켜야지 자동으로 close 함.
      try {
        // try문 안에 try문 둔 이유 -> 자동 close 하려고
        try (Socket clientSocket = this.socket;
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

          System.out.println("클라이언트와 연결되었음.");

          // 클라이언트가 보낸 명령을 읽는다.
          String command = in.readUTF();
          System.out.println(command + " 요청 처리중...");

          Servlet servlet = null;

          if (command.equals("serverstop")) {
            isStoping = true;
            // ServerApp.this.isStoping = true;
            // ServerApp.isStoping = true;
            return;
          } else if ((servlet = findServlet(command)) != null) {
            servlet.service(command, in, out);
          } else {
            out.writeUTF("fail");
            out.writeUTF("지원하지 않는 명령입니다.");
          }
          out.flush();
          System.out.println("클라이언트에게 응답 완료!");
        }
      } catch (Exception e) {
        System.out.println("클라이언트와의 통신 오류!" + e.getMessage());
      }
      System.out.println("클라이언트와 연결을 끊었음.");
    }

  }

  public static void main(String[] args) {

    ServerApp server = new ServerApp(8888);

    // 서버의 시작과 종료 상태를 보고 받을 객체를 등록한다.
    // => 보고를 받는 객체는 ServletContextListener 규칙에 따라 만든 클래스여야 한다.
    server.addServletContextListener(new AppInitListener());

    server.execute();
  }
}


