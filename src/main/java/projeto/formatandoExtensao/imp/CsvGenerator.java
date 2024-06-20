package projeto.formatandoExtensao.imp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import projeto.formatandoExtensao.model.Memorial;
import projeto.formatandoExtensao.model.SegmentoVante;
import projeto.formatandoExtensao.model.Vertice;

public class CsvGenerator {

	public void gerarCsv(File filePDF, List<Memorial> memoriais) throws IOException {
		for (int i = 0; i < memoriais.size(); i++) {
			File fileCSV = criarFileCSV(filePDF, i + 1);
			
			FileWriter fileWriterCSV = new FileWriter(fileCSV, StandardCharsets.ISO_8859_1);
			
			fileWriterCSV.write("Código Vertice;Longitude;Latitude;Altitude;Código Segmento;Azimute;Dist.(m);Confrontações\n");
				
			escreverCSV(memoriais.get(i) , fileWriterCSV);
			
			fileWriterCSV.flush();
			
			fileWriterCSV.close();
		}
	}

	private static File criarFileCSV(File filePDF, int contador) {
		String urlCSV = filePDF.getPath().substring(0, filePDF.getPath().length() - 4);
		urlCSV += "_0" + contador + ".csv";

		return new File(urlCSV);
	}	
	
	private void escreverCSV(Memorial memorial, FileWriter fileWriterCSV) throws IOException {
		for(int i=0; i < memorial.getListVertice().size(); i++) {
			
			Vertice vertice = memorial.getListVertice().get(i);
			SegmentoVante segVante = memorial.getListSegmentoVante().get(i);
						
			fileWriterCSV.write(vertice.getCodigo() + ";" + vertice.getLongitude() + ";" + vertice.getLatitude() + ";" + vertice.getAltitude()
			 + ";" + segVante.getCodigo() + ";" + segVante.getAzimute() + ";" + segVante.getDistancia() + ";" + segVante.getConfrontacoes() + "\n");
		}
	}
	
}
