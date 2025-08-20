package com.challenge.ChallengeApp1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService {
      public Long nextId=1L;
      @Autowired
      ChallengeRepository challengeRepository;
      public ChallengeService(){}

      public List<Challenge> getAllChallenges(){
            return challengeRepository.findAll();
      }
      public boolean addChallenge(Challenge challenge){
            if(challenge!=null){
                  challenge.setId(nextId++);
                  challengeRepository.save(challenge);
                  return true;
            }
            else{
                  return false;
            }
            
      }
      public Challenge getChallenge(String month){
            Optional<Challenge> challenge=challengeRepository.findByMonthIgnoreCase(month);
            return challenge.orElse(null);
      }
      public boolean updateChallenge(Long id, Challenge updatedChallenge) {
            Optional<Challenge>challenge=challengeRepository.findById(id);
            if(challenge.isPresent()){
                  Challenge challengeToUpdate=challenge.get();
                  challengeToUpdate.setMonth(updatedChallenge.getMonth());
                  challengeToUpdate.setDescription(updatedChallenge.getDescription());
                  challengeRepository.save(challengeToUpdate);
            }
            return false;
      }
      public boolean deleteChallenge(Long id) {
        if(challengeRepository.existsById(id)){
            challengeRepository.deleteById(id);
            return true;
        }
        return false;
      }
      

}
