package com.example.quartz_demo.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.quartz.JobExecutionContext;

@Component
public class KurCekmeJobRunner implements CommandLineRunner {

    @Autowired
    private KurCekmeJob kurCekmeJob;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Manually triggering KurCekmeJob...");

        // Boş bir JobExecutionContext (gerekirse null olabilir)
        JobExecutionContext context = null;

        // KurCekmeJob manuel olarak tetikleniyor
        kurCekmeJob.execute(context);

        System.out.println("KurCekmeJob executed manually.");
    }
}
