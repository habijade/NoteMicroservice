package com.nnk.springboot.proxy;

import com.nnk.springboot.client.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "patients", url = "${patient.url}")
public interface PatientClient {
    @GetMapping("patient/{id}")
    Optional<Patient> getPatient(@PathVariable("id") Integer id);
}
