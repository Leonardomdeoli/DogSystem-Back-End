package org.dogsystem.controller;

import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.dogsystem.utils.ServicePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@RestController
public class ReportService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@GetMapping(value = ServicePath.REPORT_PATH)
	@ResponseBody
	public ResponseEntity<Object> getReportAgendamentos(HttpServletResponse response,
			@RequestParam(value = "descrRel") String descrRel,
			@RequestParam(value = "data", required = false) Date dataInicial) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (dataInicial != null) {
			params.put("schedulingDate", dataInicial);
		}
		LOGGER.info("Gerando relatorios de " + descrRel);
		return buildReport(response, descrRel, "src/main/resources/reports/" + descrRel + ".jrxml", params);
	}

	private ResponseEntity<Object> buildReport(HttpServletResponse response, String name, String path,
			Map<String, Object> params) {

		try {
			// Gerando o jasper design
			LOGGER.info("Importando arquivo .jrxml");
			JasperDesign design = JRXmlLoader.load(path);

			// Compilando o relatório
			LOGGER.info("Compilando arquivo .jrxml para .Jasper");
			JasperReport report = JasperCompileManager.compileReport(design);

			DataSource ds = jdbcTemplate.getDataSource();

			if (ds == null) {
				throw new DataSourceLookupFailureException(
						"Não foi possível conectar ao banco de dados, Mensagem técnica [DataSource is null].");
			}

			LOGGER.info("Gerando jasperPrint");
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, ds.getConnection());
			response.setContentType("application/x-pdf");
			response.setHeader("Content-disposition", "inline; filename=" + name + ".pdf");

			LOGGER.info("Exportando em formato pdf");
			final OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception ex) {

			LOGGER.error(ex.getMessage());
			return new ResponseEntity<>("Erro ao gerar o relatório,  " + ex.getMessage(),
					HttpStatus.EXPECTATION_FAILED);
		}

	}
}
