package com.linkit.datarest.runner;

import com.linkit.datarest.models.MainEntity;
import com.linkit.datarest.repository.MainEntityRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private Logger log = Logger.getLogger(DataLoader.class);

    private MainEntityRepository mainEntityRepository;

    @Autowired
    public DataLoader(MainEntityRepository mainEntityRepository) {
        this.mainEntityRepository = mainEntityRepository;
    }

    @Override
    public void run(String... args){
        if(isDbEmpty()){
            for (int x = 0; x < 100; x++){
                MainEntity mainEntity = new MainEntity();
                mainEntity.setName("Name-" + x);
                mainEntity.setDescription("Description-" + x);
                log.info("Adding object to DB: " + mainEntity);
                mainEntityRepository.save(mainEntity);
            }
        }
    }

    private boolean isDbEmpty(){
        log.info("Size of elements here: " + mainEntityRepository.findAll().spliterator().getExactSizeIfKnown());
        return !(mainEntityRepository.findAll().spliterator().getExactSizeIfKnown() > 0);
    }
}
