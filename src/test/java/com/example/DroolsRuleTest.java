package com.example;



import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DroolsRuleTest <T> {

    @Test
    public void testStudentSessionAddress() {

        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("rulesSessionStudent");

        List<String> results = new ArrayList<>();
        kieSession.setGlobal("results", results);

        Person person = new Student();
        kieSession.insert(person);

        kieSession.fireAllRules();

        assertEquals("PIPPO", person.getName());

        kieSession.dispose();
    }

    @Test
    public void testWorkerSessionAddress() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();

        KieSession kieSession = kieContainer.newKieSession("rulesSessionWorker");

        List<String> results = new ArrayList<>();
        kieSession.setGlobal("results", results);


        Worker person = new Worker();
        kieSession.insert(person);

        kieSession.fireAllRules();

        assertEquals("PIPPO", person.getName());

        kieSession.dispose();
    }
}

