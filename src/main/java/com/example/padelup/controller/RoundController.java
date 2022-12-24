package com.example.padelup.controller;

import com.example.padelup.model.dto.RoundDto;
import com.example.padelup.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/round")
public class RoundController {
   private final RoundService roundService;
   @Autowired
   public RoundController(RoundService roundService){
      this.roundService = roundService;
   }

   @PostMapping
   public ResponseEntity<RoundDto> create(@RequestBody RoundDto roundDto) {
      return ResponseEntity.status(HttpStatus.CREATED).body(roundService.create(roundDto));
   }
   @DeleteMapping(path = "/{id}")
   public ResponseEntity<String> deleteRound(@PathVariable("id") Integer pId) {

      boolean delete = roundService.delete(pId);
      return ResponseEntity.ok(delete ? "Round with id " + pId + " was deleted" : "Round Not Deleted");
   }
   @GetMapping("/{id}")
   public ResponseEntity<RoundDto> findById(@PathVariable("id") Integer id){
      return ResponseEntity.ok(roundService.findById(id));
   }

   @GetMapping
   public ResponseEntity<List<RoundDto>> findAll(){
      return ResponseEntity.ok(roundService.findAll());
   }


}
