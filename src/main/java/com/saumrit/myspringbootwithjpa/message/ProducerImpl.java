package com.saumrit.myspringbootwithjpa.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class ProducerImpl<PAYLOAD> {

    @Autowired
    private StreamBridge streamBridge;
    private PAYLOAD payload;

    public ProducerImpl() {

    }

    public boolean send(String bindingName, PAYLOAD payload){
        streamBridge.send(bindingName,payload);
        return true;
    }
}
