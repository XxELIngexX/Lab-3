package edu.eci.cvds.tdd.registry;

import org.junit.Assert;
import org.junit.Test;

public class RegistryTest {
    private Registry registry = new Registry();
    @Test
    public void validateRegistryResult() {
        Person person = new Person();
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.NULL_PERSON, result);
    }

    @Test
    public void validateAliveMenorPerson(){
        Person person = new Person("Sebastian",1001328625,17,Gender.MALE,true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.UNDERAGE, result);
        Person person2 = new Person("Sebastian",1001328625,0,Gender.MALE,true);
        result = registry.registerVoter(person2);
        Assert.assertEquals(RegisterResult.UNDERAGE, result);
    }

    @Test
    public void validateAlaveMayorPerson(){
        Person person = new Person("Sebastian",1001328625,25,Gender.MALE,true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void notValidadeDeadPerson(){
        Person person = new Person("Sebastian",1001328625,15,Gender.MALE,false);
        Person person2 = new Person("Sebastian",1001328625,25,Gender.MALE,false);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.DEAD, result);
        result = registry.registerVoter(person2);
        Assert.assertEquals(RegisterResult.DEAD, result);
    }

    @Test
    public void notValidAge(){
        Person person = new Person("Sebastian",1001328625,-15,Gender.MALE,true);
        Person person2 = new Person("Sebastian",1001328625,225,Gender.MALE,true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
        result = registry.registerVoter(person2);
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void validateIfThePersonAlreadyVoted(){
        Person person = new Person("Sebastian",1001328625,24, Gender.MALE,true);
        Person person2 = new Person("Sebastian",1001328625,24, Gender.MALE,true);
        RegisterResult result1 = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.VALID, result1);
        RegisterResult result2 = registry.registerVoter(person2);
        Assert.assertEquals(RegisterResult.DUPLICATED, result2);
    }
}
