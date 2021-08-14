package com.smrc.gateone.service;

import java.util.List;
import java.util.Map;

import com.smrc.gateone.dto.GateAndMetricDetailsDTO;
import com.smrc.gateone.model.Gate;
import com.smrc.gateone.model.PreviewsScreen;

public interface GateAndMetricService {
   public List<Map> getMetricDetails(Integer projectId);
   public List<Map> getGateDetails(Integer projectId);
   public PreviewsScreen getPreviewScreen(Integer projectId);
   public void saveGateAndMetricsDetails(GateAndMetricDetailsDTO gateAndMetricDetailsDTO);
}
