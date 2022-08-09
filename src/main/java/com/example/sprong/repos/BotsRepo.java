package com.example.sprong.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sprong.domain.Bots;

@Repository
public interface BotsRepo extends JpaRepository<Bots, Integer> {

}