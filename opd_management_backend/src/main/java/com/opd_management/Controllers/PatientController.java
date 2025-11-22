package com.opd_management.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opd_management.Services.DoctorService;
import com.opd_management.Services.PatientService;
import com.opd_management.dtos.PatientDto;
import com.opd_management.entities.Doctor;
import com.opd_management.entities.Patient;

@RestController
@RequestMapping("/patients")

public class PatientController {
	
	@Autowired 
	private DoctorService doctorService; //to access doctor method
	
	@Autowired
	private PatientService patientService; //to access patient method

	//saving patient data 
	@PostMapping("/")
	public ResponseEntity<Patient>SavePatient(@RequestBody PatientDto patientDto){
		
		Patient patient = new Patient();
		
		patient.setPatient_name(patientDto.getPatient_name());
		patient.setAge(patientDto.getAge());
		patient.setAddress(patientDto.getAddress());
		patient.setAlcohol(patientDto.getAlcohol());
		patient.setBlood_group(patientDto.getBlood_group());
		patient.setGender(patientDto.getGender());
		patient.setHeight(patientDto.getHeight());
		patient.setMobileno(patientDto.getMobileno());
		patient.setSmoking(patientDto.getSmoking());
		patient.setTobacco(patientDto.getTobacco());
		patient.setCreated_at(patientDto.getCreated_at());
		
		Doctor doctor = doctorService.getDoctorById(patientDto.getDoctorid());
		patient.setDoctorid(doctor);
		
		Patient SavePatient = patientService.savePatient(patient);
		
		return new ResponseEntity<>(SavePatient,HttpStatus.CREATED);
	}
	
	// show all patient list 
	@GetMapping("/")
	public ResponseEntity<List<Patient>>GetAllPatient(){
		
		List<Patient> patient = patientService.getAllPatient();
		
		if(patient == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(patient,HttpStatus.FOUND);
	}
	
	//show patient by there id
	@GetMapping("/{id}")
	public ResponseEntity<Patient>GetPatientById(@PathVariable("id")int id){
		
		Patient patient = patientService.getPatientByID(id);
		
		if(patient == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(patient,HttpStatus.FOUND);
	}
	
	//update patient by there id
	@PutMapping("/{id}")
	public ResponseEntity<Patient>UpdatePatient(@PathVariable("id")int id,@RequestBody PatientDto patientDto){
		
		Patient patient = patientService.getPatientByID(id);
		
		if(patient == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		patient.setPatient_name(patientDto.getPatient_name());
		patient.setAge(patientDto.getAge());
		patient.setAddress(patientDto.getAddress());
		patient.setAlcohol(patientDto.getAlcohol());
		patient.setBlood_group(patientDto.getBlood_group());
		patient.setGender(patientDto.getGender());
		patient.setHeight(patientDto.getHeight());
		patient.setMobileno(patientDto.getMobileno());
		patient.setSmoking(patientDto.getSmoking());
		patient.setTobacco(patientDto.getTobacco());
		patient.setCreated_at(patientDto.getCreated_at());
		
		Doctor doctor = doctorService.getDoctorById(patientDto.getDoctorid());
		patient.setDoctorid(doctor);
		
		Patient UpdatePatient = patientService.savePatient(patient);
		
		return new ResponseEntity<>(UpdatePatient,HttpStatus.OK);
	}
	
	//delete patient by there id
	@DeleteMapping("/{id}")
	public  ResponseEntity<Patient>DeletePatientById(@PathVariable("id")int id){
		
		Patient patient = patientService.getPatientByID(id);
		
		if(patient == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		patientService.DeletePatientByID(id);
		return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
	}
	
}
