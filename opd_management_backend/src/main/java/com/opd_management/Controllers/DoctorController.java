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
import com.opd_management.dtos.DoctorDto;
import com.opd_management.entities.Doctor;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
	
	// doctor service to access method 
	@Autowired
	private DoctorService doctorService;

	
	//to save a doctor details or data
	@PostMapping("/")
	public ResponseEntity<Doctor>SaveDoctor(@RequestBody DoctorDto doctorDto){
		 Doctor doctor = new Doctor();
		 doctor.setName(doctorDto.getName());
		 doctor.setEmail(doctorDto.getEmail());
		 doctor.setAddress(doctorDto.getAddress());
		 doctor.setPassword(doctorDto.getPassword());
		 doctor.setSpecialization(doctorDto.getSpecialization());
		 doctor.setClinic_name(doctorDto.getClinic_name());
		 doctor.setMobileno(doctorDto.getMobileno());
		 doctor.setToken(doctorDto.getToken());
		 doctor.setStatus(doctorDto.getStatus());
		 doctor.setCreated_at(doctorDto.getCreated_at());
		 doctor.setUpdated_at(doctorDto.getUpdated_at());
		 
		 Doctor saveDoctor = doctorService.saveDoctor(doctor);
		 
		 return new ResponseEntity<>(saveDoctor,HttpStatus.CREATED);
	}
	
	//get all doctor list
	@GetMapping("/")
	public ResponseEntity<List<Doctor>>GetAllDoctor(){
		List<Doctor> doctor = doctorService.getAllDoctor();
		if(doctor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(doctor,HttpStatus.FOUND);
	}
	
	//get doctor details by there id
	@GetMapping("/{id}")
	public ResponseEntity<Doctor>GetDoctorById(@PathVariable("id")int id){
		
		Doctor doctor = doctorService.getDoctorById(id);
		if(doctor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(doctor,HttpStatus.FOUND);
	}
	
	// update doctor details by there id
	@PutMapping("/{id}")
	public ResponseEntity<Doctor>UpdateDoctor(@PathVariable("id")int id , @RequestBody DoctorDto doctorDto){
		
		Doctor doctor = doctorService.getDoctorById(id);
		if(doctor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		 doctor.setName(doctorDto.getName());
		 doctor.setEmail(doctorDto.getEmail());
		 doctor.setAddress(doctorDto.getAddress());
		 doctor.setPassword(doctorDto.getPassword());
		 doctor.setSpecialization(doctorDto.getSpecialization());
		 doctor.setClinic_name(doctorDto.getClinic_name());
		 doctor.setMobileno(doctorDto.getMobileno());
		 doctor.setToken(doctorDto.getToken());
		 doctor.setStatus(doctorDto.getStatus());
		 doctor.setCreated_at(doctorDto.getCreated_at());
		 doctor.setUpdated_at(doctorDto.getUpdated_at());
		 
		 Doctor UpdateDoctor = doctorService.saveDoctor(doctor);
		 return new ResponseEntity<>(UpdateDoctor,HttpStatus.OK);
	}
	
	
	// delete doctor details by there id
	@DeleteMapping("/{id}")
	public ResponseEntity<Doctor>DeleteDoctor(@PathVariable("id")int id){
		
		Doctor doctor = doctorService.getDoctorById(id);
		
		if(doctor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		doctorService.DeleteById(id);
		return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
	}

}
