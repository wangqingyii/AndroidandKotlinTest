package com.wangqingyi.javatest.inheritanceAndComposition;

/**
 * 继承与组合
 * <p>
 * 继承是实现复用的重要手段，但继承带来一个最大坏处就是破坏封装。组合也是实现类复用的重要方式，采用组合方式来实现类复用
 * 则能提供更好的封装性。
 * 继承带来了高度复用的同时，也带来了一个严重的问题：继承严重破坏父类的封装性。
 * 设计父类应遵循如下规则：
 * 1.尽量隐藏父类的内部数据。尽量把父类所有成员变量都设置成private访问类型，不要让子类直接访问父类的成员
 * 2.不要让子类可以随意访问、修改父类的方法。父类中那些仅为辅助其他的工具类方法，应该使用private访问控制修饰符，让子类无法访问该方法。
 * 如果父类中的方法需要被外部类调用，则必须以public修饰，但又不希望子类重写该方法，可以使用final修饰符来修饰该方法
 * 如果希望父类的某个方法被子类重写，但又不希望被其他类自由访问，则可以使用protected来修饰该方法
 * 3.尽量不要在父类构造器中调用将要被子类重写的方法
 * <p>
 * 利用组合实现复用
 * 如果要复用一个类，除了吧这个类当成基类来继承以外，还可以把该类当成另一个类的组合成分，从而允许新类直接复用该类的public方法
 * 不管是继承还是组合，都允许在新类（对于继承就是子类）中直接复用旧类的方法
 * <p>
 * 组合是把旧类对象作为新类的成员变量组合进来，用以实现新类的功能。
 *
 * @author WangQingYi
 * @since 2021/11/7
 */
class Animal {
    private void beat() {
        System.out.println("心脏跳动");
    }

    public void breath() {
        beat();
        System.out.println("吸一口气，吐一口气");
    }
}
class Bird{
    // 将原来的父类组合到原来的子类中，将子类作为父类组成的一部分
    private Animal animal;
    public Bird(Animal animal){
        this.animal =animal;
    }
    // 定义一个自己的breath()方法
    public void breath(){
        // 直接复用Animal提供的breath()方法来实现Bird的breath方法
        animal.breath();
    }
    public void fly(){
        System.out.println("我在天空自由的飞翔");
    }

}

class Wolf{
    private Animal animal;
    public  Wolf(Animal animal){
        this.animal = animal;
    }
    // 重新定义一个自己的breath()方法
    public void breath(){
        // 直接复用Animal提供的breath()方法来实现Wolf的breath方法
        animal.breath();
    }

    public void run(){
        System.out.println("我在地上跑");
    }
}

class InheritanceAndComposition{
    public static void main(String[] args) {
        // 此时需要显式创建被组合的对象
        Animal a1 = new Animal();
        Bird b = new Bird(a1);
        b.breath();
         b.fly();
    Animal a2 = new Animal();
    Wolf wolf = new Wolf(a2);
    wolf.breath();
    wolf.run();
    }
}
