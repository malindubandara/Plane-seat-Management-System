public class Person {
    private String name;

    private String surName;

    private String email;

    public Person(String name, String surName, String email) {
        this.name = name;
        this.surName = surName;
        this.email = email;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getname() {
        return name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getSurName() {
        return surName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void printInfo() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surName);
        System.out.println("Email: " + email);
    }
}
