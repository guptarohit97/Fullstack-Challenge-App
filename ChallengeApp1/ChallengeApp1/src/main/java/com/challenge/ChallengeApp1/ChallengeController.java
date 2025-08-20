package com.challenge.ChallengeApp1;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/challenges")
@CrossOrigin(origins = "http://localhost:3000")
public class ChallengeController {
      @Autowired
      private final ChallengeService challengeService;

      public ChallengeController(ChallengeService challengeService){
            this.challengeService=challengeService;
            
      }
      @GetMapping
      public ResponseEntity<List<Challenge>> getAllChallenges(){
            return new ResponseEntity<>(challengeService.getAllChallenges(),HttpStatus.OK);
            
      }
      @PostMapping
      public ResponseEntity<String> addChallenge(@RequestBody Challenge challenge){
            boolean isChallengeAdded=challengeService.addChallenge(challenge);
            if(isChallengeAdded){
                  return new ResponseEntity("Challenge added successfully",HttpStatus.CREATED);
            }
            else{
                  return new ResponseEntity("Challenge not added successfully",HttpStatus.NOT_FOUND);
            }
      }
      @GetMapping("/{month}")
      public ResponseEntity<Challenge> getChallenge(@PathVariable String month){
            Challenge challenge=challengeService.getChallenge(month);
            if(challenge!=null){
                  return new ResponseEntity<>(challenge,HttpStatus.OK);
            }
            else{
                  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
      } 
      @PutMapping("/{id}")
      public ResponseEntity<String> updateChallenge(@PathVariable Long id,@RequestBody Challenge updatedChallenge){
            boolean isUpdated=challengeService.updateChallenge(id,updatedChallenge);
            if (isUpdated) {
                  return new ResponseEntity<>("Challenge updated successfully",HttpStatus.OK);
            }
            else {
                  return new ResponseEntity<>("Challenge not found",HttpStatus.NOT_FOUND);
            }
      }
      @DeleteMapping("/{id}")
      public ResponseEntity<String> deleteChallenge(@PathVariable Long id) {
            boolean isDeleted = challengeService.deleteChallenge(id);
            return isDeleted ? new ResponseEntity<>("Challenge deleted successfully",HttpStatus.OK) : new ResponseEntity<>("Challenge not found",HttpStatus.NOT_FOUND);
      }
      
}
