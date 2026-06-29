package com.saumrit.myspringbootwithjpa.message.producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saumrit.myspringbootwithjpa.message.ProducerImpl;
import com.saumrit.myspringbootwithjpa.model.Student;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ProducerStudentAddImpl extends ProducerImpl<ProducerStudentAddImpl.TransportDTO> {

    private final ObjectMapper objectMapper;
    private static final String bindingName = "producestudentadd";
    private static String eventName= "studentAddEvent";

    public ProducerStudentAddImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public boolean send(Student  payload){
        super.send(bindingName,prepareTransPortDTO(payload));
        return true;
    }

    public void sendAll(List<Student> payload){
        payload.forEach(this::send);
    }

    private TransportDTO prepareTransPortDTO(Student payload){
        TransportDTO transportDTO =  new TransportDTO();
        transportDTO.setRollId(payload.getRollId());
        transportDTO.setEventDateTime(LocalDateTime.now());
        return transportDTO;
    }

    public class TransportDTO{
        private String rollId;
        private LocalDateTime eventDateTime;

        public String getRollId() {
            return rollId;
        }

        public void setRollId(String rollId) {
            this.rollId = rollId;
        }

        public LocalDateTime getEventDateTime() {
            return eventDateTime;
        }

        public void setEventDateTime(LocalDateTime eventDateTime) {
            this.eventDateTime = eventDateTime;
        }
    }
}
