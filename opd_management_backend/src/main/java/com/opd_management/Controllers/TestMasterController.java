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
import com.opd_management.Services.TestMasterService;
import com.opd_management.dtos.TestMasterDto;
import com.opd_management.entities.Doctor;
import com.opd_management.entities.TestMaster;

@RestController
@RequestMapping("/testmasters")
public class TestMasterController {

	@Autowired
	private TestMasterService testMasterService;
	
	@Autowired
	private DoctorService doctorService;
	
	@PostMapping("/")
	public ResponseEntity<TestMaster>SaveTestMaster(@RequestBody TestMasterDto testMasterDto){
		
		TestMaster testMaster = new TestMaster();
		
		testMaster.setTest_name(testMasterDto.getTest_name());
		testMaster.setNormal_range(testMasterDto.getNormal_range());
		testMaster.setUnit(testMasterDto.getUnit());
		
		Doctor doctor = doctorService.getDoctorById(testMasterDto.getDoctorid());
		testMaster.setDoctorid(doctor);
		
		TestMaster SaveTestMaster = testMasterService.saveTestMaster(testMaster);
		
		return new ResponseEntity<>(SaveTestMaster,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<TestMaster>>GetAllTestMaster(){
		
		List<TestMaster> testMaster = testMasterService.GetAllTestMaster();
		
		if(testMaster == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(testMaster,HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TestMaster>GetTestMasterById(@PathVariable("id")int id){
		
		TestMaster testMaster = testMasterService.GetTestMasterById(id);
		if(testMaster == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(testMaster,HttpStatus.FOUND);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TestMaster>UpdateTestMaster(@PathVariable("id")int id,@RequestBody TestMasterDto testMasterDto){
		
		TestMaster testMaster = testMasterService.GetTestMasterById(id);
		if(testMaster == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		testMaster.setTest_name(testMasterDto.getTest_name());
		testMaster.setNormal_range(testMasterDto.getNormal_range());
		testMaster.setUnit(testMasterDto.getUnit());
		
		Doctor doctor = doctorService.getDoctorById(testMasterDto.getDoctorid());
		testMaster.setDoctorid(doctor);
		
		TestMaster UpdateTestMaster = testMasterService.saveTestMaster(testMaster);
		
		return new ResponseEntity<>(UpdateTestMaster,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<TestMaster>DeleteTestMaster(@PathVariable("id")int id){
		
		TestMaster testMaster = testMasterService.GetTestMasterById(id);
		if(testMaster == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		testMasterService.DeleteTestMaster(id);
		return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
		
	}
}
