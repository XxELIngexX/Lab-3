package edu.eci.cvds.tdd.registry;

import java.util.ArrayList;

public class Registry {

    private ArrayList<Person> peopleThatAlreadyVoted = new ArrayList<>();

    private boolean alreadyVoted(Person p) {
        boolean alreadyVote = false;
        for (int i = 0; i < this.peopleThatAlreadyVoted.size(); i++) {
            Person savedPerson = this.peopleThatAlreadyVoted.get(i);
            if (savedPerson.getId() == p.getId()) {
                alreadyVote = true;
            }
        }
        return alreadyVote;
    }

    public RegisterResult registerVoter(Person p) {
        if(p.getName() == null && p.getGender()==null) {
            return RegisterResult.NULL_PERSON;
        }
        if(!p.isAlive()) {
            return RegisterResult.DEAD;
        }
        if(p.getAge() >= 0 && p.getAge() <18){
            return RegisterResult.UNDERAGE;
        }
        if(p.getAge() < 0 || p.getAge() > 130){
            return RegisterResult.INVALID_AGE;
        }
        if(alreadyVoted(p)) {
            return RegisterResult.DUPLICATED;
        }
        peopleThatAlreadyVoted.add(p);
        return RegisterResult.VALID;
    }

    private RegisterResult validAge(int age) {
        RegisterResult result = RegisterResult.UNDERAGE;
        if(age >= 18)result = RegisterResult.VALID;
        return result;
    }
}
