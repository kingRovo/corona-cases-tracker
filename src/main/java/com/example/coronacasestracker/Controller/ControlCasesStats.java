package com.example.coronacasestracker.Controller;


import com.example.coronacasestracker.model.CoronaStats;
import com.example.coronacasestracker.services.CasesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ControlCasesStats {

    @Autowired
    CasesServices casesServices;

    @GetMapping("/")

    public String home(Model model) {
        List<CoronaStats> allStats = casesServices.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("coronastats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
}