package com.data.factory.ports;

import com.data.factory.exceptions.KubernetesServiceException;

public interface KubernetesManager {
    public Boolean createJob(String ns,String kubernetesEndpoint, String token,String job) throws KubernetesServiceException;
}
