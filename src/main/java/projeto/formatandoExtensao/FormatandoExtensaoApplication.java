package projeto.formatandoExtensao;

import java.io.File;
import java.io.IOException;
import java.util.List;

import projeto.formatandoExtensao.imp.CsvGenerator;
import projeto.formatandoExtensao.imp.DocxGenerator;
import projeto.formatandoExtensao.imp.PdfGenerator;
import projeto.formatandoExtensao.model.Memorial;
import projeto.formatandoExtensao.service.MemorialService;

public class FormatandoExtensaoApplication {
	
	public static void main(String[] args) throws IOException {	
		
		MemorialService memorialSerice = new MemorialService();	
		PdfGenerator pdfGenerator = new PdfGenerator();
		DocxGenerator docxGenerator = new DocxGenerator(); 
		CsvGenerator csvGenerator = new CsvGenerator();
		
		File filePDF = pdfGenerator.fileChooserPDF();
			
		String documentPdf = pdfGenerator.construirDocumentoPDF();
		
		List<Memorial> memoriais = memorialSerice.extrairMemoriaisPdf(documentPdf);
		
		docxGenerator.gerarDocx(filePDF, memoriais);
		
		csvGenerator.gerarCsv(filePDF, memoriais);
	}

}
