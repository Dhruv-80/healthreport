package com.healthapp.health;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HealthReportController {

    @Autowired
    private HealthReportService service;

    @GetMapping("/reports")
    public String viewReports(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "") String searchTerm,
                              Model model) {
        Page<HealthReport> reportPage = service.searchReports(searchTerm, page, 10);
        model.addAttribute("reports", reportPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", reportPage.getTotalPages());
        model.addAttribute("searchTerm", searchTerm);
        return "reports";
    }

    @PostMapping("/searchReports")
    public String searchReports(@RequestParam(defaultValue = "0") int page,
                                @RequestParam String searchTerm,
                                Model model) {
        Page<HealthReport> reportPage = service.searchReports(searchTerm, page, 10);
        model.addAttribute("reports", reportPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", reportPage.getTotalPages());
        model.addAttribute("searchTerm", searchTerm);
        return "reports";
    }

    @GetMapping("/addReport")
    public String showAddReportForm(Model model) {
        model.addAttribute("healthReport", new HealthReport());
        return "addReport";
    }

    @PostMapping("/addReport")
    public String addReport(@ModelAttribute HealthReport healthReport) {
        service.saveReport(healthReport);
        return "redirect:/reports";
    }
}
