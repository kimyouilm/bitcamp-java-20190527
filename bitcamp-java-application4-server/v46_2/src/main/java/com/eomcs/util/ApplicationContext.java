package com.eomcs.util;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.handler.Command;

// 자바 객체를 자동 생성하여 관리하는 역할
// 1단계: App 클래스에서 객체 생성 코드를 분리하기
// 2단계: 특정 패키지의 클래스에 대해 인스턴스 생성하기
public class ApplicationContext {

  Map<String, Object> objPool = new LinkedHashMap<>();

  // 자동 생성할 타입(클래스 정보(객체타입)) 목록
  ArrayList<Class<?>> classes = new ArrayList<>();

  public ApplicationContext(String packageName) throws Exception {

    createSqlSessionFactory();
    // command가 사용함 (photoboard add 요론거)
    createTransactionManager();
    // Dao객체를 만들자.
    createDao();

    // 파라미터에 주어진 패키지를 뒤져서 Command 인터페이스를 구현한 클래스를 찾는다.
    // => 패키지의 경로를 알아낸다.
    String packagePath = packageName.replace(".", "/");
    // packagePath - 패키지에대한 경로를 준거임
    File fullPath = Resources.getResourceAsFile(packagePath);

    // => 찾은 클래스의 인스턴스를 생성한다.
    findCommandClass(fullPath, packageName);
    // 클래스에 담겨있는 정보를 갖고 createCommand()를 만듬
    createCommand();

    System.out.println("생성된 객체들: ");
    Set<String> keySet = objPool.keySet();
    // 내부적으로 자기가 갖고있는 컬렉션에 저장된것을 반복문으로!
    keySet.forEach(key -> {
      System.out.printf("%s : %s\n", key, objPool.get(key).getClass().getName());
    });
  }

  private void findCommandClass(File path, String packageName) {
    File[] files = path.listFiles(file -> {
      if (file.isDirectory())
        return true;

      // true = 결과에 포함해                                                                                  $ 포함 안된것
      if (file.getName().endsWith(".class") && file.getName().indexOf('$') == -1)
        return true;

      return false;
    });

    for (File f : files) {
      if (f.isDirectory()) {
        findCommandClass(f, packageName + "." + f.getName());
      } else {
        String className = String.format(
            "%s.%s", packageName, f.getName().replace(".class", ""));

        try {
          Class<?> clazz = Class.forName(className);
          // command interface를 구현한 놈임? && 추상클래스가 아닌 일반 클래스라면 -->
          if (isCommand(clazz) && !Modifier.isAbstract(clazz.getModifiers())) {
            // --> if문 안으로 okay!
            classes.add(clazz);
          }
        } catch (ClassNotFoundException e) {
          // 클래스를 로딩하다가 오류가 발생하면 무시한다.
        }
      }
    }
  }

  private boolean isCommand(Class<?> clazz) {
  //Command 인터페이스 구현체만 등록한다.
    Class<?>[] interfaces = clazz.getInterfaces();
    for (Class<?> c : interfaces) {
      if (c == Command.class) {
        return true;
      }
    }
    return false;
  }

  private void createCommand() {
    for (Class<?> clazz : classes) {
      // 기본 생성자가 있으면 그 생성자를 호출하여 인스턴스를 만든다.
      try {
        // command에 기본생성자 가져와서
        Constructor<?> defaultConstructor = clazz.getConstructor();
        // 리턴 받을때 형변환 해줘야해
        Command command = (Command) defaultConstructor.newInstance();
        objPool.put(command.getCommandName(), command);
        continue;
      } catch (Exception e) {
      }

      // 다른 생성자 꺼내기
      // 생성자가 2,3개는요!?? ==> 그건 담에 하자...
      Constructor<?> constructor = clazz.getConstructors()[0];

      try {
        // 생성자의 파라미터 정보로 값을 준비한다.
        Parameter[] params = constructor.getParameters();
        // 파라미터 정보 줄태니까 그 정보대로 값을만들어줘 메소드!
        // 만약 값이 없으면 그 객체는 안만듬 (재료가 있어야 만들지!)
        Object[] values = prepareParameterValues(params);

        // 만들어준다고 가정하고
        // 준비된 값을 가지고 생성자를 통해 인스턴스를 생성한다.
        // 파라미터가 2개면 2개 3개면 3개를 만들어줄것이다.
        // 그리고서 prepareParameterValues로 가보자
        Command command = (Command) constructor.newInstance(values);
        // 그리고 저장!
        objPool.put(command.getCommandName(), command);

      } catch (Exception e) {
      }
    }
  }

  private Object[] prepareParameterValues(Parameter[] params) {
    // 파라미터 갯수만큼 똑같은 레퍼런스 배열을 준비
    Object[] values = new Object[params.length];

    // 파라미터의 타입에 해당하는 값을 objPool에서 찾는다.
    for (int i = 0; i < params.length; i++) {
      // getClass하면 앙대 파라미터 클래스가 되버림
      // getType int냐 BoardDao냐 판별
      // 못찾으면 중단
      // 타입을 주면 객체 찾는것
      values[i] = getBean(params[i].getType());
    }
    return values;
  }

  private Object getBean(Class<?> type) {
    // 객체보관소의 값을 가져와서 iterator로 값을 꺼내줌
    Iterator<?> values = objPool.values().iterator();
    
    while (values.hasNext()) {
      Object value = values.next();

      // 풀에서 꺼낸 객체의 타입이 일치하는지 검사
      if (value.getClass() == type)
        return value;

      // 풀에서 꺼낸 객체의 인터페이스가 일치하는지 검사
      // 만약 못찾았어? 걱정마!
      // 인터페이스로 한번더 검사!
      Class<?>[] interfaces = value.getClass().getInterfaces();
      for (Class<?> c : interfaces) {
        if (c == type)
          return value;
      }
      // 이렇게 까지 못찾았어? 그러면 throw!
    }
    throw new RuntimeException("해당 타입의 빈을 찾을 수 없습니다.");
  }

  public Object getBean(String name) {
    Object obj = objPool.get(name);
    if (obj == null)
      throw new RuntimeException("해당 이름의 빈을 찾을 수 없습니다.");
    return obj;
  }

  public void addBean(String name, Object obj) {
    if (name == null || obj == null)
      return;

    objPool.put(name, obj);
  }

  private void createSqlSessionFactory() throws Exception {
    // Mybatis 객체 준비
    InputStream inputStream =
        Resources.getResourceAsStream("com/eomcs/lms/conf/mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory =
        new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder().build(inputStream));
    objPool.put("sqlSessionFactory", sqlSessionFactory);
  }

  private void createTransactionManager() throws Exception {
    PlatformTransactionManager txManager = new PlatformTransactionManager(
        // 기존에 objectPool에들어있는 sql~것을 command에서 써야하기때문에
        (SqlSessionFactory) objPool.get("sqlSessionFactory"));
    objPool.put("txManager", txManager);
  }

  private void createDao() throws Exception {
    // DAO 구현체 생성기를 준비한다.
    MybatisDaoFactory daoFactory = new MybatisDaoFactory(
        // objectPool에 들어있는 sqlSessionFactory를 들고와서 얘를 통해서
        (SqlSessionFactory) objPool.get("sqlSessionFactory"));

    // 얘를 만들려고
    // 데이터 처리 객체를 준비한다.
    //  board는 sqlSession을 써서 안만들어도됨.
//    objPool.put("boardDao", daoFactory.createDao(BoardDao.class));
    objPool.put("memberDao", daoFactory.createDao(MemberDao.class));
    objPool.put("lessonDao", daoFactory.createDao(LessonDao.class));
    objPool.put("photoBoardDao", daoFactory.createDao(PhotoBoardDao.class));
    objPool.put("photoFileDao", daoFactory.createDao(PhotoFileDao.class));
  }
}


