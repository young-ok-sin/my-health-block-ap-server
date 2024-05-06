package com.example.myhealthblock.question;

import com.example.myhealthblock.patient.PatientService;
import com.example.myhealthblock.patient.dto.PatientEntityDTO;
import com.example.myhealthblock.question.adapter.out.QuestionAdapter;
import com.example.myhealthblock.question.adapter.out.QuestionEntity;
import com.example.myhealthblock.question.dto.QuestionDTO;
import com.example.myhealthblock.question.dto.QuestionEnrollDTO;
import com.example.myhealthblock.question.dto.QuestionEntityDTO;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionAdapter outport;
    private final PatientService patientInport;

    public String enroll(QuestionEnrollDTO dto) {
        PatientEntityDTO patientDto = patientInport.getPatientEntityDTO(dto.getUserId());
        boolean result = outport.create(patientDto.getEntity(), dto.getTitle(), dto.getCategory(), dto.getSymptom(), dto.getContent(), dto.getBodyParts(), null);

        return result ? "success" : "fail";
    }

    public QuestionDTO[] getQuestions() {
        return outport.getQuestions();
    }

    public QuestionEntityDTO getQuestionEntityDTO(int questionId) { // inport interface로 노출할 필요 존재
        return outport.getQuestionEntityDTO(questionId);
    }
}