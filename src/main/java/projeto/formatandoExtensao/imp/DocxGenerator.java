package projeto.formatandoExtensao.imp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import projeto.formatandoExtensao.model.Memorial;
import projeto.formatandoExtensao.model.SegmentoVante;
import projeto.formatandoExtensao.model.Vertice;

public class DocxGenerator {

	public void gerarDocx(File filePDF, List<Memorial> memoriais) throws IOException {

		for (int i = 0; i < memoriais.size(); i++) {
			try (XWPFDocument document = new XWPFDocument()) {

				FileOutputStream fileOut = criarFileOutPut(filePDF, memoriais.get(i), document, i);

				gerarParagrafoInicial(memoriais.get(i), document);

				gerarParagrafoFinal(document);

				document.write(fileOut);
			}
		}
	}

	private FileOutputStream criarFileOutPut(File filePDF, Memorial memorial, XWPFDocument document, int contador)
			throws FileNotFoundException, IOException {

		FileOutputStream fileOut = new FileOutputStream(criarFileDOCX(filePDF, contador + 1));

		return fileOut;
	}

	private File criarFileDOCX(File filePDF, int contador) {
		String urlDOCX = filePDF.getPath().substring(0, filePDF.getPath().length() - 4);
		urlDOCX += "_0" + contador + ".docx";

		return new File(urlDOCX);
	}

	private void gerarParagrafoInicial(Memorial memorial, XWPFDocument document) {
		String confrontantes = "";

		XWPFParagraph paragrafoInicial = configParagrafo(document);

		for (int i = 0; i < memorial.getListVertice().size(); i++) {

			Vertice vertice = memorial.getListVertice().get(i);
			SegmentoVante segmentoVante = memorial.getListSegmentoVante().get(i);

			if (i == 0) {
				configRunPaRunFrase(paragrafoInicial).setText("Inicia-se a descrição deste perímetro no vértice ");
			}
			configRunPaRunVariaveis(paragrafoInicial).setText(vertice.getCodigo());
			configRunPaRunFrase(paragrafoInicial).setText(" de coordenadas de latitude ");
			configRunPaRunVariaveis(paragrafoInicial).setText(vertice.getLatitude());
			configRunPaRunFrase(paragrafoInicial).setText(" e de longitude ");
			configRunPaRunVariaveis(paragrafoInicial).setText(vertice.getLongitude());
			configRunPaRunFrase(paragrafoInicial).setText(";");
			if (!segmentoVante.getConfrontacoes().equals(confrontantes)) {
				configRunPaRunFrase(paragrafoInicial).setText(" deste segue confrontando com ");
				configRunPaRunVariaveis(paragrafoInicial).setText(segmentoVante.getConfrontacoes());
				configRunPaRunFrase(paragrafoInicial).setText(",");

				confrontantes = segmentoVante.getConfrontacoes();
			}
			configRunPaRunFrase(paragrafoInicial).setText(" com o azimute ");
			configRunPaRunVariaveis(paragrafoInicial).setText(segmentoVante.getAzimute());
			configRunPaRunFrase(paragrafoInicial).setText(" e distância de ");
			configRunPaRunVariaveis(paragrafoInicial).setText(segmentoVante.getDistancia());
			if (i == memorial.getListVertice().size() - 1) {
				configRunPaRunFrase(paragrafoInicial).setText(" até o vértice inicial da descrição deste perímetro.");
			} else {
				configRunPaRunFrase(paragrafoInicial).setText(" até o vértice ");
			}
		}
	}

	private XWPFParagraph configParagrafo(XWPFDocument document) {
		XWPFParagraph paragrafo = document.createParagraph();
		paragrafo.setAlignment(ParagraphAlignment.BOTH);
		return paragrafo;
	}

	private XWPFRun configRunPaRunFrase(XWPFParagraph paragrafoInicial) {
		XWPFRun runPaRunFrase = paragrafoInicial.createRun();
		runPaRunFrase.setBold(false);
		return runPaRunFrase;
	}

	private XWPFRun configRunPaRunVariaveis(XWPFParagraph paragrafoInicial) {
		XWPFRun runPaRunVariaveis = paragrafoInicial.createRun();
		runPaRunVariaveis.setBold(true);
		return runPaRunVariaveis;
	}

	private void gerarParagrafoFinal(XWPFDocument document) {
		String textoFinal = "Todas as coordenadas aqui descritas estão georreferenciadas ao Sistema Geodésico Brasileiro,"
				+ " tendo como DATUM o SIRGAS 2000. Todos os azimutes e distâncias, área e perímetro foram calculadas no"
				+ " sistema local de coordenadas com origem do plano definido pela média das coordenadas (SGL – Sistema Geodésico Local). ";

		XWPFParagraph paragrafoFinal = configParagrafo(document);
		XWPFRun runFinal = configRunPaRunFrase(paragrafoFinal);

		runFinal.addBreak();
		runFinal.setText(textoFinal);
	}

}
