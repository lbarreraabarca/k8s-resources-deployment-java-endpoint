package com.data.factory.controller;

import com.data.factory.adapters.Base64Encoder;

import com.data.factory.adapters.KubernetesManagerOperator;
import com.data.factory.ports.Encoder;
import com.data.factory.ports.KubernetesManager;
import com.data.factory.service.K8SResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ResourcesController {
    
    @PostMapping(value = "/create/job")
    public ResponseEntity<String> createResources(@RequestBody String body) {
        String response;
        HttpStatus status;
        try {
            Encoder encoder = new Base64Encoder();
            KubernetesManager kubernetesManager = new KubernetesManagerOperator();
            K8SResourceService service = new K8SResourceService(encoder, kubernetesManager);
            service.createPod(body);
            response = "OK";
            status = HttpStatus.OK;
        } catch (Exception e) {
            response = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(response, status);
    }

}
