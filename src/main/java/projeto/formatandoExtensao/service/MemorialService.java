package projeto.formatandoExtensao.service;

import java.util.ArrayList;
import java.util.List;

import projeto.formatandoExtensao.model.Memorial;
import projeto.formatandoExtensao.model.SegmentoVante;
import projeto.formatandoExtensao.model.Vertice;

public class MemorialService {
	
	public List<Memorial> extrairMemoriaisPdf(String pdfFileInText) {		
		String memoriaisPdf[] = pdfFileInText.split("Denominação:");
		List<Memorial> memoriais = new ArrayList<>();
		
		for(String memorialPdf : memoriaisPdf) {
			List<Vertice> listVertice = new ArrayList<>();
			List<SegmentoVante> listSegmentoVante = new ArrayList<>();
			
			filtrarTabela(memorialPdf,listVertice,listSegmentoVante);
			configurarMemorial(memoriais,listVertice,listSegmentoVante);
		}
		
		return memoriais;
	}

	private void filtrarTabela(String memorialPdf, List<Vertice> listVertice,
			List<SegmentoVante> listSegmentoVante) {
		String lines[] = memorialPdf.split("\\r?\\n");
		
		for (String line : lines) {
			if (line.contains("°") && line.contains("'") && line.contains("\"")) {
				separarPalavras(line,listVertice,listSegmentoVante);
			}
		}
		
	}

	private void separarPalavras(String line,List<Vertice> listVertice,
			List<SegmentoVante> listSegmentoVante) {
		String[] palavras = line.split(" ");
		criaObjetos(palavras,listVertice,listSegmentoVante);
	}

	private void criaObjetos(String[] palavras,List<Vertice> listVertice,
			List<SegmentoVante> listSegmentoVante) {

		Vertice vertice = new Vertice();
		SegmentoVante segmentoVante = new SegmentoVante();
		
		configurarParametrosObjetos(palavras, vertice, segmentoVante);	
		
		listVertice.add(vertice);
		listSegmentoVante.add(segmentoVante);
	}

	private void configurarParametrosObjetos(String[] palavras, Vertice vertice, SegmentoVante segmentoVante) {
		for (int i = 0; i < palavras.length; i++) {

			switch (i) {
			case 0:
				vertice.setCodigo(palavras[i]);
				break;
			case 1:
				vertice.setLongitude(palavras[i]);
				break;
			case 2:
				vertice.setLatitude(palavras[i]);
				break;
			case 3:
				vertice.setAltitude(palavras[i]);
				break;
			case 4:
				segmentoVante.setCodigo(palavras[i]);
				break;
			case 5:
				segmentoVante.setAzimute(palavras[i]);
				break;
			case 6:
				segmentoVante.setDistancia(palavras[i]);
				break;
			case 7:
				segmentoVante.setConfrontacoes(palavras[i]);
				break;
			default:
				segmentoVante.setConfrontacoes(segmentoVante.getConfrontacoes() + " " + palavras[i]);
			}
		}
	}

	private void configurarMemorial(List<Memorial> memoriais,
			List<Vertice> listVertice, List<SegmentoVante> listSegmentoVante) {
		if(!listSegmentoVante.isEmpty() && !listVertice.isEmpty()) {
			memoriais.add(new Memorial(listVertice,listSegmentoVante));
		}
	}
	
}
