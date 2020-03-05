package util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

public class PDF2HTMLExample {

	private static final String PDF = "E:\\html.pdf";
	private static final String HTML = "E:\\HospitalManagement\\HospitalFrontDesk\\src\\main\\resources\\html.html";

	public static void main(String[] args) {
		try {
			//generateHTMLFromPDF(PDF);
			createPdf(PDF);
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static void createPdf(String file) throws IOException, DocumentException {
	    // step 1
		
	    Document document = new Document();
	    // step 2
	    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
	    // step 3
	    document.open();
	    // step 4
	    // Styles
	    
    CSSResolver cssResolver = new StyleAttrCSSResolver();
//		
		// CssFile cssFile = XMLWorkerHelper .getCSS(new ByteArrayInputStream("table {margin: 200px;}".getBytes()));
    
//		  cssResolver.addCss(cssFile);
//		 
	   
	    
	    XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
	    fontProvider.register("font/NotoNaskhArabic-Regular.ttf");
	    CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
	    HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
	    htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

	    // Pipelines
	    PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
	    HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
	    CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

	    // XML Worker
	    XMLWorker worker = new XMLWorker(css, true);
	    XMLParser p = new XMLParser(worker);
	    p.parse(new FileInputStream(HTML), Charset.forName("UTF-8"));;
	    // step 5
	    document.close();
	}
}