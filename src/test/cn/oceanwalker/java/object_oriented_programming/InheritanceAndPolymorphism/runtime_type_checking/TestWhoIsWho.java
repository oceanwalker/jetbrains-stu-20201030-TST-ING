package test.cn.oceanwalker.java.object_oriented_programming.InheritanceAndPolymorphism.runtime_type_checking;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestWhoIsWho {

    @Test
    public void test() {
        final Employee developer = new Developer("", "", 0, "", new String[0]);
        assertTrue(Developer.class.isInstance(developer));
        assertEquals("EMP", Determiner.getTypeString(new Employee("", "", 0)));
        assertEquals("DEV", Determiner.getTypeString(developer));
    }
}

class Determiner {
    public static void determineWhoIsWho(Employee[] employees) {
        for (Employee employee : employees) {
//            String result = getTypeString(employee);
//            System.out.println(result);
            System.out.println(employee instanceof Developer ? "DEV" : employee instanceof DataAnalyst ? "DA" : "EMP");
        }
    }

    static String getTypeString(Employee employee) {
        String result = "";
        if (Developer.class.isInstance(employee)) {
            result = "DEV";
        } else if (DataAnalyst.class.isInstance(employee)) {
            result = "DA";
        } else {
            result = "EMP";
        }
        return result;
    }
}

// Don't change the code below
class Employee {

    protected String name;
    protected String email;
    protected int experience;

    public Employee(String name, String email, int experience) {
        this.name = name;
        this.email = email;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getExperience() {
        return experience;
    }
}

class Developer extends Employee {

    private String mainLanguage;
    private String[] skills;

    public Developer(String name, String email, int experience, String mainLanguage, String[] skills) {
        super(name, email, experience);
        this.mainLanguage = mainLanguage;
        this.skills = skills != null ? skills : null;
    }

    public String getMainLanguage() {
        return mainLanguage;
    }

    public String[] getSkills() {
        return Arrays.copyOf(skills, skills.length);
    }
}

class DataAnalyst extends Employee {

    private boolean phd;
    private String[] methods;

    public DataAnalyst(String name, String email, int experience, boolean phd, String[] methods) {
        super(name, email, experience);
        this.phd = phd;
        this.methods = methods != null ? methods : null;
    }

    public boolean isPhD() {
        return phd;
    }

    public String[] getMethods() {
        return Arrays.copyOf(methods, methods.length);
    }
}