package com.meesho.helloworld.controller;

import java.util.List;

import com.meesho.helloworld.entity.IssueReport;
import com.meesho.helloworld.repositories.IssueRepository;
import com.meesho.helloworld.models.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueRepository issueRepository;

    @GetMapping
    @ResponseBody
    public List<IssueReport> getReport() {
        return this.issueRepository.findAll();
    }

    @PostMapping
    @ResponseBody
    public Response createReport(@RequestBody IssueReport issueReportData) {
        IssueReport issueReport = new IssueReport();
        issueReport.setEmail(issueReportData.getEmail());
        issueReport.setIssue(issueReportData.getIssue());

        IssueReport savedObject = this.issueRepository.save(issueReport);
        Response response = new Response(savedObject.getId(), 200);

        return response;
    }
}