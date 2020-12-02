package by.alekseichik.demo.rest;


import by.alekseichik.demo.dto.DiagnosisRequestDto;
import by.alekseichik.demo.repository.DiagnosisRepository;
import by.alekseichik.demo.repository.UserRepository;
import by.alekseichik.demo.services.DiagnosisServiceImpl;
import by.alekseichik.demo.services.OrderServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/diagnoses")
public class DiagnosisRestControllerV1 {
    private final UserRepository userRepository;
    private final DiagnosisRepository diagnosisRepository;

    final static Logger logger = Logger.getLogger(PatientRestControllerV1.class);

    public DiagnosisRestControllerV1(UserRepository userRepository, DiagnosisRepository diagnosisRepository) {
        this.userRepository = userRepository;
        this.diagnosisRepository = diagnosisRepository;
    }


    @PostMapping("/add")
    public void addDiagnoses(@RequestBody DiagnosisRequestDto dto) {
        DiagnosisServiceImpl service = new DiagnosisServiceImpl(userRepository, diagnosisRepository);
        service.addDiagnosis(dto.getDiagnosis(), dto.getPatientEmail(), dto.getDoctorEmail());
        logger.info("/api/v1/diagnoses/add [POST] -> adding diagnosis: " + dto.getDiagnosis());
        ;
    }
}
