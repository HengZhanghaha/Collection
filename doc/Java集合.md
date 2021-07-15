# Java集合类

## 一.概述

* 概念：对象的容器，定义了对多个对象进行操作的常用方法。可实现数组的功能。
* 集合与数组的区别
  * 数组长度固定，集合长度不固定

  * 数组可以储存的基本类型和应用类型，集合只能存储引用类型

* java.util.*

## 二.Colloection

![image-20210416224733479](C:\Users\张恒\AppData\Roaming\Typora\typora-user-images\image-20210416224733479.png)

## 三.Collection父接口

* 特点：代表一组任意类型的对象，无需、无下标，不能重复。

* 方法：

  ```java
  boolean add(Object obj)  // 添加一个对象
  boolean addAll(Collection c)  //将一个集合中的所有对象添加到此集合中
  void clear()  //清空此集合中的所有对象
  boolean contains(Object o)  //检查此集合中是否包含o对象
  boolean equals(Object o) // //比较此集合是否与指定对象相等
  boolean isEmpty() //判断此集合是否为空。
  boolean remove(Object o) //在此集合中移除o对象。
  int size() //返回此集合中的元素个数。
  Object[] toArray() //将此集合转换成数组。
  ```

  

## 四.List集合

* 特点：有序、有下标、元素可以重复

* 方法：

  ```java
  void add(int index,Object o) //在index位置插入对象o。
  boolean addAll(int index,Collection c) //将一个集合中的元素添加到此集合中的index位置。
  Object get(int index) //返回集合中指定位置的元素。
  List subList(int fromIndex,int toIndex) //返回fromIndex和toIndex之间的集合元素。
  ```

  



### 4.1ArrayList[重点]

* 数组结构实现，查询快，增删慢

* JDK1.2版本，效率快，不安全

ArrayList源码分析

* 默认容量大小：`private static final int DEFAULT_CAPACITY = 10`;

* 存放元素的数组：`transient Object[] elementData;`

* 实际元素个数：`private int size;`

* 创建对象时调用的无参构造函数：

  ```java
  COPY//这是一个空的数组
  private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
  public ArrayList() {
      this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
  }
  ```

  这段源码说明当你没有向集合中添加任何元素时，集合容量为0。那么默认的10个容量怎么来的呢？

  这就得看看add方法的源码了：

  ```java
  COPYpublic boolean add(E e) {
      ensureCapacityInternal(size + 1);  // Increments modCount!!
      elementData[size++] = e;
      return true;
  }
  ```

  假设你new了一个数组，当前容量为0，size当然也为0。这时调用add方法进入到`ensureCapacityInternal(size + 1);`该方法源码如下：

  ```java
  COPYprivate void ensureCapacityInternal(int minCapacity) {
      ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
  }
  ```

  该方法中的参数minCapacity传入的值为size+1也就是 1，接着我们再进入到`calculateCapacity(elementData, minCapacity)`里面：

  ```java
  COPYprivate static int calculateCapacity(Object[] elementData, int minCapacity) {
      if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
          return Math.max(DEFAULT_CAPACITY, minCapacity);
      }
      return minCapacity;
  }
  ```

  上文说过，elementData就是存放元素的数组，当前容量为0，if条件成立，返回默认容量`DEFAULT_CAPACITY`也就是10。这个值作为参数又传入`ensureExplicitCapacity()`方法中，进入该方法查看源码：

  ```java
  COPYprivate void ensureExplicitCapacity(int minCapacity) {
      modCount++;
      // overflow-conscious code
      if (minCapacity - elementData.length > 0)
          grow(minCapacity);
  }
  ```

  ~~我们先不要管modCount这个变量。~~

  因为elementData数组长度为0，所以if条件成立，调用grow方法，**重要的部分来了**，我们再次进入到grow方法的源码中：

  ```jade
  COPYprivate void grow(int minCapacity) {
      // overflow-conscious code
      int oldCapacity = elementData.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      if (newCapacity - minCapacity < 0)
          newCapacity = minCapacity;
      if (newCapacity - MAX_ARRAY_SIZE > 0)
          newCapacity = hugeCapacity(minCapacity);
      // minCapacity is usually close to size, so this is a win:
      elementData = Arrays.copyOf(elementData, newCapacity);
  }
  ```

  这个方法先声明了一个oldCapacity变量将数组长度赋给它，其值为0；又声明了一个newCapacity变量其值为`oldCapacity+一个增量`，可以发现这个增量是和原数组长度有关的量，当然在这里也为0。第一个if条件满足，newCapacity的值为10（这就是默认的容量，不理解的话再看看前面）。第二个if条件不成立，也可以不用注意，因为MAX_ARRAY_SIZE的定义如下：

  ```java
  COPYprivate static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
  ```

  这个值太大了以至于第二个if条件没有了解的必要。

  最后一句话就是为elementData数组赋予了新的长度，`Arrays.copyOf()`方法返回的数组是新的数组对象，原数组对象不会改变，该拷贝不会影响原来的数组。`copyOf()`的第二个自变量指定要建立的新数组长度，如果新数组的长度超过原数组的长度，则保留数组默认值。

  这时候再回到add的方法中，接着就向下执行`elementData[size++] = e;`到这里为止关于ArrayList就讲解得差不多了，当数组长度为10的时候你们可以试着过一下源码，查一下每次的增量是多少（答案是每次扩容为原来的1.5倍）。

  

### 4.2Vector

* 数组结构实现，查询快、增删慢
* JDK1.0版本，运行效率慢，线程安全

### 4.3LinkList

* 链表结构实现，增删块，查询慢

LinkList源码分析

LinkedList首先有三个属性：

- 链表大小：`transient int size = 0;`
- （指向）第一个结点/头结点：` transient Node<E> first;`
- （指向）最后一个结点/尾结点：`transient Node<E> last;`

关于Node类型我们再进入到类里看看：

```java
private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
```

首先item存放的是实际数据；next指向下一个结点而prev指向上一个结点。

Node带参构造方法的三个参数分别是前一个结点、存储的数据、后一个结点，调用这个构造方法时将它们赋值给当前对象。

LinkedList是如何添加元素的呢？先看看add方法：

```java
public boolean add(E e) {
    linkLast(e);
    return true;
}
```

进入到linkLast方法：

```java
void linkLast(E e) {
    final Node<E> l = last;
    final Node<E> newNode = new Node<>(l, e, null);
    last = newNode;
    if (l == null)
        first = newNode;
    else
        l.next = newNode;
    size++;
    modCount++;
}
```

假设刚开始new了一个LinkedList对象，first和last属性都为空，调用add进入到linkLast方法。

首先创建一个Node变量 l 将last（此时为空）赋给它，然后new一个newNode变量存储数据，并且它的前驱指向l，后继指向null；再把last指向newNode。如下图所示：

![img](https://pic.downk.cc/item/5f9a62a01cd1bbb86bc58487.png)

如果满足if条件，说明这是添加的第一个结点，将first指向newNode：

![img](https://pic.downk.cc/item/5f9a631c1cd1bbb86bc59ca6.png)

至此，LinkedList对象的第一个数据添加完毕。假设需要再添加一个数据，我们可以再来走一遍，过程同上不再赘述，图示如下：

![img](https://pic.downk.cc/item/5f9a680e1cd1bbb86bc681f6.png)

### 4.4**ArrayList和LinkedList区别**

- ArrayList：必须开辟连续空间，查询快，增删慢。
- LinkedList：无需开辟连续空间，查询慢，增删快。

![img](https://pic.downk.cc/item/5f9a6b371cd1bbb86bc74cda.png)



## 五.**泛型概述**

- Java泛型是JDK1.5中引入的一个新特性，其本质是参数化类型，把类型作为参数传递。
- 常见形式有泛型类、泛型接口、泛型方法。
- 语法：
  - **<T,…> T称为类型占位符，表示一种引用类型**。
- 好处：
  - 提高代码的重用性。
  - 防止类型转换异常，提高代码的安全性。

#### 5.1泛型类

```java
/**
 * 泛型类
 * 语法：类名<T>
 * T是类型占位符，表示一种引用类型，编写多个使用逗号隔开
 * 
 */
public class myGeneric<T>{
	//1.创建泛型变量
	//不能使用new来创建，因为泛型是不确定的类型，也可能拥有私密的构造方法。
	T t;
	//2.泛型作为方法的参数
	public void show(T t) {
		System.out.println(t);
	}
	//泛型作为方法的返回值
	public T getT() {
		return t;
	}
}

/**
 * 注意：
 * 1.泛型只能使用引用类型
 * 2.不同泛型类型的对象不能相互赋值
 */
class testGeneric {
	public static void main(String[] args) {
		//使用泛型类创建对象
		myGeneric<String> myGeneric1=new myGeneric<String>();
		myGeneric1.t="tang";
		myGeneric1.show("he");
		
		myGeneric<Integer> myGeneric2=new myGeneric<Integer>();
		myGeneric2.t=10;
		myGeneric2.show(20);
		Integer integer=myGeneric2.getT();
	}
}
```

#### 5.2泛型接口

```java
/**
 * 泛型接口
 * 语法：接口名<T>
 * 注意：不能创建泛型静态常量
 */
public interface MyInterface<T> {
    //创建常量
	String nameString="tang";
    
	T server(T t);
}

/**
 * 实现接口时确定泛型类
 */
public class MyInterfaceImpl implements MyInterface<String>{
	@Override
	public String server(String t) {
		System.out.println(t);
		return t; 
	}
}

//测试
MyInterfaceImpl myInterfaceImpl=new MyInterfaceImpl();
myInterfaceImpl.server("xxx");
//xxx

/**
 * 实现接口时不确定泛型类
 */
public class MyInterfaceImpl2<T> implements MyInterface<T>{
	@Override
	public T server(T t) {
		System.out.println(t);
		return t;
	}
}

//测试
MyInterfaceImpl2<Integer> myInterfaceImpl2=new MyInterfaceImpl2<Integer>();
myInterfaceImpl2.server(2000);
//2000
```

#### 5.3泛型方法

```java
/**
 * 泛型方法
 * 语法：<T> 返回类型
 */
public class MyGenericMethod {
	public <T> void show(T t) {
		System.out.println("泛型方法"+t);
	}
}
//测试
MyGenericMethod myGenericMethod=new MyGenericMethod();
myGenericMethod.show("tang");
myGenericMethod.show(200);
myGenericMethod.show(3.14);
```

#### 5.4泛型集合

 **泛型集合**

- **概念**：参数化类型、类型安全的集合，强制集合元素的类型必须一致。
- 特点：
  - 编译时即可检查，而非运行时抛出异常。
  - 访问时，不必类型转换（拆箱）。
  - 不同泛型指尖引用不能相互赋值，泛型不存在多态。

之前我们在创建LinkedList类型对象的时候并没有使用泛型，但是进到它的源码中会发现：

```java
public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable
{//略}
```

它是一个泛型类，而我之前使用的时候并没有传递，说明java语法是允许的，这个时候传递的类型是Object类，虽然它是所有类的父类，可以存储任意的类型，但是在遍历、获取元素时需要原来的类型就要进行强制转换。这个时候就会出现一些问题，假如往链表里存储了许多不同类型的数据，在强转的时候就要判断每一个原来的类型，这样就很容易出现错误。

## 六.Set集合

###  6.1Set子接口

* 特点:无序,无下标,元素不可重复
* 方法:全部继承Collection的方法

### 6.2HashSet[重点]

- 基于HashCode计算元素存放位置。
- 当存入元素的哈希码相同时，会调用equals进行确认，如结果为true，则拒绝后者存入。
- 存储结构：哈希表（数组+链表+红黑树）

**注**：hashSet存储过程：

1. 根据hashCode计算保存的位置，如果位置为空，则直接保存，否则执行第二步。

2. 执行equals方法，如果方法返回true，则认为是重复，拒绝存储，否则形成链表。

3. 存储过程实际上就是重复依据，要实现“注”里的问题，可以重写hashCode和equals代码：

   ```java
   @Override
   public int hashCode() {
       final int prime = 31;
       int result = 1;
       result = prime * result + age;
       result = prime * result + ((name == null) ? 0 : name.hashCode());
       return result;
   }
   @Override
   public boolean equals(Object obj) {
       if (this == obj)
           return true;
       if (obj == null)
           return false;
       if (getClass() != obj.getClass())
           return false;
       Person other = (Person) obj;
       if (age != other.age)
           return false;
       if (name == null) {
           if (other.name != null)
               return false;
       } else if (!name.equals(other.name))
           return false;
       return true;
   }
   ```

   hashCode方法里为什么要使用31这个数字大概有两个原因：

   1. 31是一个质数，这样的数字在计算时可以尽量减少散列冲突。
   
   2. 可以提高执行效率，因为31*i=(i<<5)-i，31乘以一个数可以转换成移位操作，这样能快一点；但是也有网上一些人对这两点提出质疑。
   

 **HashSet源码分析**

了解完HashMap之后，再回过头来看之前的HashSet源码，为什么放在后面写你们看一下源码就知道了（部分）：

```java
public class HashSet<E>
      extends AbstractSet<E>
      implements Set<E>, Cloneable, java.io.Serializable
  {
      private transient HashMap<E,Object> map;
      private static final Object PRESENT = new Object();
      public HashSet() {
          map = new HashMap<>();
      }
  }
```

可以看见HashSet的存储结构就是HashMap，那它的存储方式是怎样的呢？可以看一下add方法：

```java
public boolean add(E e) {
      return map.put(e, PRESENT)==null;
  }
```

很明了地发现它的add方法调用的就是map的put方法，把元素作为map的key传进去的。。

### 6.3TreeSet

- 基于排序顺序实现不重复。

- 实现了SortedSet接口，对集合元素自动排序。

- 元素对象的类型必须实现Comparable接口，指定排序规则。

- 通过CompareTo方法确定是否为重复元素。

- 
  存储结构：红黑树

## 七.Map(映射)

### 7.1Map接口的特点

1. 用于存储任意键值对(Key-Value)。
2. 键：无序、无下标、不允许重复（唯一）。
3. 值：无序、无下标、允许重复

![image-20210417110244115](C:\Users\张恒\AppData\Roaming\Typora\typora-user-images\image-20210417110244115.png)

### 7.2Map集合概述

- **特点**：存储一对数据（Key-Value），无序、无下标，键不可重复。
- **方法**：
  - `put(K key,V value)`//将对象存入到集合中，关联键值。key重复则覆盖原值。
  -  ` Object get(Object key)`//根据键获取相应的值。
  - `Set<K>`//返回所有的key
  - `Collection<V> values()`//返回包含所有值的Collection集合。
  - `Set<Map.Entry<K,V>>`//键值匹配的set集合

### 7.3HashMap[**重点**]

* JDK1.2版本，线程不安全，运行效率快；允许用null作为key或是value。

* 存储结构：哈希表（数组+链表+红黑树）

- #### **HashMap源码分析**

  - 默认初始化容量：`static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16`

    - 数组最大容量：`static final int MAXIMUM_CAPACITY = 1 << 30;`

  - 默认加载因子：`static final float DEFAULT_LOAD_FACTOR = 0.75f;`

  - 链表调整为红黑树的链表长度阈值（JDK1.8）：`static final int TREEIFY_THRESHOLD = 8;`

  - 红黑树调整为链表的链表长度阈值（JDK1.8）：`static final int UNTREEIFY_THRESHOLD = 6;`

  - 链表调整为红黑树的数组最小阈值（JDK1.8）：`static final int MIN_TREEIFY_CAPACITY = 64;`

  - HashMap存储的数组：`transient Node<K,V>[] table;`

  - HashMap存储的元素个数：`transient int size;`

    > - 默认加载因子是什么？
    >   - 就是判断数组是否扩容的一个因子。假如数组容量为100，如果HashMap的存储元素个数超过了100*0.75=75，那么就会进行扩容。
    > - 链表调整为红黑树的链表长度阈值是什么？
    >   - 假设在数组中下标为3的位置已经存储了数据，当新增数据时通过哈希码得到的存储位置又是3，那么就会在该位置形成一个链表，当链表过长时就会转换成红黑树以提高执行效率，这个阈值就是链表转换成红黑树的最短链表长度；
    > - 红黑树调整为链表的链表长度阈值是什么？
    >   - 当红黑树的元素个数小于该阈值时就会转换成链表。
    > - 链表调整为红黑树的数组最小阈值是什么？
    >   - 并不是只要链表长度大于8就可以转换成红黑树，在前者条件成立的情况下，数组的容量必须大于等于64才会进行转换。

- HashMap的数组table存储的就是一个个的Node<K,V>类型，很清晰地看到有一对键值，还有一个指向next的指针（以下只截取了部分源码）：

  ```java
  COPYstatic class Node<K,V> implements Map.Entry<K,V> {
        final K key;
        V value;
        Node<K,V> next;
    }
  ```

  之前的代码中在new对象时调用的是HashMap的无参构造方法，进入到该构造方法的源码查看一下：

  ```java
  COPYpublic HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }
  ```

  发现没什么内容，只是赋值了一个默认加载因子；而在上文我们观察到源码中table和size都没有赋予初始值，说明刚创建的HashMap对象没有分配容量，并不拥有默认的16个空间大小，这样做的目的是为了节约空间，此时table为null，size为0。

  当我们往对象里添加元素时调用put方法：

  ```java
  COPYpublic V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }
  ```

  put方法把key和value传给了putVal，同时还传入了一个hash(Key)所返回的值，这是一个产生哈希值的方法，再进入到putVal方法（部分源码）：

  ```java
  COPYfinal V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                      boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else{
            //略
        }
    }
  ```

  这里面创建了一个tab数组和一个Node变量p，第一个if实际是判断table是否为空，而我们现在只关注刚创建HashMap对象时的状态，此时tab和table都为空，满足条件，执行内部代码，这条代码其实就是把resize()所返回的结果赋给tab，n就是tab的长度，resize顾名思义就是重新调整大小。查看resize()源码（部分）：

  ```java
  COPYfinal Node<K,V>[] resize() {
        Node<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        if (oldCap > 0);
        else if (oldThr > 0);
        else {               // zero initial threshold signifies using defaults
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        } 
        @SuppressWarnings({"rawtypes","unchecked"})
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab;
        return newTab;
    }
  ```

  该方法首先把table及其长度赋值给oldTab和oldCap；threshold是阈值的意思，此时为0，所以前两个if先不管，最后else里newCap的值为默认初始化容量16；往下创建了一个newCap大小的数组并将其赋给了table，刚创建的HashMap对象就在这里获得了初始容量。然后我们再回到putVal方法，第二个if就是根据哈希码得到的tab中的一个位置是否为空，为空便直接添加元素，此时数组中无元素所以直接添加。至此HashMap对象就完成了第一个元素的添加。当添加的元素超过16*0.75=12时，就会进行扩容：

  ```java
  COPYfinal V putVal(int hash, K key, V value, boolean onlyIfAbsent,boolean evict){
        if (++size > threshold)
            resize();
    }
  ```

  扩容的代码如下（部分）：

  ```java
  COPYfinal Node<K,V>[] resize() {
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int newCap;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {//略}
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                     oldCap >= DEFAULT_INITIAL_CAPACITY)
        }
    }
  ```

  核心部分是else if里的移位操作，**也就是说每次扩容都是原来大小的两倍**。

- *注**：额外说明的一点是在JDK1.8以前链表是头插入，JDK1.8以后链表是尾插入。

### 7.4Hashtable

- JDK1.0版本，线程安全，运行效率慢；不允许null作为key或是value。

- 初始容量11，加载因子0.75。

  这个集合在开发过程中已经不用了，稍微了解即可。

### 7.5Properties

- Hashtable的子类，要求key和value都是String。通常用于配置文件的读取。

它继承了Hashtable的方法，与流关系密切，此处不详解。

### 7.6 **TreeMap**

- 实现了SortedMap接口（是Map的子接口），可以对key自动排序。
- 储存结构：红黑树

#### **TreeSet源码**

和HashSet类似，放在TreeMap之后讲便一目了然（部分）：

```java
public class TreeSet<E> extends AbstractSet<E>
    implements NavigableSet<E>, Cloneable, java.io.Serializable
{
    private transient NavigableMap<E,Object> m;
    private static final Object PRESENT = new Object();
    TreeSet(NavigableMap<E,Object> m) {
        this.m = m;
    }
    public TreeSet() {
        this(new TreeMap<E,Object>());
    }
}
```

TreeSet的存储结构实际上就是TreeMap，再来看其存储方式：

```java
public boolean add(E e) {
    return m.put(e, PRESENT)==null;
}
```

它的add方法调用的就是TreeMap的put方法，将元素作为key传入到存储结构中。

------

## 八.**Collections工具类**

- **概念**：集合工具类，定义了除了存取以外的集合常用方法。

- **方法**：

  - `public static void reverse(List<?> list)`//反转集合中元素的顺序
  - `public static void shuffle(List<?> list)`//随机重置集合元素的顺序
  - `public static void sort(List<T> list)`//升序排序（元素类型必须实现Comparable接口）

  ```java
  /**
   * 演示Collections工具类的使用
   *
   */
  public class Demo4 {
  	public static void main(String[] args) {
  		List<Integer> list=new ArrayList<Integer>();
  		list.add(20);
  		list.add(10);
  		list.add(30);
  		list.add(90);
  		list.add(70);
  		
  		//sort排序
  		System.out.println(list.toString());
  		Collections.sort(list);
  		System.out.println(list.toString());
  		System.out.println("---------");
  		
  		//binarySearch二分查找
  		int i=Collections.binarySearch(list, 10);
  		System.out.println(i);
  		
  		//copy复制
  		List<Integer> list2=new ArrayList<Integer>();
  		for(int i1=0;i1<5;++i1) {
  			list2.add(0);
  		}
  		//该方法要求目标元素容量大于等于源目标
  		Collections.copy(list2, list);
  		System.out.println(list2.toString());
  		
  		//reserve反转
  		Collections.reverse(list2);
  		System.out.println(list2.toString());
  		
  		//shuffle 打乱
  		Collections.shuffle(list2);
  		System.out.println(list2.toString());
  		
  		//补充：list转成数组
  		Integer[] arr=list.toArray(new Integer[0]);
  		System.out.println(arr.length);
  		//补充：数组转成集合 
  		String[] nameStrings= {"tang","he","yu"};
  		//受限集合，不能添加和删除
  		List<String> list3=Arrays.asList(nameStrings);
  		System.out.println(list3);
  		
  		//注：基本类型转成集合时需要修改为包装类
  	}
  }
  ```