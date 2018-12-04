package com.meesho.helloworld.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.meesho.helloworld.entity.IssueReport;;

@Repository
public interface IssueRepository extends JpaRepository<IssueReport, Long>{
    List<IssueReport> findAll();

    IssueReport save(IssueReport issueReport);

    List<IssueReport> findAllByEmail(String email);
}