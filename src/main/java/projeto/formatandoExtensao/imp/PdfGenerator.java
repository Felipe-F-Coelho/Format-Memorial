package projeto.formatandoExtensao.imp;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class PdfGenerator {
	
	private File filePDF;

	public File fileChooserPDF() throws IOException{
		JFileChooser jFileChooser = new JFileChooser();
		
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Apenas .pdf", "pdf");
		jFileChooser.setAcceptAllFileFilterUsed(false);
		jFileChooser.addChoosableFileFilter(filtro);
		
		int respostaFileChooser = jFileChooser.showOpenDialog(null); 
		
		if(respostaFileChooser == JFileChooser.APPROVE_OPTION) {
			filePDF = jFileChooser.getSelectedFile();
		}else {
			System.out.println("Arquivo Não Econtrado");
		}
		return filePDF;
	}
	
	public String construirDocumentoPDF() throws IOException {	
		try (PDDocument document = PDDocument.load(filePDF)) {
			return configurarPdf(document);
		}
	}
	
	private String configurarPdf(PDDocument document) throws IOException {
		PDFTextStripperByArea stripper = new PDFTextStripperByArea();
		stripper.setSortByPosition(true);

		PDFTextStripper tStripper = new PDFTextStripper();

		String pdfFileInText = tStripper.getText(document);
		
		return pdfFileInText;
	}

}
