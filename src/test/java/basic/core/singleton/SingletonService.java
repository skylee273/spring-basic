package basic.core.singleton;

public class SingletonService {

    //1. static 영역에 객체를 딱 1개만 생성해준다.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService(){

    }

    public void login(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
