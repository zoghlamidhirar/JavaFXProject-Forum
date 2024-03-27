package models;

public class Test {
    private int id = 0;
    private int age;
    private String name;
    public Test(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public Test(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Test() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name ;   }

    public void setName(String name) {
        this.name = name;    }




    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
