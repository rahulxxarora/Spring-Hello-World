package com.meesho.helloworld.controller;

import java.util.List;

import javax.validation.Valid;

import com.meesho.helloworld.entity.IssueReport;
import com.meesho.helloworld.repositories.IssueRepository;
import com.meesho.helloworld.validations.ValidationResponse;
import com.meesho.helloworld.models.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    public Response createReport(@Valid @RequestBody IssueReport issueReportData) {
        IssueReport issueReport = new IssueReport();
        issueReport.setEmail(issueReportData.getEmail());
        issueReport.setIssue(issueReportData.getIssue());

        IssueReport savedObject = this.issueRepository.save(issueReport);
        Response responseObj = new Response(savedObject.getId(), 200);

        return responseObj;
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationResponse handleException(MethodArgumentNotValidException exception) {
        return createValidationError(exception);
    }

    private ValidationResponse createValidationError(MethodArgumentNotValidException exception) {
        Errors errors = exception.getBindingResult();

        ValidationResponse validationresponseObj = new ValidationResponse("Validation failed. " + errors.getErrorCount() + " error(s)");
        for (ObjectError objectError : errors.getAllErrors()) {
            validationresponseObj.addValidationError(objectError.getDefaultMessage());
        }

        return validationresponseObj;
    }
}