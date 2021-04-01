package de.servicezombie.thymeleaf;

import java.io.IOException;
import java.io.Writer;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class TemplateWriter {

	private final String templateName;
	private final TemplateEngine templateEngine;

	/**
	 * @param templateName name of template in classpath:templates/ directory,
	 *                     must match to templateEngine settings.
	 */
	public TemplateWriter(final String templateName, final TemplateMode mode, final String encoding) {
		this.templateName = templateName;
		templateEngine = createThymeleafEngine(mode, encoding);
	}

	/**
	 * 
	 * Write content of template name in <code>templateName.out.html</code>
	 * 
	 * @param data
	 * 
	 * @param out  writer to store parsed template in
	 */
	public void write(final ThymeleadContextFactory contextFactory, final Writer out)
			throws IOException {

		// put variables into template
		final Context context = contextFactory.createContext();
		templateEngine.process(templateName, context, out);
	}

	/**
	 * Look for html files in classpath:templates/
	 * 
	 * @return never null
	 */
	private TemplateEngine createThymeleafEngine(final TemplateMode mode, final String encoding) {
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		resolver.setPrefix("templates/");
		resolver.setCacheable(false);
		resolver.setCharacterEncoding(encoding);

		resolver.setTemplateMode(mode);
		switch (mode) {
		case TEXT:
			resolver.setSuffix(".txt");
			break;
		case HTML:
			resolver.setSuffix(".html");
			break;
		case XML:
			resolver.setSuffix(".xml");
			break;
		case RAW:
			resolver.setSuffix("");
			break;
		default:
			throw new IllegalArgumentException("unknown template mode " + mode);
		}

		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(resolver);
		return templateEngine;
	}

}
