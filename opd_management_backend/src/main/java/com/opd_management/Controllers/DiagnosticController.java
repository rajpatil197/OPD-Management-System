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

import com.opd_management.Services.DiagnosticService;
import com.opd_management.Services.DoctorService;
import com.opd_management.Services.VisitService;
import com.opd_management.dtos.DiagnosticDto;
import com.opd_management.entities.Diagnostic;
import com.opd_management.entities.Doctor;
import com.opd_management.entities.Visit;

@RestController
@RequestMapping("/diagnostics")
public class DiagnosticController {

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private VisitService visitService;
	
	@Autowired
	private DiagnosticService diagnosticsService;
	
	@PostMapping("/")
	public ResponseEntity<Diagnostic>SaveDiagnostic(@RequestBody DiagnosticDto diagnosticDto){
		
		Diagnostic diagnostic = new Diagnostic();
		
		diagnostic.setName(diagnosticDto.getName());
		diagnostic.setCreated_at(diagnosticDto.getCreated_at());

		Doctor doctor = doctorService.getDoctorById(diagnosticDto.getDoctorid());
		diagnostic.setDoctorid(doctor);
		
		Visit  visit = visitService.GetVisitById(diagnosticDto.getVisitid());
		diagnostic.setVisitid(visit);
		
		Diagnostic SaveDiagnostic = diagnosticsService.saveDiagnostic(diagnostic);
		
		return new ResponseEntity<>(SaveDiagnostic,HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Diagnostic>>GetAllDiagnostic(){
		
		List<Diagnostic> diagnostic = diagnosticsService.GetAllDiagnostic();
		
		if(diagnostic == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(diagnostic,HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Diagnostic>GetDiagnosticById(@PathVariable("id")int id){
		
		Diagnostic diagnostic = diagnosticsService.GetDiagnosticByID(id);

		if(diagnostic == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(diagnostic,HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Diagnostic>UpdateDiagnostic(@PathVariable("id")int id,@RequestBody DiagnosticDto diagnosticDto){
		
		Diagnostic diagnostic = diagnosticsService.GetDiagnosticByID(id);

		if(diagnostic == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
		diagnostic.setName(diagnosticDto.getName());
		diagnostic.setCreated_at(diagnosticDto.getCreated_at());

		Doctor doctor = doctorService.getDoctorById(diagnosticDto.getDoctorid());
		diagnostic.setDoctorid(doctor);
		
		Visit  visit = visitService.GetVisitById(diagnosticDto.getVisitid());
		diagnostic.setVisitid(visit);
		
		Diagnostic UpdateDiagnostic = diagnosticsService.saveDiagnostic(diagnostic);
		
		return new ResponseEntity<>(UpdateDiagnostic,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Diagnostic>DeleteDiagnostic(@PathVariable("id")int id){
		
		Diagnostic diagnostic = diagnosticsService.GetDiagnosticByID(id);

		if(diagnostic == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
		
		diagnosticsService.DeleteDiagnostic(id);
		return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
	}
}
