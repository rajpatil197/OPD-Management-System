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

import com.opd_management.Services.VisitReportService;
import com.opd_management.Services.VisitService;
import com.opd_management.dtos.VisitReportDto;
import com.opd_management.entities.Visit;
import com.opd_management.entities.VisitReport;

@RestController
@RequestMapping("/visitreports")
public class VisitReportController {

	@Autowired
	private VisitReportService visitReportService;
	
	@Autowired
	private VisitService visitService;
	
	@PostMapping("/")
	public ResponseEntity<VisitReport>SaveVisitReport(@RequestBody VisitReportDto visitReportDto){
		
		VisitReport visitReport = new VisitReport();
		visitReport.setFile_name(visitReportDto.getFile_name());
		visitReport.setFile_type(visitReportDto.getFile_type());
		visitReport.setFile_url(visitReportDto.getFile_url());
		visitReport.setCreated_at(visitReportDto.getCreated_at());
		
		Visit visit = visitService.GetVisitById(visitReportDto.getVisitid());
		visitReport.setVisitid(visit);
		
		VisitReport SaveVisitReport = visitReportService.saveVisitReport(visitReport);
		
		return new ResponseEntity<>(SaveVisitReport,HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<VisitReport>>GetAllVisitReport(){
		
		List<VisitReport> visitReport = visitReportService.GetAllVisitReport();
		if (visitReport == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(visitReport,HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VisitReport>GetvisitReportById(@PathVariable("id")int id){
		
		VisitReport visitReport = visitReportService.GetVisitReportById(id);
		
		if (visitReport == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(visitReport,HttpStatus.FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<VisitReport>UpdatevisitReport(@PathVariable("id")int id,@RequestBody VisitReportDto visitReportDto){
		
		VisitReport visitReport = visitReportService.GetVisitReportById(id);
		
		if (visitReport == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		visitReport.setFile_name(visitReportDto.getFile_name());
		visitReport.setFile_type(visitReportDto.getFile_type());
		visitReport.setFile_url(visitReportDto.getFile_url());
		visitReport.setCreated_at(visitReportDto.getCreated_at());
		
		Visit visit = visitService.GetVisitById(visitReportDto.getVisitid());
		visitReport.setVisitid(visit);
		
		VisitReport UpdateVisitReport = visitReportService.saveVisitReport(visitReport);
		
		return new ResponseEntity<>(UpdateVisitReport,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<VisitReport>DeletevisitReport(@PathVariable("id")int id){
		
		VisitReport visitReport = visitReportService.GetVisitReportById(id);
		
		if (visitReport == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		visitReportService.DeleteVisitReport(id);
		
		return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
	}
}
