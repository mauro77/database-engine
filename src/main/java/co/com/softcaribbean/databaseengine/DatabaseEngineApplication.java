package co.com.softcaribbean.databaseengine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import co.com.softcaribbean.databaseengine.service.bplustree.BPlusTreeService;


@SpringBootApplication public class DatabaseEngineApplication {
  
  @Autowired
  BPlusTreeService bPlusTreeService;

  public static void main(String[] args) {
    SpringApplication.run(DatabaseEngineApplication.class, args);
    
  }
  
  
  @EventListener(ApplicationReadyEvent.class)
  public void handleStartEVent() {
      bPlusTreeService.loadBPlusTreeFromFile();
  }
  

}
