# 스프링 핵심 원리 - 기본편 정리 v2021-01-08
스프링 기초

## 전체 목차
[1.객체 지향 설계와 스프링](#객체-지향-설계와-스프링)

[2.예제 만들기](#예제-만들기)

[3.객체 지향 원리 적용](#객체-지향-원리-적용)

[4.스프링 컨테이너와 스프링 빈](#스프링-컨테이너와-스프링-빈)

[5.싱글톤 컨테이너](#싱글톤-컨테이너)

[6.컴포넌트 스캔](#컴포넌트-스캔)

[7.의존관계 자동 주입](#의존관계-자동-주입)

[8.빈 생명주기 콜백](#빈-생명주기-콜백)

[9.빈 스코프](#빈-스코프)

## 객체 지향 설계와 스프링

### 스프링 프레임워크
- **핵심 기술** : 스프링 DI 컨테이너, AOP, 이벤트, 기타
- **웹 기술** : 스프링 MVC, 스프링 WebFlux
- **데이터 접급기술** : 트랜잭션, JDBC, ORM 지원, XML 지원
- **기술 통합** : 캐시, 이메일, 원격접근, 스케줄링
- **테스트**: 스프링 기반 테스트 지원
- **언어** : 코틀린, 그루비
- 최근에는 스프링 부트를 통해서 스프링 프레임워크의 기술들을 편리하게 사용


### 스프링 부트
- **스프링을 편리하게 사용할 수 있도록 지원, 최근에는 기본으로 사용**
- 단독으로 실행할 수 있는 스프링 애플리케이션을 쉽게 생성
- Tomcat 웹 서버를 내장해서 별도의 웹 서버를 설치하지 않아도 됨
- 손쉬운 빌드 구성을 위한 starter 종속성 제공
- 스프링과 3rd parth(외부) 라이브러리 자동 구성
- 메트릭, 상태확인, 외부 구성 같은 프로덕션 준비 기능 제공

### 스프링 핵심
- 스프링은 자바 언어 기반의 프레임워크 
- 자바 언어의 가장 큰 특징 - **객체 지향 언어**
- 스프링은 객체 지향 언어가 가진 강력한 특징을 살려내는 프레임워크
- 스프링은 **좋은 객체 지향** 애플리케이션을 개발할 수 있게 도와주는 프레임워크 

### 다형성 실세계
 
![운전자-자동차](./assets/다형성_1.png)

![로미오-줄리엣](./assets/다형성_2.png)

### 역할과 구현을 분리
- **역할**과 **구현**으로 구분하면 세상이 단순해지고, **유연**해지며 **변경**도 편리해진다.
- **장점**
    + 클라이언트는 대상의 역할(인터페이스)만 알면 된다.
    + 클라이언트는 구현 대상의 내부 구조를 몰라도 된다.
    + 클라이언트는 구현 대상의 내부 구조가 변경되어도 영향을 받지 않는다.
    + 클라이언트는 구현 대상 자체를 변경해도 영향을 받지 않는다. 

![역할과 구현 분리](./assets/다형성_3.png)    

### 다형성의 본질
- **클라이언트 변경하지 않고,서버의 구현 기능을 유연하게 변경할 수 있다.**


### SOLID
클린코드로 유명한 로버트 마틴이 좋은 객체 지향 설계의 5가지 원칙을 정리

- SRP: 단일 책임 원칙 (single responsibility principle)
  + 한 클래스는 하나의 책임만 가져야 한다.
  + **요한 기준은 변경**이다. 변경이 있을 때 파급 효과가 적으면 단일 책임 원칙을 잘 따른 것 
- OCP : 개방-폐쇄 원칙(Open/closed principle)
    + 소프트웨어 요소는 **확장에는 열려** 있느나 변경에는 닫혀 있어야 한다
    + 다형성을 활용해보자.
    + 구현 객체를 변경하려면 클라이언트 코드를 변경해야 한다.
    + 분명 다형성을 사용했지만 OCP 원칙을 지킬 수 없다.
- LSP : 리스코프 치환 원칙 (Liskov substitution principle)
    + 프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다.
- ISP: 인터페이스 분리 원칙 (Interface segregation principle)
    + 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.
- DIP : 의존관계 역전 원칙 (Dependency inversion principle)
    + 프로그래머는 "추상화에 의존해야지, 구체화에 의존하면 안된다" 의존성 주입은 이 원칙을 따르는 방법 중 하나이다.
    
## 예제 만들기

### 목차
[2.스프링 핵심 원리 이해1 -예제만들기 - 비즈니스 요구사항과 설계](#비즈니스-요구사항과-설계)

[2.스프링 핵심 원리 이해1 -예제만들기 - 회원 도메인 설계](#회원-도메인-설계)

[2.스프링 핵심 원리 이해1 -예제만들기 - 회원 도메인 개발](#회원-도메인-개발)

[2.스프링 핵심 원리 이해1 -예제만들기 - 회원 도메인 실행과 테스트](#회원-도메인-실행과-테스트)

[2.스프링 핵심 원리 이해1 -예제만들기 - 주문과 할인 도메인 설계](#주문과-할인-도메인-설계)

[2.스프링 핵심 원리 이해1 -예제만들기 - 주문과 할인 도메인 개발](#주문과-할인-도메인-개발)

[2.스프링 핵심 원리 이해1 -예제만들기 - 주문과 할인 도메인 실행과 테스트](#주문과-할인-도메인-실행과-테스트)

### 비즈니스 요구사항과 설계
- 회원
    + 회원을 가입하고 조회할 수 있다.
    + 회원은 일반과 VIP 두 가지 등급이 있다.
    + 회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다. (미확정)
- 주문과 할인 정책
    + 회원은 상품을 주문할 수 있다.
    + 회원 등급에 따라 할인 정책을 적용할 수 있다.
    + 할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라. (나중에 변경 될 수 있 다.)
    + 할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 
      미루고 싶다. 최악의 경우 할인을 적용하지 않을 수 도 있다. (미확정)


### 회원 도메인 설계
- 회원 도메인 요구사항
    + 회원을 가입하고 조회할 수 있다,.
    + 회원은 일반과 VIP 두 가지 등급이 있다.
    + 회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다.(미확정)
  
**회원 도메인 협력 관계**

![회원 도에인](./assets/회원_도메인.png)

**회원 클래스 다이어그램**

![회원 클래스 다이어그램](./assets/회원_클래스.png)


### 회원 도메인 개발

**회원 엔티티**

**회원 등급**

```java
public enum Grade {
    BASIC,
    VIP
}
```

**회원 엔티티**
```java
public class Member {
    private Long id;
    private String name;
    private Grade grade;

    public Member(Long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
```

**회원 저장소**

**회원 저장소 인터페이스**
```java
public interface MemberRepository {
    void save(Member member);
    Member findById(Long memberId);
}
```

**회원 저장소 구현체**
```java
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }

}
```
**회원 서비스** 

**회원 서비스 인터페이스**
```java
public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
```

**회원 서비스 구현체**
```java
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
```
### 회원 도메인 실행과 테스트
**회원 도메인 테스트**

```java
public class MemberServiceTest {

    private final MemberService memberService = new MemberServiceImpl();

    @Test
    void join(){
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        
        //then
        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
```

**회원 도메인 설계의 문제점**

- 이 코드의 설계상 문제점은 무엇일까?
- 다른 저장소로 변경할 때 OCP 원칙을 잘 준수할까요? 
- DIP를 잘 지키고 있을까?
  
- **의존관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점이 있음**

### 주문과 할인 도메인 설계
- 주문과 할인 정책
  + 회원은 상품을 주문할 수 있다. 
  + 회원 등급에 따라 할인 정책을 적용할 수 있다. 
  + 할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라. (나중에 변경 될 수 있다.)
  + 할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루고 싶다.
  최악의 경우 할인을 적용하지 않을 수 도 있다. (미확정)
    
**주문 도메인 협력,역할,책임**

![주문_도메인_협력_역할_책임](./assets/주문_도메인_협력_책임.png)


**1. 주문생성** : 클라이언트는 주문 서비스에 주문 생성을 요청한다.

**2. 회원조회** : 할인을 위해서는 회원 등급이 필요하다. 그래서 주문 서비스는 회원 저장소에서 회원을 조회한다.

**3. 할인적용** : 주문 서비스는 회원 등급에 따른 할인 여부를 할인 정책에 위임한다.

**4. 주문 결과 반환** : 주문 서비스는 할인 결과를 포함한 주문 결과를 반환한다.

**주문 도메인 전체**

![주문_도메인_전체](./assets/주문_도메인.png)

역할과 구현을 분리해서 자유롭게 구현 객체를 조립할 수 있게 설계 했다. 덕분에 회원 저장소는 물론이고, 할인 정책도 유연하게 변경할 수 있다.

**주문 도메인 클래스 다이어그램**
![주문 도메인 클래스 다이어그램](./assets/다이어그램_1.png)

회원을 메모리에서 조회하고, 정액 할인 정책(고정 금액)을 지원해도 주문 서비스를 변경하지 않아도 된다.

회원을 메모리가 아닌 실제 DB에서 조회하고, 정률 할인 정책(주문 금액에 따라 %할인)을 지원해도 주문서비스를
변경하지 않아도 된다. 협력 관계를 그대로 재사용 할 수 있다. 

### 주문과 할인 도메인 개발
**할인 정책 인터페이스**
```java
public interface DiscountPolicy {

    /**
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
```

**정액 할인 정책 구현체**
```java
public class FixDiscountPolicy implements DiscountPolicy{
    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP)
            return discountFixAmount;
        else
            return 0;
    }
}
```

**주문 엔티티**
```java
public class Order {
    private Long memberId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;

    public Order(Long memberId, String itemName, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
```
**주문 서비스 인터페이스**
```java
public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);

}
```
**주문 서비스 구현체**
```java
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}

```
주문 생성 요청이 오면, 회원 정보를 조회하고, 할인 정책을 적용한 다음 주문 객체를 생성해서 반환한다.
**메모리 회원 리포지토리와, 고정 금액 할인 정책을 구현체로 생성한다.** ```new MemoryMemberRepository()```, ```new FixDiscountPolicy()```

- 즉 DIP 원칙을 위배하고 있다. 추상화에 의존하고 있지만 구체화에 도 의존하고 있기 때문이다.
- OCP 원칙 또한 위배하고 있다. 만약 정책이 변경되어 ```memoryMemberRepository``` -> ```MysqlRepository```로 변경된다면
파급 효과가 큼

### 주문과 할인 도메인 실행과 테스트
**주문과 할인 정책 실행**
```java
public class OrderServiceTest {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder(){
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}

```

## 객체 지향 원리 적용

### 목차
[3.스프링 핵심 원리 이해2 - 객체 지향 원리 적용 - 새로운 할인 정책 개발](#새로운-할인-정책-개발)

[3.스프링 핵심 원리 이해2 - 객체 지향 원리 적용 - 새로운 할인 정책 적용과 문제점](#새로운-할인-정책-적용과-문제점)

[3.스프링 핵심 원리 이해2 - 객체 지향 원리 적용 - 관심사의 분리](#관심사의-분리)

[3.스프링 핵심 원리 이해2 - 객체 지향 원리 적용 - AppConfig 리펙터링](#AppConfig-리펙터링)

[3.스프링 핵심 원리 이해2 - 객체 지향 원리 적용 - 좋은 객체 지향 설계의 5가지 원칙의 적용](#좋은-객체-지향-설계의-5가지-원칙-적용)

[3.스프링 핵심 원리 이해2 - 객체 지향 원리 적용 - IoC,DI,그리고 컨테이너](#IoC,DI,그리고-컨테이너)

[3.스프링 핵심 원리 이해2 - 객체 지향 원리 적용 - 스프링으로 전환하기](#스프링으로-전환하기)

### 새로운 할인 정책 개발

**새로운 할인 정책을 확장해보자**
- **악덕 기획자** : 서비스 오픈 직전에 할인 정책을 지금처럼 고정 금액 할인이 아니라 좀 더 합리적인 주문 금액
당 할인하는 정률%할인으로 변경하고 싶어요. 예를 들어서 기존 정책은 VIP가 10000원을 주문하든 20000원을 주문하든 항상 1000원을 할인 했는데,
이번에 새로나온 정책은 10%로 지정해두면 고객이 10000원 주문시 1000원을 할인해주고, 20000원 주문시에 2000원을 할인해주는 거에요!

- **순진 개발자** : 제가 처음부터 고정 금액 할인은 아니라고 했잖아요.
- **악덕 기획자** : 애자일 소프트웨어 개발 선언 몰라요? "계획을 따르기보단 변화에 대응하기를"
- **순진 개발자** : ...(하지만) 난 유연한 설계가 가능하도록 객체지향 설계 원칙을 준수했지.

**RateDiscountPolicy 추가**
![할인정책 추가](./assets/RateDiscountPolicy_add.png)

**RateDiscountPolicy 코드 추가**
```java
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        }else{
            return 0;
        }
    }
}
```
**테스트 작성**
```java
class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP 10% 할인이 적용되어야 한다.")
    void vip_o(){
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP 아니면 할인이 적용되지 않아야 한다.")
    void vip_x(){
        //given
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(0);
    }
}
```

### 새로운 할인 정책 적용과 문제점
방금 추가한 할인 정책을 적용해보자.

할인 정책을 애플리케이션에 적용해보자

할인정책을 변경하려면 클라이언트인 ```OrderServiceImpl``` 코드를 고쳐야 한다.
```java
public class OrderServiceImpl implements OrderService {
// private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
 private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
}
```

**문제점 발견**
- 우리는 역할과 구현을 충실히 분리했다 → OK
- 다형성도 활용하고, 인터페이스와 구현 객체를 분리했다. → OK
- OCP, DIP 같은 객체지향 설계 원칙을 충실히 준수했다. 
  + 그렇게 보이지만 사실 아니다.
- DIP : 주문서비스 클라이언트 ```OrderServiceImpl```는  ```DiscountPolicy``` 인터페이스에 의존하면서 
DIP를 지킨 것 같은데?
  + → 클래스 의존관계를 분석해 보자. 추상(인터페이스) 뿐만 아니라 **구체(구현) 클래스에도 의존**하고 있다.
    + 추상(인터페이스) 의존 : ```DiscountPolicy``` 
    + 구체(구현)클래스 : ```FixDiscountPolicy```, ```RateDiscountPolicy```
- OCP : 변경하지 않고 확장할 수 있다고 했는데!!
  + **→ 지금 코드는 기능을 확장해서 변경하면, 클라이언트 코드에 영향을 준다!** 따라서 **OCP를 위반**한다.
  
**어떻게 문제를 해결할 수 있을까?**
- 클라이언트 코드인 ```OrderServiceImpl```은 ```DiscountPolicy```의 인터페이스 뿐만 아니라 구체 클래스도
함께 의존한다.
- 그래서 구체 클래스를 변경할 때 클라이언트 코드도 함께 변경해야 한다. 
- **DIP 위반 →** 추상에만 의존하도록 변경(인터페이스에만 의존)
- DIP를 위반하지 않도록 인터페이스에만 의존하도록 의존관계를 변경하면 된다. 

**인터페이스에만 의존하도록 설계를 변경하자**
``` private DiscountPolicy discountPolicy;```

- 인터페이스에만 의존하도록 설계와 코드를 변경했다.
- **그런데 구현체가 없는데 어떻게 코드를 실행할 수 있을까?**
- 실제 실행을 해보면 NPE(Null Pointer exception)가 발생한다.

**해결방안**
- 이 문제를 해결하려면 누군가가 클라이언트인 ```OrderServiceImpl```에 ```DiscountPolicy```의 구현객체를 
대신 생성하고 주입해주어야 한다.
  
### 관심사의 분리
- 애플리케이션을 하나의 공연이라고 생각해보자. 각각의 인터페이스 배역(배우 역할)이라 생각하자. 그런데!
실제 배역에 맞는 배우를 선택하는 것은 누가 하는가?
- 로미오와 줄리엣 공연을 하면 로미오 역할을 누가 할지 줄리엣 역할을 누가 할지는 배우들이 정하는게 아니다.
이전 코드는 마치 로미오 역할(인터페이스)을 하는 레오나르도 디카프리오(구현체,배우)가 줄리엣 역할
  (인터페이스)을 하는 여자 주인공(구현체, 배우)을 직접 초빙하는 것과 같다. 디카프리오는 공연도 해야하고 동시에
  여자 주인공도 공연에 직접 초빙해야 하는 **다양한 책임**을가지고 있다.
  
**관심사를 분리하자**
- 배우는 본인의 역할인 배역을 수행하는 것에만 집중해야 한다.
- 디카프리오는 어떤 여자 주인공이 선택되더라도 똑같이 공연을 할 수 있어야 한다. 
- 공연을 구성하고, 담당 배우를 섭외하고, 역할에 맞는 배우를 지정하는 책임을 담당하는 별도의 **공연 기획자**가
나올 시점이다.
- 공연 기획자를 만들고, 배우와 공연 기획자의 책임을 확실히 분리하자.

**AppConfig 등장**
- 애플리케이션의 전체 동작 방식을 구성(config)하기 위해, **구현 객체를 생성**하고, 연결하는 책임을 가지는
별도의 설정 클래스를 만들자
 
**App Config**
```java
public class AppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }
    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}
```
- AppConfig는 애플리케이션의 실제 동작에 필요한 **구현 객체를 생성**한다.
  + ```MemberServiceImpl```
  + ```MemoryMemberRepository```
  + ```OrderServiceImpl```
  + ```FixDiscountPolict```
  
- AppConfig 생성한 객체 인스턴스의 참조(래퍼런스)를 **생성자를 통해서 주입(연결)** 해준다.
  + ```MemberServiceImpl``` → ```MemoryMemberRepository```
  + ```OrderServiceImpl``` → ```MemoryMemberRepository```, ```FixDiscountPolicy```
  
> 참고 : 지금은 각 클래스에 생성자가 없어서 컴파일 오류가 발생한다. 바로 다음에 코드에서 생성자를 만든다.

**MemberServiceImpl - 생성자 주입**
```java
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
```
- 설계 변경으로 ```MemberServiceImpl```은 ```MemoryMemberRepository```를 의존하지 않는다.
- 단지 ```MemberRepository``` 인터페이스만 의존한다. 
- ```MemberServiceImpl```입장에서 생성자를 통해 구현 객체가 들어올지(주입될지)는 알 수 없다.
- ```MemberServiceImpl```의 생성자를 통해서 어떤 구현객체를 주입할지는 오직 외부```AppConfig```에서 결정 된다.
- ```MemberServiceImpl```은 이제부터 **의존관계에 대한 고민은 외부**에 맡기고 **실행에만 집중**하면 된다.

**그림 -클래스 다이어그램**
![클래스 다이어그램](./assets/ClassDiagram_1.png)

- 객체의 생성과 연결은 ```AppConfig```가 담당한다.
- **DIP 완성 :** ```MemberServiceImpl```은 ```MemberRepository```인 추상에만 의존하면 된다. 
이제 구체 클래스를 몰라도 된다.
- **관심사의 분리 :** 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다.

**그림-회원 객체 인스턴스 다이어그램**

![회원 객체 인스턴스](./assets/회원_객체_인스턴스.png)

- ```appConfig```객체는```memoryMemberRepository```객체를 생성하고 그 참조값을 ```memberServiceImpl```을
생성하면서 생성자로 전달한다.
  
- 클라이언트인```memberServiceImpl```입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 
DI(Dependency Injection) 우리말로 의존관계 주입 또는 의존성 주입이라고 한다.
  
**OrderServiceImpl-생성자 주입**
```java
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
```
- 설계 변경으로 ```OrderServiceImpl```은```FixDiscountPolicy```를 의존하지 않는다!
- 단지```DiscountPolicy```인터페이스만 의존한다.
- ```OrderServiceImpl```입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)알 수 없다.
- ```OrderServiceImple```의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(```AppConfig```)에서 결정한다.
- ```OrderServiceImpl```은 이제부터 실행에만 집중하면 된다.
- ```OrderServiceImpl```에는```MemoryMemberRepository```,```FixDiscountPolicy```객체의 의존관계가 주입된다.

**테스트 코드 오류 수정**
```java
class Test{
  @BeforeEach
  public void beforeEach(){
    AppConfig appConfig = new AppConfig();
    memberService = appConfig.memberService();
    orderService = appConfig.orderService();
  }
}
    
```

### AppConfig 리펙터링
현재 AppConfig를 보면 **중복**이 있고, **역할**에 따른 **구현**이 잘 안보인다.

**기대하는 그림**

![기대하는 그림](./assets/ClassDiagram_2.png)

**리펙터링 후**
```java
public class AppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public FixDiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

}
```
- ```new MemoryMemberRepository()``` 이 부분이 중복 제거 되었다. 이제 ```MemoryMemberRepository```를
다른 구현체로 변경할 때 한 부분만 변경하면 된다.
  
- ```AppConfig```를 보면 역할과 구현 클래스가 한눈에 들어온다. 애플리케이션 전체 구성이 어떻게 진행되어 있는지
빠르게 파악할 수 있다.
### 좋은 객체 지향 설계의 5가지 원칙 적용
여기서 3가지 SRP, DIP OCP 적용

**단일 책임 원칙**
**한 클래스는 하나의 책임만 가져야 한다.**
- 클라이언트 객체는 직접 구현 객체를 생성하고, 연결하고, 실행하는 다양한 책임을 가지고 있음
- SRP 단일 책임 원칙을 따르면서 관심사를 분리함 
- 구현 객체를 생성하고 연결하는 책임은 AppConfig가 담당
- 클라이언트 객체는 실행하는 책임만 담당

**DIP 의존관계 역전 원칙**

**프로그래머는 "추상화에 의존해야지, 구체화에 의존하면 안된다." 의존성 주입은 이 원칙을 따르는 방법 중 하나다.**

- 새로운 할인 정책을 개발하고, 적용하려고 하니 클라이언트 코드도 함께 변경해야 했다. 왜냐하면 기존 클라이언트 
코드(```OrderServiceImpl```)는 DIP를 지키며 ```DiscountPolicy```추상화 인터페이스에 의존하는 것 같았지만,
```FixDiscountPolicy```구체화 구현 클래스에도 함께 의존했다.
  
- 클라이언트 코드가 ```DiscountPolicy```추상화 인터페이스에만 의존하도록 코드를 변경했다.
- 하지만 클라이언트 코드는 인터페이스로만으로는 아무것도 실행할 수 없다.
- AppConfig가 ```FixDiscountPolicy```객체 인스턴스를 클라이언트 코드 대신 생성해서 클라이언트 코드에 
의존관계를 주입했다. 이렇게해서 DIP 원칙을 따르면서 문제도 해결했다.

**OCP**
**소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.**
- 다형성 사용하고 클라이언트가 DIP를 지킴
- 애플리케이션을 사용영역과 구성영역으로 나눔
- AppConfig가 의존관계를 ```FixDiscountPolicy``` → ```RateDiscountPolicy```로 변경해서 클라이언트 코드에
주입하므로 클라이언트 코드는 변경하지 않아도 됨
- **소프트웨어 요소를 새롭게 확장해도 사용 영역의 변경은 닫혀 있다.**

### Ioc,DI,그리고 컨테이너

**제어의 역전(Inversion of Control)**
- 기존 프로그램은 클라이언트 구현 객체가 스스로 필요한 서버 구현 객체를 생성하고, 연결하고, 실행했다. 한
마디로 구현 객체가 프로그램의 제어 흐름을 스스로 조종했다. 개발자 입장에서 자연스러운 흐름이다.
  
- 반면에 AppConfig가 등장한 이후에 구현 객체는 자신의 로직을 실행하는 역할만 담당한다. 프로그램의 제어 흐름
은 이제 AppConfig가 가져간다. 예를 들어서 ```OrderServiceImpl```은 필요한 인터페이스들을 호출하지만 어떤
구현 객체들이 실행될지 모른다.
  
- 프로그램에 대한 제어 흐름에 대한 권한은 모두 AppConfig가 가지고 있다. 심지어 ```OrderServiceImpl```도 
AppConfig가 생성한다. 그리고 AppConfig는 ```OrderServiceImpl```이 아닌 OrderService 인터페이스의 다른 구현 객체를
생성하고 실행할 수 도 있다. 그런 사실도 모른체 ```OrderServiceImpl```은 묵묵히 자신의 로직을 실행할 뿐이다.
  
- 이렇듯 프로그램 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것을 제어의 역전(IoC)이라 한다.

**프레임워크 vs 라이브러리**
- 프레임워크가 내가 작성한 코드를 제어하고, 대신 실행하면 그것은 프레임워크가 맞다.(JUnit)
- 반면에 내가 작성한 코드가 직접 제어의 흐름을 담당한다면 그것은 프레임워크가 아니라 라이브러리다.

**의존관계 주입 DI(Dependency Injection)**
- ```OrderServiceImpl```은 ```DiscountPolicy```인터페이스에 의존한다. 실제 어떤 구현 객체가 사용될지는 모른다.
- 의존관계는 **정적인 클래스 의존 관계와, 실행 시점에 결정되는 동적인 객체(인스턴스) 의존 관계** 둘을 분리해서 생각해야 한다.

**정적인 클래스 의존관계**
클래스가 사용하는 import 코드만 보고 의존관계를 쉽게 판단할 수 있다. 정적인 의존관계는 애플리케이션을 실행하지 
않아도 분석할 수 있다.

### 스프링으로 전환하기

지금까지는 순수한 자바 코드만으로 DI를 적용했다. 이제 스프링을 사용해보자.

**AppConfig** 스프링 기반으로 변경
```java
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public FixDiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

}
```

**스프링 컨테이너**
- ```ApplicationContext```를 스프링 컨테이너라 한다.
- 기존에는 개발자가 ```AppConfig```를 사용해서 직접 객체를 생성하고 DI를 했지만, 이제부터는 스프링 컨테이너를 통해서
사용한다.
  
- 스프링 컨테이너는```@Configuration```이 붙은 ```AppConfig```를 설정(구성)정보로 사용한다. 여기서```@Bean```
이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다.이렇게 스프링 컨테이너에 등록된 객체를 스프링 빈이라 한다.
  
- 스프링 빈은 ```@Bean```이 붙은 메서드의 명을 스프링 빈의 이름으로 사용한다. (```memberServie```,```orderService```)

- 이전에는 개발자가 필요한 객체를 ```AppConfig```를 사용해서 직접 조회했지만, 이제부터는 스프링 컨테이너를 통해서 필요한 스프링
빈(객체)를 찾아야 한다. 스프링 빈은 ```applicationContext.getBean()```메서드를 사용해서 찾을 수 있다.
  
- 기존에는 개발자가 직접 자바코드로 모든 것을 했다면 이제부터는 스프링 컨테이너에 객체를 스프링 빈으로 등록하고,
스프링 컨테이너에서 스프링 빈을 찾아서 사용하도록 변경되었다.
  

## 스프링 컨테이너와 스프링 빈

### 목차
[4. 스프링 컨테이너와 스프링 빈 - 스프링 컨테이너 생성](#스프링-컨테이너-생성)

[4. 스프링 컨테이너와 스프링 빈 - 컨테이너에 등록된 모든 빈 조회](#컨테이너에-등록된-모든-빈-조회)

[4. 스프링 컨테이너와 스프링 빈 - 스프링 빈 조회 - 기본](#스프링-빈-조회-기본)

[4. 스프링 컨테이너와 스프링 빈 - 스프링 빈 조회 - 동일한 타입이 둘 이상](#스프링-빈-조회-동일한-타입이-둘-이상)

[4. 스프링 컨테이너와 스프링 빈 - 스프링 빈 조회 - 스프링 빈 조회 상속관계](#스프링-빈-조회-상속관계)

[4. 스프링 컨테이너와 스프링 빈 - BeanFactory와 ApplicationContext](#BeanFactory-ApplicationContext)

[4. 스프링 컨테이너와 스프링 빈 - 다양한 설정 형식 지원 - 자바 코드, XML](#자바-코드,-XML)

[4. 스프링 컨테이너와 스프링 빈 - 스프링 빈 설정 메타 정보 - BeanDefinition](#BeanDefinition)



### 스프링 컨테이너 생성
스프링 컨테이너가 생성되는 과정을 알아보자 

```java
// 스프링 컨테이너 생성
class Test{
  ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);    
}
```
- ```ApplicationContext```를 스프링 컨테이라 한다.
- ```ApplicationContext```는 인터페이스이다. 
- 스프링 컨테이너는 XML 기반으로 만들 수 있고, 애노테이션 기반의 자바 설정 클래스로 만들 수 있다.
- 직전에 ```AppConfig```를 사용했던 방식이 애노테이션 기반의 자바 설정 클래스로 스프링 컨테이너를 만든 것이다.
- 자바 설정 클래스를 기반으로 스프링 컨테이너 ```ApplicationContext```를 만들어 보자
  + ```new AnnotationConfigApplicationContext(AppConfig.class)```;
  + 이 클래스는 ```ApplicationContext``` 인터페이스의 구현체이다
  
> 참고 : 더 정확히는 스프링 컨테이너를 부를 때 ```BeanFactory```, ```ApplicationContext```로 구분해서 이야기 한다. 
> ```BeanFactory```를 직접 사용하는 경우는 거의 없으므로 일반적으로 ```ApplicationContext```를 스프링 컨테이라 한다. 

#### 스프링 컨테이너 생성과정

**1. 스프링 컨테이너 생성**
![스프링 컨테이너 생성](./assets/스프링_컨테이너_생성.png)
- ```new AnnotationConfigApplicationContext(Appconfig.class)```
- 스프링 컨테이너를 생성할 때는 구성 정보를 지정해주어야 한다. 
- 여기서는 ```AppConfig.class```를 구성 정보로 지정했다. 

**2.스프링 빈 등록**
  
![스프링 빈 등록](./assets/스프링_빈_등록)
- 스프링 컨테이너는 파라미터로 넘어온 설정 클래스 정보를 사용해서 스프링 빈을 등록한다. 

**3.스프링 빈 의존관계 설정 - 준비**
![스프링 빈 의존관계 설정](./assets/스프링_빈_의존관계_설정)

**4.스프링 빈 의존관계 설정 - 완료**
![스프링 빈 의존관계 설정](./assets/스프링_빈_의존관계-설정2)

- 스프링 컨테이너는 설정 정보를 참고해서 의존관계(DI)를 주입한다. 
- 단순히 자바 코드를 호출하는 것 같지만, 차이가 있다. 이 차이는 뒤에 싱글톤 컨테이너에서 설명한다. 

## 컨테이너에 등로된 모든 빈 조회
스프링 컨테이너에 실제 스프링 빈들이 잘 등록 되었는지 확인해보자. 

```java
class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object=" + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            //Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE : 스프링 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object=" + bean);
            }
        }
    }
}

```
- 모든 빈 출력하기
  + 실행하면 스프링에 등록된 모든 빈 정보를 출력할 수 있다.
  + ```ac.getBeanDefinitionNames()``` : 스프링에 등록된 모든 빈 이름을 조회한다.
  + ```ac.getBean()``` : 빈 이름으로 빈 객체 (인스턴스)를 조회한다.
  
- 애플리케이션 빈 출력하기
  + 스프링이 내부에서 사용하는 빈은 제외하고, 내가 등록한 빈만 출력해보자.
  + 스프링이 내부에서 사용하는 빈은 ```getRole()```로 구분할 수 있다. 
    + ```ROLE_APPLICATION``` : 일반적으로 사용자가 정의한 빈 
    + ```ROLE_INFRASTRUCTURE``` : 스프링이 내부에서 사용하는 빈
  
### 스프링 빈 조회 기본
스프링 컨테이너에서 스프링 빈을 찾는 가장 기본적인 조회 방법

- ```ac.getBean(빈이름, 타입)```
- ```ac.getBean(타입)```
- 조회 대상 스프링 빈이 없으면 예외 발생
  + ```NoSuchBeanDefinitionException: No bean named 'xxxxx' available```
  
**예제 코드**
```java
public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입만으로 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){
        assertThrows(NoSuchBeanDefinitionException.class, () ->
                ac.getBean("xxxxx", MemberService.class));
    }
}
```
> 참고 : 구체타입으로 조회화 변경시 유연성이 떨어진다.

### 스프링 빈 조회 동일한 타입 둘 이상
- 타입으로 조회시 같은 타입의 스프링 빈이 둘 이상이면 오류가 발생한다. 이때는 빈 이름을 지정하자.
- ```ac.getBeanType()```을 사용하면 해당 타입의 모든 빈을 조회한다.

**예제 코드**
```java
public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByTypeDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class, () ->
                ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByName(){
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
    }
    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beanOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig{
        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
```

### 스프링 빈 조회 상속관계

- 부모 타입으로 조회하면, 자식 타입도 함께 조회된다.
- 그래서 모든 자바 객체의 최고 부모인 ```Object```타입으로 조회하면, 모든 스프링 빈을 조회한다.

![스프링 빈 조회](./assets/스프링_빈_조회)

**예제 코드**
```java
public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate(){
        //DiscountPolicy bean = ac.getBean(DiscountPolicy.class)
        assertThrows(NoUniqueBeanDefinitionException.class, () ->
                ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType(){
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    void findAllBeanByObjectType(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key  = " + key + " value = " + beansOfType.get(key));
        }
    }

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
```

### BeanFactory ApplicationContext

beanFactory와 ApplicationContext에 대해서 알아보자.

![BeanFactory](./assets/BeanFactory)

**BeanFactory**
- 스프링 컨테이너의 최상위 인터페이스이다. 
- 스프링 빈을 관리하고 조회하는 역할을 담당한다. 
- ```getBean()```을 제공한다.
- 지금까지 우리가 사용했던 대부분의 기능은 BeanFactory가 제공하는 기능

**ApplicationContext**
- BeanFactory 기능을 모두 상속받아서 제공
- 빈을 관리하고 검색하는 기능을 BeanFactory가 제공해주는데, 둘의 차이는?
- 애플리케이션을 개발할 때는 빈은 관리하고 조회하는 기능은 물론이고, 수 많은 부가기능이 필요하다. 

**ApplicationContext가 제공하는 부가기능**

![ApplicationContext](./assets/ApplicationContext)

- **메시지 소스를 활용한 국제화 기능**
  + 한국에서 들어오면 한국어, 영어권에서 들어오면 영어로 출력
  
- **환경변수**
  + 로컬,개발,운영등을 구분해서 처리 
  
- **애플리케이션 이벤트**
  + 이벤트를 발행하고 구독하는 모델을 편리하게 지원
  
- **편리한 리소스 조회**
  + 파일,클래스,외부 등에서 리소스를 편리하게 조회
  
**정리**
- ApplicationContext는 BeanFactory 기능을 상속
- ApplicationContext -> 빈 관리 기능 + 편리한 부가 기능을 제공
- BeanFactory 직접 사용하는 경우 거의 X, 대부분 ApplicationContext 사용
- BeanFactory, ApplicationContext를 스프링 컨테이너라고 한다. 


### 자바 코드, XML
- 스프링 컨테이너는 다양한 형식의 설정 정보를 받아드 수 있게 유연하게 설계되어 있다. 
- 자바 코드, XML, Groovy 등등

![!자바코드, XML](./assets/xml)

**애노테이션 기반 자바 코드 설정 사용**
- ```new AnnotationConfigApplicationContext(AppConfig.class)``` 
- ```AnnotationConfigApplicationContext```를 사용하면서  ```xml```설정 파일을 넘기면 된다. 

**XML 설정 사용**
- 최근에는 스프링 부트를 사용하면서 XML은 사용하지 않음 but, 아직 많은 레거시 프로젝트들이 XML로 되어 있고, 또 XML을 사용하면 컴파일 없이 빈 설정 정보를
변경할 수 있는 장점도 있으므로 배워두는게 좋음
  
- ```GenericXmlApplictionContext를``` 사용하면서 xml 설정 파일을 넘기면 된다.

**XMlAppConfig 사용 자바 코드**
```java
public class XmlAppContext {

    @Test
    void xmlAppContext(){
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
```

**xml 기반의 스프링 빈 설정 정보**

```src/main/resources/appConfig.xml```

```html
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="memberService" class="basic.core.service.MemberServiceImpl">
        <constructor-arg name="memberRepository" ref="memberRepository"/>
    </bean>

    <bean id="memberRepository" class="basic.core.repository.MemoryMemberRepository"/>

    <bean id="orderService" class="basic.core.service.OrderServiceImpl">
        <constructor-arg name="memberRepository" ref="memberRepository"/>
        <constructor-arg name="discountPolicy" ref="discountPolicy"/>
    </bean>
    <bean id="discountPolicy" class="basic.core.repository.RateDiscountPolicy"/>
</beans>
```

### BeanDefinition

- 역할과 구현을 개념적으로 나눈 것이다.
  + XML을 읽어서 BeanDefinition을 만들면 된다.
  + 자바 코드를 읽어서 BeanDefinition을 만들면 된다.
  + 스프링 컨테이너는 자바코드인지, XML인지 몰라도 된다. 오직 BeanDefinition만 알면된다.
  
- ```BeanDefinition```을 빈 설정 메타정보라 한다.
  + ```@Bean```, ```<bean>```당 각각 하나씩 메타 정보가 생성된다.
  
- 스프링 컨테이너는 이 메타 정보를 기반으로 스프링 빈을 생성한다. 

![BeanDefinition](./assets/BeanDefinition)

**코드 레벨**

![BeanDefinition](./assets/BeanDefinition_Code)

- ```AnnotationConfigApplicationContext```는 ```AnnotatedBeanDefinitionReader```를 사용해서 
```AppConfig.class```를 읽고 ```BeanDefinition```를 생성한다.

- ```GenericXmlApplicationContext```는 ```XmlBeanDefinitionReader```를 사용해서 
```appConfig.xml```설정 정보를 읽고 ```BeanDefinition```을 생성한다.
  
- 새로운 형식의 설정 정보가 추가되면, XxxBeanDefinitionReader를 만들어서 ```BeanDefinition```을 생성하면 된다.

**BeanDefinition 살펴보기**

**BeanDefinition 정보**


- BeanClassName: 생성할 빈의 클래스 명(자바 설정 처럼 팩토리 역할의 빈을 사용하면 없음) 
- factoryBeanName: 팩토리 역할의 빈을 사용할 경우 이름, 예) appConfig 
- factoryMethodName: 빈을 생성할 팩토리 메서드 지정, 예) memberService
- Scope: 싱글톤(기본값)
- lazyInit: 스프링 컨테이너를 생성할 때 빈을 생성하는 것이 아니라, 실제 빈을 사용할 때 까지 최대한 생성을 지연처리 하는지 여부
- InitMethodName: 빈을 생성하고, 의존관계를 적용한 뒤에 호출되는 초기화 메서드 명 
- DestroyMethodName: 빈의 생명주기가 끝나서 제거하기 직전에 호출되는 메서드 명 
- Constructor arguments, Properties: 의존관계 주입에서 사용한다. (자바 설정 처럼 팩토리 역할 의 빈을 사용하면 없음)

```java
public class BeanDefinitionTest {
//    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName +
                        " beanDefinition = " + beanDefinition);
            }
        }
    }
}
```

## 싱글톤 컨테이너

### 목차

[5. 싱글톤 컨테이너 - 웹 애플리케이션과 싱글톤](#웹-애플리케이션과-싱글톤)

[5. 싱글톤 컨테이너 - 싱글톤 패턴](#싱글톤-패턴)

[5. 싱글톤 컨테이너 - 싱글톤 컨테이너](#싱글톤-컨테이너)

[5. 싱글톤 컨테이너 - 싱글톤 방식의 주의점](#싱글톤-방식의-주의점)

[5. 싱글톤 컨테이너 - @Configuration과 싱글톤](#@Configuration과-싱글톤)

[5. 싱글톤 컨테이너 - @Configuration과 바이트코드 조작의 마법](#@Configuration과-바이트코드-조작의-마법)

**웹 애플리케이션과 싱글톤**

- 웹 애플리케이션은 보통 여러 고객이 동시에 요청을 한다.

![클라이언트 요청](./assets/client_request)

**스프링 없는 순수한 DI 컨테이너 테스트**
```java
public class SingletonTest {

  @Test
  @DisplayName("스프링 없는 순수한 DI 컨테이너")
  void pureContainer() {
    AppConfig appConfig = new AppConfig();
    //1. 조회: 호출할 때 마다 객체를 생성
    MemberService memberService1 = appConfig.memberService();

    //2. 조회: 호출할 때 마다 객체를 생성
    MemberService memberService2 = appConfig.memberService();

    //참조값이 다른 것을 확인
    System.out.println("memberService1 = " + memberService1);
    System.out.println("memberService2 = " + memberService2);

    //memberService != memberService2
    assertThat(memberService1).isNotSameAs(memberService2);

  }
}
```
- 우리가 만들었던 스프링 없는 순수한 DI 컨테이너인 Appconfig는 요청을 할 때 마다 객체를 새로 생성한다.
- 고객 트래픽이 초당 100이 나오면 초당 100개 객체가 생성되고 소멸된다! -> 메모리 낭비가 심하다
- 해결방안은 해당 객체가 딱 1개만 생성되고, 공유하도록 설계하면 된다. -> 싱글톤 패턴

### 싱글톤 패턴
- 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴인다. 
- 그래서 객체 인스턴스를 2개 이상 생성하지 못하도록 막아야 한다. 
  + private 생성자를 사용해서 외부에서 임의로 new 키워드를 사용하지 못하도록 막아야 한다.
  
```java
public class SingletonService {

  //1. static 영역에 객체를 딱 1개만 생성해둔다.
  private static final SingletonService instance = new SingletonService();

  //2. public 으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
  public static SingletonService getInstance() {
    return instance;
  }

  //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
  private SingletonService(){

  }
  public void login(){
    System.out.println("싱글톤 객체 로직 호출");
  }
}
```
- static  영역에 객체 instance를 미리 하나 생성해서 올려둔다.
- 이 객체 인스턴스가 필요하면 오직 ```getInstance()```메서드를 통해서만 조회할 수 있다.
이 메서드를 호출하면 항상 같은 인스턴스를 반환한다.
- 딱 1개의 객체 인스턴스만 존재해야 하므로, 생성자를 private으로 막아서 혹시라도 외부에서 new 키워드로 객체
인스턴스가 생성되는 것을 막는다.
  
```java
class Test{
  @Test
  @DisplayName("싱글톤 패턴을 적용한 객체 사용")
  void singletonServiceTest(){
    SingletonService singletonService1 = SingletonService.getInstance();
    SingletonService singletonService2 = SingletonService.getInstance();

    System.out.println("singletonService1 = " + singletonService1);
    System.out.println("singletonService2 = " + singletonService2);

    assertThat(singletonService1).isSameAs(singletonService2);
    //same == 참조 객체
    //equal == 인스턴스 비교
  }
}
```

> 참고 : 싱글톤 패턴을 구현하는 방법은 여러가지가 있다. 여기서는 객체를 미리 생성해두는 가장
> 단순하고 안전한 방법을 택함

싱글톤 패턴을 적용하면 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유 해서 효율적으로 사용할 수 있다. 하지만 싱글톤 패턴은 다음과 같은 수 많은 문제점들을 가지고 있다.

**싱글톤 패턴 문제**
- 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
- 의존관계상 클라이언트가 구체 클래스에 의존한다. DIP를 위반한다. 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다. 테스트하기 어렵다.
- 내부 속성을 변경하거나 초기화 하기 어렵다.
- private 생성자로 자식 클래스를 만들기 어렵다.
- 결론적으로 유연성이 떨어진다.
- 안티패턴으로 불리기도 한다.

### 싱글톤 컨테이너 

스프링 컨테이너는 싱글톤 패턴의 문제점을 해결하면서, 객체 인스턴스를 싱글톤(1개만 생성)으로 관리한다.

**싱글톤 컨테이너**
- 스프링 컨테이너는 싱글톤 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리한다.
- 스프링 컨테이너는 싱글톤 컨테이너 역할을 한다. 이렇게 싱글톤 객체를 생성하고 관리하는 기능을 싱글톤 레지스트리라 한다.
- 스프링 컨테이너의 이런 기능 덕분에 싱글톤 패턴의 모든 단점을 해결하면서 객체를 싱글톤으로 유지할 수 있다.
  + 싱글톤 패턴을 위한 지저분한 코드가 들어가지 않아도 된다. 
  + DIP, OCP, 테스트, private 생성자로부터 자유롭게 싱글톤을 사용할 수 있다. 
  
**스프링 컨테이너를 사용하는 테스트 코드**
```java
class Test{
  @Test
  @DisplayName("스프링 컨테이너와 싱글톤")
  void springContainer() {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    //1. 조회: 호출할 때 마다 객체를 생성
    MemberService memberService1 = ac.getBean("memberService", MemberService.class);
    MemberService memberService2 = ac.getBean("memberService", MemberService.class);

    //참조값이 다른 것을 확인
    System.out.println("memberService1 = " + memberService1);
    System.out.println("memberService2 = " + memberService2);

    //memberService != memberService2
    assertThat(memberService1).isSameAs(memberService2);

  }
}
```

**싱글톤 컨테이너 적용 후**

![싱클톤 컨테이너 적용 후](./assets/spring_container)

- 스프링 컨테이너 덕분에 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유해서 
효율적으로 재사용할 수 있다. 
  
> 참고 : 스프링의 기본 빈 등록 방식은 싱글톤이지만, 싱글톤 방식만 지원하는 것은 아니다. 요청할 때 마다 새로운 
> 객체를 생성해서 반환하는 기능도 제공한다. 

### 싱글톤 방식의 문제점 

- 싱글톤 패턴이든, 스프링 같은 싱글톤 컨테이너를 사용하든, 객체 인스턴스를 하나만 생성해서 공유하는 싱
글톤 방식은 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에 싱글톤 객체는 상태를 유지(stateful)하게
설계 하면 안된다.
  
- 무상태(stateless)로 설계해야 한다!
  + 특정 클라이언트에 의존적인 필드가 있으면 안된다.
  + 특정 클라이언가 값을 변경할 수 있는 필드가 있으면 안된다.
  + 가급적 읽기만 가능해야 한다.
  + 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
  
- 스프링 빈의 필드에 공유 값을 설정하면 정말 큰 장애가 발생할 수 있다!!!

**상태를 유지할 경우 발생하는 문제점 예시**
```java
public class StatefulService {

    private int price; // 상태를 유지하는 필드

    public void order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 여기가 문제!
    }

    public int getPrice(){
        return price;
    }
}
```

**상태를 유지할 경우 발생하는 문제점 예시**
```java
class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A 사용자 10000원 주문
        statefulService1.order("userA", 10000);
        //ThreadB : B 사용자 20000원 주문
        statefulService2.order("userB", 20000);
        //ThreadA : 사용자 A 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
          return new StatefulService();
        };
    }
}
```
- 최대한 단순히 설명하기 위해, 실제 쓰레드는 사용하지 않는다.
- ThreadA가 사용자A 코드를 호출하고 ThreadB가 사용자B 코드를 호출한다고 가정
- ```StatefulService```의 ```price``` 필드는 공유되는 필드인데, 특정 클라이언트가 값을 변경한다.
- 사용자A의 주문금액은 10000원이 되어야 하는데, 20000원이라는 결과가 나왔다.
- 실무에서 이런 경우를 종종 보는데, 이로인해 정말 해결하기 어려운 큰 문제들이 터짐 
- 진짜 공유필드는 조심해야 한다! 스프링 빈은 항상 무상태(stateless)로 설계하자


### @Configuration과 싱글톤
```java
@Configuration
public class AppConfig {

    //@Bean memberService() -> new MemoryMemberRepository()
    //@Bean orderService() -> new MemoryMemberRepository()

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public FixDiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

}

```

- memberService 빈을 만드는 코드를 보면 ```memberRepository()```를 호출한다. 
    + 이 메서드를 호출하면 new ```MemoryMemberRepository()```를 호출한다. 
- orderService 빈을 만드는 코드도 동일하게 ```memberRepository()```를 호출한다. 
    + 이 메서드를 호출하면 ```new MemoryMemberRepository()```를 호출한다.
      

결과적으로 각각 다른 2개의 ```MemoryMemberRepository```가 생성되면서 싱글톤이 깨지는 것 
처럼 보인다. 스프링 컨테이너는 이 문제를 어떻게 해결할까?

**검증 용도의 코드 추가**

```java
public class MemberServiceImpl implements MemberService {
      private final MemberRepository memberRepository;
      //테스트 용도
      public MemberRepository getMemberRepository() {
          return memberRepository;
      }
}
  
public class OrderServiceImpl implements OrderService {
      private final MemberRepository memberRepository;
      //테스트 용도
      public MemberRepository getMemberRepository() {
          return memberRepository;
      }
}
```

```java
public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = memberService.getMemberRepository();

        System.out.println("memberService  -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean = " + bean.getClass());
    }
}
```

- 확인해보면 memberRepository 인스턴스는 모두 같은 인스턴스가 공유되어 사용
- AppConfig의 자바 코드를 보면 분명 각각 2번 ```new MemoryMemberRepository```호출해서 다른 인스턴스가
생성되야 하는데?
- 어떻게 된 일일까? 혹시 두 번 호출이 안되는 것일까? 실험을 통해서 알아보자.

**AppConfig에 호출 로그 남김**

```java
@Configuration
public class AppConfig {

    //@Bean memberService() -> new MemoryMemberRepository()
    //@Bean orderService() -> new MemoryMemberRepository()

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public FixDiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

}
```

- 스프링 컨테이너가 각각 @Bean을 호출해서 스프링 빈을 생성한다. 그래서 ```memberRepository()``` 
  는 다음 과 같이 총 3번이 호출되어야 하는 것 아닐까?

- 1. 스프링 컨테이너가 스프링 빈에 등록하기 위해 @Bean이 붙어있는 ```memberRepository()``` 호출
- 2. memberService() 로직에서 ```memberRepository()``` 호출
- 3. orderService() 로직에서 ```memberRepository()``` 호출

그런데 출력결과는 모두 한번만 호출
```
call AppConfig.memberService
call AppConfig.memberRepository
call AppConfig.orderService
```

### @Configuration과 바이트코드 조작의 마

스프링 컨테이너는 싱글톤 레지스트리다. 따라서 스프링 빈이 싱글톤이 되도록 보장해주어야 한다. 그런데
스프링이 자바 코드까지 어떻게 하기는 어렵다. 자바 코드를 보면 분명 3번 호출되어야 하는 것이 맞다.
그래서 스프링은 클래스의 바이트코드를 조작하는 라이브러리를 사용한다. 

모든 비밀은 ```Configuration```을 적용한 ```AppConfig```에 있다.

Code
```java
public class ConfigurationSingletonTest {
  @Test
  void configurationDeep(){
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    AppConfig bean = ac.getBean(AppConfig.class);
    System.out.println("bean = " + bean.getClass());
  }
}
```

- 사실 ```AnnotationConfigApplicationContext```에 파라미터로 넘긴 값은 스프링 빈으로 등록된다. 
그래서 ```AppConfig```도 스프링 빈이 된다.
  
- ```AppConfig```스프링 빈을 조회해서 클래스 정보를 출력해보자
```
bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$bd479d70
```
순수한 클래스라면 다음과 같이 출력되어야 한다.

```class hello.core.AppConfig```

그런데 예상과는 다르게 클래스 명에 xxxCGLIB가 붙으면서 상당히 복잡해진 것을 볼 수 있다.
이것은 내가 만든 클래스가 아니라 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서
AppConfig 클래스 를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로
등록한 것이다!

그림
![스프링_바이트코드_조작](./assets/spring_AppConfig)

그 임의의 다른 클래스가 바로 싱글톤이 보장되도록 해준다. 아마도 다음과 같이 바이트 코드를 조작해서
작성이 되어 있을 것이다.

**AppConfig@CGLB 예상 코드**

![바이트코드](./assets/바이트코드조작)

- @Bean이 붙은 메서드마다 이미 스프링빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면
생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.
  
- 덕분에 싱글톤이 보장되는 것이다.

>  참고 AppConfig@CGLIB 는 AppConfig 자식 타입으로, AppConfig 타입으로 조회할 수 있다.

**```@Configuration```을 적용하지 않고, ```@Bean```만 적용하면 어떻게 될까?**

```@Configuration```을 붙이면 바이트코드를 조작하는 CGLIB 기술을 사용해서 싱글톤을 보장하지만, 
만약 @Bean만 적용하면 어떻게 될까?

```java
//@Configuration 삭제 
public class AppConfig {

}
```
```bean = class hello.core.AppConfig```

이 출력결과를 통해서 AppConfig가 CGLB 기술 없이 순수한 AppConfig로 스프링 빈에 등록된 것을
확인할 수 있다.

```
call AppConfig.memberService
call AppConfig.memberRepository
call AppConfig.orderService
call AppConfig.memberRepository
call AppConfig.memberRepository
```

이 출력 결과를 통해서 MemberRepository가 총 3번 호출된 것을 알 수 있다. 1번은 @Bean에 의해 스
프링 컨테이너에 등록하기 위해서이고, 2번은 각각 ```memberRepository()``` 를 호출하면서 발생한 코드다.

**인스턴스가 같은지 테스트 결과**
```
memberService -> memberRepository =
hello.core.member.MemoryMemberRepository@6239aba6
orderService -> memberRepository  =
hello.core.member.MemoryMemberRepository@3e6104fc
memberRepository = hello.core.member.MemoryMemberRepository@12359a82
```
당연히 인스턴스가 같은지 테스트 하는 코드도 실패하고, 각각 다 다른 MemoryMemberRepository
인스턴스를 가지고 있다.

## 컴포넌트 스캔

### 목차

[6. 컴포넌트 스캔 - 컴포넌트 스캔과 의존관계 자동 주입 시작하기](#컴포넌트-스캔과-의존관계-자동-주입-시작하기) 

[6. 컴포넌트 스캔 - 탐색 위치와 기본 스캔 대상](#탐색-위치와-기본-스캔-대상)

[6. 컴포넌트 스캔 - 필터](#필터)

[6. 컴포넌트 스캔 - 중복 등록과 충돌](#중복-등록과-충돌)

**컴포넌트 스캔과 의존관계 자동 주입 시작하기**

- 지금까지 스프링 빈을 등록할 때는 자바 코드의 @Bean이나 XML의 <bean> 등을 통해서 설정 정보에
직접 등록할 스프링 빈을 나열했다.
  
- 예제에서는 몇개가 안되었지만, 이렇게 등록해야 할 스프링 빈이 수십, 수백개가 되면 일일이 등록하기도 귀 
찮고, 설정 정보도 커지고, 누락하는 문제도 발생한다. 역시 개발자는 반복을 싫어한다.

- 그래서 스프링은 설정 정보가 없어도 자동으로 스프링 빈을 등록하는 컴포넌트 스캔이라는 기능을
제공한다.
  
- 또 의존관계도 자동으로 주입하는 ```@Autowired```라는 기능도 제공한다.

코드로 컴포넌트 스캔과 의존관계 자동 주입을 알아보자.

**AutoAppConfig.java**
```java
package basic.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters= @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
```

- 컴포넌트 스캔을 사용하려면 먼저 ```@ComponentScan```을 설정 정보에 붙여주면 된다.
- 기존의 AppConfig와는 다르게 @Bean으로 등록한 클래스가 하나도 없다.

컴포넌트 스캔은 이름 그대로 ```@Component``` 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록한 다. ```@Component``` 를 붙여주자.

> **참고**: ```@Configuration``` 이 컴포넌트 스캔의 대상이 된 이유도 ```@Configuration``` 소스코드를 열어보면
> ```@Component``` 애노테이션이 붙어있기 때문이다.

이제 각 클래스가 컴포넌트 스캔의 대상이 되도록 ```@Component```애노테이션을 붙여주자.

**MemoryMemberRepository @Component 추가**

```java
   @Component
  public class MemoryMemberRepository implements MemberRepository {}
```

**RateDiscountPolicy @Component추가**
```java
@Component
  public class RateDiscountPolicy implements DiscountPolicy {}
```

**MemberServiceImpl @Component @Autowired 추가**
```java
@Component
  public class MemberServiceImpl implements MemberService {
      private final MemberRepository memberRepository;
      @Autowired
      public MemberServiceImpl(MemberRepository memberRepository) {
          this.memberRepository = memberRepository;
      }
}
```
- 이전에 AppConfig에서는 ```@Bean```으로 직접 설정 정보를 작성했고, 의존관계도 직접 명시했다.
이제는 이런 설정 정보 자체가 없기 때문에, 의존관계 주입도 이 클래스 안에서 해결해야 한다.
  
- @Autowired는 의존관계를 자동으로 주입해준다. 자세한 룰은 뒤에서 설명한다.

**OrderServiceImpl @Component, @Autowired 추가**
```java
@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
```
- @Autowired를 사용하면 생성자에 여러 의존관계도 한번에 주입받을 수 있다.

**AutoAppConfigTest**

```java
public class AutoAppConfigTest {

    @Test
    void basicScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
```

- ```AnnotationConfigApplicationContext```를 사용하는 것은 기존과 동일하다.
- 설정 정보로 ```AutoAppConfig```클래스를 넘겨준다.
- 실행해보면 기존과 같이 잘 동작하는 것을 확인할 수 있다.

로그를 잘 보면 컴포넌트 스캔이 잘 동작하는 것을 확인할 수 있다.
```
 ClassPathBeanDefinitionScanner - Identified candidate component class:
.. RateDiscountPolicy.class
.. MemberServiceImpl.class
.. MemoryMemberRepository.class
.. OrderServiceImpl.class

```

컴포넌트 스캔과 자동 의존관계 주입이 어떻게 동작하는지 그림으로 알아보자.

**1.@ComponentScan**

![컴포넌트 스캔](./assets/ComponentScan)

- ```@ComponentScan```은 ```@Component```가 붙은 모든 클래스를 스프링 빈으로 등록한다.
- 이때 스프링 빈의 기본 이름은 클래스명을 사용하되 맨 앞글자만 소문자를 사용한다.
  + **빈 이름 기본 전략:** MemberServiceImpl 클래스 → memberServiceImpl
  + **빈 이름 직접 지정:** 만약 스프링 빈의 이름을 직접 지정하고 싶으면 ```@Component("memberService2")```
  이런식으로 이름을 부여하면 된다.

**2.@Autowired 의존관계 자동 주입**

![Autowired](./assets/Autowired)

- 생성자에 ```@AutoWired```를 지정하면, 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입한다.
- 이때 기본 조회 전략은 타입이 같은 빈을 찾아서 주입한다.
  + ```getBean(MemberRepository.class)```와 동일하다고 이해하면 된다.
  
![Autowired](./assets/Autowired2)

### 탐색 위치와 기본 스캔 대상 

**탐색할 패키지의 시작 위치 지정**

모든 자바 클래스를 다 컴포넌트 스캔하면 시간이 오래 걸린다. 그래서 꼭 필요한 위치부터 탐색하도록
시작 위치를 지정할 수 있다.

```
@ComponentScan(
          basePackages = "hello.core",
}
```

- ```basePackages:``` 탐색할 패키지의 시작위치를 지정한다. 이 패키지를 포함해서 하위 패키지를 모두 탐색한다.
  + ```basePackages = {"hello.core", "hello.service"}```이렇게 여러 시작 위치를 지정할 수도 있다.
  
- ```basePackageClasses``` 지정한 클래스의 패키지를 탐색 시작 위치로 지정한다.
- 만약 지정하지 않으면 ```@ComponentScan```이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.

**권장하는 방법**

개인적으로 즐겨 사용하는 방법은 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 둠

**컴포넌트 스캔 기본 대상**

컴포넌트 스캔은 ```@Component```뿐만 아니라 다음과 내용도 추가로 대상에 포함한다.
- ```@Component :``` 컴포넌트 스캔에서 사용
- ```@Controller :``` 스프링 MVC 컨트롤러에서 사용
- ```@Service :``` 스프링 비즈니스 로직에서 사용
- ```@Repository :``` 스프링 데이터 접근계층에서 사용
- ```@Configuration :``` 스프링 설정 정보에서 사용

해당 클래스의 소스 코드를 보면 ```@Component```를 포함하고 있는 것 을 알 수 있다. 

```
@Component
public @interface Controller {
    }
@Component
public @interface Service {
    }
@Component
public @interface Configuration {
    }
```

> 참고 : 사실 에노테이션에는 상속관계라는 것이 없다. 그래서 이렇게 애노테이션이 특정 애노테이션을 들고
> 있는 것을 인식할 수 있는 것은 자바 언어가 지원하는 기능은 아니고, 스프링이 지원하 기능이다.

컴포넌트 스캔의 용도 뿐만 아니라 다음 애노테이션이 있으면 스프링 부가 기능을 수행한다. 
- ```@Controller:``` 스프링 MVC 컨트롤러로 인식
- ```@Repository:``` 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해준다.
- ```@Configuration:``` 앞서 보았듯이 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리를 한다.
- ```Service:``` 사실 ```@Service```는 특별한 처리를 하지 않는다. 대신 개발자들이 핵심 비즈니스 로직이 여기에 있겠구나 라고
비즈니스 계층을 인식하는데 도움이 된다.
  
> 참고: ```useDefaultFilters```은 기본으로 켜져있는데, 이 옵션을 끄면 기본 스캔 대상들이 제외된다.

